set -xv
year=2015
day=1

while [ $year != 2025 ] ; do
	while [ $day != 26 ] ; do
		if [ -f ALL.$year/year${year}_day${day}.java ]; then
			cp ALL.$year/year${year}_day${day}.java $year/day$day/
		fi
		if [ -f ALL.$year/year${year}_day${day}_2.java ]; then
			cp ALL.$year/year${year}_day${day}_2.java $year/day$day/
		fi
#		if [ $day != 25 ] ; then
#			cp ALL.$year/year${year}_day${day}_2.java $year/day$day/
#		fi
		day=$(($day+1))
        done
	day=1
        year=$(($year + 1))
done
#cp ALL.2022/year2022.day25.snafu.java 2022/day25/day25snafu.java
