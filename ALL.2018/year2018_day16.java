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


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2018_day16 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int numBlanks = 0;
	public static int regb[] = new int[5];
	public static int op, regA, regB, regC;
	public static int ans[][] = new int[17][5];
	public static int rega[] = new int[5];

	public static int tot = 0;
	public static void main(String [] args) {
		out.println("		2018 Day16.1");
		out.flush();
		Vector<String> blah = new Vector<>();
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
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("Before: \\[(\\d+), (\\d+), (\\d+), (\\d+)\\]");
		Pattern p2 = Pattern.compile("(\\d+) (\\d+) (\\d+) (\\d+)");
		Pattern p3 = Pattern.compile("After:  \\[(\\d+), (\\d+), (\\d+), (\\d+)\\]");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m1 = p1.matcher(blah.get(i));
			if (m1.find()) {
				out.println("Before");
				numBlanks = 0;
				regb[0] = Integer.valueOf(m1.group(1));
				regb[1] = Integer.valueOf(m1.group(2));
				regb[2] = Integer.valueOf(m1.group(3));
				regb[3] = Integer.valueOf(m1.group(4));
				continue;
			}
			Matcher m2 = p2.matcher(blah.get(i));
			if (m2.find()) {
				out.println("Middle");
				op = Integer.valueOf(m2.group(1));
				regA = Integer.valueOf(m2.group(2));
				regB = Integer.valueOf(m2.group(3));
				regC = Integer.valueOf(m2.group(4));

				int regbOrig[] = new int [4];
				for (int ii = 0; ii < 4; ii++) {
					regbOrig[ii] = regb[ii];
				}
				for (int ii = 0; ii < 16; ii++) {
					for (int kk = 0; kk < 4; kk++) {
						regb[kk] = regbOrig[kk];
					}
					switch(ii) {
						case 0: //addr
							regb[regC] = regb[regA] + regb[regB];
							break;
						case 1: //addi
							regb[regC] = regb[regA] + regB;
							break;
						case 2: //mulr
							regb[regC] = regb[regA] * regb[regB];
							break;
						case 3: //muli
							regb[regC] = regb[regA] * regB;
							break;
						case 4: //banr
							regb[regC] = regb[regA] & regb[regB];
							break;
						case 5: //bani
							regb[regC] = regb[regA] & regB;
							break;
						case 6: //borr
							regb[regC] = regb[regA] | regb[regB];
							break;
						case 7: //bori
							regb[regC] = regb[regA] | regB;
							break;
						case 8: //setr
							regb[regC] = regb[regA];
							break;
						case 9: //seti
							regb[regC] = regA;
							break;
						case 10: //gtir
							if (regA > regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 11: //gtir
							if (regb[regA] > regB) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 12: //gtrr
							if (regb[regA] > regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 13: //eqir
							if (regA == regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 14:
							if (regb[regA] == regB) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 15:
							if (regb[regA] == regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						default:
							out.println("ERR");
					}
					ans[ii][0] = regb[0];
					ans[ii][1] = regb[1];
					ans[ii][2] = regb[2];
					ans[ii][3] = regb[3];

				}
				numBlanks = 0;
				continue;
			}
			Matcher m3 = p3.matcher(blah.get(i));
			if (m3.find()) {
				out.println("here3");
				rega[0] = Integer.valueOf(m3.group(1));
				rega[1] = Integer.valueOf(m3.group(2));
				rega[2] = Integer.valueOf(m3.group(3));
				rega[3] = Integer.valueOf(m3.group(4));

				int count = 0;
				for (int ii = 0; ii < 16; ii++) {
					if (ans[ii][0] == rega[0] && ans[ii][1] == rega[1] && ans[ii][2] == rega[2] && ans[ii][3] == rega[3]) {
                                		count++;
					}
				}
				if (count >= 3) {
					tot++;
				}
				numBlanks = 0;
				continue;
                        }
			if (blah.get(i).length() == 0) {
				numBlanks++;
				if (numBlanks > 2) {break;}
				continue;
			}

		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}
}

class Tuple<X,Y > {
	public X first;
	public Y second;

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

@SuppressWarnings("unchecked")
class QuadTuple<X,Y, Z, W> {
	public X first;
	public Y second;
	public Z third;
	public W fourth;

	public QuadTuple(X first, Y second, Z third, W fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	@Override
	public boolean equals(Object o) {
		QuadTuple tu2 = (QuadTuple) o;
		if (this == o) return true;
		if (!(o instanceof QuadTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth);
	}

}

@SuppressWarnings("unchecked")
class CinqTuple<X,Y, Z, V, W> {
	public X first;
	public Y second;
	public Z third;
	public V fourth;
	public W fifth;

	public CinqTuple(X first, Y second, Z third, V fourth, W fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
	}
	@Override
	public boolean equals(Object o) {
		CinqTuple tu2 = (CinqTuple) o;
		if (this == o) return true;
		if (!(o instanceof CinqTuple)) return false;

		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		if (!fourth.equals(tu2.fourth)) {return false;}
		if (!fifth.equals(tu2.fifth)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third, fourth, fifth);
	}

}

