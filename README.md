<pre>
	Install:
o green button Code -> Download Zip
o unzip AOCjava-main.zip 
o unzip is a cygwin package
o cd AOCjava-main
o set the <b>JAVA paths</b> at the top of FIRST_JAVA.sh
o sh FIRST_JAVA.sh
o these work with my inputfiles -- java code may need to be adjusted slightly for your input
o note: a lot of java coding ahead...
o websearch: Advent of code year 20xy day ab solutions 
   (for many solutions / many days / many years (reddit))
o have found around 20 bugs in my C/C++ AOC due to coding in Java (a lot are simple like array sizes due to input file differences) - will fix soon	
o when i get to the end of day25, I will code the ones I left out - more meaty/complicated ones are left, and I want another go at them as time consuming...
o my 2 cents 
	-- Java is saving me tons of time with its awesome runtime checks (plus an exact line number of an issue)
	-- execution time is on a par with c++ (for most things and of course faster in some areas and slower in some)
	-- if i was to code the next AOC,25, i will probably write it in Java and then port to C++
	-- Java has issues with quickly copying 2D/3D arrays/grids etc... even zeroeing 2D/3D grids is
	     slow..(memset memcpy in C++ is quick) . is vector access a little slower in Java?
</pre>
<h2>Java solutions to AOC</h2>
<address><b>first updated 06052025@19:32</b></address><br>
<address><b>conor.williams@gmail.com</b></address><br>

<h3>New todos to finish</h3>
<pre>
year: 2024
<s> 2024 day 1 part 1  |  2024 day 1 part 2
 2024 day 2 part 1  |  2024 day 2 part 2
 2024 day 3 part 1  |  2024 day 3 part 2
 2024 day 4 part 1  |  2024 day 4 part 2
 2024 day 5 part 1  |  2024 day 5 part 2
 2024 day 6 part 1  |  2024 day 6 part 2
 2024 day 7 part 1  |  2024 day 7 part 2
 2024 day 8 part 1  |  2024 day 8 part 2
 2024 day 9  part 1  |  2024 day 9 part 2
 2024 day 10 part 1  |  2024 day 10 part 2
 2024 day 11 part 1  |  2024 day 11 part 2
 2024 day 12 part 1  |  2024 day 12 part 2
 2024 day 13 part 1  |  2024 day 13 part 2
 2024 day 14 part 1  |  2024 day 14 part 2
 2024 day 15 part 1  |  2024 day 15 part 2
 2024 day 16 part 1  |  2024 day 16 part 2
 2024 day 17 part 1</s>  |  2024 day 17 part 2
 <s>2024 day 18 part 1  |  2024 day 18 part 2
 2024 day 19 part 1  |  2024 day 19 part 2
 2024 day 20 part 1  |  2024 day 20 part 2
 2024 day 21 part 1  |  2024 day 21 part 2</s>
 2024 day 22 part 1  |  2024 day 22 part 2
 2024 day 23 part 1  |  2024 day 23 part 2
 2024 day 24 part 1  |  2024 day 24 part 2
 2024 day 25 part 1  
year: 2023
<s> 2023 day 1 part 1  |  2023 day 1 part 2
 2023 day 2 part 1  |  2023 day 2 part 2
 2023 day 3 part 1  |  2023 day 3 part 2
 2023 day 4 part 1  |  2023 day 4 part 2
 2023 day 5 part 1  |  2023 day 5 part 2
 2023 day 6 part 1  |  2023 day 6 part 2
 2023 day 7 part 1  |  2023 day 7 part 2
 2023 day 8 part 1  |  2023 day 8 part 2
 2023 day 9  part 1  |  2023 day 9 part 2
 2023 day 10 part 1  |  2023 day 10 part 2
 2023 day 11 part 1  |  2023 day 11 part 2</s>
 2023 day 12 part 1  |  2023 day 12 part 2
 <s>2023 day 13 part 1  |  2023 day 13 part 2
 2023 day 14 part 1  |  2023 day 14 part 2
 2023 day 15 part 1  |  2023 day 15 part 2
 2023 day 16 part 1  |  2023 day 16 part 2
 2023 day 17 part 1  |  2023 day 17 part 2
 2023 day 18 part 1  |  2023 day 18 part 2
 2023 day 19 part 1 </s> |  2023 day 19 part 2
 2023 day 20 part 1  |  2023 day 20 part 2
 <s>2023 day 21 part 1 </s> |  2023 day 21 part 2
 2023 day 22 part 1  |  2023 day 22 part 2
 2023 day 23 part 1  |  2023 day 23 part 2
 2023 day 24 part 1  |  2023 day 24 part 2
 2023 day 25 part 1  
