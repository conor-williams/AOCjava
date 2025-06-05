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


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2020_day11 {
	//	        public static int maxPath = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2020 Day11.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		int leny = 0;
		int lenx = 0;
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
		sx = lenx;
		sy = leny;
		grid = new char[sy][sx];
		// already = new int[sy][sx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		int co = 0;
		while ( 1 == 1) {
			out.println(co);
			co++;

			//char prev[][] = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);

			char gridTmp[][] = Arrays.stream(grid).map(char[]::clone).toArray(char[][]::new);
			for (int y = 0; y < sy; y++) {
				for (int x = 0; x < sx; x++) {
					int countOcc = 0;
					if (y-1 >= 0 && x-1 >=0) {
						if (grid[y-1][x-1] == '#') {
							countOcc++;
						}
					}
					if (y-1 >= 0) {
						if (grid[y-1][x] == '#') {
							countOcc++;
						}
					}
					if (y-1 >= 0 && x+1 < sx) {
						if (grid[y-1][x+1] == '#') {
							countOcc++;
						}
					}
					if (x+1 < sx) {
						if (grid[y][x+1] == '#') {
							countOcc++;
						}
					}
					if (x-1 >= 0) {
						if (grid[y][x-1] == '#') {
							countOcc++;
						}
					}
					if (y+1 < sy && x+1 < sx) {
						if (grid[y+1][x+1] == '#') {
							countOcc++;
						}
					}
					if (y+1 < sy && x-1 >= 0) {
						if (grid[y+1][x-1] == '#') {
							countOcc++;
						}
					}

					if (y+1 < sy) {
						if (grid[y+1][x] == '#') {
							countOcc++;
						}
					}
					if (grid[y][x] == 'L' && countOcc == 0) {
						gridTmp[y][x] = '#';
					} else if (grid[y][x] == '#' && countOcc >= 4) {
						gridTmp[y][x] = 'L';
					}
				}
			}
			if ( Arrays.deepEquals(gridTmp, grid)) {break;}
			grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);

			/*
			for (int yy = 0; yy < sy; yy++) {
				for (int xx = 0; xx < sx; xx++) {
					out.print(grid[yy][xx]);
				}
				out.println();
			}
			out.println();
			Scanner scanner = new Scanner(System.in); scanner.nextLine();
			*/
		}

		int count = 0;
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '#') {
					count++;
				}
			}
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(count);
		out.println("");
	}
}

class Tuple<X,Y > {
	public final X first;
	public final Y second;

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

