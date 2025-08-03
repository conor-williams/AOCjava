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
class year2024_day23_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	static Map<Tuple<String, String>, Integer> mp = new HashMap<>();
	static Map<String, Integer> mpOne = new HashMap<>();
	static int maxNumConnected = 0;
	static Vector <String> maxOldList = new Vector<>();
	static Vector <Tuple <String, Integer>> A = new Vector<>();

	public static void main(String [] args) {
		out.println("		2024 Day23.2");
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


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([a-z]{2})-([a-z]{2})");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			mp.put(new Tuple(m.group(1), m.group(2)), 1);
			mp.put(new Tuple(m.group(2), m.group(1)), 1);
			mpOne.put(m.group(1), mpOne.getOrDefault(m.group(1), 0) + 1);
			mpOne.put(m.group(2), mpOne.getOrDefault(m.group(2), 0) + 1);
		}

		for (var it : mpOne.entrySet()) {A.add(new Tuple(it.getKey(), it.getValue()));}
		A.sort((s1, s2) -> cmp(s1, s2));

		//DO
		Vector <String> old = new Vector<>();
		int numConnected = 0;
		//all are 13
		int pos1 = 0;
after:
		for (var it1: A) {
			pos1++;
			if (it1.second <= maxNumConnected) {break after;}
			//if (distance(it1, A.end()) <= maxNumConnected) {break after;}
			String st1 = it1.first;
			int pos2 = 0;
aa1:
			for (var it2: A) {
				pos2++;
				while (pos2 <= pos1) {continue aa1;}
				if (it2.second <= maxNumConnected) {break after;}
				//if (distance(it2, A.end()) <= maxNumConnected-1) {goto ne2;}
				String st2 = it2.first;
				old.clear();
				if (mp.get(new Tuple(st1, st2)) != null) {
					old.add(st1); old.add(st2);
					int pos3 = 0;
aa2:
					for (var it10: A) {
						pos3++;
						while (pos3 <= pos2) {continue aa2;}
						String st10 = it10.first;
						if (it10.second <= maxNumConnected) {break after;}
						numConnected = 2;
						recSearch(st10, old, numConnected, pos3);
					}
				}
			}
		}
		Collections.sort(maxOldList);
		String ans1 = new String();;
		for (var st1: maxOldList) {
			ans1 += st1 + ",";
		}
		StringBuilder sb = new StringBuilder(ans1);;
		sb.deleteCharAt(ans1.length()-1);
		ans1 = sb.toString();

		//
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans1);
		out.println("");
	}
	static int recSearch(String justadded, Vector <String> oldList, int numConnected, int pos4) {
		for (var st1: oldList) {
			if (mp.get(new Tuple(justadded, st1)) == null) {
				return -1;
			}
		}
		oldList.add(justadded);
		numConnected++;
		if (numConnected > maxNumConnected) {maxOldList = new Vector(oldList); maxNumConnected = numConnected;}

		//incomingIt++;
		int pos5 = 0;
aa5:
		for (var it4: A) {
			pos5++;
			while (pos5 <= pos4) {continue aa5;}
			//if ((numConnected + distance(it4, A.end())) <= maxNumConnected) {return -3;}
			recSearch(it4.first, oldList, numConnected, pos5);
		}
		return 0;
	}
	static int cmp(Tuple<String, Integer> a, Tuple<String, Integer> b)
	{
		if(a.second.equals(b.second)) {
			//return a.first.compareTo(b.first) < 0;
			return a.first.compareTo(b.first);
		}
		return a.second.compareTo(b.second);
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

