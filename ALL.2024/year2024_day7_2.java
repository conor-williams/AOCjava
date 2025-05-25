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


//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//		System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2024_day7_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day7.2");
		out.println("	SLOW ~37seconds");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([\\d]+): ([\\s\\d]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		BigInteger one =  new BigInteger("1");
		BigInteger zero =  new BigInteger("0");
		BigInteger tot = zero ;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			///out.println(blah.get(i));
			m.find();
			
			BigInteger left = new BigInteger(m.group(1));
			String right = m.group(2);

			Scanner scanner = new Scanner(right);
			scanner.useDelimiter("[\t\\s]+");
			Vector <BigInteger> var_ints = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				var_ints.add(new BigInteger(ne));
			}

			double fr = 3;
			double en = var_ints.size();
			int end = (int)Math.pow(fr, en);

			for (int kk = 0; kk < end; kk++) { // 15 in decimal is 1111 in binary
				String ter = padLeftZeros(Integer.toString(kk, 3), 20);
				String rev = new StringBuilder(ter).reverse().toString();
				//out.println(rev);
				BigInteger val = var_ints.get(0);
				for (int jj = 1; jj < var_ints.size(); jj++) {
					char ch = rev.charAt(jj-1);
					if (ch == '0') {
						val = val.add(var_ints.get(jj));
					} else if (ch == '1') {
						val = val.multiply(var_ints.get(jj));
					} else {
						val = new BigInteger(val.toString() + var_ints.get(jj).toString());
					}
				}
				if (val.compareTo(left) == 0) {
					tot = tot.add(left);
					break;
				}
			}

		}
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static String padLeftZeros(String inputString, int length) {
		if (inputString.length() >= length) {
			return inputString;
		}
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length - inputString.length()) {
			sb.append('0');
		}
		sb.append(inputString);
		return sb.toString();
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

