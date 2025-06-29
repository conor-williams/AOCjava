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


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2024_day13_2 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day13.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
                //int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
                                //if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
                /*sx = lenx;
                sy = leny;
                grid = new char[sy][sx];
                already = new int[sy][sx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("Button (A|B): X\\+(\\d+), Y\\+(\\d+)");
		Pattern p2 = Pattern.compile("Prize: X=(\\d+), Y=(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigLong.parseLong((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		long ax = 0;
		long ay = 0;
		long bx = 0;
		long by = 0;
		long px = 0;
		long py = 0;
		long mintot = 0;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			out.println("here1");
			out.println(blah.get(i));
			if (m.find()) {
				if (m.group(1).equals("A")) {
					ax = Long.parseLong(m.group(2));
					ay = Long.parseLong(m.group(3));
				} else if (m.group(1).equals("B")) {
					bx = Long.parseLong(m.group(2));
					by = Long.parseLong(m.group(3));
				} else {
					out.println("ERR22");
				}
				continue;
			}
			Matcher m2 = p2.matcher(blah.get(i));
			if (m2.find()) {
				out.println(blah.get(i));
				px = Long.parseLong(m2.group(1)) + 10000000000000L;
				py = Long.parseLong(m2.group(2)) + 10000000000000L;
				//px = Long.parseLong(m2.group(1));
				//py = Long.parseLong(m2.group(2));

				//long mxa = px/ax + 2; long mya = py/ay + 2; mxa = mxa > mya ? mxa: mya;
				//long mxb = px/bx + 2; long myb = py/by + 2; mxb = mxb > myb ? mxb: myb;

				double mintokens = 0;
				
				double ta = (double)((bx*py) - (by*px)) / (double)((ay*bx) - (by * ax));
				double tb = (double)(px - (ax * ta)) / (double)bx;
				out.println(ta);
				out.println(tb);
				if (ta %1 == 0 && tb %1 == 0) {
					mintokens = (1*tb) + (3*ta);
					if (mintokens != 0) {
						mintot += mintokens;
					}
				}
				continue;
			}
			if (blah.get(i).length() == 0) {
				continue;
			}
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(mintot);
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
		if (!this.first.equals(tu2.first)) {return false;}
                if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}
