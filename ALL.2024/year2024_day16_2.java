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
// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2024_day16_2 {
	public static int minPath = Integer.MAX_VALUE;
	public static int minSteps = Integer.MAX_VALUE;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int already [][] = new int[leny][lenx];
	public static int minOrig = Integer.MAX_VALUE;

	public static int startx = 0;
	public static int starty = 0;
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static Set <Tuple <Integer, Integer>> se = new HashSet<>();
	public static int sx = 0;
	public static int sy = -1;
	public static int ex = 0;
       	public static int ey = -1;
	public static void main(String [] args) {
		out.println("		2024 Day16.2");
		out.println("	SLOW ~5minutes 30seconds");
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

		//int sx = 0, sy = -1, ex = 0, ey = -1;
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
		startx = sx;
		starty = sy;

		for (int yy = 1; yy < leny-1; yy++) {
			for (int xx = 1; xx < lenx-1; xx++) {
				if ((xx == sx && yy == sy) || (xx == ex && yy == ey)) {
				} else {
					deadend(xx, yy);
				}
			}
		}

		out.println("after deadend sealing");
		next(sx, sy, ex, ey, 0, 1, 1, 0);
		out.println(minPath);
		out.println(minSteps);
		Vector <Tuple <Integer, Integer>> ve = new Vector<>();
		already = new int[leny][lenx];
		minOrig = minPath;
		next2(sx, sy, ex, ey, 0, 1, 1, 0, ve);
		//Arrays.stream(already).forEach(row -> Arrays.fill(row, 0));

		int count = 0;
		for (int yy = 1; yy < leny-1; yy++) {
			for (int xx = 1; xx < lenx-1; xx++) {
				if (grid[yy][xx] == '#') {
					continue;
				}
				if (se.contains(new Tuple(xx, yy)) ) {
					continue;
				}
				//Arrays.stream(already).forEach(row -> Arrays.fill(row, 0));
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
				//if (dirs.size() == 1) {out.println("dead end"); continue;}
				var dirsOrig = new Vector(dirs);
				count++;
				do {
					doit(xx, yy, sx, sy, 0, dirs.get(0), dirs.get(0)); 
					int one = minPath;

					doit(xx, yy, ex, ey, 0, dirs.get(1), dirs.get(1));
					one += minPath;
					int dirsdiff = Math.abs(dirs.get(0) - dirs.get(1));
					if (dirsdiff == 1 || dirsdiff == 3) {
						one+=1000;
					}
					if (one == minOrig) {
						se.add(new Tuple(xx, yy));
						if (dirs.size() == 2) {
							checkit2(dirs.get(0), dirs.get(1), xx, yy);
						}
						break;
					}
					nextPermutation(dirs);
				} while (!dirs.equals(dirsOrig));
			}
		}

		out.print("searched: "); out.println(count);
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(se.size());
		out.println("");
	}
	public static void deadend(int xx, int yy) {
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
	public static void nextPermutation(Vector <Integer> nums) {
		int i = nums.size() - 2;
		while (i >= 0 && nums.get(i) >= nums.get(i + 1)) {
			i--;
		}
		if (i >= 0) {
			int j = nums.size() - 1;
			while (j >= 0 && nums.get(j) <= nums.get(i)) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

        private static void swap(Vector <Integer> nums, int i, int j) {
                int temp = nums.get(i);
                nums.set(i, nums.get(j));
                nums.set(j, temp);
        }

        private static void reverse(Vector <Integer> nums, int start) {
                int end = nums.size() - 1;
                while (start < end) {
                        swap(nums, start, end);
                        start++;
                        end--;
                }
        }

	public static void checkit2(int dir1, int dir2, int xx, int yy) {
		if (Math.abs(dir1 - dir2) == 2) {
			if (dir1 == 1 || dir2 == 3) {
				/*
				for (int xx2 = xx-1; xx2 > 0; xx2--) {
					if (checkit(xx2, yy) == 0) {
						break;
					}
				}
				*/

				for (int xx2 = xx+1; xx2 < lenx-1; xx2++) {
					if (checkit(xx2, yy) == 0) {
						break;
					}
				}
			} else if (dir1 == 0 || dir1 == 2) {
				/*
				for (int yy2 = yy-1; yy2 > 0; yy2--) {
					if (checkit(xx, yy2) == 0) {
						break;
					}
				}
				*/
				for (int yy2 = yy+1; yy2 < leny-1; yy2++) {
					if (checkit(xx, yy2) == 0) {
						break;
					}
				}
			}

		}
	}
	public static int checkit(int xx, int yy) {
		if (grid[yy][xx] == '#') {
			return 0;
		}
		Vector <Integer> dirs2 = new Vector<>();
		if (grid[yy-1][xx] == '.') {
			dirs2.add(0);
		} 
		if (grid[yy][xx+1] == '.') {
			dirs2.add(1);
		} 
		if (grid[yy+1][xx] == '.') {
			dirs2.add(2);
		} 
		if (grid[yy][xx-1] == '.') {
			dirs2.add(3);
		}
		if (dirs2.size() > 2) {
			return 0;
		}
		se.add(new Tuple(xx, yy));
		return 1;
	}
	static class dirs {
		int north;
		int south;
		int east;
		int west;
	}

	static class Node implements Comparable<Node> {
		int x, y;
		int path;
		int dir;
		Node parent;

		Node(int x, int y, int path, int dir, Node parent) {
			this.x = x;
			this.y = y;
			this.path = path;
			this.dir = dir;
			this.parent = parent;
		}
		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.path, other.path);
		}

	}
	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < lenx && y < leny;
	}

	public static void findAllPathsBFS(int[] start, int[] end) {
		List<List<int[]>> allPaths = new ArrayList<>();
		int rows = grid.length, cols = grid[0].length;
		boolean[][] visited = new boolean[rows][cols];
		///Queue<Node> queue = new LinkedList<>();
		PriorityQueue<Node> queue = new PriorityQueue<>();

		queue.add(new Node(start[0], start[1], 0, 1, null));
		//queue.add(new Node(start[0], start[1], new ArrayList<>(), grid[0][0]-48));
		visited[start[1]][start[0]] = true;

		int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up

		while (!queue.isEmpty()) {
			Node current = queue.poll();

			if (current.x == end[0] && current.y == end[1]) {
				int path = current.path;
				out.println("end reached...");
				out.print(path); out.print(" V "); out.println(minPath);
				if (path == minPath) {out.println("got an end with minPath"); }
				continue;
			}

			visited[current.y][current.x] = true;
			for (int[] dir : directions) {
				int newX = current.x + dir[0];
				int newY = current.y + dir[1];
				if (grid[newY][newX] == '#') {continue;}
				int path = current.path;
				int dirdir = 0;
				if (dir[0] == 0 && dir[1] == 1) {
					dirdir = 2;
				} else if (dir[0] == 1 && dir[1] == 0) {
					dirdir = 1;
				} else if (dir[0] == 0 && dir[1] == -1) {
					dirdir = 0;
				} else if (dir[0] == -1 && dir[1] == 0) {
					dirdir = 3;
				}

				if (isValid(newX, newY)) {// && !visited[newY][newX]) 
					if (Math.abs(dirdir - current.dir) == 1 || Math.abs(dirdir - current.dir) == 3) {path += 1000;}
					//int risk = current.risk + (grid[newY][newX] - '0');
					if (path < minPath) {
						queue.add(new Node(newX, newY, path+1, dirdir, current));
					}
				}


			}
		}
	}

	public static void findAllPathsAStarred(int[] start, int[] end) {
		PriorityQueue<Node> openSet = new PriorityQueue<>();
		boolean[][] closedSet = new boolean[grid.length][grid[0].length];

		openSet.add(new Node(start[0], start[1], 0, 1, null));
		//openSet.add(new Node(start[0], start[1], 0, null));

		while (!openSet.isEmpty()) {
			Node current = openSet.poll();

			if (current.x == end[0] && current.y == end[1]) {

				int path = current.path;
				out.println("end reached...");
				out.print(path); out.print(" V "); out.println(minPath);
				if (path == minPath) {out.println("got an end with minPath"); }

				continue; // Continue to find other paths
			}

			//closedSet[current.y][current.x] = true;

			for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
				int newX = current.x + dir[0];
				int newY = current.y + dir[1];
				if (grid[newY][newX] == '#') {continue;}
				int path = current.path;
				int dirdir = 0;
				if (dir[0] == 0 && dir[1] == 1) {
					dirdir = 2;
				} else if (dir[0] == 1 && dir[1] == 0) {
					dirdir = 1;
				} else if (dir[0] == 0 && dir[1] == -1) {
					dirdir = 0;
				} else if (dir[0] == -1 && dir[1] == 0) {
					dirdir = 3;
				}


				if (isValid(newX, newY)/* && !closedSet[newY][newX]*/) {
					if (Math.abs(dirdir - current.dir) == 1 || Math.abs(dirdir - current.dir) == 3) {path += 1000;}
					//int risk = current.risk + (grid[newY][newX] - '0');
					if (path < minPath) {
						openSet.add(new Node(newX, newY, path+1, dirdir, current));
					}
				}
			}

		}
	}
	public static int next4(int x, int y, int ex, int ey, int path, int dir, int prevDir) {
		if (path > minOrig) {return 0;}
		if (path > minPath) {return 0;}
		if (Math.abs(prevDir - dir) == 1 || Math.abs(prevDir -dir) == 3) {path += 1000;}
		if ((x < 1) || (y > (leny-2)) || (y < 1) || (x > (lenx -2))) {return 0;}
		if (grid[y][x] == '#') {return 0;}

		if (x == ex && y == ey) {
			if (ex == startx && ey == starty && (dir == 0 || dir == 2)) {
				path += 1000;
			}

			if (path < minPath) {minPath = path; }
			return 0;
		}
		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			next4(x, y-1, ex, ey, path+1, 0, dir);
			next4(x+1, y, ex, ey, path+1, 1, dir);
			next4(x, y+1, ex, ey, path+1, 2, dir);
			next4(x-1, y, ex, ey, path+1, 3, dir);
		}
		return 0;
	}
	public static int next3(int x, int y, int ex, int ey, int path, int dir, int prevDir, int steps, Vector <Tuple<Integer, Integer>> ve) {
		//if (steps > minSteps) {return 0;}
		if (path > minPath) {return 0;}
		if (path > minOrig) {return 0;}
		if (Math.abs(prevDir - dir) == 1 || Math.abs(prevDir -dir) == 3) {path += 1000;}
		if ((x < 0) || (y > (leny-1)) || (y < 0) || (x > (lenx -1))) {return 0;}
		if (grid[y][x] == '#') {return 0;}

		if (x == ex && y == ey) {
			if (path == minPath) {
				out.println("end reached is minPath");

				for (int ii = 0; ii < ve.size(); ii++) {
					var pa = ve.get(ii);
					se.add(new Tuple(pa.first, pa.second));
				}
				se.add(new Tuple(x, y));
				//out.println(se.size());
				//se.clear();
			}
			return 0;
		}
		if (already[y][x] == 0 || path < already[y][x]) {// steps <= already[y][x]) 
								 //already[y][x] = steps;
			already[y][x] = path;
			Vector <Tuple <Integer, Integer>> ve1 = new Vector(ve);
			Vector <Tuple <Integer, Integer>> ve2 = new Vector(ve);
			Vector <Tuple <Integer, Integer>> ve3 = new Vector(ve);
			Vector <Tuple <Integer, Integer>> ve4 = new Vector(ve);
			ve1.add(new Tuple(x, y));
			ve2.add(new Tuple(x, y));
			ve3.add(new Tuple(x, y));
			ve4.add(new Tuple(x, y));
			next3(x, y-1, ex, ey, path+1, 0, dir, steps+1, ve1);
			next3(x+1, y, ex, ey, path+1, 1, dir, steps+1, ve2);
			next3(x, y+1, ex, ey, path+1, 2, dir, steps+1, ve3);
			next3(x-1, y, ex, ey, path+1, 3, dir, steps+1, ve4);
		}
		return 0;
	}
	public static void doit(int x, int y, int ex, int ey, int path, int dir, int prevDir) {
		minPath = 999999;
		Arrays.stream(already).forEach(row -> Arrays.fill(row, 0));
		next4(x, y, ex, ey, 0, prevDir, dir); 
	}
	public static int next2(int x, int y, int ex, int ey, int path, int dir, int prevDir, int steps, Vector <Tuple<Integer, Integer>> ve) {
		//if (steps > minSteps) {return 0;}
		if (path > minPath) {return 0;}
		if (path > minOrig) {return 0;}
		if (Math.abs(prevDir - dir) == 1 || Math.abs(prevDir -dir) == 3) {path += 1000;}
		if ((x < 0) || (y > (leny-1)) || (y < 0) || (x > (lenx -1))) {return 0;}
		if (grid[y][x] == '#') {return 0;}

		if (x == ex && y == ey) {
			if (path == minPath) {
				out.println("end reached is minPath");

				for (int ii = 0; ii < ve.size(); ii++) {
					var pa = ve.get(ii);
					se.add(new Tuple(pa.first, pa.second));
				}
				se.add(new Tuple(x, y));
				//out.println(se.size());
				//se.clear();
			}
			return 0;
		}
		if (already[y][x] == 0 || path < already[y][x]) {// steps <= already[y][x]) 
								 //already[y][x] = steps;
			already[y][x] = path;
			Vector <Tuple <Integer, Integer>> ve1 = new Vector(ve);
			Vector <Tuple <Integer, Integer>> ve2 = new Vector(ve);
			Vector <Tuple <Integer, Integer>> ve3 = new Vector(ve);
			Vector <Tuple <Integer, Integer>> ve4 = new Vector(ve);
			ve1.add(new Tuple(x, y));
			ve2.add(new Tuple(x, y));
			ve3.add(new Tuple(x, y));
			ve4.add(new Tuple(x, y));
			next2(x, y-1, ex, ey, path+1, 0, dir, steps+1, ve1);
			next2(x+1, y, ex, ey, path+1, 1, dir, steps+1, ve2);
			next2(x, y+1, ex, ey, path+1, 2, dir, steps+1, ve3);
			next2(x-1, y, ex, ey, path+1, 3, dir, steps+1, ve4);
		}
		return 0;
	}

	public static int next(int x, int y, int ex, int ey, int path, int dir, int prevDir, int steps) {
		if (path > minPath) {return 0;}
		if (path > minOrig) {return 0;}
		if (Math.abs(prevDir - dir) == 1 || Math.abs(prevDir -dir) == 3) {path += 1000;}
		if ((x < 0) || (y > (leny-1)) || (y < 0) || (x > (lenx -1))) {return 0;}
		if (grid[y][x] == '#') {return 0;}

		if (x == ex && y == ey) {
			if (path < minPath) {minPath = path; minSteps = steps; /*out.println("one"); out.println(minPath);*/}
			return 0;
		}
		if (already[y][x] == 0 || path < already[y][x]) {
			already[y][x] = path;
			next(x, y-1, ex, ey, path+1, 0, dir, steps+1);
			next(x+1, y, ex, ey, path+1, 1, dir, steps+1);
			next(x, y+1, ex, ey, path+1, 2, dir, steps+1);
			next(x-1, y, ex, ey, path+1, 3, dir, steps+1);
		}
		return 0;
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