year: 2022
<s> 2022 day 1 part 1  |  2022 day 1 part 2
 2022 day 2 part 1  |  2022 day 2 part 2
 2022 day 3 part 1  |  2022 day 3 part 2
 2022 day 4 part 1  |  2022 day 4 part 2
 2022 day 5 part 1  |  2022 day 5 part 2
 2022 day 6 part 1  |  2022 day 6 part 2
 2022 day 7 part 1  |  2022 day 7 part 2
 2022 day 8 part 1  |  2022 day 8 part 2
 2022 day 9  part 1  |  2022 day 9 part 2
 2022 day 10 part 1  |  2022 day 10 part 2
 2022 day 11 part 1  |  2022 day 11 part 2
 2022 day 12 part 1  |  2022 day 12 part 2</s>
 2022 day 13 part 1  |  2022 day 13 part 2
 2022 day 14 part 1  |  2022 day 14 part 2
 <s>2022 day 15 part 1 </s> |  2022 day 15 part 2
 2022 day 16 part 1  |  2022 day 16 part 2
 <s>2022 day 17 part 1  |  2022 day 17 part 2
 2022 day 18 part 1  |  2022 day 18 part 2
 2022 day 19 part 1</s>  |  2022 day 19 part 2
 <s>2022 day 20 part 1  |  2022 day 20 part 2
 2022 day 21 part 1  |  2022 day 21 part 2
 2022 day 22 part 1 </s> |  2022 day 22 part 2
 2022 day 23 part 1  |  2022 day 23 part 2
 2022 day 24 part 1  |  2022 day 24 part 2
<s> 2022 day 25 part 1  </s>
year: 2021
<s> 2021 day 1 part 1  |  2021 day 1 part 2
 2021 day 2 part 1  |  2021 day 2 part 2
 2021 day 3 part 1  |  2021 day 3 part 2
 2021 day 4 part 1  |  2021 day 4 part 2
 2021 day 5 part 1  |  2021 day 5 part 2
 2021 day 6 part 1  |  2021 day 6 part 2
 2021 day 7 part 1  |  2021 day 7 part 2
 2021 day 8 part 1  |  2021 day 8 part 2
 2021 day 9  part 1  |  2021 day 9 part 2
 2021 day 10 part 1  |  2021 day 10 part 2
 2021 day 11 part 1  |  2021 day 11 part 2
 2021 day 12 part 1  |  2021 day 12 part 2
 2021 day 13 part 1  |  2021 day 13 part 2
 2021 day 14 part 1  |  2021 day 14 part 2
 2021 day 15 part 1  |  2021 day 15 part 2
 2021 day 16 part 1  |  2021 day 16 part 2
 2021 day 17 part 1  |  2021 day 17 part 2
 2021 day 18 part 1  |  2021 day 18 part 2
 2021 day 19 part 1  |  2021 day 19 part 2
 2021 day 20 part 1  |  2021 day 20 part 2
 2021 day 21 part 1  |  2021 day 21 part 2
 2021 day 22 part 1  |  2021 day 22 part 2</s>
 2021 day 23 part 1  |  2021 day 23 part 2
 2021 day 24 part 1  |  2021 day 24 part 2
 2021 day 25 part 1  
year: 2020
<s> 2020 day 1 part 1  |  2020 day 1 part 2
 2020 day 2 part 1  |  2020 day 2 part 2
 2020 day 3 part 1  |  2020 day 3 part 2
 2020 day 4 part 1  |  2020 day 4 part 2
 2020 day 5 part 1  |  2020 day 5 part 2
 2020 day 6 part 1  |  2020 day 6 part 2
 2020 day 7 part 1  |  2020 day 7 part 2
 2020 day 8 part 1  |  2020 day 8 part 2
 2020 day 9  part 1  |  2020 day 9 part 2
 2020 day 10 part 1  |  2020 day 10 part 2
 2020 day 11 part 1  |  2020 day 11 part 2
 2020 day 12 part 1  |  2020 day 12 part 2
 2020 day 13 part 1  |  2020 day 13 part 2
 2020 day 14 part 1  |  2020 day 14 part 2
 2020 day 15 part 1  |  2020 day 15 part 2
 2020 day 16 part 1  |  2020 day 16 part 2
 2020 day 17 part 1  |  2020 day 17 part 2
 2020 day 18 part 1  |  2020 day 18 part 2
 2020 day 19 part 1  |  2020 day 19 part 2
 2020 day 20 part 1 </s> |  2020 day 20 part 2
 <s>2020 day 21 part 1  |  2020 day 21 part 2
 2020 day 22 part 1  |  2020 day 22 part 2</s>
 2020 day 23 part 1  |  2020 day 23 part 2
 2020 day 24 part 1  |  2020 day 24 part 2
 2020 day 25 part 1  
