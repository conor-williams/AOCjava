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

class year2016_day8 {
	public static int sx = 50;
	public static int sy = 6;
	public static int [][] grid = new int[sy][sx];
	public static void main(String [] args) {
		out.println("		2016 Day8.1");
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
		Pattern p1 = Pattern.compile("rect (\\d+)x(\\d+)");
		Pattern p2 = Pattern.compile("rotate row y=(\\d+) by (\\d+)");
		Pattern p3 = Pattern.compile("rotate column x=(\\d+) by (\\d+)");
		for (int i = 0; i < blah.size(); i++) {
			String in = blah.get(i);
			Matcher m1 = p1.matcher(in);
			Matcher m2 = p2.matcher(in);
			Matcher m3 = p3.matcher(in);

			if (m1.find()) {
				int x1 = Integer.valueOf(m1.group(1));
				int y1 = Integer.valueOf(m1.group(2));
				for (int yy = 0; yy < y1; yy++) {
					for (int xx = 0; xx < x1; xx++) {
						grid[yy][xx] = 1;
					}
				}
			} else if (m2.find()) {
				int r1 = Integer.valueOf(m2.group(1));
				int by1 = Integer.valueOf(m2.group(2));

				int[] copiedRow = Arrays.copyOf(grid[r1], grid[r1].length);

				int yy = r1;
				for (int x = 0; x < sx; x++) {
					int frVal = copiedRow[x];
					int to = (x+by1) % sx;
					grid[yy][to] = frVal;
				}
			} else if (m3.find()) {
				int c1 = Integer.valueOf(m3.group(1));
				int by1 = Integer.valueOf(m3.group(2));
				int [] copiedColumn = new int[6];

				for (int y = 0; y < sy; y++) {
					copiedColumn[y] = grid[y][c1];
				}
				int xx = c1;
				for (int y = 0; y < sy; y++) {
					int frVal = copiedColumn[y];
					int to = (y+by1) % sy;
					grid[to][xx] = frVal;
				}
			} else {
				out.println("ERR"); Runtime.getRuntime().halt(0);
			}
			
		}
		int count = 0;
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == 1) {
					count++;
				}
				//out.print(grid[yy][xx]);
			}
			//out.println("");
		}

		out.print("**j_ans: ");
		out.print(count);
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

