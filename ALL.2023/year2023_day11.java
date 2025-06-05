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
class year2023_day11 {
//	        public static int maxPath = 0;
        public static int sx = 0;
        public static int sy = 0;
        public static char grid [][] = new char[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day11.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		int leny = 0;
                int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
                                if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

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
			String ne = blah.get(i);
			int found = 0;
			for (int ii = 0; ii < ne.length(); ii++) {
				if (ne.charAt(ii) != '.') {
					found = 1;
					break;
				}
			}
			if (found == 0) {
				blah.add(i, ne);
				i++;
				leny++;
			}
		}

		for (int xx = 0; xx < lenx; xx++) {
			int found = 0;
			for (int yy = 0; yy < leny; yy++) {
				if (blah.get(yy).charAt(xx) != '.') {
					found = 1;
					break;
				}
			}
			if (found == 0) {
				for (int yy = 0; yy < leny; yy++) {
					String ne = blah.get(yy);
					String to = ne.substring(0, xx) + "." +
						ne.substring(xx);

					blah.set(yy, to);
				}
				xx++;
				lenx++;

			}
		}
                sx = lenx;
                sy = leny;
                grid = new char[sy][sx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }

		Vector <Tuple <Integer, Integer>> gal = new Vector<>();
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '#') {
					Tuple <Integer, Integer> tu1 = new Tuple(xx, yy);
					gal.add(tu1);
				}
			}
		}

		int count = 0;
		long tot = 0;
		for (int ii = 0; ii < gal.size(); ii++) {
			var g1 = gal.get(ii);
			for (int jj = ii+1; jj < gal.size(); jj++) {
				if (ii == jj) {continue;}
				var g2 = gal.get(jj);
				count++;
				int man = Math.abs(g1.first - g2.first) +
				       	Math.abs(g1.second-g2.second);
				tot += man;
			}
		}

		//out.println(count);
		//
		//
		//
		//
//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
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

