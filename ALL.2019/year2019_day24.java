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
class year2019_day24 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	static int SX = 10;
	public static char grid [][] = new char[SX][SX];
	public static char gridTmp [][] = new char[SX][SX];
	//    public static int already [][] = new int[leny][lenx];
	static Map<Integer, Integer> mp = new HashMap<>();

	static int PAD = 1;
	public static void main(String [] args) {
		out.println("		2019 Day24.1");
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
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */
		{
			for (int y = 0; y < PAD; y++) {
				for (int ii = 0; ii < PAD+lenx+PAD; ii++) {
					grid[y][ii] = '.';
				}
			}

			for (int y = PAD; y < leny+PAD; y++) {
				for (int ii = 0; ii < PAD; ii++) {
					grid[y][ii] = '.';
				}
				for (int ii = PAD; ii < PAD+lenx; ii++) {
					grid[y][ii] = blah.get(y-PAD).charAt(ii-PAD);
				}
				for (int ii = lenx+PAD; ii < PAD+lenx+PAD; ii++) {
					grid[y][ii] = '.';
				}
			}
			for (int y = PAD+leny; y < PAD+leny+PAD; y++) {
				for (int ii = 0; ii < PAD+lenx+PAD; ii++) {
					grid[y][ii] = '.';
				}
			}
		}
		lenx += 2;
		leny += 2;

		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				gridTmp[y][x] = '.';
			}
		}
		int t = 0;
		mp.put(calcbio(), 1);
		int ans = 0;
after:
		while (true) {
			for (int y = 1; y < leny-1; y++) {
				for (int x = 1; x < lenx-1; x++) {
					int count = 0;
					if (grid[y-1][x] == '#') {count++;}
					if (grid[y][x+1] == '#') {count++;}
					if (grid[y+1][x] == '#') {count++;}
					if (grid[y][x-1] == '#') {count++;}

					switch(grid[y][x]) {
						case '#':
							if (count == 1) {
								gridTmp[y][x] = '#';
							} else {
								gridTmp[y][x] = '.';
							}
							break;
						case '.':
							if (count == 1 || count == 2) {
								gridTmp[y][x] = '#';
							} else {
								gridTmp[y][x] = '.';
							}
							break;

					}
				}
			}
			grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);

			int var_bio = calcbio();
			if (!mp.containsKey(var_bio)) {
				mp.put(var_bio, 1);
			} else {
				ans = var_bio;
				break after;
			}
			t++;
		}


		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	static int calcbio() {
		int bio = 0;
		int pos = 0;
		for (int y = 1; y < leny-1; y++) {
			for (int x = 1; x < lenx-1; x++) {
				if (grid[y][x] == '#') {
					bio |= 1 << pos;
				}
				pos++;
			}
		}

		return bio;
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

