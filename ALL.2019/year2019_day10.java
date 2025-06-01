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
class year2019_day10 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int sx = 33;
	public static int sy = 33;
	public static char grid [][] = new char[sy][sx];
	public static void main(String [] args) {
		out.println("		2019 Day10.1");
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
		PrintStream originalOut = System.out;
                System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Vector <Tuple <Double, Double>> asteroids = new Vector<>();
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '#') {
					Tuple <Double, Double> tu1 = new Tuple((double)xx, (double)yy);
					asteroids.add(tu1);
				}
			}
		}
		out.println(asteroids.size());

		int max = 0;
		Tuple <Double, Double> best = new Tuple<>(0.0, 0.0);
		for (int ii = 0; ii < asteroids.size(); ii++) {
			var tu1 = asteroids.get(ii);
			Set <Tuple <Double, Integer>> sees = new HashSet<>();
			for (int jj = 0; jj < asteroids.size(); jj++) {
				if (ii == jj) {continue;}
				var tu2 = asteroids.get(jj);
				double top1 = tu2.first-tu1.first;
				double bottom1 = tu2.second-tu1.second;
				double tanangle = top1/bottom1;
				int quadrant = 0;
				if (top1 <= 0 && bottom1 >= 0) {
					quadrant = 1;
				} else if (top1 >= 0 && bottom1 >= 0) {
					quadrant = 2;
				} else if (top1 <= 0 && bottom1 <= 0) {
					quadrant = 3;
				} else {
					quadrant = 4;
				}

				//if (top == 0 || bottom == 0) {continue;}
				Tuple <Double, Integer> tuzzz = new Tuple(tanangle, quadrant);
				sees.add(tuzzz);
			}
			out.print("x,y: "); out.print(tu1.first); out.print(" "); out.println(tu1.second);
			out.println(sees.size());
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			if (sees.size() > max) {max = sees.size(); best = new Tuple(tu1.first, tu1.second);}
		}

		out.println(best.first);
		out.println(best.second);



		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(max);
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

