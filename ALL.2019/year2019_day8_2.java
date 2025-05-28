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


//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//		System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2019_day8_2 {
	public static int xMax = 25;
	public static int yMax = 6;
	public static int lMax = 100;
	public static int [][][] grid = new int[lMax][yMax][xMax];
	public static int [][] gridImage = new int[yMax][xMax];

	public static void main(String [] args) {
		out.println("		2019 Day8.2");
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
		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
after:
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			int pos = 0;
			for (int layer = 0; layer < lMax; layer++) {
				for (int yyy = 0; yyy < yMax; yyy++) {
					for (int xxx = 0; xxx < xMax; xxx++) {
						//layer = pos / (yMax * xMax);
						//out.print("layer:"); out.println(layer);
						if (pos >= ne.length()) {break after;}
						//out.print("layer: "); out.print(layer); out.print(" xxx "); out.print(xxx); out.print(" yyy "); out.println(yyy);
						grid[layer][yyy][xxx] = ne.charAt(pos) - '0';
						pos++;
					}
				}
			}

		}
		for (int yy = 0 ; yy < yMax; yy++) {
			for (int xx = 0; xx < xMax; xx++) {
				for (int layer = 0; layer < lMax; layer++) {
					if (grid[layer][yy][xx] == 2) {
						continue;
					} else if (grid[layer][yy][xx] == 1) {
						gridImage[yy][xx] = 1;
						break;
					} else {
						gridImage[yy][xx] = 0;
						break;
					}
				}
			}
		}
		out.println("**j_ans: ");
		for (int yy = 0 ; yy < yMax; yy++) {
			for (int xx = 0; xx < xMax; xx++) {
				if (gridImage[yy][xx] == 1) {
					out.print("1");
				} else if (gridImage[yy][xx] == 0) {
					out.print(" ");
				}
			}
			out.println();
		}
		out.println();

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

