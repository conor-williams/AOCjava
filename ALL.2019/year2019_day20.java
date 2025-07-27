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
class year2019_day20 {
	public static int minPath = Integer.MAX_VALUE;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int already [][] = new int[leny][lenx];

	static class to_s { int x = 0; int y = 0; }
	static Map <Tuple <Integer, Integer>, to_s> mp = new HashMap<>();
	static Map <String, Integer> mpLets = new HashMap<>();

	public static void main(String [] args) {
		out.println("		2019 Day20.1");
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

		int stX = 0;
		int stY = 0;
		int endX = 0;
		int endY = 0;
		for (int y = 0; y < leny-1; y++) {
			for (int x = 0; x < lenx-1; x++) {
				char fc1 = ' ', sc1 = ' '; int posX1 = 0, posY1 = 0;

				int found = 0;
				if ((Character.isUpperCase(grid[y][x])) && (Character.isUpperCase(grid[y+1][x]))) {
					fc1 = grid[y][x];
					sc1 = grid[y+1][x];
					String var_str2 = new String();
					var_str2 += "" + fc1 + sc1;
					if (mpLets.get(var_str2) != null) {continue;}
					if (y+2 == leny) {
						posX1 = x; posY1 = y-1;
					} else if (y == 0) {
						posX1 = x; posY1 = y+2;
					} else if (grid[y+2][x] == '.') {
						posX1 = x; posY1 = y+2;
					} else if (grid[y-1][x] == '.') {
						posX1 = x; posY1 = y-1;
					}
					found = 1;
				} else if ((Character.isUpperCase(grid[y][x])) && (Character.isUpperCase(grid[y][x+1]))) {
					fc1 = grid[y][x];
					sc1 = grid[y][x+1];
					String var_str2 = "" + fc1 + sc1;
					if (mpLets.get(var_str2) != null) {continue;}
					if (x+2 == lenx)  {
						posX1 = x-1; posY1 = y;
					} else if (x == 0) {
						posX1 = x+2; posY1 = y;
					} else if (grid[y][x+2] == '.') {
						posX1 = x+2; posY1 = y;
					} else if (grid[y][x-1] == '.') {
						posX1 = x-1; posY1 = y;
					}
					found = 1;
				}
				if (found == 1) {
					if (fc1 == 'A' && sc1 == 'A') {
						stX = posX1; stY = posY1;
					} else if (fc1 == 'Z' && sc1 == 'Z') {
						endX = posX1; endY = posY1;
					} else {
						getsecond(fc1, sc1, posX1, posY1);
					}
				}
			}
		}
		for (int y = 0; y <leny; y++) {
			for (int x = 0; x < lenx; x++) {
				already[y][x] = -1;
			}
		}
		int path = 0;
		ne(stX, stY, endX, endY, path);
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minPath);
		out.println("");
	}

	static void ne(int x, int y, int eX, int eY, int path) {
		if (x == eX && y == eY) {
			if (path < minPath) {minPath = path; }
			return;
		}
		if (grid[y][x] != '.') {return;}

		if (x < 0 || x >= lenx || y < 0|| y>= leny) {return;}

		var iter = mp.get(new Tuple(x,y));
		if (iter == null) {} else {path++; x = iter.x; y = iter.y;}

		if (already[y][x] == -1 || path < already[y][x]) {
			already[y][x] = path;
			ne(x+1, y, eX, eY, path+1);
			ne(x-1, y, eX, eY, path+1);
			ne(x, y+1, eX, eY, path+1);
			ne(x, y-1, eX, eY, path+1);
		}

	}

	static void getsecond(char one1, char two1, int px1, int py1) {
		char fc2 = 0, sc2 = 0; int posX2 = 0, posY2 = 0;
after:
		for (int y = 0; y < leny-1; y++) {
			for (int x = 0; x < lenx-1; x++) {
				if ((Character.isUpperCase(grid[y][x])) && (Character.isUpperCase(grid[y+1][x]))) {
					fc2 = grid[y][x]; sc2 = grid[y+1][x];
					if (fc2 == one1 && sc2 == two1) {
						if (y+2 == leny) {
							posX2 = x; posY2 = y-1;
						} else if (y == 0) {
							posX2 = x; posY2 = y+2;
						} else if (grid[y+2][x] == '.') {
							posX2 = x; posY2 = y+2;
						} else if (grid[y-1][x] == '.') {
							posX2 = x; posY2 = y-1;
						}
						if (posX2 == px1 && posY2 == py1) {continue;} else {break after;}
					}
				} else if ((Character.isUpperCase(grid[y][x])) && (Character.isUpperCase(grid[y][x+1]))) {
					fc2 = grid[y][x]; sc2 = grid[y][x+1];

					if (fc2 == one1 && sc2 == two1) {
						if (x+2 == lenx)  {
							posX2 = x-1; posY2 = y;
						} else if (x == 0) {
							posX2 = x+2; posY2 = y;
						} else if (grid[y][x+2] == '.') {
							posX2 = x+2; posY2 = y;
						} else if (grid[y][x-1] == '.') {
							posX2 = x-1; posY2 = y;
						}
						if (posX2 == px1 && posY2 == py1) {continue;} else {break after;}
					}
				}
			}
		}
		to_s to1 = new to_s();
		to1.x = px1; to1.y = py1;
		to_s to2 = new to_s();
		to2.x = posX2; to2.y = posY2;

		mp.put(new Tuple(px1, py1), to2);
		mp.put(new Tuple(posX2, posY2), to1);
		String var_str = "" + one1 + two1;
		mpLets.put(var_str, 1);
		return;
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

