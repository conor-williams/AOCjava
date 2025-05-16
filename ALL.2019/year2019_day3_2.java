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


class year2019_day3_2 {
	public static int sz = 16500;
	public static char [][] grid = new char[sz][sz];
	//public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day3.2");
		out.println("	/java -Xmx3g year2019_day3_2.java *ex2.txt");

		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Pattern p = Pattern.compile("(U|R|D|L)(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		int [][] trip1 = new int[sz][sz];
		int [][] trip2 = new int[sz][sz];
		for (int i = 0; i < blah.size(); i++) {
			int lenlen = 0;
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			Vector <Integer> var_ints = new Vector<>();
			int x = sz/2;
			int y = sz/2;
			if (i == 1) {
				grid[y][x] = '1';
			} else if (i == 2) {
				if (grid[y][x] == '1') {
					grid[y][x] = '3';
				} else {
					grid[y][x] = '2';
				}
			}
			while (scanner.hasNext()) {
				String ne = scanner.next();
				Matcher m = p.matcher(ne);
				m.find();
				char URDL = m.group(1).charAt(0);
				int val = Integer.valueOf(m.group(2));
				switch (URDL) {
					case 'U':
						for (int yy = 0; yy < val; yy++) {
							y--;
							lenlen++;
							if (i == 0) {
								grid[y][x] = '1';
								if (trip1[y][x] == 0) {
									trip1[y][x] = lenlen;
								}
							} else if (i == 1) {
								if (trip2[y][x] == 0) {
									trip2[y][x] = lenlen;
								}
								if (grid[y][x] == '1' || grid[y][x] == '3') {
									grid[y][x] = '3';
								} else {
									grid[y][x] = '2';
								}
							}
						}
						break;
					case 'R':
						for (int xx = 0; xx < val; xx++) {
							x++;
							lenlen++;
							if (i == 0) {
								if (trip1[y][x] == 0) {
									trip1[y][x] = lenlen;
								}
								grid[y][x] = '1';
							} else if (i == 1) {
								if (trip2[y][x] == 0) {
									trip2[y][x] = lenlen;
								}
								if (grid[y][x] == '1' || grid[y][x] == '3') {
									grid[y][x] = '3';
								} else {
									grid[y][x] = '2';
								}
							}
						}
						break;
					case 'D':
						for (int yy = 0; yy < val; yy++) {
							y++;
							lenlen++;
							if (i == 0) {
								if (trip1[y][x] == 0) {
									trip1[y][x] = lenlen;
								}
								grid[y][x] = '1';
							} else if (i == 1) {
								if (trip2[y][x] == 0) {
									trip2[y][x] = lenlen;
								}
								if (grid[y][x] == '1' || grid[y][x] == '3') {
									grid[y][x] = '3';
								} else {
									grid[y][x] = '2';
								}
							}
						}
						break;
					case 'L':
						for (int xx = 0; xx < val; xx++) {
							x--;
							lenlen++;
							if (i == 0) {
								if (trip1[y][x] == 0) {
									trip1[y][x] = lenlen;
								}
								grid[y][x] = '1';
							} else if (i == 1) {
								if (trip2[y][x] == 0) {
									trip2[y][x] = lenlen;
								}
								if (grid[y][x] == '1' || grid[y][x] == '3') {
									grid[y][x] = '3';
								} else {
									grid[y][x] = '2';
								}
							}
						}
						break;
				}

			}

		}
		int minlen = 9999999;
		for (int yy = 0; yy < sz; yy++) {
			for (int xx = 0; xx < sz; xx++) {
				if (grid[yy][xx] == '3') {
					int len = trip1[yy][xx] + trip2[yy][xx];
					//out.println(trip1[yy][xx]); out.println(trip2[yy][xx]); out.println("------");
					if (len < minlen) {minlen = len;}
				}
			}
		}

		out.print("**j_ans: ");
		out.print(minlen);
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

