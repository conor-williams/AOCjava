import java.math.BigInteger;
import java.util.Scanner;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import static java.lang.System.out;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Objects;
import java.util.Collections;
import java.lang.Character;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;
import java.lang.*;
// /java -Xmx2g year2019_day3.java *i1.txt


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2023_day8_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day8.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([A-Z\\d]+) = \\(([A-Z\\d]+), ([A-Z\\d]+)\\)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <String, String> mpLeft = new HashMap<>();
		Map <String, String> mpRight = new HashMap<>();
		String rules = new String();
		Vector <String> starts = new Vector <>();
		Vector <String> ends = new Vector <>();
		for (int i = 0; i < blah.size(); i++) {
			if (i == 0) {
				rules = new String(blah.get(0));
				continue;
			} else if (i == 1) {continue;}
			Matcher m = p.matcher(blah.get(i));
			m.find();
			//out.println(blah.get(i));
			mpLeft.put(m.group(1), m.group(2));
			mpRight.put(m.group(1), m.group(3));
			if (m.group(1).charAt(2) == 'A') {
				starts.add(m.group(1));
				//String en = m.group(1).replaceAll("A", "Z");
				//ends.add(en);
			}
			if (m.group(1).charAt(2) == 'Z') {
				out.println(m.group(1));
				ends.add(m.group(1));
			}

		}

		int rulesPos = 0;
		String [] rule = new String[starts.size()];
		for (int ii = 0; ii < starts.size(); ii++) {
			out.println("setting rule[ii]");
			rule[ii] = starts.get(ii);
		}

		long steps = 1;
		long [] cycles = new long[starts.size()];
		out.println(starts.size());
		for (int ii = 0; ii < starts.size(); ii++) {
			out.print(starts.get(ii)); out.print(" ");
		}
		out.println();
		for (int ii = 0; ii < ends.size(); ii++) {
			out.print(ends.get(ii)); out.print(" ");
		}
		out.println();
		while (1 == 1) {
			int fin = 0;
			for (int ii = 0; ii < starts.size(); ii++) {
				if (cycles[ii] != 0) {continue;}
				if (rules.charAt(rulesPos) == 'L') {
					rule[ii] = mpLeft.get(rule[ii]);
				} else if (rules.charAt(rulesPos) == 'R') {
					rule[ii] = mpRight.get(rule[ii]);
				}
				if (ends.contains(rule[ii]) && cycles[ii] == 0) {
					out.println("setting cycles"); out.println(steps);
					out.println(rule[ii]);
					cycles[ii] = steps;
				} 
				//out.print(ends.get(ii)); out.print(" V "); out.println(rule[ii]);

				//out.print("rule[ii]: "); out.println(rule[ii]);
			}
			int found = 0;
			for (int ii = 0; ii < starts.size(); ii++) {
				if (cycles[ii] == 0) {
					found = 1;
					break;
				}
			}
			if (found == 0) {
				break;
			}

			rulesPos++;
			if (rulesPos >= rules.length()) {
				rulesPos = 0;
			}
			steps++;
		}
		/*
		   long tot = 1;
		   long lcm1 = lcm(start.get(0), starts.get(1));

		   for (int ii = 1; ii < starts.size(); ii++) {
		   out.println(cycles[ii]);
		   long lcm1 = cycles[ii];
		   }
		   */
		System.setOut(originalOut);

		out.print("**j_ans: ");
		out.print(lcmOfArray(cycles));
		out.println("");
	}
	// Function to calculate GCD using Euclid's algorithm
	private static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	// Function to calculate LCM of two numbers
	private static long lcm(long a, long b) {
		return (a / gcd(a, b)) * b;
	}

	// Function to calculate LCM of an array of numbers
	public static long lcmOfArray(long[] numbers) {
		long result = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			result = lcm(result, numbers[i]);
		}
		return result;
	}
}

class Tuple<X,Y > {
	public final X first;
	public final Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

