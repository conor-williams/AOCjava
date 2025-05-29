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
class year2022_day9 {
	public static int sx = 7000;
	public static int sy = 7000;
	public static int headx = sx/2;
	public static int heady = sy/2;
	public static int tailx = sx/2;
	public static int taily = sy/2;
	public static int [][] wherebeen = new int[sy][sx];

	public static void main(String [] args) {
		out.println("		2022 Day9.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(L|R|U|D) (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		wherebeen[taily][tailx]++;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			char LRUD = m.group(1).charAt(0);
			int dist = Integer.valueOf(m.group(2));

			switch (LRUD) {
				case 'U':
					for (int ii = 0; ii < dist; ii++) {
						heady--;
						movetail();
					}
					break;
				case 'R':
					for (int ii = 0; ii < dist; ii++) {
						headx++;
						movetail();
					}
					break;
				case 'D':
					for (int ii = 0; ii < dist; ii++) {
						heady++;
						movetail();
					}			
					break;
				case 'L':
					for (int ii = 0; ii < dist; ii++) {
						headx--;
						movetail();
					}
					break;
				default:
					out.println("ERR");
					Runtime.getRuntime().halt(0);
			}

		}
		long ans = 0;
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (wherebeen[yy][xx] > 0) {
					ans++;
				}
			}
		}
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}

	public static void show() {
		for (int y = (sy/2) -15; y < (sy/2)+15; y++) {
			for (int x = (sx/2)-15; x < (sx/2)+15; x++) {
				if (headx == x && heady == y) {
					out.print("H");
				} else if (tailx == x && taily == y) {
					out.print("T");
				} else {
					out.print(".");
				}
			}
			out.println();
		}
		out.println();
	}

	public static void movetail() {
		if (taily == heady && tailx == headx) {
			wherebeen[taily][tailx]++;
		} else if (taily == heady) { //left right same line
			if (Math.abs(tailx - headx) > 1) {
				if (tailx > headx) { tailx--; } else { tailx++; }
				wherebeen[taily][tailx]++;
			}
		} else if (tailx == headx) {
			if (Math.abs(heady - taily) > 1) {
				if (taily > heady) { taily--; } else { taily++; }
				wherebeen[taily][tailx]++;
			}
		} else { // off line
			if (Math.abs(headx - tailx) + Math.abs(heady-taily) > 2) {
				if (tailx > headx){ tailx--;} else { tailx++;}
				if (taily > heady){ taily--;} else { taily++;}
				wherebeen[taily][tailx]++;
			}
		}
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

