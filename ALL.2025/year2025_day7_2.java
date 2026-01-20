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
import java.util.stream.IntStream;


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
///MyClass[] array = IntStream.range(0, 5) .mapToObj(i -> new MyClass()) .toArray(MyClass[]::new);
@SuppressWarnings("unchecked")
class year2025_day7_2 {
//	        public static int maxPath = 0;
        public static int lenx = 0;
        public static int leny = 0;
        public static char grid [][] = new char[leny][lenx];
        public static long scores [][] = new long[leny][lenx];
	public static Vector <String> vePaths = new Vector<>();
	public static int numPaths = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static long scoG = 0;

	public static void main(String [] args) {
		out.println("		2025 Day7.2");
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
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }

		sx = 0;
		sy = 0;
after:
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == 'S') {
					grid[yy][xx] = '.';
					sx = xx;
					sy = yy;
					break after;
				}
			}
		}
		/*
		for (int yy = 0; yy < leny-1; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == '|') {
				        if (grid[yy+1][xx] == '.') {
						grid[yy+1][xx] = '|';
					} else if (grid[yy+1][xx] == '^') {
					 	grid[yy+1][xx-1] = '|';
					 	grid[yy+1][xx+1] = '|';
					}
				} 
			}
		}
		int count = 0;
		for (int yy = 1; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == '^' && grid[yy-1][xx] == '|') {
					count++;
				}
			}
		}
		*/
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		String path = "";
		out.println(sx + " " + sy);
		//next(sx, sy);
                scores = new long[leny][lenx];
		nextBtm(leny-1);

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(scoG);
		out.println("");
	}
	public static void nextBtm(int y) {
		if (y <= 0) {
			out.println("score: " + scores[1][sx]);
			scoG = scores[1][sx];
			return;

		}
		for (int xx = 0; xx < lenx; xx++) {
			//out.println("y-1: " + Integer.toString(y-1));
			//out.println("xx-1: " + Integer.toString(xx-1));
			if (xx-1 >= 0) {
				if (grid[y-1][xx-1] == '^') {
					if (scores[y][xx] == 0) {
						scores[y][xx] = 1;
						out.println("set: " + y + "," + xx + scores[y][xx]);
					}
				}
			}
			if (xx+1 <= lenx-1) {
				if (grid[y-1][xx+1] == '^') {
					if (scores[y][xx] == 0) {
						scores[y][xx] = 1;
						out.println("set: " + y + "," + xx + scores[y][xx]);
					}
				}
			}
			//out.println("y-2: " + Integer.toString(y-2));
		}
		for (int xx = 0; xx < lenx; xx++) {
			/*
			int s1 = 0, s2 = 0;
			if (xx-1 >= 0) {
				s1 = scores[y][xx-1];
			} 
			if (xx+1 <= lenx-1) {
				s2 = scores[y][xx+1];
			}
				if (grid[yy][xx] == '^') {
					scores[y-2][xx] = s1 + s2;
					break;
				}
			}
			*/

			
			if (grid[y-1][xx] == '^') {
				long sLeft = scores[y][xx-1];
				long sRight = scores[y][xx+1];
				if (y+1 >= leny || y-1 == leny - 1) {
					scores[y-2][xx] = sLeft + sRight;
				} else if (grid[y+1][xx-1] == '^' && grid[y+1][xx+1] == '^') {
					scores[y-2][xx] = sLeft + sRight;
				} else if (grid[y+1][xx-1] == '^' && grid[y+1][xx+1] != '^') {
					for (int yyy = y; yyy < leny; yyy+=2) {
						if (scores[yyy][xx+1] != 0 && scores[yyy][xx+1] != 1) {
							sRight = scores[yyy][xx+1];
							break;
						}
					}
					scores[y-2][xx] = sLeft + sRight;
					
				} else if (grid[y+1][xx-1] != '^' && grid[y+1][xx-1] == '^') {
					for (int yyy = y; yyy < leny; yyy+=2) {
						if (scores[yyy][xx-1] != 0 && scores[yyy][xx-1] != 1) {
							sLeft = scores[yyy][xx-1];
							break;
						}
					}
					scores[y-2][xx] = sLeft + sRight;
				} else { //neither
					for (int yyy = y; yyy < leny; yyy+=2) {
						if (scores[yyy][xx+1] != 0 && scores[yyy][xx+1] != 1) {
							sRight = scores[yyy][xx+1];
							break;
						}
					}
					for (int yyy = y; yyy < leny; yyy+=2) {
						if (scores[yyy][xx-1] != 0 && scores[yyy][xx-1] != 1) {
							sLeft = scores[yyy][xx-1];
							break;
						}
					}
					scores[y-2][xx] = sLeft + sRight;
				}
			}
		}	
		y -= 2;
		nextBtm(y);
	}
	public static void next (int x, int y) ///, String path) 
	{
		if (y == leny-1) {
			/*
			if (!vePaths.contains(path)) {
				vePaths.add(path);
				numPaths++;
			}
			*/
			return;
		}
		if (scores[y][x] != 0) {
			numPaths += scores[y][x];
			return;
		}
		if (grid[y][x] == '.') {
			next(x, y+1); ///, path + Integer.toString(x));
		} else if (grid[y][x] == '^') {
			next(x+1, y+1);///, path + Integer.toString(x+1));
			next(x-1, y+1);///, path + Integer.toString(x-1));
		} else {
			out.println("whats here?" + "[" + grid[y][x] + "]");
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

