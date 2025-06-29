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


///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2016_day13 {
//	        public static int maxPath = 0;
	        public static int minPath = 9999;
        public static int sx = 50;
        public static int sy = 50;
        public static char grid [][] = new char[sy][sx];
        public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day13.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		int leny = 0;
                int lenx = 0;
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
		lenx = sx;
		leny = sy;
		int fav = Integer.parseInt(blah.get(0));
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				int an = xx*xx + 3*xx + 2*xx*yy + yy + yy*yy + fav;
				String binbin = Integer.toString(an, 2);
				long onesCount = binbin.chars()
					.filter(c -> c == '1')
					.count();
				if (onesCount % 2 == 0) {
					grid[yy][xx] = '.';
				} else {
					grid[yy][xx] = '#';
				}

			}
		}
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}

		next(1, 1, 31, 39, 0);

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
	}
	public static void next(int x, int y, int ex, int ey, int path) {
		if (x >= sx|| y >= sy || x < 0 || y < 0) {return;}
		if (grid[y][x] == '#') {return;}
		if (x == ex && y == ey) { 
			if (path < minPath) {minPath = path;}
			return;
		}

		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			next(x, y-1, ex, ey, path+1);
			next(x+1, y, ex, ey, path+1);
			next(x, y+1, ex, ey, path+1);
			next(x-1, y, ex, ey, path+1);
		}
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
