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

@SuppressWarnings("unchecked")
class year2024_day9 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int [] fs = new int[1000000];
	public static void main(String [] args) {
		out.println("		2024 Day9.1");
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
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		long checkSum = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			ne = ne.concat("0");
			int fileNumber = 0;
			int start = 0;
			int kk = 0;
			out.println(ne);
			for (int yy = 0; yy < ne.length(); yy+=2) {
				int len = ne.charAt(yy)-'0';
				int spaces = ne.charAt(yy+1)-'0';
				out.println(len);
				out.println(spaces);

				for (kk = start; kk < start+len; kk++) {
					fs[kk] = fileNumber;
				}
				for (; kk < start+len+spaces; kk++) {
					fs[kk] = -1;
				}
				fileNumber++;
				start = kk;
			}
			int size = kk;
			/*
			for (int yy = 0; yy < size; yy++) {
				if (fs[yy] != -1) {
					out.print(fs[yy]);
				} else {
					out.print(".");
				}
			}
			//nextSpace() = lastCharacter();
			out.println(size);
			*/
			while ( 1 == 1) {
				int nextSpace = 0;
				int nextCharacter = 0;
				for (int yy = 0; yy < size; yy++) {//forward
					if (fs[yy] == -1) {
						nextSpace = yy;
						break;
					}
				}
				for (int zz = size-1; zz >= 0; zz--) { //backward
					if (fs[zz] != -1) {
						nextCharacter = zz;
						break;
					}
				}
				if (nextSpace >= nextCharacter) {
					break;
				} else {
					fs[nextSpace] = fs[nextCharacter];
					fs[nextCharacter] = -1;
				}
			}
			for (int yy = 0; yy < size; yy++) {
				if (fs[yy] != -1) {
					checkSum += yy * fs[yy];
					//out.print(fs[yy]);
				}
			}

		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(checkSum);
		out.println("");
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

