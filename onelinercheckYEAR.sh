year=$1
day=1;
pa=$PWD
while [ $day != 26 ]; do
	if [ -d $pa ]; then
		cd $pa
		if [ -d $year/day$day/ ]; then
			cd $year/day$day/
		fi
	fi

	if [ -f year${year}_day${day}.class ] ; then
		
		$JAVA -Xmx3g -Xss1g year${year}_day$day $year.day$day.i1.txt
	fi 
	if [ -f year${year}_day${day}_2.class ] ; then
		$JAVA -Xmx3g -Xss1g year${year}_day${day}_2 $year.day$day.i1.txt
	fi 
	day=$(($day+1));
done
#-Xss1g
