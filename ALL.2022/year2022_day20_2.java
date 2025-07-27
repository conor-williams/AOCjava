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
class year2022_day20_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	static int gPo1 = 0;
	static Vector <BigInteger> veOrig = new Vector<>();
	static Vector <BigInteger> ve3 = new Vector<>();
	static Vector <Integer> vePos = new Vector<>();
	static Vector <Integer> vePosTmp = new Vector<>();
	static int hide = 0;

	static int sz = 0;
	public static void main(String [] args) {
		out.println("		2022 Day20.2");
		out.println("	SLOW ~50seconds");
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

                BigInteger one =  new BigInteger("1");
                BigInteger zero =  new BigInteger("0");

		for (int i = 0; i < blah.size(); i++) {
			veOrig.add(new BigInteger(blah.get(i)).multiply(new BigInteger("811589153")));
			ve3.add(new BigInteger(blah.get(i)).multiply(new BigInteger("811589153")));
		}

		sz = veOrig.size();
		for (int i = 0; i < veOrig.size(); i++) {
			vePos.add(i);
			vePosTmp.add(i);
		}
		int it = 0;
		for (int kkk = 0; kkk < 10; kkk++) {
			it = 0;
			for (int po1 = 0; po1 < sz; po1++) {
				gPo1 = po1;
				vePosTmp = new Vector(vePos);
				BigInteger mv1 = veOrig.get(it);
				int position1 = 0;
				for (int zz = 0; zz < sz; zz++) {
					if (vePos.get(zz) == po1) {position1 = zz; break;}
				}
				{
					position1 %= sz;
					Collections.reverse(vePos);
					Collections.reverse(vePos.subList(0, sz - position1));
					Collections.reverse(vePos.subList(sz - position1, sz));
				}
				for (int zz = 0; zz < sz; zz++) {
					if (vePos.get(zz) == po1) {position1 = zz; break;}
				}
				vePosTmp = new Vector(vePos);
				if (mv1.compareTo(zero) > 0) {
					BigInteger mv1Orig = mv1;
					
					int sz3 = sz-2;
					int sz4 = sz-1;
					if (mv1.compareTo(BigInteger.valueOf(sz3)) >= 0) {mv1 = mv1.mod(BigInteger.valueOf(sz4));}
				} else if (mv1.compareTo(zero) < 0) {
					BigInteger mv1Orig = mv1;
					BigInteger t = zero;
					BigInteger mv2 = mv1;
					BigInteger times = mv2.multiply(BigInteger.valueOf(-1)).divide(BigInteger.valueOf(sz-1));
					int sztmp = sz-1;
					BigInteger ttmp = times.add(one);
					mv1 = mv1.add(ttmp.multiply(BigInteger.valueOf(sztmp)));
					
					mv1 = mv1.mod(BigInteger.valueOf(sztmp));
				}
				int sz2 = sz-1;
				if (mv1.compareTo(zero) == 0 || mv1.compareTo(BigInteger.valueOf(sz)) == 0 || mv1.compareTo(BigInteger.valueOf(sz2)) == 0) {
					it++;
					continue;
				}
				int mvRep = mv1.intValue();
				{ 
					for (int zzz = 1; zzz <= mvRep; zzz++) {
						vePosTmp.set(zzz-1, vePos.get(zzz));
					}
					vePosTmp.set(mvRep, vePos.get(0));
					vePos = new Vector(vePosTmp);
					it++;
				}
			}
		}
		int end = 0;
		int endings[] = {1000, 2000, 3000};
		BigInteger sum = zero;
		int var_pos = getCurrentPositionOf(0);
		for (int i = 0; i < 3; i++) {
			int endMod = endings[i] %sz;
			int var2 = (var_pos + endMod) %sz;
			sum = sum.add(ve3.get(vePos.get(var2)));
		}

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(sum);
		out.println("");
	}
	static int getCurrentPositionOf(int val)
	{
		for (int i = 0; i < sz; i++) {
			if (ve3.get(vePos.get(i)).compareTo(BigInteger.valueOf(val)) == 0)  {
				return i;
			}
		}
		return -1;

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

