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

class year2018_day8 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static Vector <Integer> var_ints = new Vector<>();
	public static void main(String [] args) {
		out.println("		2018 Day8.1");
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
		for (int i = 0; i < blah.size(); i++) {
			 Scanner scanner = new Scanner(blah.get(i));
			 scanner.useDelimiter("[\t\\s ]+");
			 while (scanner.hasNext()) {
			        String ne = scanner.next();
			        var_ints.add(Integer.valueOf(ne));
			 }
		}
		

		parseChild(0);

		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static int level = 0;
	public static long tot = 0;
	public static int ii = 0;
	public static void parseChild(int start) {
		level++;
		///out.print("in ParseChild:"); out.println(level);

		//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		for (ii = start; ii < var_ints.size(); ii++) {
			int numChildren = var_ints.get(ii);
			int numMetaData = var_ints.get(ii+1);
			ii+=2;

			//out.print("children: "); out.print(numChildren); out.print(" numMetaData "); out.println(numMetaData);
			if (numChildren > 0) {
				for (int zz = 0; zz < numChildren; zz++) {
				//	out.print("ii now: "); out.println(ii);
					parseChild(ii);
				}
				int qqq = ii;
				for (; qqq < ii+numMetaData; qqq++) {
				//	out.print("here2..level:"); out.println(level);
					if (qqq < var_ints.size()) {
						tot += var_ints.get(qqq);
						//out.println(var_ints.get(qqq));
					} else {
						out.println("ERR over...");
						Runtime.getRuntime().halt(0);
					}
				}
				ii = qqq;
				level--;
				return;
			} else if (numChildren == 0) {
				//out.println("numChildren zero...");
				int qqq = ii;
				for (; ii < qqq+numMetaData; ii++) {
					tot += var_ints.get(ii);
					//out.println(var_ints.get(ii));
				}
				//ii = ii+2+numMetaData;
				level--;
				return;
			}
			//out.print("numMetaData: ");out.println(numMetaData); out.print("ii is : "); out.println(ii);
			//collect the metadata
		}
		level--;
		return;
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

