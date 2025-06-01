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
class year2023_day10_2 {
	public static int maxPath = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	public static int already [][] = new int[sy][sx];
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day10.2");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		int lenx = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;

			while ((line = br.readLine()) != null) {
				if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//grid[leny] = line.toCharArray();
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		sx = lenx;
		sy = leny;
		grid = new char[sy][sx];
		already = new int[sy][sx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int startX = 0;
		int startY = 0;
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == 'S') {
					startX = xx;
					startY = yy;
				}
			}
		}

		already[startY][startX] = 1;
		if (startY-1 >= 0) {
			if (grid[startY-1][startX] == '7' || 
					grid[startY-1][startX] == 'F' ||
					grid[startY-1][startX] == '|') {
				next(startX, startY-1, 1);
					}
		}
		if (startX+1 < sx) {
			if (grid[startY][startX+1] == 'J' ||
					grid[startY][startX+1] == '7' ||
					grid[startY][startX+1] == '-') {
				next(startX+1, startY, 1);
					}
		}
		if (startY+1 < sy) {
			if (grid[startY+1][startX] == 'L' ||
					grid[startY+1][startX] == '|' ||
					grid[startY+1][startX] == 'J') {
				next(startX, startY+1, 1);
					}
		}
		if (startX-1 >= 0) {
			if (grid[startY][startX-1] == '-' || grid[startY][startX-1] == 'L' ||
					grid[startY][startX-1] == 'F') {
				next(startX-1, startY, 1);
					}
		}

		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (already[yy][xx] == 0) {
					grid[yy][xx] = '.';
				}
			}
		}
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
			

		int tot = 0;
		for (int yy = 0; yy < sy; yy++) {
			boolean outside = true;
			char prev = 'X';
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '-') {
					//outside = outside;
				} else if (grid[yy][xx] == '|') {
					outside = !outside;
				} else if (grid[yy][xx] == 'L') {
					//outside = !outside;
					prev = 'L';
				} else if (grid[yy][xx] == '7') {
					if (prev == 'L') {
						outside = !outside;
					}
					prev = '7';
				} else if (grid[yy][xx] == 'F') {
					//	outside = !outside;
					prev = 'F';
				} else if (grid[yy][xx] == 'J') {
					//outside = !outside;
					if (prev == 'F') {
						outside = !outside;
					}
					prev = 'J';
				}	
				if (!outside && grid[yy][xx] == '.') {
					grid[yy][xx] = 'I';
					tot++;
				}
			}
		}

		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static void next (int x, int y, int path) {
		if (x < 0 || y < 0 || x >= sx || y >= sy) {return;}
		if (grid[y][x] == 'S') {if (path > maxPath) {maxPath = path; return;}
		}
		if (already[y][x] == 1) {return;}	
		already[y][x]++;
		switch (grid[y][x]) {
			case '|':
				next(x, y-1, path+1);
				next(x, y+1, path+1);
				break;
			case '-':
				next(x+1, y, path+1);
				next(x-1, y, path+1);
				break;
			case 'L':
				next(x, y-1, path+1);
				next(x+1, y, path+1);
				break;
			case 'J':
				next(x-1, y, path+1);
				next(x, y-1, path+1);
				break;
			case '7':
				next(x, y+1, path+1);
				next(x-1, y, path+1);
				break;
			case 'F':
				next(x, y+1, path+1);
				next(x+1, y, path+1);
				break;
			case '.':
				return;
			case 'S':
				out.println("case S end reached..");
				//Scanner scanner = new Scanner(System.in); scanner.nextLine();
				break;
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

