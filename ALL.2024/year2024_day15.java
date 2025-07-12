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
class year2024_day15 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day15.1");
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
		int gg = 0;
		String moves = new String("");
		for (int i = 0; i < blah.size();i++) {
			if (blah.get(i).length() == 0) {
				leny--;
				gg = 1;
				continue;
			}
			if (gg == 0) {
				grid[i] = blah.get(i).toCharArray();
			} else if (gg == 1) {
				leny--;
				moves += new String(blah.get(i));
			}
		}

		out.print("moves : "); out.println(moves);
		out.print("moves length: "); out.println(moves.length());
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		out.println(grid[leny-1]);
		out.println("---------");


		int sx = 0;
		int sy = 0;
after1:
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == '@') {
					grid[y][x] = '.';
					sx = x; sy = y; break after1;
				}
			}
		}
		int numMoves = moves.length();


after2:

		for (int i = 0; i < numMoves; i++) {
			//printit(); printf("MOVE: %c\n", moves[i]); getchar();
			switch (moves.charAt(i)) {
				case '^':
					//sx stays same
					if (grid[sy-1][sx] == '.') {
						sy--;
					} else if (grid[sy-1][sx] == '#') {
						//no move
					} else if (grid[sy-1][sx] == 'O') {
						//O with no dot ending in hash -- no move
						for (int y = sy-2; y > 0; y--) {
							if (grid[y][sx] == '.') {
								grid[sy-1][sx] = '.';
								grid[y][sx] = 'O';
								sy--;
								break;
							} else if (grid[y][sx] == 'O') {
								continue;
								//keep going until space
							} else if (grid[y][sx] == '#') {
								break;
								//nomove
							} else {
								//printf("ERR^"); exit(0);
							}
						}
					}
					break;
				case '>':
					//y stays same
					if (grid[sy][sx+1] == '.') {
						sx++;
					} else if (grid[sy][sx+1] == '#') {
						//no move
					} else if (grid[sy][sx+1] == 'O') {
						//O with no dot ending in hash -- no move
						for (int x = sx+2; x < lenx; x++) {
							if (grid[sy][x] == '.') {
								grid[sy][sx+1] = '.';
								grid[sy][x] = 'O';
								sx++;
								break;
							} else if (grid[sy][x] == 'O') {
								continue;
								//keep going until space
							} else if (grid[sy][x] == '#') {
								break;
								//nomove
							} else {
								//printf("ERR>"); exit(0);
							}
						}
					}
					break;
				case 'v':
					//sx stays same
					if (grid[sy+1][sx] == '.') {
						sy++;
					} else if (grid[sy+1][sx] == '#') {
						//no move
					} else if (grid[sy+1][sx] == 'O') {
						//O with no dot ending in hash -- no move
						for (int y = sy+2; y < leny; y++) {
							if (grid[y][sx] == '.') {
								grid[sy+1][sx] = '.';
								grid[y][sx] = 'O';
								sy++;
								break;
							} else if (grid[y][sx] == 'O') {
								continue;
								//keep going until space
							} else if (grid[y][sx] == '#') {
								break;
								//nomove
							} else {
								//printf("ERRv"); exit(0);
							}
						}
					}
					break;
				case '<':
					//sy stays same
					if (grid[sy][sx-1] == '.') {
						sx--;
					} else if (grid[sy][sx-1] == '#') {
						//no move
					} else if (grid[sy][sx-1] == 'O') {
						//O with no dot ending in hash -- no move
						for (int x = sx-2; x > 0; x--) {
							if (grid[sy][x] == '.') {
								grid[sy][sx-1] = '.';
								grid[sy][x] = 'O';
								sx--;
								break;
							} else if (grid[sy][x] == 'O') {
								continue;
								//keep going until space
							} else if (grid[sy][x] == '#') {
								break;
								//nomove
							} else {
								///printf("ERR<"); exit(0);
							}
						}
					}
					break;
				default:
					//printf("UNK"); exit(0);
					break;
			}
		}

		long ans = 0;
		for (int y = 0; y < leny; y++) {
                	for (int x = 0; x < lenx; x++) {
                        	if (grid[y][x] == 'O') {
                                	ans += (100 * y) + x;
                        	}
                	}
        	}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
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

