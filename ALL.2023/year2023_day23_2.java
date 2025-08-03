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
import java.util.stream.Collectors;

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
class year2023_day23_2 {
	public static int maxPath = 0;
	public static int minPath = 99999;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int already [][] = new int[leny][lenx];
	static int sx, sy, ex, ey;
	static Vector <Tuple <Integer, Integer>> allNodes = new Vector<>();
	static Map <Tuple <Integer, Integer>, Vector<TreTuple <Integer, Integer, Integer>>> mp = new HashMap<>();

	public static void main(String [] args) {
		out.println("		2023 Day23.2");
		out.println("	SLOW ~30seconds");
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
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		grid = new char[leny][lenx];
		already = new int[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}


		for (int yy = 0; yy < leny-1; yy++) {
			for (int xx = 0; xx < lenx-1; xx++) {
				if (grid[yy][xx] == '^' || grid[yy][xx] == '>' || grid[yy][xx] == 'v' || grid[yy][xx] == '<') {
					grid[yy][xx] = '.';
				}
			}
		}

		sx = 1;
		sy = 0;
		ex = lenx-2;
		ey = leny-1;

		for (int yy = 1; yy < leny-1; yy++) {
			for (int xx = 1; xx < lenx-1; xx++) {
				if ((xx == sx && yy == sy) || (xx == ex && yy == ey)) {
				} else {
					deadend(xx, yy);
				}
			}
		}

		out.println("after deadend sealing");

		allNodes = new Vector<>();
		allNodes.add(new Tuple(1, 0));
		allNodes.add(new Tuple(lenx-2, leny-1));
		{
			int count44 = 0, count33 = 0, count22 = 0, count11 = 0;
			for (int yy = 1; yy < leny-2; yy++) {
				for (int xx = 1; xx < lenx-2; xx++) {

					if (grid[yy][xx] != '#') {
						int count1 = 0;
						if (grid[yy-1][xx] != '#') {count1++;}
						if (grid[yy][xx+1] != '#') {count1++;}
						if (grid[yy+1][xx] != '#') {count1++;}
						if (grid[yy][xx-1] != '#') {count1++;}
						if (count1 == 1) {count11++;}
						if (count1 == 2) {count22++;}
						if (count1 == 3) {count33++; allNodes.add(new Tuple(xx, yy));}
						if (count1 == 4) {count44++; allNodes.add(new Tuple(xx, yy));}
					}
				}
			}
			//out.print("count11: "); out.println(count11);
			//out.print("count22: "); out.println(count22);
			//out.print("count33: "); out.println(count33);
			//out.print("count44: "); out.println(count44);
		}


		int pos1 = 0;
		for (var tu1: allNodes) {
			pos1++;
			int start[] = {tu1.first, tu1.second};
			if (mp.get(new Tuple(tu1.first, tu1.second)) == null) {
				Vector <TreTuple<Integer, Integer, Integer>> ve1 = new Vector<>();
				mp.put(new Tuple(tu1.first, tu1.second), ve1);
			}
			var ve2 = mp.get(new Tuple(tu1.first, tu1.second));
			int pos2 = 0;
			for (var tu2:  allNodes) {
				pos2++;
				if (pos1 == pos2) {continue;}
				int end[] = {tu2.first, tu2.second};

				int dist = 0;
				minPath = 99999;
				if ((dist = findAllPathsAStarred(start, end)) != -1) {
					ve2.add(new TreTuple(tu2.first, tu2.second, dist));
				} else {
				}
			}
			mp.put(new Tuple(tu1.first, tu1.second), ve2);
		}

		/*
		for (var en: mp.entrySet()) {
			out.print(en.getKey().first); out.print(" "); out.println(en.getKey().second);
			var xx = en.getValue();
			for (int ii = 0; ii < xx.size(); ii++) {
				out.print(xx.get(ii).first); out.print(" "); out.print(xx.get(ii).second); out.print(" "); out.println(xx.get(ii).third);
			}
			out.println("--------");
		}
		*/
		
		int start2[] = {1, 0};
		int end2[] = {lenx-2, leny-1};
		find2(start2, end2);

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxPath);
		out.println("");
	}
	static class Node2 implements Comparable<Node2> {
		int x, y;
		int path;
		Vector <Tuple <Integer, Integer>> already;
		//Node parent;

		Node2(int x, int y, int path, Vector <Tuple<Integer, Integer>> already ) {
			this.x = x;
			this.y = y;
			this.path = path;
			this.already = new Vector(already);
		}
		@Override
		public int compareTo(Node2 other) {
			return Integer.compare(other.path, this.path);
		}

	}

	public static void find2(int[] start, int[] end) {
		PriorityQueue<Node2> openSet = new PriorityQueue<>();
		//boolean[][] closedSet = new boolean[grid.length][grid[0].length];

		Vector <Tuple <Integer, Integer>> ve = new Vector<>();
		ve.add(new Tuple(start[0], start[1]));
		openSet.add(new Node2(start[0], start[1], 0, ve));

		while (!openSet.isEmpty()) {
			Node2 current = openSet.poll();

			if (current.x == end[0] && current.y == end[1]) {
				if (current.path > maxPath) {maxPath = current.path; }
				continue;

			}

			var xx = mp.get(new Tuple(current.x, current.y));
			for (int ii = 0; ii < xx.size(); ii++) {

				if (!current.already.contains(new Tuple(xx.get(ii).first, xx.get(ii).second))) {
					var vec = new Vector(current.already);
					vec.add(new Tuple(xx.get(ii).first, xx.get(ii).second));
					openSet.add(new Node2(xx.get(ii).first, xx.get(ii).second, current.path+xx.get(ii).third, vec));
				}
			}

		}
	}
	public static void deadend(int xx, int yy) {
		if (grid[yy][xx] == '#') {return;}
		if ((xx == sx && yy == sy) || (xx == ex && yy == ey)) {return;}
		Vector <Integer> dirs = new Vector<>();
		if (grid[yy-1][xx] != '#') {
			dirs.add(0);
		}
		if (grid[yy][xx+1] != '#') {
			dirs.add(1);
		}
		if (grid[yy+1][xx] != '#') {
			dirs.add(2);
		}
		if (grid[yy][xx-1] != '#') {
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



	static class Node implements Comparable<Node> {
		int x, y;
		int dir;
		int path;
		//Node parent;

		Node(int x, int y, int path, int dir/*, Node parent*/) {
			this.x = x;
			this.y = y;
			this.path = path;
			this.dir = dir;
			//this.parent = parent;
		}
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.path, other.path);

			//return this.risk > other.risk;
		}

	}

	public static int findAllPathsAStarred(int[] start, int[] end) {
		PriorityQueue<Node> openSet = new PriorityQueue<>();
		boolean[][] closedSet = new boolean[grid.length][grid[0].length];

		openSet.add(new Node(start[0], start[1], 0, 0/*, null*/));
		openSet.add(new Node(start[0], start[1], 0, 1/*, null*/));
		openSet.add(new Node(start[0], start[1], 0, 2/*, null*/));
		openSet.add(new Node(start[0], start[1], 0, 3/*, null*/));

		int found = 0;
		while (!openSet.isEmpty()) {
			Node current = openSet.poll();

			if (current.x == end[0] && current.y == end[1]) {
				found = 1;
				out.println("END..."); out.println(current.path);
				if (current.path < minPath) {minPath = current.path; /*out.println(current.path);*/}
				continue;
			}
			if (grid[current.y][current.x] == '#') {continue;}
			if (current.x == start[0] && current.y == start[1]) {}
			else if (allNodes.contains(new Tuple(current.x, current.y))) {continue;}

			closedSet[current.y][current.x] = true;

			int newDir = -1;
			for (int[] dir : new int[][]{{0, -1}, {1, 0}, {0, 1}, {-1, 0}}) {
				newDir++;
				int newX = current.x + dir[0];
				int newY = current.y + dir[1];

				if (newX >= 0 && newX < lenx && newY >= 0 && newY < leny && !closedSet[newY][newX]) {
					if (grid[newY][newX] == '#') {continue;}
					if (newDir != 0 && grid[newY][newX] == '^') {continue;}
					if (newDir != 1 && grid[newY][newX] == '>') {continue;}
					if (newDir != 2 && grid[newY][newX] == 'v') {continue;}
					if (newDir != 3 && grid[newY][newX] == '<') {continue;}
					openSet.add(new Node(newX, newY, current.path+1, newDir/*, current*/));
				}

			}
		}
		if (found == 0) {return -1;}
		if (found == 1) {return minPath;}
		return -1;
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

	/*
	   public static void next(int x, int y, int ex, int ey, int path, int dir) {
	   if (x >= lenx || x < 0 || y>=leny || y < 0 || grid[y][x] == '#') {return;}

	   if (x == ex && y == ey) {
	   out.print("found end"); out.println(path);
	   if (path > maxPath) {out.println("max..."); maxPath = path;}
	   return;
	   }
	   if (dir != 0 && grid[y][x] == '^') {return;}
	   if (dir != 1 && grid[y][x] == '>') {return;}
	   if (dir != 2 && grid[y][x] == 'v') {return;}
	   if (dir != 3 && grid[y][x] == '<') {return;}

	   if (already[y][x] == 0 || path > already[y][x]) {
	   already[y][x] = path;
	   next(x, y-1, ex, ey, path+1, 0);
	   next(x+1, y, ex, ey, path+1, 1);
	   next(x, y+1, ex, ey, path+1, 2);
	   next(x-1, y, ex, ey, path+1, 3);
	   }

	   }
	   */
		/*
		   List<List<Integer>> g = new ArrayList<>();
		   int v = mp.size();
		   for (int ii = 0; ii < 4; ii++) {
		   g.add(new ArrayList<>());
		   }
		   */

		/*
		   int maxPath = 0;
		   int start[] = {1, 0};
		   int end[] = {lenx-2, leny-1};
		   findAStarred2(start, end);
		   */
		//out.println("here3");
		/*
		   int start[] = {1, 0};
		   int end[] = {lenx-2, leny-1};
		   findAllPathsAStarred(start, end);
		   */

		/*
		   out.println(grid[leny-1][lenx-2]);
		   next(1, 0, lenx-2, leny-1, 0, 2);
		   */

	/*
	static void findpaths(List<List<Integer> > g, int src, int dst, int v)
	{

		// Create a queue which stores
		// the paths
		Queue<List<Integer> > queue = new LinkedList<>();

		// Path vector to store the current path
		List<Integer> path = new ArrayList<>();
		path.add(src);
		queue.offer(path);

		while (!queue.isEmpty())
		{
			path = queue.poll();
			int last = path.get(path.size() - 1);

			// If last vertex is the desired destination
			// then print the path
			if (last == dst)
			{
				printPath(path);
				continue;
			}

			// Traverse to all the nodes connected to
			// current vertex and push new path to queue
			List<Integer> lastNode = g.get(last);
			for(int i = 0; i < lastNode.size(); i++)
			{
				if (isNotVisited(lastNode.get(i), path))
				{
					List<Integer> newpath = new ArrayList<>(path);
					newpath.add(lastNode.get(i));
					queue.offer(newpath);
				}
			}
		}
	}
	*/

