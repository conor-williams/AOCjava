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
import java.util.PriorityQueue;

@SuppressWarnings("unchecked")
class year2021_day15_2 {
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int already [][] = new int[leny][lenx];
	public static int minRisk = 99999;

	public static Vector<Vector <Tuple <Integer, Integer>>> vepaths = new Vector<>();
	public static void main(String [] args) {
		out.println("		2021 Day15.2");
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
		already = new int[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}
		//out.println(leny*5);
		//out.println(lenx*5);
		char gridTmp [][] = new char[leny*5][lenx*5];
		for (int iy = 0; iy < 5; iy++) {
			for (int ix = iy, ixO = 0; ix < iy+5; ix++, ixO++) {
				for (int yy = 0; yy < leny; yy++) {
					for (int xx = 0; xx < lenx; xx++) {
						int val = grid[yy][xx] - '0';
						val += ix;
						if (val >= 10) {
							val -= 9;
						}
						//out.print(yy+(iy*leny)); out.print(" "); out.println(xx+(ix*lenx));
						gridTmp[yy+(iy*leny)][xx+(ixO*lenx)] = (char)(val + '0');
					}
				}
			}
		}
		
		lenx *= 5;
		leny *= 5;
		grid = new char[leny][lenx];
		grid =	gridTmp;
		already = new int[leny][lenx];

		
		/*
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				for (int ii = 1; ii < 5; ii++) {
					int val = grid[yy][xx] - '0';
					if (val == 10) {val = 1;}
					char vv = val+'0';
					grid[yy][xx+(ii*lenx)] = vv;
				}
			}
		}
		for (int iy = 1; iy < 5; iy++) {
			for (int yy = 0+(iy*leny); yy < (iy*leny); yy++) {
				for (int xx = 0; xx < lenx; xx++) {
					int val = grid[yy][xx] - '0';
					for (int ix = 1; iix < 5; iix++) {
						if (val == 10) {val = 1;}
						char vv = val+'0';
						grid[yy+
				}
			}
		}
		*/


		int test1 = 0;
		int xx7 = 0;
		for (int yy7 = 0; yy7 < leny; yy7++) {
			test1+=grid[yy7][xx7] - '0';
		}
		int yy7 = leny-1;
		for (int xx8 = 1; xx8 < lenx; xx8++) {
			test1+= grid[yy7][xx8] - '0';
		}
		minRisk = test1;

		//var allPaths = findAllPaths(grid);
		int start[] = {0,0};
		int end[]  = {lenx-1, leny-1};
		//List<List<int[]>> paths = findAllPathsBFS(start, end);
		//findAllPathsBFS(start, end);

		//findAllPathsAStarred(start, end);
		next(0, 0, lenx-1, leny-1, 0, grid[0][0]-'0');
		//		System.setOut(originalOut);
		//next(0, 0, lenx-1, leny-1, 0, 0);
		out.print("**j_ans: ");
		out.print(minRisk-(grid[0][0]-'0'));
		out.println("");

	}

	public static void findAllPathsAStarred(int[] start, int[] end) {
		//List<List<Node>> allPaths = new ArrayList<>();
		//PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));
		PriorityQueue<Node> openSet = new PriorityQueue<>();
		boolean[][] closedSet = new boolean[grid.length][grid[0].length];

		openSet.add(new Node(start[0], start[1], grid[start[0]][start[1]] - '0', null));
		//openSet.add(new Node(start[0], start[1], 0, null));

		while (!openSet.isEmpty()) {
			Node current = openSet.poll();

			if (current.x == end[0] && current.y == end[1]) {
				//allPaths.add(reconstructPath(current));
				out.println("END..."); out.println(current.risk);
				int risk = current.risk;// + (grid[current.y][current.x] - '0');
				if (risk < minRisk) {minRisk = risk;out.println(risk);
					var li = reconstructPath(current);	

					for (int yy = 0; yy < leny; yy++) {
						for (int xx = 0; xx < lenx; xx++) {

							int fo = 0;
							for (int ii = 0; ii < li.size(); ii++) {
								if (li.get(ii).x == xx &&
										li.get(ii).y == yy) {
									out.print("*");
									fo = 1;
									break;
								}
							}
							if (fo == 0) {
								out.print(grid[yy][xx]);
							}

						}
						out.println();
					}
					out.println();


				}

				continue; // Continue to find other paths
			}

			closedSet[current.y][current.x] = true;

			for (int[] dir : new int[][]{{0, 1}, {1, 0}/*, {0, -1}, {-1, 0}*/}) {
				int newX = current.x + dir[0];
				int newY = current.y + dir[1];

				if (isValid(newX, newY) && !closedSet[newY][newX]) {
					int risk = current.risk + (grid[newY][newX] - '0');
					if (risk < minRisk) {
						openSet.add(new Node(newX, newY, risk, current));
					}
				}
			}
		}
	}
	private static List<Node> reconstructPath(Node node) {
		List<Node> path = new ArrayList<>();
		while (node != null) {
			path.add(node);
			node = node.parent;
		}
		Collections.reverse(path);
		return path;
	}


