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
class year2015_day15_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day15.2");
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
		Pattern p = Pattern.compile("([a-zA-Z]+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <String, Vector <Integer>> mp = new HashMap<>();
		Vector<Integer> ve2 [] = new Vector[blah.size()];
		int calories[] = new int[blah.size()];
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			Vector <Integer> ve = new Vector<>();
			for (int ii = 2; ii < 2+4; ii++) {
				ve.add(Integer.valueOf(m.group(ii)));
			}
			calories[i] = Integer.valueOf(m.group(6));
			out.println(calories[i]);
			mp.put(m.group(1), ve);
			ve2[i] = new Vector(ve);
		}
		int targetSum = 100;
		int numberCount = blah.size();
		out.print("numberCount:"); out.println(numberCount);
		List<List<Integer>> combinations = new ArrayList<>();
		//findCombinations(combinations, new ArrayList<>(), targetSum, numberCount, 1);

		for (int aa = 0; aa < 100; aa++) {
			for (int bb = 0; bb < 100; bb++) {
				if (aa+bb >100) {continue;}
				for (int cc = 0; cc < 100; cc++) {
					if (aa+bb+cc >100) {continue;}
					for (int dd = 0; dd < 100; dd++) {
						List <Integer> comb = new ArrayList<>();
						if (aa + bb + cc + dd == 100) {
							comb.add(aa);
							comb.add(bb);
							comb.add(cc);
							comb.add(dd);
							combinations.add(comb);
						}
					}
				}
			}
		}

		long max = 0;
		int ma [] = new int[numberCount];
		long mul [] = new long[4];
		for (List<Integer> combination : combinations) {
			Vector <Long> ve3 = new Vector<>();
			int cals = 0;
			for (int ii = 0; ii < numberCount; ii++) {
				cals += combination.get(ii) * calories[ii];
			}
			if (cals != 500) {continue;}
			for (int jj = 0; jj < 4; jj++) {//contains
				

				long tmp1 = 0;
				for (int ii = 0; ii < numberCount; ii++) {//ingred
					//out.println(combination.get(ii));
					tmp1 += combination.get(ii) * ve2[ii].get(jj);
					//out.println(ve2[ii].get(jj));
					//Scanner scanner = new Scanner(System.in); scanner.nextLine();
				}
				if (tmp1 < 0) {tmp1 = 0;}
				ve3.add(tmp1);
			}
			long prod1 = 1;
			for (int kk = 0; kk < 4; kk++) {
				prod1 *= ve3.get(kk);
			}
			if (prod1 > max) {
				max = prod1; 
				for (int kk = 0; kk < numberCount; kk++) {
					ma[kk] = combination.get(kk);
				}
				for (int kk = 0; kk < 4; kk++) {
					mul[kk] = ve3.get(kk);
				}
			}
		}

		for (int kk = 0; kk < 4; kk++) {
			out.println(mul[kk]);
		}
		for (int kk = 0; kk < numberCount; kk++) {
			out.println(ma[kk]);
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(max);
		out.println("");
	}
	private static void findCombinations(List<List<Integer>> result, List<Integer> current, int target, int count, int start) {
		if (count == 0) {
			if (target == 0) {
				result.add(new ArrayList<>(current));
			}
			return;
		}

		for (int i = start; i <= target; i++) {
			current.add(i);
			findCombinations(result, current, target - i, count - 1, i);
			current.remove(current.size() - 1);
		}
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

