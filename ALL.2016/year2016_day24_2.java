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
class year2016_day24_2 {
	public static int maxPath = 0;
	public static int minPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = new char[leny][lenx];
	public static int already [][] = new int[leny][lenx];
	static int NUMS = 0;
	static int mymin = Integer.MAX_VALUE;
	static int distGrid[][];


	public static void main(String [] args) {
		out.println("		2016 Day24.2");
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


		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] != '#' && grid[yy][xx] != '.') {
					if (grid[yy][xx] - 48 > NUMS) {NUMS = grid[yy][xx] -48;}
				}
			}
		}
		NUMS++;
		distGrid = new int[NUMS][NUMS];

		for (int fr = 0; fr < NUMS-1; fr++) {
			for (int to = fr+1; to < NUMS; to++) {
				int tmp = sDist(fr, to);
				distGrid[fr][to] = tmp;
				distGrid[to][fr] = tmp;
			}
		}
		int tosort[] = new int[NUMS];
		for (int i = 0; i < NUMS; i++) {
			tosort[i] = i;
		}
		Arrays.sort(tosort);
		int tosortOrig[] = new int[NUMS];
		tosortOrig = Arrays.copyOf(tosort, NUMS);

		int minSumDist = Integer.MAX_VALUE;
		do {
			if (tosort[0] != 0) {continue;}
			int sumDist = 0;
			for (int j = 0; j < NUMS-1; j++) {
				sumDist += distGrid[tosort[j]][tosort[j+1]];
			}
			sumDist+= distGrid[tosort[NUMS-1]][tosort[0]];
			if (sumDist < minSumDist) {minSumDist = sumDist;}
		} while (nextPermutation(tosort) && !Arrays.equals(tosort, tosortOrig));


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minSumDist);
		out.println("");
	}
	static int sDist(int fr, int to) {

		int Gfry = 0, Gfrx = 0, Gtoy = 0, Gtox = 0;
next:
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == fr+48 ) {
					Gfry = y; Gfrx = x; break next;
				}
			}
		}
next2:
		for (int y = 0; y < leny; y++) {
			for (int x = 0; x < lenx; x++) {
				if (grid[y][x] == to+48 ) {
					Gtoy = y; Gtox = x; break next2;
				}
			}

		}

		for (int y = 0; y< leny; y++) {
			for (int x = 0; x < lenx; x++) {
				already[y][x] = -1;
			}
		}
		already[Gfry][Gfrx] = 0;
		mymin = Integer.MAX_VALUE;

		next(Gfrx, Gfry, Gtox, Gtoy, 0, 1);
		return mymin;
	}

	static void next(int x, int y, int dx, int dy, int steps, int first) {
		if (grid[y][x] == '#') {return;}
		if (x == dx && y == dy) {
			if (steps < mymin) {mymin = steps;}
			return;
		}

		if (first == 1 || steps < already[y][x] || already[y][x] == -1 ) {
			already[y][x] = steps;
			if (y-1 >= 0)  {next(x, y-1, dx, dy, steps+1, 2);}
			if (x+1 <= lenx) {next(x+1, y, dx, dy, steps+1, 2);}
			if (y+1 <= leny) {next(x, y+1, dx, dy, steps+1, 2);}
			if (x-1 >= 0)  {next(x-1, y, dx, dy, steps+1, 2);}
		}
	}
	public static boolean nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
		return true;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private static void reverse(int[] nums, int start) {
		int end = nums.length - 1;
		while (start < end) {
			swap(nums, start, end);
			start++;
			end--;
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

