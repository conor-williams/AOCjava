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


class year2023_day3 {
	public static char [][] grid = new char[150][150];
	public static void main(String [] args) {
		out.println("		2023 Day3.1");
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
		int totGood = 0;
		for (int i = 0; i < blah.size(); i++) {
			String line1 = blah.get(i);
			Vector <Tuple <Integer, Integer>> pointdigit = new Vector<>();
			for (int kk = 0; kk < line1.length(); kk++) {
				char ch = line1.charAt(kk);
				if (Character.isDigit(ch)) {
					Tuple <Integer, Integer> tu = new Tuple<>(kk, i);
					pointdigit.add(tu);
				} else if (!Character.isDigit(ch) && pointdigit.size() > 0) {
					totGood += checkAround(pointdigit, line1.length(), blah.size());
					pointdigit.clear();
					//out.println(totGood);
				}
				if (pointdigit.size() > 0 && kk==line1.length()-1) {
					totGood += checkAround(pointdigit, line1.length(), blah.size());
					pointdigit.clear();
				}
			}
		}
		out.print("**j_ans: ");
		out.print(totGood);
		out.println("");
	}
	public static int checkAround(Vector <Tuple <Integer, Integer>> ve, int xlen, int ylen) {
		char [] thechars = new char[20];
		for (int ii = 0; ii < ve.size(); ii++) {
			Tuple <Integer, Integer> tu = ve.get(ii);
			int x = tu.first; int y = tu.second;
			thechars[ii] = grid[y][x];
		}
		int found = 0;
		for (int ii = 0; ii < ve.size(); ii++) {
			Tuple <Integer, Integer> tu = ve.get(ii);
			int x = tu.first; int y = tu.second;


			if (x-1 >= 0 && y-1 >=0       && !charOk(grid[y-1][x-1])) {
				found = 1;
				break;
			}
			if (y-1 >= 0                  && !charOk(grid[y-1][x])) {
				found = 1;
				break;
			}
			if (x+1 < xlen && y-1 >= 0    && !charOk(grid[y-1][x+1])) {
				found = 1;
				break;
			}
			if (x+1 < xlen                && !charOk(grid[y][x+1])) {
				found = 1;
				break;
			}
			if (x+1 < xlen && y+1 < ylen  && !charOk(grid[y+1][x+1])) {
				found = 1;
				break;
			}
			if (y+1 < ylen                && !charOk(grid[y+1][x])) {
				found = 1;
				break;
			}
			if (x-1 >= 0 && y+1 < ylen    && !charOk(grid[y+1][x-1])) {
				found = 1;
				break;
			}
			if (x-1 >= 0                  && !charOk(grid[y][x-1])) {
				found = 1;
				break;
			}

		}
		if (found == 0) {return 0;}
		else {
			return Integer.valueOf(new String(thechars, 0, ve.size()));
		}
	}
	public static boolean charOk(char gg) {
		boolean ok = true;

		switch (gg) {
			case '.':
				break;
			case '#':
			case '$':
			case '%':
			case '&':
			case '*':
			case '+':
			case '-':
			case '/':
			case '=':
			case '@':
				ok = false;
				break;
		}
		return ok;
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

