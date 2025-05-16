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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2023_day3_2 {
	public static char [][] grid = new char[150][150];
	public static Vector <Vector <Tuple <Integer, Integer>>> digits = new Vector<>();
	public static void main(String [] args) {
		out.println("		2023 Day3.2");
		out.println("	SLOW 2mins40seconds");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
				grid[leny] = line.toCharArray();
				//out.println(grid[leny]);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);

		for (int i = 0; i < blah.size(); i++) {
			String line1 = blah.get(i);
			Vector <Tuple <Integer, Integer>> pointdigit = new Vector<>();
			for (int kk = 0; kk < line1.length(); kk++) {
				char ch = line1.charAt(kk);
				if (Character.isDigit(ch)) {
					Tuple <Integer, Integer> tu = new Tuple<>(kk, i);
					pointdigit.add(tu);
				} else if (!Character.isDigit(ch) && pointdigit.size() > 0) {
					Vector <Tuple <Integer, Integer>> p2 = new Vector<>();
					p2.addAll(pointdigit);
					digits.add(p2);
					pointdigit.clear();
				}
				if (pointdigit.size() > 0 && kk==line1.length()-1) {
					Vector <Tuple <Integer, Integer>> p2 = new Vector<>();
					p2.addAll(pointdigit);
					digits.add(p2);
					pointdigit.clear();
				}
			}
		}
		int totGear = 0;
		for (int i = 0; i < blah.size(); i++) {
			String line1 = blah.get(i);
			for (int kk = 0; kk < line1.length(); kk++) {
				char ch = line1.charAt(kk);
				if (ch == '*') {
					//out.print("got star @ "); out.print(kk); out.print(" yyy "); out.println(i);
					Tuple <Integer, Integer> tu20 = inTwoNums(kk, i, line1.length(), blah.size());
					totGear += tu20.first * tu20.second;
					//out.print(tu20.first); out.print(" mul "); out.println(tu20.second);
				}
			}
		}
		out.print("**j_ans: ");
		out.print(totGear);
		out.println("");
	}
	public static Tuple <Integer, Integer> inTwoNums(int x, int y, int xlen, int ylen) {
		char [] thechars = new char[20];
		for (int one = 0; one < digits.size(); one++) {
			Vector <Tuple <Integer, Integer>> ve1 = digits.get(one);
			for (int two = 0; two < digits.size(); two++) {
				if (one == two) {
					continue;
				}
				Vector <Tuple <Integer, Integer>> ve2 = digits.get(two);
				int m1 = getTheInt(ve1);
				int m2 = getTheInt(ve2);

				//out.print(m1); out.print(" connected to "); out.println(m2);
				if (inBoth(x, y, ve1, ve2, xlen, ylen)) {
					int mul1 = getTheInt(ve1);
					int mul2 = getTheInt(ve2);
					Tuple <Integer, Integer> tutu = new Tuple<>(mul1, mul2);
					return tutu;
				} else {
				//	out.println("inBoth returned false...");
				}
			}
		}
		Tuple <Integer, Integer> blank = new Tuple<>(0, 0);
		return blank;
	}

	public static int getTheInt(Vector <Tuple <Integer, Integer>> ve) {
		char [] thechars = new char[20];
		for (int ii = 0; ii < ve.size(); ii++) {
			Tuple <Integer, Integer> tu = ve.get(ii);
			int x = tu.first; int y = tu.second;
			thechars[ii] = grid[y][x];
		}
		//out.println(thechars);
		return Integer.valueOf(new String(thechars, 0, ve.size()));
	}

	public static boolean inBoth (int x, int y, Vector <Tuple <Integer, Integer>> ve1, Vector <Tuple <Integer, Integer>> ve2, int xlen, int ylen) {
		int found1 = 0;
		for (int ii = 0; ii < ve1.size(); ii++) {
			Tuple <Integer, Integer> tu = ve1.get(ii);
			int x1 = tu.first; int y1 = tu.second;

			if (x-1 >= 0 && y-1 >=0       && (y-1 == y1 &&  x-1 == x1)) {
				found1 = 1;
				break;
			}
			if (y-1 >= 0                  && (y-1 == y1 && x == x1)) {
				found1 = 1;
				break;
			}
			if (x+1 < xlen && y-1 >= 0    && (y-1 == y1 && x+1 == x1)) {
				found1 = 1;
				break;
			}
			if (x+1 < xlen                && (y == y1 && x+1 == x1)) {
				found1 = 1;
				break;
			}
			if (x+1 < xlen && y+1 < ylen  && (y+1 == y1 && x+1 == x1)) {
				found1 = 1;
				break;
			}
			if (y+1 < ylen                && (y+1 == y1 && x == x1)) {
				found1 = 1;
				break;
			}
			if (x-1 >= 0 && y+1 < ylen    && (y+1 == y1 && x-1 == x1)) {
				found1 = 1;
				break;
			}
			if (x-1 >= 0                  && (y == y1 && x-1 == x1)) {
				found1 = 1;
				break;
			}

		}

		int found2 = 0;
		if (found1 == 1) {
			for (int ii = 0; ii < ve2.size(); ii++) {
				Tuple <Integer, Integer> tu = ve2.get(ii);
				int x1 = tu.first; int y1 = tu.second;

				if (x-1 >= 0 && y-1 >=0       && (y-1 == y1 &&  x-1 == x1)) {
					found2 = 1;
					break;
				}
				if (y-1 >= 0                  && (y-1 == y1 && x == x1)) {
					found2 = 1;
					break;
				}
				if (x+1 < xlen && y-1 >= 0    && (y-1 == y1 && x+1 == x1)) {
					found2 = 1;
					break;
				}
				if (x+1 < xlen                && (y == y1 && x+1 == x1)) {
					found2 = 1;
					break;
				}
				if (x+1 < xlen && y+1 < ylen  && (y+1 == y1 && x+1 == x1)) {
					found2 = 1;
					break;
				}
				if (y+1 < ylen                && (y+1 == y1 && x == x1)) {
					found2 = 1;
					break;
				}
				if (x-1 >= 0 && y+1 < ylen    && (y+1 == y1 && x-1 == x1)) {
					found2 = 1;
					break;
				}
				if (x-1 >= 0                  && (y == y1 && x-1 == x1)) {
					found2 = 1;
					break;
				}

			}
		}
		if (found1 == 1 && found2 == 1) {
			return true;
		} else {
			return false;
		}
	}
}

public class Tuple<X,Y > {
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

