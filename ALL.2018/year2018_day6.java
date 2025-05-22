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
// /java -Xmx2g year2019_day3.java *i1.txt


//System.setOut(originalOut);
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2018_day6 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day6.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+),\\s+(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		Vector <Tuple <Integer, Integer>> vetu = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			Tuple <Integer, Integer> tup = new Tuple<>(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)));
			vetu.add(tup);
		}

		/*
		for (int kk = 0; kk < vetu.size(); kk++) {
			Tuple <Integer, Integer> tu2 = vetu.get(kk);
			//out.print(tu2.first); out.print(" "); out.println(tu2.second);
		}
		*/
		//Tuple<Integer, Integer> maxTuple = vetu.stream().max(Comparator.comparingInt(tuple -> tuple.first)).orElse(null);
		Tuple<Integer, Integer> maxXTuple = vetu.stream().max(Comparator.comparingInt(t -> t.first)).orElse(null);
		Tuple<Integer, Integer> minXTuple = vetu.stream().min(Comparator.comparingInt(t -> t.first)).orElse(null);
		Tuple<Integer, Integer> maxYTuple = vetu.stream().max(Comparator.comparingInt(t -> t.second)).orElse(null);
		Tuple<Integer, Integer> minYTuple = vetu.stream().min(Comparator.comparingInt(t -> t.second)).orElse(null);
		//out.println(maxXTuple.first);
		//out.println(minXTuple.first);
		
		//out.println(maxYTuple.second);
		//out.println(minYTuple.second);
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
		int [][] grid = new int[maxYTuple.second+60][maxXTuple.first+60];
		for (int yy = 0; yy < maxYTuple.second+50; yy++) {
			for (int xx = 0; xx < maxXTuple.first+50; xx++) {
				int minMan = 9999999;
				int which = 0;
				int [] dist = new int[100];
				for (int ii = 0; ii < vetu.size(); ii++) {
					Tuple <Integer, Integer> tu22 = vetu.get(ii);
					int man = Math.abs(tu22.second-yy) + Math.abs(tu22.first-xx);
					if (man < minMan) {minMan = man; which = ii+1;}
					dist[ii] = man;
					//if (man == minMan) {which = 0;}
				}
				int co = 0;
				for (int ii = 0; ii < blah.size(); ii++) {
					if (dist[ii] == minMan) {
						co++;
					}
				}
				if (co == 1) {
					grid[yy][xx] = which;
				} else {
					grid[yy][xx] = 0;
				}
			}
		}
		int count[] = new int [100];
		for (int yy = 0; yy < maxYTuple.second+50; yy++) {
			for (int xx = 0; xx < maxXTuple.first+50; xx++) {
				count[grid[yy][xx]]++;
			}
		}
		Set <Integer> se = new HashSet<>();
		
		for (int yy = 0; yy < maxYTuple.second+50; yy++) {
			int xx1 = 0;
		        int xx2 = maxXTuple.first+50-1;
			se.add(grid[yy][xx1]);
			se.add(grid[yy][xx2]);
		}
		for (int xx = 0; xx < maxXTuple.second+50; xx++) {
			int yy1 = 0;
			int yy2 = maxYTuple.first+50-1;
			se.add(grid[yy1][xx]);
			se.add(grid[yy2][xx]);
		}
		//out.println(" going to ignore...");

		for (Integer element: se) {
		    //System.out.println(element); // Access each element
		    count[element] = 0;
		}
		count[0] = 0;
		//List b = Arrays.asList(ArrayUtils.toObject(count));
		int maxArea = Arrays.stream(count).max().getAsInt();

		out.print("**j_ans: ");
		out.print(maxArea);
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

