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


class year2024_day4_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static char [][] grid = new char [200][200];
	public static void main(String [] args) {
		out.println("		2024 Day4.2");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		int lenx = -1;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
				if (lenx == -1) {
					lenx = line.length();
				}
				grid[leny] = line.toCharArray();
				leny++;
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
		int tot = 0;

		for (int yy = 0; yy < leny-2; yy++) {
			for (int xx = 0; xx < lenx-2; xx++) {
				if ((grid[yy][xx] == 'M' && grid[yy+1][xx+1] == 'A' &&
						grid[yy+2][xx+2] == 'S') &&
					((grid[yy+2][xx] == 'M' && grid[yy][xx+2] == 'S') || (
						grid[yy+2][xx] == 'S' && grid[yy][xx+2] == 'M'))) {
					tot++;
				}
			}
		}

		for (int yy = 0; yy < leny-2; yy++) {
			for (int xx = 0; xx < lenx-2; xx++) {
				if ((grid[yy][xx] == 'S' && grid[yy+1][xx+1] == 'A' &&
						grid[yy+2][xx+2] == 'M') &&
					((grid[yy+2][xx] == 'M' && grid[yy][xx+2] == 'S') || (
						grid[yy+2][xx] == 'S' && grid[yy][xx+2] == 'M'))) {
					tot++;
				}
			}
		}

		out.print("**j_ans: ");
		out.print(tot);
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

