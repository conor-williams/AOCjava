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
class year2018_day22_2 {
	//public static int minPath = Integer.MAX_VALUE;
	public static int minPath = 1080;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int tX = 0;
	public static int tY = 0;
	public static int depth = 0;
	static long tot = 0;

	static class already_s { int path[] = new int[15]; int tool; int both; 
		already_s(already_s o) {
			this.tool = o.tool;
			this.both = o.both;
			for (int ii = 0; ii < 15; ii++) {
				this.path[ii] = o.path[ii];
			}
		}
		already_s() {
			this.tool = 0;
			this.both = 0;
			for (int ii = 0; ii < 15; ii++) {
				this.path[ii] = 0;
			}
		}
	}
	public static already_s already [][] = new already_s[0][0];
	static final char WET = '=';
	static final char ROC = '.';
	static final char NAR = '|';
	static final char NEI = 10;
	static final char TOR = 11;
	static final char CLI = 12;

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day22.2");
		out.println("	SLOW ~30seconds");
		out.flush();
		Vector<String> blah = new Vector<>();
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
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(depth|target): ((\\d+),(\\d+)|(\\d+))");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			if (blah.get(i).charAt(0) == 'd') {
				out.println(m.group(1));
				out.println(m.group(2));
				out.println("-------");
				depth = Integer.valueOf(m.group(2));
				out.print("depth: "); out.println(depth);
			} else if (blah.get(i).charAt(0) == 't') {
				out.println(m.group(1));
				out.println(m.group(2));
				out.println(m.group(3));
				out.println(m.group(4));
				out.println("--------");
				tX = Integer.valueOf(m.group(3));
				out.println(tX);
				tY = Integer.valueOf(m.group(4));
				out.print("tX, tY"); out.print(tX); out.print(" "); out.println(tY);
			}
		}

		grid = new char[tY+55][tX+55];
		int geo[][] = new int [tY+55][tX+55];
		int ero[][] = new int[tY+55][tX+55];
		int risk[][] = new int[tY+55][tX+55];
		already = new already_s[tY+55][tX+55];
		for (int yy = 0; yy< tY+55; yy++) {
			for (int xx = 0; xx < tX+55; xx++) {
				already[yy][xx] = new already_s();
			}
		}


		geo[0][0] = 0;		
		geo[tY][tX] = 0;

		{
			int y = 0;
			for (int x = 0; x < tX+55; x++) {
				geo[y][x] = x * 16807; 
			}

			int x = 0;
			for (y = 0; y < tY+55; y++) {
				geo[y][x] = y * 48271;
			}
		}

		geo[0][0] = 0;		
		geo[tY][tX] = 0;
		{
			int y = 0;
			for (int x = 0; x < tX+55; x++) {
				ero[y][x] = (geo[y][x] + depth) % 20183;
			}

			int x = 0;
			for (y = 0; y < tY+55; y++) {
				ero[y][x] = (geo[y][x] + depth) % 20183;
			}
		}
		geo[0][0] = 0;		
		geo[tY][tX] = 0;
		{
			for (int y = 1; y < tY+55; y++) {
				for (int x = 1; x< tX+55; x++) {
					if (x == 0 && y==0) {
					} else if (x == tX && y==tY) {
					} else {
						geo[y][x] = ero[y][x-1] * ero[y-1][x];
					}
					ero[y][x] = (geo[y][x] + depth) % 20183;
				}
			}
		}

		//{printf("here3: ero is %lld\n", ero[0][1]);}

		{
			for (int y = 0; y < tY+55; y++) {
				for (int x = 0; x< tX+55; x++) {
					switch(ero[y][x] % 3) {
						case 0:
							grid[y][x] = ROC;
							break;
						case 1:
							grid[y][x] = WET;
							break;
						case 2:
							grid[y][x] = NAR;
							break;
					}
				}
			}
		}
		{
			for (int yy = 0; yy< tY+55; yy++) {
				for (int xx = 0; xx < tX+55; xx++) {
					already[yy][xx] = new already_s();
				}
			}
			out.println("TOR");
			next(0,1, TOR, 1);
			for (int yy = 0; yy< tY+55; yy++) {
				for (int xx = 0; xx < tX+55; xx++) {
					already[yy][xx] = new already_s();
				}
			}
			out.println("CLI");
			next(0,1, CLI, 8);

			for (int yy = 0; yy< tY+55; yy++) {
				for (int xx = 0; xx < tX+55; xx++) {
					already[yy][xx] = new already_s();
				}
			}
			out.println("TOR");
			next(1,0, TOR, 1);

			for (int yy = 0; yy< tY+55; yy++) {
				for (int xx = 0; xx < tX+55; xx++) {
					already[yy][xx] = new already_s();
				}
			}
			out.println("TOR");
			next(1,0, CLI, 8);


		}


		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
	}

	static int ind = 0;
	static void next(int x, int y, int tool, int path) {

		if (x >= (tX+55)) {return;} 
		if (x < 0) {return;}
		if (y >= (tY+55)) {return;}
		if (y < 0) {return;}

		if (x == 0 && y == 0) {return;}

		if (x == tX && y == tY) {
			if (tool == NEI) {
				return;
			} else if (tool == TOR) {
			} else if (tool == CLI) {
				path += 7;
			} 
			if (path < minPath) {
				minPath = path;
			}
			return;
		}
		if (path >= minPath) {return;}

		if (already[y][x].path[tool] == 0 || path < already[y][x].path[tool]) { 
			int newpath;
			switch(grid[y][x]) { //10 neither //11 torch //12 climbing
				case WET:
					if (tool == TOR) {return;}
					already[y][x].path[tool] = path;
					for (int t = 10; t <= 12; t++) {
						if (t == TOR) {
							continue;
						} else {
							if (t != tool) {
								newpath = path + 7+1;
							} else {
								newpath = path+1;
							}
							{next(x, y-1, t, newpath);}
							{next(x+1, y, t, newpath);}
							{next(x, y+1, t, newpath);}
							{next(x-1, y, t, newpath);}
						}
					}
					break;
				case ROC:
					if (tool == NEI) {return;}
					already[y][x].path[tool] = path;
					for (int t = 10; t <= 12; t++) {
						if (t == NEI) {
							continue;
						} else {
							if (t != tool) {
								newpath = path+ 7+1;
							} else {
								newpath = path + 1;
							}

							{next(x, y-1, t, newpath);}
							{next(x+1, y, t, newpath);}
							{next(x, y+1, t, newpath);}
							{next(x-1, y, t, newpath);}
						}
					}
					break;
				case NAR:
					if (tool == CLI) {return;}
					already[y][x].path[tool] = path;
					for (int t = 10; t <= 12; t++) {
						if (t == CLI) {
							continue;
						} else {
							if (t != tool) {
								newpath = path+ 7+1;	
							} else {
								newpath = path + 1;
							}
							{next(x, y-1, t, newpath);}
							{next(x+1, y, t, newpath);}
							{next(x, y+1, t, newpath);}
							{next(x-1, y, t, newpath);}
						}
					}
					break;
				case 'M':
					out.println("ERR1");
				default:
					out.println("ERR2");
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
/*
	static class Node implements Comparable<Node> {
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

			for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
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

	private static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < lenx && y < leny;
	}

	
*/

