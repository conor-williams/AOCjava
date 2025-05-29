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
class year2021_day9_2 {
	public static int sx = 100;
	public static int sy = 100;
	public static char grid[][] = new char[sy][sx];
	public static int gridAlready[][] = new int[sy][sx];
	public static int basinSize = 0;
	public static Vector<Tuple<Integer, Integer>> Basin = new Vector<>();
	public static void main(String [] args) {
		out.println("		2021 Day9.2");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
				grid[leny] = line.toCharArray();
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		int count = 0;
		long sum = 0;
		Vector <Integer> bs = new Vector<>();
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (yy-1 >= 0) {
					if (grid[yy][xx]  < grid[yy-1][xx]) {
						//ok
					} else {
						continue;
					}
				}
				if (xx+1 < sx) {
					if (grid[yy][xx]  < grid[yy][xx+1]) {
					} else {
						continue;
					}
				}
				if (yy+1 < sy) {
					if (grid[yy][xx]  < grid[yy+1][xx]) {
					} else {
						continue;
					}
				}
				if (xx-1 >= 0) {
					if (grid[yy][xx]  < grid[yy][xx-1]) {
					} else {
						continue;
					}
				}
				Basin = new Vector<>();
				basinSize = 1;
				gridAlready = new int[sy][sx];
				gridAlready[yy][xx] = 1;
				next(xx, yy);
                                for (int ii = 0; ii < Basin.size(); ii++) {
					var tu1 = Basin.get(ii);
                                        next(tu1.first, tu1.second);
					Basin.remove(ii);
					ii--;
                                }
				//out.println(basinSize);
				bs.add(basinSize);

			}
		}
		Collections.sort(bs, Collections.reverseOrder());
		long ansans = bs.get(0) * bs.get(1) *bs.get(2);
//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ansans);
		out.println("");
	}

	public static int check(int x, int y, int x1, int y1) {
        	if (gridAlready[y1][x1] != 1 && grid[y1][x1] != '9') {
                	if (grid[y1][x1] > grid[y][x]) {
                        	gridAlready[y1][x1] = 1;
                        	basinSize++;
                        	return 1;
                	}
        	}
        	return 0;

	}

	public static void next(int x, int y) {
        	gridAlready[y][x] = 1;
	        if (y+1 < sy && check(x, y, x, y+1) == 1) { Tuple <Integer, Integer> tu1 = new Tuple(x, y+1); Basin.add(tu1);}
       		if (y-1 >= 0 && check(x, y, x, y-1) == 1) { Tuple <Integer, Integer> tu1 = new Tuple(x, y-1); Basin.add(tu1);}
	        if (x+1 < sx && check(x, y, x+1, y) == 1) { Tuple <Integer, Integer> tu1 = new Tuple(x+1, y); Basin.add(tu1);}
       		if (x-1 >= 0 && check(x, y, x-1, y) == 1) { Tuple <Integer, Integer> tu1 = new Tuple(x-1, y); Basin.add(tu1);}
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
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

