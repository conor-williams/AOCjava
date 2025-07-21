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
class year2018_day18_2 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static char gridOrig [][] = new char[leny][lenx];
	public static char gridMem [][][] = new char[2000][leny][lenx];
	public static char gridNew [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	public static long tot = 0;

	public static void main(String [] args) {
		out.println("		2018 Day18.2");
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
		gridOrig = new char[leny][lenx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
			gridOrig[i] = blah.get(i).toCharArray();
		}




		int TIMES = 10000;
		int cycle = 0;
after:
		for (int kk = 0; kk < TIMES; kk++) {
			if (kk > 1000) {
				if ( Arrays.deepEquals( Arrays.stream(grid).map(String::new).toArray(), Arrays.stream(gridOrig).map(String::new).toArray()
						      )) {
					//out.print("cycle @:"); out.println(kk);
					//out.print("cyclesize :"); out.println(kk-1000);
					int which = (int)(((1000000000L - 1000 ) % ((kk-1000))) + (long)1000);
					grid = Arrays.stream(gridMem[which-1000]).map(char[]::clone).toArray(char[][]::new);

					//out.println(which);
					break after;
			      }
			}
			if (kk == 1000) {
				gridOrig = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
			}
			if (kk >= 1000) {
				gridMem[kk-1000] = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
			}
			gridNew = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
			for (int y = 0; y < leny; y++) {
				for (int x = 0; x < lenx; x++) {
					check(x, y, grid[y][x]);
				}
			}
			grid = Arrays.stream(gridNew).map(char[]::clone).toArray(char[][]::new);

		}


		tot = 0;
		int countL = 0;
		int countT = 0;
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == '#') {
					countL++;
				} else if (grid[y][x] == '|') {
					countT++;
				}
			}
		}


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(countT*countL);
		out.println("");
	}
	public static void check(int x, int y, char tre) {
		int count = 0;
		int count2 = 0;
		switch (tre) {
			case '.':
				count = cou(x, y, '|');
				if (count >=3) {gridNew[y][x] = '|';}
				break;
			case '|':
				count = cou(x, y, '#');
				if (count >=3) {gridNew[y][x] = '#';}
				break;
			case '#':
				count = cou(x, y, '#');
				count2 = cou(x, y, '|');
				if (count >= 1 && count2 >= 1) {} else {gridNew[y][x] = '.';}
				break;
		}
	}

	public static int cou(int x, int y, char tre) {
		int count = 0;
		if (y-1 >= 0) {if (grid[y-1][x] == tre) {count++;}}
		if (y-1 >= 0 && x+1 < lenx) {if (grid[y-1][x+1] == tre) {count++;}}
		if (x+1 < lenx) {if (grid[y][x+1] == tre) {count++;}}
		if (y+1 < leny && x+1 < lenx) {if (grid[y+1][x+1] == tre) {count++;}}
		if (y+1 < leny) {if (grid[y+1][x] == tre) {count++;}}
		if (y+1 < leny && x-1 >= 0) {if (grid[y+1][x-1] == tre) {count++;}}
		if (x-1 >=0) {if (grid[y][x-1] == tre) {count++;}}
		if (y-1 >= 0 && x-1 >=0) {if (grid[y-1][x-1] == tre) {count++;}}

		return count;
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

