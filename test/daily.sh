if [ -z $1 ]
then
	echo "Missing folder name. Try \"bash daily.sh day1\"."
	exit 1
fi

# Get to current dir so all dir-based functions work
scriptDir=$(dirname $0)
cd "$scriptDir"

# Set directories of subfolders
dir=$1
transDir=$dir/trans
TESTS=$dir/tests/*
BUFOUT=$dir/out.txt

# Test counter
testCnt=1

# Formatting
green="\e[0;49;32m"
#red="\e[0;49;31m"
reset="\e[0m"

# Remove old transaction files
rm $transDir/*
rm daily_transaction_file.txt

# Create blank files if they don't exist
# For backend
touch current_user_accounts_file.txt
touch daily_transaction_file.txt
touch items.if.txt

# For frontend
touch $dir/current_user_accounts_file.txt
touch $dir/available_items_file.txt
# Temp buffer
touch $BUFOUT

# Copy root files into day test files
cat current_user_accounts_file.txt >$dir/current_user_accounts_file.txt
cat items.if.txt >$dir/available_items_file.txt


for f in $TESTS
do
	touch $transDir/daily_transaction_file_$testCnt.txt
	echo "Run ${testCnt}: ${f}"
	../SQA-Front-End/auction-system.exe "${dir}/current_user_accounts_file.txt" "${dir}/available_items_file.txt" "${transDir}/daily_transaction_file_${testCnt}.txt" <$f >$BUFOUT

	((testCnt++))
done

rm $BUFOUT

# Merge transaction files
cat $transDir/* >$dir/merged_transaction_file.txt
cat $dir/merged_transaction_file.txt >daily_transaction_file.txt

echo -e "\n${green}Created merged transaction file.${reset}\n"

# Run backend
java -jar ../out/artifacts/SQA_Back_End_jar/SQA-Back-End.jar