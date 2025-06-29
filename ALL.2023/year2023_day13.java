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
class year2023_day13 {
        public static int sx = 100;
        public static int sy = 100;
        public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day13.1");
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
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int pos = 0;
		int xlen = 0;
		long tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {
				out.print("**pos:"); out.println(pos);
				out.print("**xlen:"); out.println(xlen);
				for (int yy = 0; yy < pos; yy++) {
					for (int xx = 0; xx < xlen; xx++) {
						out.print(grid[yy][xx]);
					}
					out.println();
				}
				out.println();
				int val1 = 0;
				int val2 = 0;
				tot += val1 = checkHorizontal(xlen, pos, grid);
				tot += val2 = checkVertical(xlen, pos, grid);
				if (val1 == 0 && val2 == 0) {
					out.println("neither..>");
					Scanner scanner = new Scanner(System.in); scanner.nextLine();
				}
				pos = 0;
				grid = new char[100][100];
				xlen = 0;
				continue;
			} else {
				grid[pos] = blah.get(i).toCharArray();
				if (xlen == 0) {
					xlen = blah.get(i).length();
				}
				pos++;
			}
		}
		for (int yy = 0; yy < pos; yy++) {
			for (int xx = 0; xx < xlen; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		tot += checkHorizontal(xlen, pos, grid);
		tot += checkVertical(xlen, pos, grid);
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static int checkHorizontal(int lx, int ly, char gg[][]) {
		int htot = 0;
		int found = 0;
		for (int slicey = 1; slicey < ly; slicey++) {
			int good = 0;
after:
			for (int yy1 = slicey-1, yy2 = slicey; yy1 >= 0 && yy2 < ly; yy1--, yy2++) {
				for (int xx = 0; xx < lx; xx++) {
					if (gg[yy1][xx] == gg[yy2][xx]) {
						//good
					} else {
						good = 1;
						//no good
						break after;
					}
				}
			}
			if (good == 0) {
				out.print("horiz @ "); out.print(slicey);
				htot += slicey*100;
				found = 1;
			}
		}
		if (found == 0) {
			out.println("horiz not found");
		}
		return htot;
	}
	public static int checkVertical(int lx, int ly, char gg[][]) {
		int vtot = 0;
		int found = 0;
		for (int slicex = 1; slicex < lx; slicex++) {
			int good = 0;
			out.println("------new slice-------");
after:
			for (int xx1 = slicex-1, xx2 = slicex; xx1 >= 0 && xx2 < lx; xx1--, xx2++) {
				out.print("xx1: "); out.println(xx1);
				out.print("xx2: "); out.println(xx2);
				out.print("ly: "); out.println(ly);
	
				for (int yy = 0; yy < ly; yy++) {
					if (gg[yy][xx1] == gg[yy][xx2]) {
						//good
					} else {
						good = 1;
						//no good
						break after;
					}
				}
			}
			if (good == 0) {
				out.print("vert @ "); out.print(slicex);
				vtot += slicex;
				found = 1;
			}
		}
		if (found == 0) {
			out.println("vert not found");
		}
		return vtot;
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
