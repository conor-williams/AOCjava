export JAVAC=/cygdrive/f/jdk-25/bin/javac.exe
export JAVA=/cygdrive/f/jdk-25/bin/java.exe

if [ ! -f $JAVAC ]; then
	echo "set JAVAC in FIRST_JAVA.sh";
	exit 0;
else 
	echo "JAVAC ok"
fi

if [ ! -f $JAVA ]; then
	echo "set JAVA in FIRST_JAVA.sh";
	exit 0
else 
	echo "JAVA ok"
fi

	
### a guide to running...
echo "tar of input-2015-2030.tar.gz takes ~10seconds"
tar -xvzf input-2015-2030.tar.gz

echo "sh specialCopy.sh takes ~1minute"
sh specialCopy.sh

echo "sh specialCompile.sh takes ~11minutes"

sh specialCompile.sh
#mv 2019/day25/*.exe 2019/day25/2019.day25.INTERACTIVE.exe

sh runALL.sh

