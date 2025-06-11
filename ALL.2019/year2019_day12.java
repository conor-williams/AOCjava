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
class year2019_day12 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day12.1");
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
		for (int ro = 1; ro <= 1000; ro++) {
			if (ro == 1) {
				out.print("Round (before): "); out.println(ro);
				for (int ii = 0; ii < 4; ii++) {
					out.print("pos: "); out.print(planetsPos[ii].first); out.print(" "); out.print(planetsPos[ii].second); out.print(" "); out.print(planetsPos[ii].third); out.print(" ");
					out.print(" vel: "); out.print(planetsVel[ii].first); out.print(" "); out.print(planetsVel[ii].second); out.print(" "); out.print(planetsVel[ii].third); out.println();

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
			if (ro % 10 == 0 || ro == 1) {
				out.print("Round: "); out.println(ro);
				for (int ii = 0; ii < 4; ii++) {
					out.print("pos: "); out.print(planetsPos[ii].first); out.print(" "); out.print(planetsPos[ii].second); out.print(" "); out.print(planetsPos[ii].third); out.print(" ");
					out.print(" vel: "); out.print(planetsVel[ii].first); out.print(" "); out.print(planetsVel[ii].second); out.print(" "); out.print(planetsVel[ii].third); out.println();

				}
			}
		}
		for (int ii = 0; ii < 4; ii++) {
			out.print("pos: "); out.print(planetsPos[ii].first); out.print(" "); out.print(planetsPos[ii].second); out.print(" "); out.print(planetsPos[ii].third); out.print(" ");
			out.print(" vel: "); out.print(planetsVel[ii].first); out.print(" "); out.print(planetsVel[ii].second); out.print(" "); out.print(planetsVel[ii].third); out.println();

		}
		long tot = 0;
		for (int ii = 0; ii < 4; ii++) {
			long en1 = Math.abs(planetsPos[ii].first) + Math.abs(planetsPos[ii].second) + Math.abs(planetsPos[ii].third);
			long en2 = Math.abs(planetsVel[ii].first) + Math.abs(planetsVel[ii].second) + Math.abs(planetsVel[ii].third);
			long ee = en1 *en2;
			tot += ee;

		}
		System.setOut(originalOut);
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

