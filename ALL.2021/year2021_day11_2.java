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
class year2021_day11_2 {
	//	        public static int maxPath = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static int grid [][] = new int[sy][sx];
	public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day11.2");
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
		sx = lenx;
		sy = leny;
		grid = new int[sy][sx];
		already = new int[sy][sx];
		for (int i = 0; i < blah.size();i++) {
			String ne = blah.get(i);
			for (int xx = 0; xx < ne.length(); xx++) {
				grid[i][xx] = ne.charAt(xx) - '0';
			}
		}

		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		int gridtmp [][] = new int[sy][sx];
		int gridfl [][] = new int[sy][sx];
		int rounds = 100;
		int totFlashes = 0;
		int gSize = sy*sx;
		int ans = 0;
aftaft1:
		for (int ro = 1; ; ro++) {
			for (int y = 0; y < leny; y++) {
				for (int x = 0; x < lenx; x++) {
					gridtmp[y][x] = grid[y][x] + 1;
				}
			}

			int totBefore = totFlashes;
			while (1 == 1) {
				int ref = 0;
				for (int y = 0; y < leny; y++) {
					for (int x = 0; x < lenx; x++) {
						if (gridtmp[y][x] > 9 && gridfl[y][x] == 0) {
							gridfl[y][x] = 1;
							totFlashes++;
							if (y-1 >= 0) {gridtmp[y-1][x]++;}
							if (y-1 >= 0 && x+1 < lenx) {gridtmp[y-1][x+1]++;}
							if (x+1 < lenx) {gridtmp[y][x+1]++;}
							if (x+1 < lenx && y+1 < leny) {gridtmp[y+1][x+1]++;}
							if (y+1 < leny) {gridtmp[y+1][x]++;}
							if (y+1 < leny && x-1 >= 0) {gridtmp[y+1][x-1]++;}
							if (x-1 >= 0) {gridtmp[y][x-1]++;}
							if (y-1 >= 0 && x-1 >= 0) {gridtmp[y-1][x-1]++;}
							gridtmp[y][x] = 0;
						}
					}
				}
aft1:
				for (int y = 0; y < leny; y++) {
					for (int x = 0; x < lenx; x++) {
						if (gridtmp[y][x] > 9 && gridfl[y][x] == 0) {
							ref = 1;
							break aft1;
						}
					}
				}
				if (ref == 0) {break;}
			}

			int totAfter = totFlashes;
			int diff = totAfter - totBefore;
			if (diff == gSize) {ans = ro; break aftaft1;}

			for (int y = 0; y < leny; y++) {
				for (int x = 0; x < lenx; x++) {
					if (gridfl[y][x] == 1) {
						grid[y][x] = 0;
						gridfl[y][x] = 0;
					} else {
						grid[y][x] = gridtmp[y][x];
					}
				}
			}
		}

		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	//		System.setOut(originalOut);
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

