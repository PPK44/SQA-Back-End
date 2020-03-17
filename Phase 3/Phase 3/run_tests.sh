#!/bin/bash
# Script to read in test inputs and feed them into the program. 
# It also compares the expected output to the program's actual output.
# It tracks the tests that fail with a name and time stamp in test_results.txt.
# For every test that fails, it writes the difference between the expected output and actual output in diff_log.txt.
# It assumes the script is in the same directory as two other directories:
#	tests/: Contains the test inputs and expected outputs
#	program/: Contains the program's C++ files which have been precompiled into a file named auction
# It assumes the program has been compiled using compile_program.sh in the same directory.

tests="tests/*"
daily_transaction_file="daily_transaction_file.of.txt"
datetime=`date +"%Y-%m-%d %T"`
test_results_file="test_results.txt"
diff_log_file="diff_log.txt"

process_input_file() {
	# $1 is the input file name given as the 1st argument. 
	# These variables extract parts of the input file path to create the output file.
	# They are local so they do not affect outside their scope.
	local transaction=`echo "$1" | cut -d '/' -f2` 
	local test_name=`echo "$1" | cut -d '/' -f3`
	local output_dir="outputs/$transaction/$test_name"
	local output_file="$output_dir/$test_name.ou.txt"
	local expected_transactions="tests/$transaction/$test_name/$test_name.of.txt"

	# Make sure the test output file exists
	mkdir -p "$output_dir"
	touch "$output_file"
    touch "$output_dir/$test_name.of.txt"

	# For each line in the input file, feed it as user inputs into the program,
	# then output the results in an appropriately named output file.
	(while IFS= read -r line; do
     echo "$line"
	 done < "$1") | program/auction program/users.if.txt program/items.if.txt > "$output_file"

	local expected_output=`echo "$1" | cut -d '.' -f1`
	expected_output+=".ou.txt"

	# Write a file documenting if the file is ok or not.
	# If either the console output or daily transaction file do not match the expected output,
	# write to the difference log file. 
	local console_diff=$(diff "$expected_output" "$output_file")
	local transaction_diff=$(diff "$expected_transactions" "$daily_transaction_file")
    if [ "$console_diff" == "" ] && [ "$transaction_diff" == "" ];
    then
        echo "($datetime) TEST $test_name: GOOD" >> "$test_results_file"
        echo "TEST $test_name: GOOD"
    else
        # If either the program output or the transaction file does not match, document the issue
        echo "($datetime) TEST $test_name: BAD" >> "$test_results_file"
        echo "TEST $test_name: BAD"
        echo "TEST $test_name:" >> "$diff_log_file"
        echo "Console output:" >> "$diff_log_file"
        echo "$console_diff" >> "$diff_log_file"
        echo "Transaction file:" >> "$diff_log_file"
        echo "$transaction_diff" >> "$diff_log_file"
        echo "" >> "$diff_log_file"
    fi
    (cat "$daily_transaction_file") > "$output_dir/$test_name.of.txt"
}

# Add a banner to the test results to label the date and time of testing
echo "##################################################" >> "$test_results_file"
echo "TEST RESULTS ($datetime)" >> "$test_results_file"
echo "##################################################" >> "$test_results_file"
echo "" >> "$test_results_file"

# Add a banner to the difference log to label the date and time of testing
echo "##################################################" >> "$diff_log_file"
echo "DIFFERENCE LOG ($datetime)" >> "$diff_log_file"
echo "##################################################" >> "$diff_log_file"
echo "" >> "$diff_log_file"

echo "Test run ($datetime)"
for transaction in $tests
do
    # If its a directory, move into it
    if [ -d "$transaction" ]
    then
    	transaction_name=`echo "$transaction" | cut -d '/' -f2`
    	echo "Testing $transaction_name" # Print what the script is currently testing
    	echo "$transaction_name" >> "$test_results_file" # Label the transaction the following tests are for
    	echo "" >> "$test_results_file" # Add a blank line to the test results, for cleanliness
    	echo "$transaction_name" >> "$diff_log_file" # Label the transaction the following differences are for
    	echo "" >> "$diff_log_file" # Add a blank line to the difference log, for cleanliness
        test_cases="$transaction/*"
        for case in $test_cases
        do
        	# If its a directory, move into it
   		 	if [ -d "$transaction" ]
   		 	then
				files="$case/*"
				for file in $files
				do
					# If the file extension matches the test input format, 
					# call a function to process the file
					extension=`echo "$file" | cut -d '.' -f2`
					if [ "$extension" == "in" ]
					then
						process_input_file "$file" 
					fi
				done
   		 	fi
        done
        echo "" >> "$test_results_file" # Add a blank line to the test results, for cleanliness
        echo "" >> "$diff_log_file" # Add a blank line to the difference log, for cleanliness
    fi
done

echo "" >> "$test_results_file" # Add a blank line to the test results, for cleanliness
echo "Testing finished!"