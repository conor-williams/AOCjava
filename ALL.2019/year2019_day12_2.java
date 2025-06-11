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
class year2019_day12_2 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day12.2");
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
		/*sx = lenx;
		  sy = leny;
		  grid = new char[sy][sx];
		  already = new int[sy][sx];
		  for (int i = 0; i < blah.size();i++) {
		  grid[i] = blah.get(i).toCharArray();
		  }
		  */

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("<x=(-?[\\d\\s]+), y=(-?[\\d\\s]+), z=(-?[\\d\\s]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		TreTuple<Long, Long, Long> planetsPos[] = new TreTuple[4];
		TreTuple<Long, Long, Long> planetsVel[] = new TreTuple[4];
		TreTuple<Long, Long, Long> gravityTmp[] = new TreTuple[4];
		for (int ii = 0; ii < 4; ii++) {
			planetsPos[ii] = new TreTuple((long)0, (long)0, (long)0);
			planetsVel[ii] = new TreTuple((long)0, (long)0, (long)0);
			gravityTmp[ii] = new TreTuple((long)0, (long)0, (long)0);
		}
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			//TreTuple <Long, Long, Long> tu1 = new TreTuple(Long.parseLong(m.group(1)), Long.parseLong(m.group(2)), Long.parseLong(m.group(3)));
			planetsPos[i] = new TreTuple(Long.parseLong(m.group(1)), Long.parseLong(m.group(2)), Long.parseLong(m.group(3)));
			//planetsPos[i] = new TreTuple(tu1);
		}

		Vector <Long> repeatPosx[] = new Vector[4];
		Vector <Long> repeatPosy[] = new Vector[4];
		Vector <Long> repeatPosz[] = new Vector[4];
		Vector <Long> repeatVelx[] = new Vector[4];
		Vector <Long> repeatVely[] = new Vector[4];
		Vector <Long> repeatVelz[] = new Vector[4];

		for (int ii = 0; ii < 4; ii++) {
			repeatPosx[ii] = new Vector<>();
			repeatPosy[ii] = new Vector<>();
			repeatPosz[ii] = new Vector<>();

			repeatVelx[ii] = new Vector<>();
			repeatVely[ii] = new Vector<>();
			repeatVelz[ii] = new Vector<>();
		}
		int found[][] = new int[4][3];
		long lcs[][] = new long[4][3];
		long lcs3[] = new long[3];