	/*
	   private static int heuristic(int[] current, int[] end) {
	   return Math.abs(current[0] - end[0]) + Math.abs(current[1] - end[1]); // Manhattan distance
	   }
	   */
	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < lenx && y < leny;
	}

	//public static List<List<int[]>> findAllPathsBFS(int[] start, int[] end) 
	/*
	   public static void findAllPathsBFS(int[] start, int[] end) {
	   List<List<int[]>> allPaths = new ArrayList<>();
	   int rows = grid.length, cols = grid[0].length;
	   boolean[][] visited = new boolean[rows][cols];
	///Queue<Node> queue = new LinkedList<>();
	PriorityQueue<Node> queue = new PriorityQueue<>();

	queue.add(new Node(start[0], start[1], new ArrayList<>(), grid[0][0]-48));
	visited[start[0]][start[1]] = true;

	int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up

	while (!queue.isEmpty()) {
	Node current = queue.poll();

	if (current.x == end[0] && current.y == end[1]) {
	int risk = current.risk + grid[current.x][current.y] - '0';
	if (risk < minRisk) {minRisk = risk;}
	//allPaths.add(current.path);
	continue;
	}

	for (int[] dir : directions) {
	int newX = current.x + dir[0];
	int newY = current.y + dir[1];
	if (newX >= 0 && newX < 4 && newY >= 0 && newY < 4) {
	int risk = current.risk + grid[newX][newY] - '0';
	if (risk < minRisk) {
	if (!current.path.stream().anyMatch(arr -> Arrays.equals(arr, new int[]{newX, newY}))) {
	queue.add(new Node(newX, newY, current.path, risk));
	}
	}
	}
	}
	}
	   }

	   public static List<List<int[]>> findAllPathsDFS(char[][] grid) {
	   int rows = grid.length, cols = grid[0].length;
	   int[] start = new int[]{0,0};//findPoint(grid, 'S');
	   int[] finish = new int[]{leny-1, lenx-1};//findPoint(grid, 'F');
	   List<List<int[]>> result = new ArrayList<>();
	   boolean[][] visited = new boolean[rows][cols];

	   dfs(grid, start[0], start[1], finish, visited, new ArrayList<>(), result, 0);
	   return result;
	   }
	   */

	/*
	   private static void dfs(char[][] grid, int x, int y, int[] finish, boolean[][] visited,
	   List<int[]> path, List<List<int[]>> result, int risk) {
	// Base cases

	if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length ||
	visited[x][y]) {
	return;
	}

	risk += grid[x][y] - '0';
	if (risk >= minRisk) {return;}
	visited[x][y] = true;

	if (x == finish[0] && y == finish[1]) {
	out.println("found end");

	if (risk < minRisk) {
	minRisk = risk;
	out.print("minRisk: "); 
	out.println(minRisk);
	}
	} else {
	// Explore all 4 possible directions
	dfs(grid, x + 1, y, finish, visited, path, result, risk); // Down
	dfs(grid, x - 1, y, finish, visited, path, result, risk); // Up
	dfs(grid, x, y + 1, finish, visited, path, result, risk); // Right
	dfs(grid, x, y - 1, finish, visited, path, result, risk); // Left
	}

	// Backtrack
	//path.remove(path.size() - 1);
	visited[x][y] = false;
	   }

*/
	/*
	   private static int[] findPoint(char[][] grid, char target) {
	   for (int i = 0; i < grid.length; i++) {
	   for (int j = 0; j < grid[0].length; j++) {
	   if (grid[i][j] == target) {
	   return new int[]{i, j};
	   }
	   }
	   }
	   throw new IllegalArgumentException("Target point not found in the grid.");
	   }
	   */
	public static void next(int x, int y, int ex, int ey, int path, int risk) {
		if (x < 0 || y < 0 || x > lenx-1 || y > leny-1) {return;}

		if (risk >= minRisk) {return;}
		if (x == ex && y == ey) {
			//out.println(risk);
			if (risk < minRisk) {minRisk = risk; }
		} 
		
		if (already[y][x] == 0 || risk < already[y][x]) {
			already[y][x] = risk;
			if (y-1 >= 0) {
			next(x, y-1, ex, ey, path+1, risk+grid[y-1][x] - '0');
			}
			if (x+1 < lenx) {
			next(x+1, y, ex, ey, path+1, risk+grid[y][x+1] - '0');
			}
			if (y+1 < leny) {
			next(x, y+1, ex, ey, path+1, risk+grid[y+1][x] - '0');
			}
			if (x-1 >= 0) {
			next(x-1, y, ex, ey, path+1, risk+grid[y][x-1] - '0');
			}
		}

	}
}

class Node implements Comparable<Node> {
	int x, y;
	int risk;
	Node parent;

	Node(int x, int y, int risk, Node parent) {
		this.x = x;
		this.y = y;
		this.risk = risk;
		this.parent = parent;
	}
	@Override
	public int compareTo(Node other) {
		return Integer.compare(this.risk, other.risk);
		//return this.risk > other.risk;
	}

}


@SuppressWarnings("unchecked")
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

