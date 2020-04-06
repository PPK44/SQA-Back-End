# Formatting
green="\e[0;49;32m"
reset="\e[0m"

for day in {1..5}
do
	echo "Testing day ${day}"
	bash daily.sh "day${day}" >/dev/null
	if [ $day -ne 5 ]
	then
		nextDay=$(($day+1))
		cp --preserve "day${day}/out/available_items_file.txt" "day${nextDay}/in/available_items_file.txt"
		cp --preserve "day${day}/out/current_user_accounts_file.txt" "day${nextDay}/in/current_user_accounts_file.txt"
	fi
done

cp --preserve "day${nextDay}/in/available_items_file.txt" "weeklyOut/available_items_file.txt"
cp --preserve "day${nextDay}/in/current_user_accounts_file.txt" "weeklyOut/current_user_accounts_file.txt"

echo -e "${green}Output files created.${reset}"
echo "Weekly logs found in weeklyOut folder."
echo "Daily logs found in each day's folder under out or termout."