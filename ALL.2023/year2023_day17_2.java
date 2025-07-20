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
class year2023_day17_2 {
	public static int minPath = 99999;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day17.2");
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

		int start[] = {0,0};
		int end[]  = {lenx-1, leny-1};
		minPath = lenx*leny*9;
		findAllPathsAStarred(start, end);

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
	}

	public static void findAllPathsAStarred(int[] start, int[] end) {
		//List<List<Node>> allPaths = new ArrayList<>();
		//PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));
		Set<CinqTuple<Integer, Integer, Integer, Integer, Integer>> se = new HashSet<>();
		PriorityQueue<Node> openSet = new PriorityQueue<>();
		boolean[][] closedSet = new boolean[grid.length][grid[0].length];

		openSet.add(new Node(0, start[0], start[1], 0, 1, 0, null));
		openSet.add(new Node(0, start[0], start[1], 1, 0, 0, null));
		//openSet.add(new Node(start[0], start[1], 0, null));

		//out.println(minPath);
		while (!openSet.isEmpty()) {
			Node current = openSet.poll();
			
			if (current.path > minPath) {continue;}
			CinqTuple <Integer, Integer, Integer, Integer, Integer> tu1 =
				new CinqTuple(current.x, current.y, current.dirX, current.dirY, current.ste);
			if (se.contains(tu1)) {continue;}
			se.add(tu1);
			//out.print(current.x); out.print(" "); out.println(current.y);

			if (current.x == end[0] && current.y == end[1]) {
				//allPaths.add(reconstructPath(current));
				//out.print("END: "); out.print("current.path: "); out.println(current.path);
				//out.println(current.x); out.println(current.y);
				//out.println(current.path);
				if (current.path < minPath && current.ste >= 4 && current.ste <= 10) {
					minPath = current.path;
					continue;
				}
				continue; // Continue to find other paths
			}


			for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
				int go = 0;
				int nx = 0, ny = 0, numSte = 0;
				if (dir[0] - current.dirX == 0 && dir[1] - current.dirY == 0) {
					if (current.ste < 10) {
						//same dir
						nx = current.x + current.dirX;
						ny = current.y + current.dirY;
						numSte = current.ste+1;

					}
				} else if (dir[0]+current.dirX == 0 && dir[1]+current.dirY == 0) {
					//opposite dir
					continue;
				} else {
					if (current.ste >= 4 && current.ste <= 10) {
						nx = current.x + dir[0];
						ny = current.y + dir[1];
						numSte = 1;
					}
				}

				if (nx < 0 || nx > lenx-1 || ny < 0 || ny > leny-1) {
					continue;
				} else {
					openSet.add(new Node(current.path+(grid[ny][nx]-48), nx, ny, dir[0], dir[1], numSte, current));
				}
			}
		}
	}

	public static class Node implements Comparable<Node> {

		int path;
		int x, y;
		int dirX;
		int dirY;
		int ste;
		Node parent;

		Node(int path, int x, int y, int dirX, int dirY, int ste, Node parent) {
			this.path = path;
			this.x = x;
			this.y = y;
			this.dirX = dirX;
			this.dirY = dirY;
			this.ste = ste;
			this.parent = parent;
		}
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.path, other.path);
			//return this.risk > other.risk;
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

