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
class year2024_day22_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	static Deque<Long> secrets = new ArrayDeque<>();
	static int TIMES = 2000;

	static Map <QuadTuple<Integer, Integer, Integer, Integer>, Integer> sellerMonkey[] = new HashMap[3000];
	static Set <QuadTuple<Integer, Integer, Integer, Integer>> seqset = new HashSet<>();
	static Deque <QuadTuple<Integer, Integer, Integer, Integer>> deQ = new ArrayDeque<>();
	static QuadTuple<Integer, Integer, Integer, Integer> maxtu = new QuadTuple(0, 0, 0, 0);

	static class diffs_s {
		int val;
		int dif;
	}
	static long maxSum = 0;
	static diffs_s diffs[][] = new diffs_s[3000][2001];


	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day22.2");
		out.println("	SLOW ~1minute");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
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

		for (int ii = 0; ii < 3000; ii++) {
			sellerMonkey[ii] = new HashMap<>();
		}
		for (int ii = 0; ii < 3000; ii++) {
			for (int jj = 0; jj < 2001; jj++) {
				diffs[ii][jj] = new diffs_s();
			}
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
		for (int i = 0; i < blah.size(); i++) {
			secrets.addLast(Long.parseLong(blah.get(i)));
		}



		for (int i = 0; i < TIMES;i++) {
			Deque <Long> secretsTmp = new ArrayDeque<>();
			int numpos = 0;
			for (var snum: secrets) {
				long snumBef = snum;
				long mul1 = snum * 64;
				snum = mix(snum, mul1);
				snum = prune(snum);
				long div1 = snum / 32;
				snum = mix(snum, div1);
				snum = prune(snum);
				long mul2 = snum * 2048;
				snum = mix(snum, mul2);
				snum = prune(snum);
				secretsTmp.addLast(snum);

				diffs[numpos][i].dif = (((int)(long)snum % 10) - ((int)(long)snumBef % 10));
				diffs[numpos][i].val = ((int)(long)snum % 10);
				numpos++;
			}
			secrets = new ArrayDeque(secretsTmp);
		}

		for (int numpos = 0; numpos < leny; numpos++) {
			for (int i = 3; i < TIMES; i++) {
				int one = diffs[numpos][i-3].dif;
				int two = diffs[numpos][i-2].dif;
				int three = diffs[numpos][i-1].dif;
				int four = diffs[numpos][i].dif;
				QuadTuple <Integer, Integer, Integer, Integer> tu1 = new QuadTuple(one, two, three, four);
				int v5 = diffs[numpos][i].val;

				if (!sellerMonkey[numpos].containsKey(tu1)) {
					//sellerMonkey[numpos][tu1] = v5;
					sellerMonkey[numpos].put(tu1, v5);
					if (!seqset.contains(tu1)) {
						seqset.add(tu1);
						deQ.addLast(tu1);
					}
				} else {
				}
			}
		}
		long tmpSum = 0;
		int go = 0;
		for (var tu: deQ) {
			go = 0;
			tmpSum = 0;
next:
			for (int numpos = 0; numpos < leny; numpos++) {
				go = 0;
				//look ahead
				if ((((leny - numpos) * 9) + tmpSum) < maxSum) {go = 1; break next;}
				int ind1 = 0;
				if (sellerMonkey[numpos].containsKey(tu)) {
					tmpSum += sellerMonkey[numpos].get(tu);
				}
			}
			if (go == 0) {
				if (tmpSum > maxSum) {maxtu = tu; maxSum = tmpSum;}
			}
			continue;
		}

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxSum);
		out.println("");
	}
	static long mix(long secret, long inin) {
		return inin ^ secret;

	}
	static long prune(long inin) {
		return inin % 16777216;
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

