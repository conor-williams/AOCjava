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
class year2020_day20_2 {
	static int GX = 10;
	static int NUMG = 144;
	static class grid_s {
		int num;
		char gr[][] = new char[GX][GX];
	}
	static grid_s grid[]  = new grid_s[NUMG];
	static char gridTmp[][] = new char[GX][GX];
	static char gridOrig[][] = new char[GX][GX];
	static Map <Integer, Integer> sidesMap = new HashMap<>();
	static int POS = 0;


	public static int leny = 0;

	public static void main(String [] args) {
		out.println("		2020 Day20.2");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		if (false) {
			for (int ii = 0; ii < NUMG; ii++) {
				grid[ii] = new grid_s();
			}
			Pattern p = Pattern.compile("Tile (\\d+):");
			int lin = 0;
			leny = -1;
			for (int i = 0; i < blah.size(); i++) {
				if (blah.get(i).length() == 0) {
					continue;
				} else if (blah.get(i).charAt(0) == 'T') {
					leny++;
					Matcher m = p.matcher(blah.get(i));
					m.find();
					grid[leny].num = Integer.valueOf(m.group(1));
					lin = 0;
				} else {
					grid[leny].gr[lin] = blah.get(i).toCharArray();
					lin++;
				}
			}
			leny++;
			if (NUMG != leny) {
				out.println(NUMG);
				out.println(leny);
				out.println("ERR");
				Runtime.getRuntime().halt(0);
			}
			long mul = 1;
			for (int i = 0; i < NUMG; i++) {
				POS = 0;
				sidesMap.clear();
				saveOrig(i);

				check(i); rotate(i); check(i); save(i); VFlipArray(grid[i].gr); check(i); recover(i); HFlipArray(grid[i].gr); check(i); recover(i); VFlipArray(grid[i].gr); HFlipArray(grid[i].gr); check(i); recover(i); check(i);
				check(i); rotate(i); check(i); save(i); VFlipArray(grid[i].gr); check(i); recover(i); HFlipArray(grid[i].gr); check(i); recover(i); VFlipArray(grid[i].gr); HFlipArray(grid[i].gr); check(i); recover(i); check(i);
				check(i); rotate(i); check(i); save(i); VFlipArray(grid[i].gr); check(i); recover(i); HFlipArray(grid[i].gr); check(i); recover(i); VFlipArray(grid[i].gr); HFlipArray(grid[i].gr); check(i); recover(i); check(i);
				check(i); rotate(i); check(i); save(i); VFlipArray(grid[i].gr); check(i); recover(i); HFlipArray(grid[i].gr); check(i); recover(i); VFlipArray(grid[i].gr); HFlipArray(grid[i].gr); check(i); recover(i); check(i);

				recoverOrig(i);

				if (sidesMap.size() == 2) {
					mul *= grid[i].num;
				} else {
				}
			}
		}

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print("TODO TODO");
		out.println("");
	}

	static void HFlipArray(char array[][])
	{
		int columns = GX;
		int rows = GX;
		for (int row = 0; row < rows; row++)
		{
			for (int index = 0; index < columns/2; index++) {
				char tmp1 = array[row][index];
				char tmp2 = array[row][columns-1-index];
				array[row][index] = tmp2;
				array[row][columns-1-index] = tmp1;
			}

		}
	}
	static void FlipColumn(char array[][], int column, int rows)
	{
		for (int row = 0; row < rows/2; row++)
		{
			char tmp1 = array[row][column];
			char tmp2 = array[rows-1-row][column];
			array[row][column] = tmp2;
			array[rows-1-row][column] = tmp1;


		}
	}

	static void VFlipArray(char array[][])
	{
		int columns = GX;
		int rows = GX;
		for (int column = 0; column < columns; column++)
		{
			FlipColumn(array, column, rows);
		}
	}
	static void saveOrig(int i) {
		gridOrig = Arrays.stream(grid[i].gr).map(char[]::clone).toArray(char[][]::new);
	}
	static void recoverOrig(int i) {
		grid[i].gr = Arrays.stream(gridOrig).map(char[]::clone).toArray(char[][]::new);
	}

	static void save(int i) {
		gridTmp = Arrays.stream(grid[i].gr).map(char[]::clone).toArray(char[][]::new);
	}
	static void recover(int i) {
		grid[i].gr = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
	}

	static void check(int i) {
		for (int j = 0; j < NUMG; j++) {
			if (i == j) {continue;}
			{//TOP of I with bottom of J
				int abber = 0;

				for (int x = 0; x < GX; x++) {
					if (grid[i].gr[0][x] == grid[j].gr[GX-1][x]) {
					} else {
						abber = 1;
						break;
					}
				}
				if (abber == 0) {sidesMap.put(j, 1);}
			}
			{//BOTTOM of I with TOP of J
				int abber = 0;
				for (int x = 0; x < GX; x++) {
					if (grid[i].gr[GX-1][x] == grid[j].gr[0][x]) {
					} else {
						abber = 1;
						break;
					}
				}
				if (abber == 0) {POS |= (1 << 1); sidesMap.put(j, 1); }
			}
			{//LEFT of I with RIGHT of J
				int abber = 0;
				for (int y = 0; y < GX; y++) {
					if (grid[i].gr[y][0] == grid[j].gr[y][GX-1]) {
					} else {
						abber = 1;
						break;
					}
				}

				if (abber == 0) {POS |= (1 << 2); sidesMap.put(j, 1);}
			}
			{//RIGHT of I with LEFT of J
				int abber = 0;
				for (int y = 0; y < GX; y++) {
					if (grid[i].gr[y][GX-1] == grid[j].gr[y][0]) {
					} else {
						abber = 1;
						break;
					}
				}

				if (abber == 0) {POS |= (1 << 3); sidesMap.put(j, 1);}
			}
		}
	}



	static void rotate(int wh) {
		transpose(wh);
		reverse(wh);	
	}

	static void transpose(int wh) {
		for(int i=0; i<GX; i++){
			for(int j=i+1; j<GX; j++) {
				char tmp1 = grid[wh].gr[i][j];
				char tmp2 = grid[wh].gr[j][i];
				grid[wh].gr[i][j] = tmp2;
				grid[wh].gr[j][i] = tmp1;
			}
		}
	}

	static void reverse(int wh) {
		for(int i=0; i<GX; i++){
			for(int j=0; j<GX/2; j++){
				char tmp1 = grid[wh].gr[i][j];
				char tmp2 = grid[wh].gr[i][(GX)-j-1];
				grid[wh].gr[i][j] = tmp2;
				grid[wh].gr[i][(GX)-j-1] = tmp1;

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

