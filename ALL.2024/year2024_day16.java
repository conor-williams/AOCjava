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


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2024_day16 {
	public static int minPath = Integer.MAX_VALUE;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int sx = -1;
	public static int sy = -1;
	public static int ex = -1;
	public static int ey = -1;

	public static void main(String [] args) {
		out.println("		2024 Day16.1");
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
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}

after:
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == 'S') {
					sx = x; sy = y;
					grid[y][x] = '.';
					if (sy != -1 && ey != -1) {break after;}
				} else if (grid[y][x] == 'E') {
					ex = x; ey = y;
					grid[y][x] = '.';
					if (sy != -1 && ey != -1) {break after;}
				}
			}
		}

		for (int yy = 1; yy < leny-1; yy++) {
			for (int xx = 1; xx < lenx-1; xx++) {
				if ((xx == sx && yy == sy) || (xx == ex && yy == ey)) {
				} else {
					deadend(xx, yy);
				}
			}
		}
		next5(sx, sy, ex, ey);

		//System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
	}



	static class Node2 implements Comparable<Node2> {
		int x, y;
		int dir;
		int path;

		Node2(int x, int y, int dir, int path) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.path = path;
		}
		@Override
		public int compareTo(Node2 other) {
			return Integer.compare(this.path, other.path);
		}
	};

	static void next5(int x, int y, int ex, int ey) {
		PriorityQueue<Node2> Q = new PriorityQueue<>();

		Vector<TreTuple<Integer, Integer, Integer>> seen1 = new Vector<>();
		Q.add(new Node2(x, y, 1, 0));

		while (!Q.isEmpty()) {
			var current = Q.poll();

			if (seen1.contains(new TreTuple(current.x, current.y, current.dir))) {continue;}
			seen1.add(new TreTuple(current.x, current.y, current.dir));
			//out.print(current.x); out.print(" "); out.println(current.y);

			if (current.x == ex && current.y == ey) {
				/*
				if (ex == sx && ey == sy && (current.dir == 0 || current.dir == 2)) {
					current.path += 1000;
				}
				out.println("found end.....");
				*/
				minPath = current.path;
				break;

			}

			int ignoreDir = (((current.dir-2) + 4) % 4);
			for (int i = 0; i < 4; i++) {
				if (i == ignoreDir) {continue;}
				int newPath = current.path;
				if (Math.abs(current.dir - i) == 1 || Math.abs(current.dir -i) == 3) {
					newPath+=1000;
				}

				if (i == 0 && grid[current.y-1][current.x] != '#') {
					Q.add(new Node2(current.x, current.y-1, i, newPath+1));
				} else if (i == 1 && grid[current.y][current.x+1] != '#') {
					Q.add(new Node2(current.x+1, current.y, i, newPath+1));
				} else if (i == 2 && grid[current.y+1][current.x] != '#') {
					Q.add(new Node2(current.x, current.y+1, i, newPath+1));
				} else if (i == 3 && grid[current.y][current.x-1] != '#') {
					Q.add(new Node2(current.x-1, current.y, i, newPath+1));
				}
			}
		}
	}

	static void deadend(int xx, int yy) {
		if (grid[yy][xx] == '#') {return;}
		if ((xx == sx && yy == sy) || (xx == ex && yy == ey)) {return;}
		Vector <Integer> dirs = new Vector<>();
		if (grid[yy-1][xx] == '.') {
			dirs.add(0);
		}
		if (grid[yy][xx+1] == '.') {
			dirs.add(1);
		}
		if (grid[yy+1][xx] == '.') {
			dirs.add(2);
		}
		if (grid[yy][xx-1] == '.') {
			dirs.add(3);
		}
		if (dirs.size() == 1) {
			grid[yy][xx] = '#';
			if (yy-1 >0) {
				deadend(xx, yy-1);
			}
			if (xx+1 < lenx+1) {
				deadend(xx+1, yy);
			}
			if (yy+1 < leny+1) {
				deadend(xx, yy+1);
			}
			if (xx-1 > 0) {
				deadend(xx-1, yy);
			}
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

