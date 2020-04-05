for day in {1..5}
do
	bash daily.sh "day${day}" >/dev/null
	if [ $day -ne 5 ]
	then
		nextDay=$(($day+1))
		cp --preserve "day${day}/out/available_items_file.txt" "day${nextDay}/in/available_items_file.txt"
		cp --preserve "day${day}/out/current_user_accounts_file.txt" "day${nextDay}/in/current_user_accounts_file.txt"
		#cat "day${day}/out/available_items_file.txt" >"day${nextDay}/in/available_items_file.txt"
		#cat "day${day}/out/current_user_accounts_file.txt" >"day${nextDay}/in/current_user_accounts_file.txt"
	fi
done

