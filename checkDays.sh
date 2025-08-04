year=2015
if [ '@'$1 = '@' ]; then
	echo "give a day"
	exit
fi
day=$1
pa=$PWD
while [ $year != 2025 ]; do
	cd $year/day$day
	/java -Xss1g -Xmx3g year${year}_day${day}.java *i1.txt
	if [ @$day != '@25' ]; then
		/java -Xss1g -Xmx3g year${year}_day${day}_2.java *i1.txt
	fi
	year=$((year+1))
	cd $pa
done
