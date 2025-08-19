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

//thanks nebble_longbottom @ reddit

@SuppressWarnings("unchecked")
class year2024_day16_2 {
	static Vector<Tuple<Integer, Integer>> dirs = new Vector<>();
	static class Node {
		int x;
		int y;
		int dir;

		Node(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		@Override
		public boolean equals(Object obj) {
			Node xx = (Node)obj;
			return xx.x == this.x && xx.y == this.y && xx.dir == this.dir;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y, dir);
		}
	}
	static int ex = -1;
	static int ey = -1;
	static int sx = -1;
	static int sy = -1;
	static int lenx = 0;
	static int leny = 1;
	static char[][] grid = new char[leny][lenx];
	public static void main(String [] args) {
		out.println("		2024 Day16.2");
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
		dirs.add(new Tuple(-1, 0));
		dirs.add(new Tuple(0, 1));
		dirs.add(new Tuple(1, 0));
		dirs.add(new Tuple(0, -1));

		//PrintStream originalOut = System.out;
		//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

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

		/*
		   for (int yy = 0; yy < leny; yy++) {
		   for (int xx = 0; xx < lenx; xx++) {
		   out.print(grid[yy][xx]);
		   }
		   out.println();
		   }
		   */

		var p2 = solve();
		//System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(p2.second);
		out.println("");
	}
	static Tuple <Map<Node, Map<Node, Integer>>, Map<Node, Map<Node, Integer>>> make_graph() {
		Map<Node, Map<Node, Integer>> graph = new HashMap<>();
		Map<Node, Map<Node, Integer>> rgraph = new HashMap<>();

		Node start_node = new Node(sx, sy, 1);
		Vector<Node> nodes_stack = new Vector<>();
		nodes_stack.add(start_node);

		int count = 0;
		int cdar[] = {1,-1};
		while (nodes_stack.size() != 0) {
			var node = nodes_stack.lastElement();
			nodes_stack.remove(nodes_stack.size()-1);

			if (graph.containsKey(node)) {
				continue;
			}
			int c = node.x;
			int r = node.y;
			int d = node.dir;

			for (int cd: cdar) {
				int dd = ((d+cd)+4) % 4;
				int dr = dirs.get(dd).first;
				int dc = dirs.get(dd).second;

				//if (r+dr > leny-1 || r+dr < 0 || c+dc > lenx-1 || c+dc < 0) {continue;}
				if (grid[r+dr][c+dc] != '#') {
					Node new_node = new Node(c, r, dd);
					graph.putIfAbsent(node, new HashMap());
					graph.get(node).put(new_node, 1000);
					rgraph.putIfAbsent(new_node, new HashMap());
					rgraph.get(new_node).put(node, 1000);
					if (!graph.containsKey(new_node)) {
						nodes_stack.add(new_node);
					}
				}
			}
			int dr10 = dirs.get(d).first;
			int dc10 = dirs.get(d).second;

			if (grid[r+dr10][c+dc10] == '#') {
				continue;
			}
			int rr = r+dr10;
			int cc = c+dc10;
			int last_d = d;
			int dist = 1;
			while (true) {
				count++;
				Vector<TreTuple<Integer, Integer, Integer>> valid_moves = new Vector<>();
				int backwards_dir = ((last_d)+2) %4;
				for (int di2 = 0; di2 < 4; di2++) {
					if (backwards_dir == di2) {continue;}
					int dr2 = dirs.get(di2).first;
					int dc2 = dirs.get(di2).second;

					if (grid[rr+dr2][cc+dc2] != '#') {
						valid_moves.add(new TreTuple(di2, dc2, dr2));
					}
				}
				if (valid_moves.size() == 0) {
					break;
				} else if (valid_moves.size() == 1) {
					int di = valid_moves.get(0).first;
					int dc = valid_moves.get(0).second;
					int dr = valid_moves.get(0).third;

					rr+= dr;
					cc+= dc;
					dist++;

					if (rr == ey && cc == ex) {
						Node new_node = new Node(cc, rr, last_d);
						graph.putIfAbsent(node, new HashMap<>());
						graph.get(node).put(new_node, dist);
						rgraph.putIfAbsent(new_node, new HashMap<>());
						rgraph.get(new_node).put(node, dist);

						if (!graph.containsKey(new_node)) {
							nodes_stack.add(new_node);
						}
					} else {
						dist += (di == last_d) ? 0 : 1000;
						last_d = di;
					}
				} else if (valid_moves.size() > 1) {
					Node new_node = new Node(cc, rr, last_d);
					graph.putIfAbsent(node, new HashMap<>());
					graph.get(node).put(new_node, dist);
					rgraph.putIfAbsent(new_node, new HashMap<>());
					rgraph.get(new_node).put(node, dist);

					if (!graph.containsKey(new_node)) {
						nodes_stack.add(new_node);
					}
					break;
				}
			}
		}
		return new Tuple(graph, rgraph);
	}

	static Tuple <Integer, Integer> solve() {
		var pa = make_graph();
		Map<Node, Map<Node, Integer>> graph = pa.first;
		Map<Node, Map<Node, Integer>> rgraph = pa.second;

		PriorityQueue< TupleSpecial<Integer, Node>> heap = new PriorityQueue<>();

		Map <Node, Integer> dists = new HashMap<>();

		Node no = new Node(sx, sy, 1);
		heap.add(new TupleSpecial(0, no)); 

		int p1 = 0;
		Node end_node = new Node(ex, ey, 0);
		while (!heap.isEmpty()) {
			var xx = heap.poll();
			int dist = xx.first;
			Node node = xx.second;

			if (dists.containsKey(node)) {
				continue;
			}
			dists.put(node, dist);
			int c = node.x;
			int r = node.y;
			int d = node.dir;

			if (r == ey && c == ex) {
				p1 = dist;
				end_node = new Node(ex, ey, d);
				break;
			}

			var pa1 = graph.get(node);
			for (var no1: pa1.entrySet()) {
				Node adjacent = no1.getKey();
				int weight = no1.getValue();
				int new_dist = dist+weight;
				heap.add(new TupleSpecial(new_dist, adjacent));
			}
		}

		int p2 = 0;
		Vector <Node> nodes = new Vector<>();
		nodes.add(end_node);

		Set <Node> seen = new HashSet<>();
		int count = 0;

		while (!nodes.isEmpty()) {
			count++;
			Node node = nodes.lastElement();
			nodes.remove(nodes.size()-1);

			if (seen.contains(node)) { continue; }
			seen.add(node);
			p2+=1;

			if (rgraph.get(node) != null) {
				var pa1 = rgraph.get(node);
				for (var no2 : pa1.entrySet()) {
					Node adjacent = no2.getKey();
					int weight = no2.getValue();

					if (dists.containsKey(adjacent) && dists.get(adjacent) + graph.get(adjacent).get(node) == dists.get(node)) {
						nodes.add(adjacent);
						p2+= (graph.get(adjacent).get(node) % 1000) -1;
					}
				}
			}
		}
		return new Tuple(p1, p2);
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


}

class TupleSpecial<X,Y > implements Comparable <TupleSpecial> {
	public X first;
	public Y second;

	public TupleSpecial(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		TupleSpecial tu2 = (TupleSpecial) o;
		if (this == o) return true;
		if (!(o instanceof TupleSpecial)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}
	@Override
	public int compareTo(TupleSpecial other) {
		//return Integer.compare(this.risk, other.risk);
		TupleSpecial ot = (TupleSpecial)other;
		return Integer.compare((Integer)this.first, (Integer)ot.first);
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

