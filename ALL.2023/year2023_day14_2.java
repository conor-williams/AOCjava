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


// grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2023_day14_2 {
//	        public static int maxPath = 0;
        public static int lenx = 0;
        public static int leny = 0;
        public static char grid [][] = new char[leny][lenx];
    //    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day14.2");
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

		
		char gridFirst[][] = new char[leny][lenx];
		gridFirst = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
		char grid1000[][] = new char[leny][lenx];
		///Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
		int fir = -1;
		int sec = -1;
		for (int ii = 0; ii < 1000000000; ii++) {
			if (ii == 1000) {
				grid1000 = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
			}
			north();
			west();
			south();
			east();
			if (ii > 1000) {
				if (fir == -1 && Arrays.deepEquals(grid1000, grid)) {
					out.print("same: ");
					out.println(ii);
					out.println(ii-1000);
					fir = ii;
				} else if (Arrays.deepEquals(grid1000, grid)) {
					sec = ii;
					break;
				}
			}
			//printGrid();
		}
		

		int which = ((1000000000 - 1000) % (sec-fir))+1000;
		out.print("which: "); out.println(which);
		out.println(sec - fir);

		grid = Arrays.stream(gridFirst).map(char[]::clone).toArray(char[][]::new);
		for (int ii = 0; ii < 1000; ii++) {
			north();
			west();
			south();
			east();
		}
		long score = 0;
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == 'O') {
					score += leny - yy;
				}
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(score);
		out.println("");
	}
	public static void printGrid() {
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		Scanner scanner = new Scanner(System.in); scanner.nextLine();
	}
	public static void east() {
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = lenx-1; xx >= 0; xx--) {
				if (grid[yy][xx] == 'O') {
					for (int xx1 = xx+1; xx1 < lenx; xx1++) {
						if (grid[yy][xx1] == '.') {
							grid[yy][xx1-1] = '.';
							grid[yy][xx1] = 'O';
						} else {
							break;
						}
					}
				}
			}
		}
	}
	public static void west() {
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == 'O') {
					for (int xx1 = xx-1; xx1 >= 0; xx1--) {
						if (grid[yy][xx1] == '.') {
							grid[yy][xx1+1] = '.';
							grid[yy][xx1] = 'O';
						} else {
							break;
						}
					}
				}
			}
		}
	}
	public static void south() {
		for (int xx = 0; xx < lenx; xx++) {
			for (int yy = leny-1; yy >= 0; yy--) {
				if (grid[yy][xx] == 'O') {
					for (int yy1 = yy+1; yy1 < leny; yy1++) {
						if (grid[yy1][xx] == '.') {
							grid[yy1-1][xx] = '.';
							grid[yy1][xx] = 'O';
						} else {
							break;
						}
					}
				}
			}
		}
	}
	public static void north() {
		for (int xx = 0; xx < lenx; xx++) {
			for (int yy = 0; yy < leny; yy++) {
				if (grid[yy][xx] == 'O') {
					for (int yy1 = yy-1; yy1 >= 0; yy1--) {
						if (grid[yy1][xx] == '.') {
							grid[yy1+1][xx] = '.';
							grid[yy1][xx] = 'O';
						} else {
							break;
						}
					}
				}
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

