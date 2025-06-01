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
class year2024_day10_2 {
	public static int tot = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day10.2");
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
		already = new int[sy][sx];
		for (int i = 0; i < blah.size();i++) {
			grid[i] = blah.get(i).toCharArray();
		}

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Vector <Tuple <Integer, Integer>> starts = new Vector<>();
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '0') {
					Tuple <Integer, Integer> tu1 = 
						new Tuple(xx, yy);
					starts.add(tu1);
				}
			}
		}

		long tottot = 0;
		for (int ii = 0; ii < starts.size(); ii++) {
			already = new int[sy][sx];
			var tu2 = starts.get(ii);
			tot = 0;
			out.print(tu2.first); out.print(" "); out.println(tu2.second);

			next(tu2.first, tu2.second, 0);
			tottot += tot;
			out.println(tot);
			//out.println("-------");
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tottot);
		out.println("");
	}
	public static void next(int x, int y, int curval) {
		if (x < 0 || y < 0 || x >= sx || y >= sy) {return;}
		if (grid[y][x] != curval+'0') {return;}
		if (grid[y][x] == '9') {out.println("found end");
			out.print(x); out.print(" "); out.println(y);
			tot++; return;}
		//if (already[y][x] >= 1) {return;}
		//already[y][x]++;


		next(x, y-1, curval+1);
		next(x+1, y, curval+1);
		next(x, y+1, curval+1);
		next(x-1, y, curval+1);
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

