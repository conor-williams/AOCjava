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


// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2021_day13_2 {
	//	        public static int maxPath = 0;
	public static int sx = 0;
	public static int sy = 0;
	public static char grid [][] = new char[sy][sx];
	public static char gridTmp [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day13.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
		//int lenx = 0;
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
		Pattern p = Pattern.compile("(\\d+),(\\d+)");
		int maxx = 0;
		int maxy = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {break;}
			Matcher m = p.matcher(blah.get(i));
			m.find();
			if (Integer.valueOf(m.group(1)) > maxx) {maxx = Integer.valueOf(m.group(1));}
			if (Integer.valueOf(m.group(2)) > maxy) {maxy = Integer.valueOf(m.group(2));}
		}
		sx = maxx+1;
		sy = maxy+1;
		grid = new char[sy][sx];
		gridTmp = new char[sy][sx];
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				grid[yy][xx] = '.';
			}
		}
		boolean folds = false;
		Vector <Tuple<Character, Integer>> ve = new Vector<>();
		Pattern p2 = Pattern.compile("fold along (x|y)=(\\d+)");
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {
				folds =  true;
				continue;
			}
			if (folds == false) {
				Matcher m = p.matcher(blah.get(i));
				m.find();
				int xx = Integer.valueOf(m.group(1));
				int yy = Integer.valueOf(m.group(2));
				grid[yy][xx] = '#';
			} else {
				Matcher m = p2.matcher(blah.get(i));
				m.find();
				char exory = m.group(1).charAt(0);
				int value = Integer.valueOf(m.group(2));
				Tuple tu1 = new Tuple(exory, value);
				ve.add(tu1);
			}
		}

		/*
		   var fir = ve.get(0);
		   char xory = fir.first;
		   int val = fir.second;

		   out.println(xory);
		   out.println(val);
		   */

		for (int qqq = 0; qqq < ve.size(); qqq++) {
			var fir = ve.get(qqq);
			char xory = fir.first;
			int val = fir.second;
			if (xory == 'x') {
				for (int yy = 0; yy < sy; yy++) {
					for (int xx = 0; xx < val; xx++) {
						gridTmp[yy][xx] = grid[yy][xx];
					}
				}
				for (int yy = 0; yy < sy; yy++) {
					int tr = 2;
					for (int xx = val+1; xx < sx; xx++) {
						if (grid[yy][xx] == '#') {
							gridTmp[yy][xx-tr] = '#';
						}
						tr+=2;
					}
				}
				sx = val;
			} else {
				for (int xx = 0; xx < sx; xx++) {
					for (int yy = 0; yy < val; yy++) {
						gridTmp[yy][xx] = grid[yy][xx];
					}
				}
				for (int xx = 0; xx < sx; xx++) {
					int tr = 2;
					for (int yy = val+1; yy < sy; yy++) {
						if (grid[yy][xx] == '#') {
							//out.print("yy:"); out.println(yy);
							//out.print("tr:"); out.println(tr);
							//out.println(yy-tr);
							gridTmp[yy-tr][xx] = '#';
						}
						tr+=2;
					}
				}
				sy = val;
			}
			grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
		}

		out.println("**j_ans: ");
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '.') {
					out.print(" ");
				} else {
					out.print(grid[yy][xx]);
				}
			}
			out.println();
		}
		out.println();

		//		System.setOut(originalOut);
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
