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
class year2024_day8 {
	public static int sy = 50;
	public static int sx = 50;
	public static char [][] grid = new char[sy][sx];

	public static void main(String [] args) {
		out.println("		2024 Day8.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			int leny = 0;
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

		Vector <Tuple <Integer, Integer>> [] carsUp = new Vector[26];
		for (int ii = 0; ii < 26; ii++) {
			carsUp[ii] = new Vector<>();
		}
		Vector <Tuple <Integer, Integer>> [] carsLo = new Vector[26];
		for (int ii = 0; ii < 26; ii++) {
			carsLo[ii] = new Vector<>();
		}
		Vector <Tuple <Integer, Integer>> [] carsDi = new Vector[10];
		for (int ii = 0; ii < 10; ii++) {
			carsDi[ii] = new Vector<>();
		}
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				boolean isUpp = Character.isUpperCase(grid[yy][xx]);
				if (isUpp) {
					int index = grid[yy][xx] - 'A';

					Tuple <Integer, Integer> tu = new Tuple(xx, yy);
					carsUp[index].add(tu);
					continue;
				} 
				boolean isLow = Character.isLowerCase(grid[yy][xx]);
				if (isLow) {
					int index = grid[yy][xx] - 'a';
					Tuple <Integer, Integer> tu = new Tuple(xx, yy);
					//out.println(yy); out.println(xx); out.println(grid[yy][xx]); out.print("index: "); out.println(index);
					carsLo[index].add(tu);
					continue;
				}
				boolean isDi = Character.isDigit(grid[yy][xx]);
				if (isDi) {
					int index = grid[yy][xx] - '0';
					Tuple <Integer, Integer> tu = new Tuple(xx, yy);
					carsDi[index].add(tu);
					continue;
				}
			}
		}

		Vector <Tuple<Integer, Integer>> ans = new Vector<>();

		for (int ii = 0; ii < 26; ii++) {
			if (carsUp[ii].size() == 0) {continue;}
			for (int ll = 0; ll < carsUp[ii].size()-1; ll++) {
				var tu1 = carsUp[ii].get(ll);
				for (int kk = ll+1; kk < carsUp[ii].size(); kk++) {
					var tu2 = carsUp[ii].get(kk);

					int distx1 = tu2.first - tu1.first;
					int disty1 = tu2.second - tu1.second;
					int distx2 = tu1.first - tu2.first;
					int disty2 = tu1.second - tu2.second;

					int newx1 = tu1.first - distx1;
					int newy1 = tu1.second - disty1;
					int newx2 = tu2.first - distx2;
					int newy2 = tu2.second - disty2;


					Tuple <Integer, Integer> tmp1 = new Tuple(newx1, newy1);
					Tuple <Integer, Integer> tmp2 = new Tuple(newx2, newy2);

					if (!tmp1.equals(tu1) && !tmp1.equals(tu2)) {
						if (tmp1.first >= sx || tmp1.second >= sy || tmp1.first <0 || tmp1.second <0) {
						} else {
							if (!ans.contains(tmp1)) {
								ans.add(tmp1);
							}
						}
					} else {
						out.println("equal");
					}

					if (!tmp2.equals(tu1) && !tmp2.equals(tu2)) {
						if (tmp2.first >= sx || tmp2.second >= sy || tmp2.first <0 || tmp2.second <0) {
						} else {
							if (!ans.contains(tmp2)) {
								ans.add(tmp2);
							}
						}
					} else {
						out.println("equal");
					}

				}

			}
		}
		for (int ii = 0; ii < 26; ii++) {
			if (carsLo[ii].size() == 0) {continue;}
			for (int ll = 0; ll < carsLo[ii].size()-1; ll++) {
				var tu1 = carsLo[ii].get(ll);
				for (int kk = ll+1; kk < carsLo[ii].size(); kk++) {
					var tu2 = carsLo[ii].get(kk);

					int distx1 = tu2.first - tu1.first;
					int disty1 = tu2.second - tu1.second;
					int distx2 = tu1.first - tu2.first;
					int disty2 = tu1.second - tu2.second;

					int newx1 = tu1.first - distx1;
					int newy1 = tu1.second - disty1;
					int newx2 = tu2.first - distx2;
					int newy2 = tu2.second - disty2;


					Tuple <Integer, Integer> tmp1 = new Tuple(newx1, newy1);
					Tuple <Integer, Integer> tmp2 = new Tuple(newx2, newy2);

					if (!tmp1.equals(tu1) && !tmp1.equals(tu2)) {
						if (tmp1.first >= sx || tmp1.second >= sy || tmp1.first <0 || tmp1.second <0) {
						} else {
							if (!ans.contains(tmp1)) {
								ans.add(tmp1);
							}
						}
					} else {
						out.println("equal");
					}

					if (!tmp2.equals(tu1) && !tmp2.equals(tu2)) {
						if (tmp2.first >= sx || tmp2.second >= sy || tmp2.first <0 || tmp2.second <0) {
						} else {
							if (!ans.contains(tmp2)) {
								ans.add(tmp2);
							}
						}
					} else {
						out.println("equal");
					}

				}

			}
		}
		for (int ii = 0; ii < 10; ii++) {
			if (carsDi[ii].size() == 0) {continue;}
			for (int ll = 0; ll < carsDi[ii].size()-1; ll++) {
				var tu1 = carsDi[ii].get(ll);
				for (int kk = ll+1; kk < carsDi[ii].size(); kk++) {
					var tu2 = carsDi[ii].get(kk);

					int distx1 = tu2.first - tu1.first;
					int disty1 = tu2.second - tu1.second;
					int distx2 = tu1.first - tu2.first;
					int disty2 = tu1.second - tu2.second;

					int newx1 = tu1.first - distx1;
					int newy1 = tu1.second - disty1;
					int newx2 = tu2.first - distx2;
					int newy2 = tu2.second - disty2;


					Tuple <Integer, Integer> tmp1 = new Tuple(newx1, newy1);
					Tuple <Integer, Integer> tmp2 = new Tuple(newx2, newy2);

					if (!tmp1.equals(tu1) && !tmp1.equals(tu2)) {
						if (tmp1.first >= sx || tmp1.second >= sy || tmp1.first <0 || tmp1.second <0) {
						} else {
							if (!ans.contains(tmp1)) {
								ans.add(tmp1);
							}
						}
					} else {
						out.println("equal");
					}

					if (!tmp2.equals(tu1) && !tmp2.equals(tu2)) {
						if (tmp2.first >= sx || tmp2.second >= sy || tmp2.first <0 || tmp2.second <0) {
						} else {
							if (!ans.contains(tmp2)) {
								ans.add(tmp2);
							}
						}
					} else {
						out.println("equal");
					}

				}

			}
		}

		//		System.setOut(originalOut);
		/*
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				int found = 0;
				for (int kk = 0; kk < ans.size(); kk++) {
					var tu1 = ans.get(kk);
					if (tu1.first == xx && tu1.second == yy) {
						out.print('#');
						found = 1;
						break;
					}
				}
				if (found == 0) {
					out.print(grid[yy][xx]);
				}
			}
			out.println();
		}
		out.println();
		*/
		out.print("**j_ans: ");
		out.print(ans.size());
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

