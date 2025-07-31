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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

// /java -Xmx2g year2019_day3.java *i1.txt



//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}
//// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2019_day22_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	static BigInteger ENDL = new BigInteger("119315717514047");

	static BigInteger two =  new BigInteger("2");
	static BigInteger one =  new BigInteger("1");
	static BigInteger zero =  new BigInteger("0");
	static class inst_s {
		int type;
		int val;
	}
	static int instTOT = 0;
	static inst_s inst[] = new inst_s[200];

	static BigInteger offset = zero;
	static BigInteger increment = one;

	static Vector<Integer> deck = new Vector<>();
	public static void main(String [] args) {
		out.println("		2019 Day22.2");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */

		for (int i = 0; i < 200; i++) {
			inst[i] = new inst_s();
		}
		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("cut (-?\\d+)");
		Pattern p2 = Pattern.compile("deal with increment (-?\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).equals("deal into new stack")) {
				inst[i].type = 1;
				continue;
			}

			Matcher m = p.matcher(blah.get(i));
			if (m.find()) {
				int cutNum = Integer.valueOf(m.group(1));
				inst[i].type = 2;
				inst[i].val = cutNum;
				continue;

			}

			Matcher m2 = p2.matcher(blah.get(i));
			if (m2.find()) {
				int dealInc = Integer.valueOf(m2.group(1));
				inst[i].type = 3;
				inst[i].val = dealInc;
				continue;
			}


		}
		instTOT = blah.size();
		runFOR2();
		BigInteger I1 = one.subtract(increment);
		BigInteger E2 = ENDL.subtract(two);
		offset = offset.multiply(pow2(I1, E2, ENDL));
		BigInteger second = new BigInteger("101741582076661");
		increment = pow2(increment, second, ENDL);

		BigInteger inc2020 = increment.multiply(new BigInteger("2020"));
		BigInteger oneMinus = one.subtract(increment);
		BigInteger tenE = new BigInteger("100").multiply(ENDL);

		BigInteger tt1 = oneMinus.multiply(offset);
		BigInteger tt2 = inc2020.add(tt1);

		BigInteger tt3 = (((tt2.mod(ENDL)).mod(ENDL)).add(tenE)).mod(ENDL);

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tt3);
		out.println("");
	}
	static void runFOR2() {
		for (int i = 0; i < instTOT; i++) {
			switch(inst[i].type) {
				case 1: { //into // reverse
						offset = offset.subtract(increment); 
						increment = increment.multiply(new BigInteger("-1"));

						break;
				}
				case 2:
					{ //cut
						offset = offset.add(increment.multiply(BigInteger.valueOf(inst[i].val)));
						break;
					}
				case 3:
					{ //with increment
						BigInteger in = BigInteger.valueOf(inst[i].val);
						BigInteger E = ENDL.subtract(two);
						BigInteger tm = pow2(in, E, ENDL);
						increment = increment.multiply(tm);
						break;
					}

			}

		}
	}
	static BigInteger pow2(BigInteger x, BigInteger y, BigInteger z)
	{

		BigInteger res = one;
		BigInteger x1 = x;

		if (x1.compareTo(zero) > 0) {
			x1 = x1.mod(z);
		} else if (x1.compareTo(zero) < 0) {
			BigInteger tmp1 = x1.mod(z);
			BigInteger tmp2 = new BigInteger("5000").multiply(z);
			BigInteger tmp3 = tmp1.add(tmp2);
			x1 = tmp3.mod(z);
		}

		if (x1.compareTo(zero) == 0){ return zero;}

		while (y.compareTo(zero) > 0)
		{
			int yyyy1 = y.mod(new BigInteger("10")).intValue() - 48;
			if ((yyyy1 & 1) == 1) {
				res = (res.multiply(x1)).mod(z);
			}

			y = y.divide(two);
			x1 = (x1.multiply(x1)).mod(z);
		}
		return res;

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

