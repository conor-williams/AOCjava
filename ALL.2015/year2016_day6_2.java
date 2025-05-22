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
class year2015_day6_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int [][] grid = new int[1001][1001];
	public static void main(String [] args) {
		out.println("		2015 Day6.2");
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
		Pattern p = Pattern.compile("(turn on|toggle|turn off) (\\d+),(\\d+) through (\\d+),(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();

			String wat = m.group(1);
			int fromx = Integer.valueOf(m.group(2));
			int fromy = Integer.valueOf(m.group(3));
			int tox = Integer.valueOf(m.group(4));			
			int toy = Integer.valueOf(m.group(5));

			if (wat.equals("turn on")) {
				for (int yy = fromy; yy<= toy; yy++) {
					for (int xx = fromx; xx <= tox; xx++) {
						grid[yy][xx]++;
					}
				}
			} else if (wat.equals("toggle")) {
				for (int yy = fromy; yy<= toy; yy++) {
					for (int xx = fromx; xx <= tox; xx++) {
						grid[yy][xx]+=2;
					}
				}
			} else if (wat.equals("turn off")) {
				for (int yy = fromy; yy<= toy; yy++) {
					for (int xx = fromx; xx <= tox; xx++) {
						grid[yy][xx]--;
						if (grid[yy][xx] < 0) {grid[yy][xx] = 0;}
					}
				}
			}

		}
		int tot = 0;
		for (int yy = 0; yy < 1000; yy++) {
			for (int xx = 0; xx < 1000; xx++) {
				tot+= grid[yy][xx];
			}
		}
		out.print("**j_ans: ");
		out.print(tot);
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

