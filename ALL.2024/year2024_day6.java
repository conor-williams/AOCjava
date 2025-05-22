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
// /java -Xmx2g year2019_day3.java *i1.txt


//System.setOut(originalOut);
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2024_day6 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day6.1");
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
		char [][]grid = new char[140][140];

		for (int i = 0; i < blah.size(); i++) {
			grid[i] = blah.get(i).toCharArray();
		}
		int lenx = blah.get(0).length();
		int leny = blah.size();
		//out.println(lenx); out.println(leny);
		
		
		int stx = 0;
		int sty = 0;
after:
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (grid[yy][xx] == '^') {
					sty = yy;
					stx = xx;
					break after;
				}
			}
		}

		int dir = 0;
		int x = stx;
		int y = sty;
		//out.println(x); out.println(y);
		char [][] already = new char[140][140];
		already[y][x] = '1';
		while (1 == 1) {
			int xprev = x;
			int yprev = y;
			int dirprev = dir;
			switch (dir) {
				case 0:
					if (y-1 < 0) { break; }

					if (grid[y-1][x] != '#') {
						y--;
						already[y][x] = '1';
					} else {
						dir++;
					}

					break;
				case 1:
					if (x+1 >= lenx) {break;}
					if (grid[y][x+1] != '#') {
						x++;
						already[y][x] = '1';
					} else {
						dir++;
					}
					break;
				case 2:
					if (y+1 >= leny) {break;}
					if (grid[y+1][x] != '#') {
						y++;
						already[y][x] = '1';
					} else {
						dir++;
					}
					break;
				case 3:
					if (x-1 < 0){break;}
					if (grid[y][x-1] != '#') {
						x--;
						already[y][x] = '1';
					} else {
						dir++;
					}
					break;
				}
			dir %= 4;
			if (x == xprev && y == yprev && dir == dirprev) {
				break;
			}
		}

		int count = 0;
		for (int yy = 0; yy < leny; yy++) {
			for (int xx = 0; xx < lenx; xx++) {
				if (already[yy][xx] == '1') {
					count++;
				}
			}
		}

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

