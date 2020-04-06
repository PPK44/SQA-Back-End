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

# Test counter
testCnt=1

# Formatting
green="\e[0;49;32m"
reset="\e[0m"

# Remove old transaction files
rm $transDir/*
rm $dir/out/daily_transaction_file.txt

# For frontend
touch $dir/in/current_user_accounts_file.txt
touch $dir/in/available_items_file.txt

# Copy in files into out files
cat "${dir}/in/current_user_accounts_file.txt" >"${dir}/out/current_user_accounts_file.txt"
cat "${dir}/in/available_items_file.txt" >"${dir}/out/available_items_file.txt"


for f in $TESTS
do
	touch $transDir/daily_transaction_file_$testCnt.txt
	touch $dir/termout/termout_$testCnt.txt
	echo "Running test ${testCnt}"
	../SQA-Front-End/auction-system.exe "${dir}/in/current_user_accounts_file.txt" "${dir}/in/available_items_file.txt" "${transDir}/daily_transaction_file_${testCnt}.txt" <$f >"${dir}/termout/termout_${testCnt}.txt"

	((testCnt++))
done

# Merge transaction files
cat $transDir/* >"${dir}/out/daily_transaction_file.txt"

echo -e "Running backend"

touch ${dir}/termout/termout_backend.txt

# Run backend
cd $dir/out
java -jar ../../../out/artifacts/SQA_Back_End_jar/SQA-Back-End.jar > "../termout/termout_backend.txt"
echo -e "${green}Output files created.${reset}"
echo "Output files are found in the out and termout folders."