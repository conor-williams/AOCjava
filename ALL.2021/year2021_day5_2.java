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


class year2021_day5_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int SZ = 1000;
	public static int [][] grid = new int[SZ][SZ];
	public static void main(String [] args) {
		out.println("		2021 Day5.2");
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
		Pattern p = Pattern.compile("(\\d+),(\\d+)[\\s]+->[\\s]+(\\d+),(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int x1 = Integer.valueOf(m.group(1));
			int y1 = Integer.valueOf(m.group(2));
			int x2 = Integer.valueOf(m.group(3));
			int y2 = Integer.valueOf(m.group(4));


			if (x1 == x2 || y1 == y2) {
				if (x1 > x2) {
					int yy = y1;
					for (int xx = x2; xx <= x1; xx++) {
						grid[yy][xx]++;
					}
				} else if (x2 > x1) {
					int yy = y1;
					for (int xx = x1; xx <= x2; xx++) {
						grid[yy][xx]++;
					}
				} else if (y1 > y2) {
					int xx = x1;
					for (int yy = y2; yy <= y1; yy++) {
						grid[yy][xx]++;
					}
				} else if (y2 > y1) {
					int xx = x1;
					for (int yy = y1; yy <= y2; yy++) {
						grid[yy][xx]++;
					}
				}

			} else {//diag
				if (x2 > x1 && y2 > y1) {
					int yy = y1;
					for (int xx = x1; xx <= x2; xx++) {
						grid[yy][xx]++;
						yy++;
					}
				} else if (x2 > x1 && y2 < y1) {
					int yy = y1;
					for (int xx = x1; xx <= x2; xx++) {
						grid[yy][xx]++;
						yy--;
					}
				} else if (x1 > x2 && y2 > y1) {
					int yy = y2;
					for (int xx = x2; xx <= x1; xx++) {
						grid[yy][xx]++;
						yy--;
					}
				} else if (x1 > x2 && y1 > y2) {
					int yy = y2;
					for (int xx = x2; xx <= x1; xx++) {
						grid[yy][xx]++;
						yy++;
					}
				}

			}

		}
		int tot = 0;
		for (int yy = 0; yy < SZ; yy++) {
			for (int xx = 0; xx < SZ; xx++) {
				if (grid[yy][xx] >= 2) {
					tot++;
				}
			}
		}
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