year: 2019
<s> 2019 day 1 part 1  |  2019 day 1 part 2
 2019 day 2 part 1  |  2019 day 2 part 2
 2019 day 3 part 1  |  2019 day 3 part 2
 2019 day 4 part 1  |  2019 day 4 part 2
 2019 day 5 part 1  |  2019 day 5 part 2
 2019 day 6 part 1  |  2019 day 6 part 2
 2019 day 7 part 1  |  2019 day 7 part 2
 2019 day 8 part 1  |  2019 day 8 part 2
 2019 day 9  part 1  |  2019 day 9 part 2
 2019 day 10 part 1  |  2019 day 10 part 2
 2019 day 11 part 1  |  2019 day 11 part 2
 2019 day 12 part 1  |  2019 day 12 part 2
 2019 day 13 part 1  |  2019 day 13 part 2
 2019 day 14 part 1  |  2019 day 14 part 2
 2019 day 15 part 1  |  2019 day 15 part 2
 2019 day 16 part 1  |  2019 day 16 part 2
 2019 day 17 part 1  |  2019 day 17 part 2</s>
 2019 day 18 part 1  |  2019 day 18 part 2
 <s>2019 day 19 part 1  |  2019 day 19 part 2
 2019 day 20 part 1  |  2019 day 20 part 2
 2019 day 21 part 1  |  2019 day 21 part 2</s>
 2019 day 22 part 1  |  2019 day 22 part 2
 2019 day 23 part 1  |  2019 day 23 part 2
 2019 day 24 part 1  |  2019 day 24 part 2
 2019 day 25 part 1  
year: 2018
<s> 2018 day 1 part 1  |  2018 day 1 part 2
 2018 day 2 part 1  |  2018 day 2 part 2
 2018 day 3 part 1  |  2018 day 3 part 2
 2018 day 4 part 1  |  2018 day 4 part 2
 2018 day 5 part 1  |  2018 day 5 part 2
 2018 day 6 part 1  |  2018 day 6 part 2
 2018 day 7 part 1  |  2018 day 7 part 2
 2018 day 8 part 1  |  2018 day 8 part 2
 2018 day 9  part 1  |  2018 day 9 part 2
 2018 day 10 part 1  |  2018 day 10 part 2
 2018 day 11 part 1  |  2018 day 11 part 2
 2018 day 12 part 1  |  2018 day 12 part 2
 2018 day 13 part 1  |  2018 day 13 part 2
 2018 day 14 part 1  |  2018 day 14 part 2</s>
 2018 day 15 part 1  |  2018 day 15 part 2
 <s>2018 day 16 part 1  |  2018 day 16 part 2
 2018 day 17 part 1  |  2018 day 17 part 2
 2018 day 18 part 1  |  2018 day 18 part 2
 2018 day 19 part 1</s>  |  2018 day 19 part 2
 <s>2018 day 20 part 1  |  2018 day 20 part 2
 2018 day 21 part 1  |  2018 day 21 part 2
 2018 day 22 part 1  |  2018 day 22 part 2</s>
 2018 day 23 part 1  |  2018 day 23 part 2
 2018 day 24 part 1  |  2018 day 24 part 2
 2018 day 25 part 1  
year: 2017
<s> 2017 day 1 part 1  |  2017 day 1 part 2
 2017 day 2 part 1  |  2017 day 2 part 2
 2017 day 3 part 1  |  2017 day 3 part 2
 2017 day 4 part 1  |  2017 day 4 part 2
 2017 day 5 part 1  |  2017 day 5 part 2
 2017 day 6 part 1  |  2017 day 6 part 2
 2017 day 7 part 1  |  2017 day 7 part 2
 2017 day 8 part 1  |  2017 day 8 part 2
 2017 day 9  part 1  |  2017 day 9 part 2
 2017 day 10 part 1  |  2017 day 10 part 2
 2017 day 11 part 1  |  2017 day 11 part 2
 2017 day 12 part 1  |  2017 day 12 part 2
 2017 day 13 part 1  |  2017 day 13 part 2
 2017 day 14 part 1  |  2017 day 14 part 2
 2017 day 15 part 1  |  2017 day 15 part 2
 2017 day 16 part 1  |  2017 day 16 part 2
 2017 day 17 part 1  |  2017 day 17 part 2
 2017 day 18 part 1 </s> |  2017 day 18 part 2
 <s>2017 day 19 part 1  |  2017 day 19 part 2
 2017 day 20 part 1  |  2017 day 20 part 2
 2017 day 21 part 1  |  2017 day 21 part 2</s>
 2017 day 22 part 1  |  2017 day 22 part 2
 2017 day 23 part 1  |  2017 day 23 part 2
 2017 day 24 part 1  |  2017 day 24 part 2
