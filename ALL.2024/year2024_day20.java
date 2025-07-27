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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// /java -Xmx2g year2019_day3.java *i1.txt



//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}
//// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2024_day20 {
	public static int minPath = Integer.MAX_VALUE;
	public static int minPathOrig = Integer.MAX_VALUE;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static char gridTmp [][] = new char[leny][lenx];
	public static int already [][] = new int[leny][lenx];
	static Map<Long, Long> mp = new HashMap<>();
	static Set <Tuple<Integer, Integer>> se = new HashSet<>();

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day20.1");
		out.flush();
		Vector<String> blah = new Vector<>();
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		grid = new char[leny][lenx];
		gridTmp = new char[leny][lenx];
		already = new int[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}


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
		//for (int i = 0; i < blah.size(); i++) { }
		//		System.setOut(originalOut);
		int sx = 0, sy = 0, ex = 0, ey = 0;
		int foundS = 0, foundE = 0;
after:
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (foundS == 1 && foundE == 1) {
					break after;
				} else if (grid[y][x] == 'S') {
					foundS = 1;
					sx = x; sy = y;
					grid[sy][sx] = '.';
				} else if (grid[y][x] == 'E') {
					foundE = 1;
					ex = x; ey = y;
					grid[ey][ex] = '.';
				}
			}
		}
		minPath = minPathOrig;
		Vector <Tuple <Integer, Integer>> ve = new Vector<>();
		ve.clear();
		next(sx, sy, ex, ey, 0, ve);
		Set <Tuple <Integer, Integer>> se2 = new HashSet<>();
		int noCheatMinPath = minPath;
		for (var se1: se) {
			int x1 = se1.first; int y1 = se1.second;
			if (y1-1 > 0) {if (grid[y1-1][x1] == '#') { se2.add(new Tuple(x1, y1-1));}}
			if (x1+1 < lenx-1) {if (grid[y1][x1+1] == '#') { se2.add(new Tuple(x1+1, y1));}}
			if (y1+1 < leny-1) {if (grid[y1+1][x1] == '#') { se2.add(new Tuple(x1, y1+1));}}
			if (x1-1 > 0) {if (grid[y1][x1-1] == '#') { se2.add(new Tuple(x1-1, y1));}}
        	}
		long count = 0;
		copyGrid();
		for (var se3: se2) {
			int x1 = se3.first; int y1 = se3.second;
			grid[y1][x1] = '.';
			already = new int[leny][lenx];
			minPath = minPathOrig;
			nextNoVec(sx, sy, ex, ey, 0, noCheatMinPath);
			long save = noCheatMinPath - minPath;
			long val = 0;
			if (mp.get(save) == null) {
				val = 0;
			} else {
				val = mp.get(save);
			}

			mp.put(save, val+1);
			if (save >= 100) {count++;}
			resetGrid();
		}


		out.print("**j_ans: ");
		out.print(count);
		out.println("");
	}
	static void copyGrid() {
		gridTmp = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
	}
	static void resetGrid() {
		grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
	}
	static int next(int x, int y, int ex, int ey, int path, Vector <Tuple<Integer, Integer>> ve) {

		if (x < 0 || y < 0 || ex > lenx-1 || ey > leny -1 || grid[y][x] == '#') {return 55;}

		if (x == ex && y == ey) {
			if (path < minPath) {minPath = path; for (var pa: ve) {se.add(pa);}return 22;}
			return 22;
		}

		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			ve.add(new Tuple(x,y));
			if (22 == next(x, y-1, ex, ey, path+1, ve)) {return 22;}
			if (22 == next(x+1, y, ex, ey, path+1, ve)) {return 22;}
			if (22 == next(x, y+1, ex, ey, path+1, ve)) {return 22;}
			if (22 == next(x-1, y, ex, ey, path+1, ve)) {return 22;}
		}
		return 56;
	}
	static void nextNoVec(int x, int y, int ex, int ey, int path, int noCheatPath) {
		if (path > noCheatPath) {return;}

		if (x < 0 || y < 0 || ex > lenx-1 || ey > leny -1 || grid[y][x] == '#') {return;}

		if (x == ex && y == ey) {
			if (path < minPath) {minPath = path;}
			return;
		}

		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			nextNoVec(x, y-1, ex, ey, path+1, noCheatPath);
			nextNoVec(x+1, y, ex, ey, path+1, noCheatPath);
			nextNoVec(x, y+1, ex, ey, path+1, noCheatPath);
			nextNoVec(x-1, y, ex, ey, path+1, noCheatPath);
		}
	}


}

class Tuple<X,Y > {
	public X first;
	public Y second;

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

@SuppressWarnings("unchecked")
class TreTuple<X,Y, Z> {
	public X first;
	public Y second;
	public Z third;

	public TreTuple(Object o) {
		TreTuple tu2 = (TreTuple) o;
		this.first = (X)tu2.first;
		this.second = (Y)tu2.second;
		this.third = (Z)tu2.third;
	}
	public TreTuple(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTuple tu2 = (TreTuple) o;
		if (this == o) return true;
		if (!(o instanceof TreTuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}
		if (!this.third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}

@SuppressWarnings("unchecked")
class QuadTuple<X,Y, Z, W> {
	public X first;
	public Y second;
	public Z third;
	public W fourth;

	public QuadTuple(X first, Y second, Z third, W fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	@Override
	public boolean equals(Object o) {
		QuadTuple tu2 = (QuadTuple) o;
		if (this == o) return true;
		if (!(o instanceof QuadTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth);
	}

}

@SuppressWarnings("unchecked")
class CinqTuple<X,Y, Z, V, W> {
	public X first;
	public Y second;
	public Z third;
	public V fourth;
	public W fifth;

	public CinqTuple(X first, Y second, Z third, V fourth, W fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}
	@Override
	public boolean equals(Object o) {
		CinqTuple tu2 = (CinqTuple) o;
		if (this == o) return true;
		if (!(o instanceof CinqTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		if (!fifth.equals(tu2.fifth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth, fifth);
	}

}

