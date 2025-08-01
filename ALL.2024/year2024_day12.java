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
class year2024_day12 {
	//	        public static int maxPath = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	public static int gridNum [][] = new int[sy][sx];
	public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day12.1");
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
		gridNum = new int[sy][sx];

		int reg = 0;
		int areas[] = new int[2000];
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (already[yy][xx] == 0) {
					char ch = grid[yy][xx];
					regionSize = 0;
					regionPoints.clear();
					floodsize(ch, xx, yy);
					
					for (int ii = 0; ii < regionPoints.size(); ii++) {
						var tu1 = regionPoints.get(ii);
						gridNum[tu1.second][tu1.first] = reg;
						already[tu1.second][tu1.first] = 1;
					}
					out.println(regionSize);
					areas[reg] = regionSize;
					reg++;
				}
			}
		}
		/*
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				areas[grid[yy][xx]-'A']++;
			}
		}
		*/

		int perms[] = new int[reg+1];
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				int let = gridNum[yy][xx];
				if (yy-1 >= 0) {
					if (gridNum[yy-1][xx] == let) {
					} else {
						perms[let]++;
					}
				} else {
					perms[let]++;
				}

				if (xx+1 < sx) {
					if (gridNum[yy][xx+1] == let) {
					} else {
						perms[let]++;
					}
				} else {
					perms[let]++;
				}

				if (yy+1 < sy) {
					if (gridNum[yy+1][xx] == let) {
					} else {
						perms[let]++;
					}
				} else {
					perms[let]++;
				}

				if (xx-1 >= 0) {
					if (gridNum[yy][xx-1] == let) {
					} else {
						perms[let]++;
					}
				} else {
					perms[let]++;
				}
			}
		}
		long tot = 0;

		for (int ii = 0; ii < reg+1; ii++) {
			if (areas[ii] != 0) {
				out.println(ii);
				out.print("area: "); out.print(areas[ii]);
				out.print(" perm: "); out.println(perms[ii]);
				tot += perms[ii] * areas[ii];
			}
		}


		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
	public static int regionSize = 0;
	public static Vector <Tuple <Integer, Integer>> regionPoints = new Vector <> ();
	public static void floodsize(char ch, int x, int y) {
		if (x < 0 || y < 0 || x >= sx || y >= sy) {return;}
		if (already[y][x] == 1) {return;}
		if (grid[y][x] == ch) {regionSize++; regionPoints.add(new Tuple(x, y));} else {return;}
		already[y][x] = 1;
		floodsize(ch, x, y-1);
		floodsize(ch, x+1, y);
		floodsize(ch, x, y+1);
		floodsize(ch, x-1, y);
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