<s> 2017 day 25 part 1</s>  
year: 2016
<s> 2016 day 1 part 1  |  2016 day 1 part 2
 2016 day 2 part 1  |  2016 day 2 part 2
 2016 day 3 part 1  |  2016 day 3 part 2
 2016 day 4 part 1  |  2016 day 4 part 2
 2016 day 5 part 1  |  2016 day 5 part 2
 2016 day 6 part 1  |  2016 day 6 part 2
 2016 day 7 part 1  |  2016 day 7 part 2
 2016 day 8 part 1  |  2016 day 8 part 2
 2016 day 9  part 1  |  2016 day 9 part 2
 2016 day 10 part 1  |  2016 day 10 part 2</s>
 2016 day 11 part 1  |  2016 day 11 part 2
 <s>2016 day 12 part 1  |  2016 day 12 part 2
 2016 day 13 part 1  |  2016 day 13 part 2
 2016 day 14 part 1  |  2016 day 14 part 2
 2016 day 15 part 1  |  2016 day 15 part 2
 2016 day 16 part 1  |  2016 day 16 part 2
 2016 day 17 part 1  |  2016 day 17 part 2
 2016 day 18 part 1  |  2016 day 18 part 2
 2016 day 19 part 1  |  2016 day 19 part 2
 2016 day 20 part 1  |  2016 day 20 part 2
 2016 day 21 part 1  |  2016 day 21 part 2
 2016 day 22 part 1 </s> |  2016 day 22 part 2
 2016 day 23 part 1  |  2016 day 23 part 2
 2016 day 24 part 1  |  2016 day 24 part 2
 2016 day 25 part 1  
year: 2015
<s> 2015 day 1 part 1  |  2015 day 1 part 2
 2015 day 2 part 1  |  2015 day 2 part 2
 2015 day 3 part 1  |  2015 day 3 part 2
 2015 day 4 part 1  |  2015 day 4 part 2
 2015 day 5 part 1  |  2015 day 5 part 2
 2015 day 6 part 1  |  2015 day 6 part 2
 2015 day 7 part 1  |  2015 day 7 part 2
 2015 day 8 part 1  |  2015 day 8 part 2
 2015 day 9  part 1  |  2015 day 9 part 2
 2015 day 10 part 1  |  2015 day 10 part 2
 2015 day 11 part 1  |  2015 day 11 part 2
 2015 day 12 part 1  |  2015 day 12 part 2
 2015 day 13 part 1  |  2015 day 13 part 2
 2015 day 14 part 1  |  2015 day 14 part 2
 2015 day 15 part 1  |  2015 day 15 part 2
 2015 day 16 part 1  |  2015 day 16 part 2
 2015 day 17 part 1  |  2015 day 17 part 2
 2015 day 18 part 1  |  2015 day 18 part 2
 2015 day 19 part 1  |  2015 day 19 part 2
 2015 day 20 part 1  |  2015 day 20 part 2
 2015 day 21 part 1  |  2015 day 21 part 2
 2015 day 22 part 1  |  2015 day 22 part 2</s>
 2015 day 23 part 1  |  2015 day 23 part 2
 2015 day 24 part 1  |  2015 day 24 part 2
 <s>2015 day 25 part 1  </s>
</pre>

<pre>
	1-5 END:20/05/2025
	6 END:22/05/2025
	7 END:25/05/2025
	8 END 28/05/2025
	9 END 29/05/2025
	10 END 01/06/2025
	11 END 05/06/2025
	12 END 11/06/2025
	13 END 29/06/2025 SJ
	14 END 06/07/2025
	15 END 12/07/2025
	16 END 17/07/2025
	17 END 20/07/2025
	18 END 21/07/2025
	19 END 25/07/2025
	20 END 27/07/2025
	21 END 28/07/2025
	22 EnD 31/07/2025
</pre>

