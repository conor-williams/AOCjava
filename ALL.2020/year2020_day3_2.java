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


class year2020_day3_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static char [][] grid = new char[400][40];
	public static void main(String [] args) {
		out.println("		2020 Day3.2");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
				grid[leny] = line.toCharArray();
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		int lenx = blah.get(0).length();

		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);

		Vector<Tuple<Integer, Integer>> slopes = new Vector<>();
		Tuple<Integer, Integer> tu = new Tuple<>(1, 1); slopes.add(tu);
		tu = new Tuple<>(3, 1); slopes.add(tu);
		tu = new Tuple<>(5, 1); slopes.add(tu);
		tu = new Tuple<>(7, 1); slopes.add(tu);
		tu = new Tuple<>(1, 2); slopes.add(tu);

		int tottrees = 1;
		for (int ii = 0; ii < slopes.size(); ii++) {
			int y = 0;
			int x = 0;

			int trees = 0;
			Tuple<Integer, Integer> tu2 = slopes.get(ii);
			//out.print(tu2.first); out.print("  "); out.println(tu2.second);
			do {
				if (x > lenx-1) {x %= lenx;}
				//out.print(x); out.print("  "); out.println(y);
				if (grid[y][x] == '#') {trees++;}
				y += tu2.second; x+=tu2.first;	
				//out.print(x); out.print(" "); out.print(y); out.println();
			} while (y < leny);

			//out.println(trees);
			tottrees *= trees;
		}

		out.print("**j_ans: ");
		out.print(tottrees);
		out.println("");
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

