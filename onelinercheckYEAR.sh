year=$1
day=1;
pa=$PWD
while [ $day != 26 ]; do
	cd $pa
	cd $year/day$day/
	if [ -f year${year}_day${day}.class ] ; then
		
		$JAVA -Xmx3g year${year}_day$day $year.day$day.i1.txt
	fi 
	if [ -f year${year}_day${day}_2.class ] ; then
		$JAVA -Xmx3g year${year}_day${day}_2 $year.day$day.i1.txt
	fi 
	day=$(($day+1));
done
