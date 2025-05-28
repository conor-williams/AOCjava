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

class year2022_day8 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int sy = 99;
	public static int sx = 99;
	public static char [][] grid = new char[sy][sx];
	public static void main(String [] args) {
		out.println("		2022 Day8.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			int leny = 0;
			while ((line = br.readLine()) != null) {
				blah.add(line);
				grid[leny] = line.toCharArray();
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		int tot = 0;
		tot += (2*sx) + (2*sy) - 4;
		//out.println(tot);
		for (int yy = 1; yy < sy-1; yy++) {
			for (int xx = 1; xx < sx-1; xx++) {
				int visible1 = 1;
				int visible2 = 1;
				int visible3 = 1;
				int visible4 = 1;
				for (int yy1 = 0; yy1 < yy; yy1++) {
					if (grid[yy1][xx] >= grid[yy][xx]) {
						visible1 = 0;
						break;
					}
				}
				for (int yy1 = yy+1; yy1 < sy; yy1++) {
					if (grid[yy1][xx] >= grid[yy][xx]) {
						visible2 = 0;
						break;
					}
				}
				for (int xx1 = 0; xx1 < xx; xx1++) {
					if (grid[yy][xx1] >= grid[yy][xx]) {
						visible3 = 0;
						break;
					}
				}
				for (int xx1 = xx+1; xx1 < sx; xx1++) {
					if (grid[yy][xx1] >= grid[yy][xx]) {
						visible4 = 0;
						break;
					}
				}
				if (visible1 == 1 || visible2 == 1 || visible3 == 1 || visible4 == 1) {
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