after:
		for (long ro = 1; ro <= 1000000; ro++) {
			int f2 = 0;
after2:
			for (int ii = 0; ii < 4; ii++) {
				for (int jj = 0; jj < 3; jj++) {
					if (found[ii][jj] == 0) {
						f2 = 1;
						break after2;
					}
				}
			}
			if (f2 == 0) {break after;}

			if (ro == 1) {
				for (int ii = 0; ii < 4; ii++) {
					//var tu1 = new TreTuple(planetsPos[ii].first, planetsPos[ii].second, planetsPos[ii].third);
					repeatPosx[ii].add(planetsPos[ii].first);
					repeatPosy[ii].add(planetsPos[ii].second);
					repeatPosz[ii].add(planetsPos[ii].third);
					//var tu2 = new TreTuple(planetsVel[ii].first, planetsVel[ii].second, planetsVel[ii].third);
					repeatVelx[ii].add(planetsVel[ii].first);
					repeatVely[ii].add(planetsVel[ii].second);
					repeatVelz[ii].add(planetsVel[ii].third);
				}
			}

			for (int ii = 0; ii < 4; ii++) {
				gravityTmp[ii] = new TreTuple((long)0, (long)0, (long)0);
			}
			for (int ii = 0; ii < 4; ii++) {
				var p1Pos = planetsPos[ii];
				TreTuple <Long, Long, Long> tmp1 = new TreTuple((long)0, (long)0, (long)0);

				for (int jj = 0; jj < 4; jj++) {
					if (ii == jj) {continue;}
					var p2Pos = planetsPos[jj];

					if (p1Pos.first > p2Pos.first) {
						tmp1.first--;
					} else if (p1Pos.first < p2Pos.first) {
						tmp1.first++;
					} else  {
					}

					if (p1Pos.second > p2Pos.second) {
						tmp1.second--;
					} else if (p1Pos.second < p2Pos.second) {
						tmp1.second++;
					} else {
					}

					if (p1Pos.third > p2Pos.third) {
						tmp1.third--;
					} else if (p1Pos.third < p2Pos.third) {
						tmp1.third++;
					} else {
					}
				}
				gravityTmp[ii] = new TreTuple(tmp1);
			}

			for (int ii = 0; ii < 4; ii++) {
				planetsVel[ii].first += gravityTmp[ii].first;
				planetsVel[ii].second += gravityTmp[ii].second;
				planetsVel[ii].third += gravityTmp[ii].third;
			}
			for (int ii = 0; ii < 4; ii++) {
				planetsPos[ii].first += planetsVel[ii].first;
				planetsPos[ii].second += planetsVel[ii].second;
				planetsPos[ii].third += planetsVel[ii].third;
			}

			for (int ii = 0; ii < 4; ii++) {
				if (found[ii][0] == 0) {
					int vv = 0;
					if ((vv = repeatPosx[ii].indexOf(planetsPos[ii].first)) ==  repeatVelx[ii].indexOf(planetsVel[ii].first) && vv != -1) {
						lcs[ii][0] = ro;
						lcs3[0] = ro;
						found[ii][0] = 1;
						out.println(lcs[ii][0]);

					}
				}
				if (found[ii][1] == 0) {
					int vv = 0;
					if ((vv = repeatPosy[ii].indexOf(planetsPos[ii].second)) == repeatVely[ii].indexOf(planetsVel[ii].second) && vv != -1) {
						lcs[ii][1] = ro;
						lcs3[1] = ro;
						found[ii][1] = 1;
						out.println(lcs[ii][1]);
					}
				}
				if (found[ii][2] == 0) {
					int vv = 0;
					if ((vv = repeatPosz[ii].indexOf(planetsPos[ii].third)) == repeatVelz[ii].indexOf(planetsVel[ii].third) && vv != -1) {
						lcs[ii][2] = ro;
						lcs3[2] = ro;
						found[ii][2] = 1;
						out.println(lcs[ii][2]);
					}
				}
			}
			for (int zzz = 0; zzz < 3; zzz++) {
				int goodone = 0;
				for (int ii = 0; ii < 4; ii++) {
					if (found[ii][zzz] != 1) {
						goodone = 1;
						break;
					}
				}
				if (goodone == 0) {
					out.println(lcs[zzz]);
				} else {
					for (int ii = 0; ii < 4; ii++) {
						found[ii][zzz] = 0;
					}
				}
			}

		}
		System.setOut(originalOut);	
		out.print("**j_ans: ");
		out.print(lcmOfArray(lcs3));
		out.println("");

	}
	// Function to calculate GCD using Euclid's algorithm
	private static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	// Function to calculate LCM of two numbers
	private static long lcm(long a, long b) {
		return (a / gcd(a, b)) * b;
	}

	// Function to calculate LCM of an array of numbers
	public static long lcmOfArray(long[] numbers) {
		long result = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			result = lcm(result, numbers[i]);
		}
		return result;
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


@SuppressWarnings("unchecked")
class TreTuple<X,Y, Z> {
	public X first;
	public Y second;
	public Z third;

	public TreTuple(Object o) {
		TreTuple tu2 = (TreTuple) o;
		this.first = (X)tu2.first;
		this.second = (Y)tu2.second;
		this.third = (Z)tu2.third;
	}
	public TreTuple(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTuple tu2 = (TreTuple) o;
		if (this == o) return true;
		if (!(o instanceof TreTuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}
		if (!this.third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}

