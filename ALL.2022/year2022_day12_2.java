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
class year2022_day12_2 {
	public static int minPath = 999999;
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2022 Day12.2");
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
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		sx = lenx;
		sy = leny;
		grid = new char[sy][sx];
		already = new int[sy][sx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}

		int foundS = 0;
		int foundE = 0;
		int stx = 0; int sty = 0;
		int enx = 0; int eny = 0;

after:
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				//out.print(grid[yy][xx]);
				if (grid[yy][xx] == 'S' && foundS == 0) {
					out.println("here22");
					//out.println(xx);
					//out.println(yy);
					//out.println("-------");
					stx = xx; sty = yy;
					foundS = 1;
					continue;
				}
				if (grid[yy][xx] == 'E' && foundE == 0) {
					out.println("here23");
					enx = xx; eny = yy;
					foundE = 1;
					continue;
				}
				if (foundS == 1 && foundE == 1) {
					break after;
				}
			}
			//out.println();
		}
		grid[sty][stx] = 'a';

		Vector <Tuple <Integer, Integer>> starts = new Vector<>();
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == 'a') {
					Tuple<Integer, Integer> tu1 = new Tuple(xx, yy);
					starts.add(tu1);
				}
			}
		}


		//already[sty][stx] = 1;
		grid[eny][enx] = 'z';
		for (int qq = 0; qq < starts.size(); qq++) {
			Arrays.stream(already).forEach(a -> Arrays.fill(a, 0));
			var tu2 = starts.get(qq);
			next(tu2.first, tu2.second, enx, eny, 'a', 0);
		}


		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
	}
	public static void next(int x, int y, int enx, int eny, char prev, int path) {
		if (x < 0 || y < 0 || x >= sx || y >= sy) {return;}
		//if (already[y][x] == 1) {return;}

		if (grid[y][x] <= prev || grid[y][x] == prev+1) {
			//ok
		} else {
			//out.println(grid[y][x]); out.println(prev); out.println("grid out");
			return;
		}
		if (x == enx && y == eny) {out.println("here1");out.print(path); if (path < minPath) {minPath = path;} return;}
		/*
		   for (int yy = 0; yy < sy; yy++) {
		   for (int xx = 0; xx < sx; xx++) {
		   if (yy == y && xx == x) {
		   out.print(" ");
		   } else {
		   out.print(grid[yy][xx]);
		   }
		   }
		   out.println();
		   }
		   out.print("path: "); out.println(path);
		   */
		//	Scanner scanner = new Scanner(System.in); scanner.nextLine();

		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			next(x, y-1, enx, eny, grid[y][x], path+1);
			next(x+1, y, enx, eny, grid[y][x], path+1);
			next(x, y+1, enx, eny, grid[y][x], path+1);
			next(x-1, y, enx, eny, grid[y][x], path+1);
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

