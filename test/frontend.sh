
# This script runs the frontend and compares terminal output
# to already created output cases in the Test folder.
# It uses input redirection to redirect already created input cases.

# Each command's Test dir folder (ex. Login, Logout)
DIRS=../test/*
for f in $DIRS
do
	# Every input case file for the command f
	INP=$f/Inputs/*
	i=0
	for g in $INP
	do
		# Test Case number
		((i++))
		printf "${f:8} Test $i - ${TEST::-4} - "

		# Finding appropriate sample and generated output file
		TEST=${g##*/}
		TERMOUT="${f}/Terminal_Outputs/${TEST::-4}.termout"
		TESTOUT="${f}/Test_Outputs/${TEST::-4}.termout"

		# Generating output file
		./Debug/frontend.exe <$g >$TESTOUT

		# Measuring difference between sample and generated output
		difCnt=$(diff $TERMOUT $TESTOUT)

		# If there is a difference, there is an error
		if [ ${#difCnt} -eq 0 ]; then
			echo -e "\e[0;49;32mPASS \e[0m"
		else
			echo -e "\e[0;49;31mFAIL \e[0m"
		fi
	done
done