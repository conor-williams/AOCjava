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

class year2015_day8_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day8.2");
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
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		long bef = 0;
		long aft = 0;
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			bef += ne.length();

			//out.print(ne);
                        ne = ne.replaceAll("^\"", "333");
                        ne = ne.replaceAll("\"$", "333");
			ne = ne.replaceAll("\"\"", "4444");
                        ne = ne.replaceAll("\\\\\\\\", "4321");
                        ne = ne.replaceAll("\\\\\\\"", "1234");
                        ne = ne.replaceAll("\\\\x[0-9a-f][0-9a-f]", "55555");

			//ne = ne.replaceAll("\"\"", "");
			//ne = ne.replaceAll("\\\\\\\\", "S");
			/*
			 *
			 * nearly
			ne = ne.replaceAll("\\\\\"", "sssq");
			ne = ne.replaceAll("\\\\\\\\", "\\\\\\\\\\\\\\\\");
			ne = ne.replaceAll("(\\\\x[0-9a-f][0-9a-f])", "\\\\$1");
			ne = ne.replaceAll("^\"", "\"\\\\\"");
			ne = ne.replaceAll("\"$", "\\\\\"\"");
			out.print(" "); out.println(ne);
			*/
			//out.print(" "); out.print(ne); out.print(" "); out.println(ne.length());
			aft += ne.length();

		}
		out.print("**j_ans: ");
		out.print(aft-bef);
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

