year=2015
day=$1
pa=$PWD
while [ $year != 2025 ]; do
	cd $year/day$day
	/java -Xss1g -Xmx3g year${year}_day${day}.java *i1.txt
	/java -Xss1g -Xmx3g year${year}_day${day}_2.java *i1.txt
	year=$((year+1))
	cd $pa
done
