
set -xv
#JAVAC=/cygdrive/f/jdk-25/bin/javac.exe
#JAVA=/cygdrive/f/jdk-25/bin/java.exe
day=1
year=2015
curdir=$PWD
echo $curdir
while [ $year != 2025 ];  do
	while [ $day != 26 ]; do
		if [ -d $curdir/$year/day$day ]; then
			cd $curdir/$year/day$day
			if [ ! -f year${year}_day$day.class ] ; then
				$JAVAC year${year}_day$day.java 
			fi
			if [ $day != 25 ]; then
				if [ ! -f year${year}_day${day}_2.class ]; then
					$JAVAC year${year}_day${day}_2.java
				fi
			fi
		fi
		day=$(( day + 1))
	done
	day=1
	year=$((year + 1))
done
