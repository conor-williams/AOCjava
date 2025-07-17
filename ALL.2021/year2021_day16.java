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
class year2021_day16 {
	//	        public static int maxPath = 0;
	public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static Map <String, Integer> mp2 = new HashMap<>();
	public static Map <Integer, String> mp = new HashMap<>();
	//public static char bitLine[] = new char[lenx*4];
	public static String bitLine = new String("");
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int vvtot = 0;
	public static String bitLineOrig = new String("");
	public static int bitLineOrigPos = 0;
	public static void main(String [] args) {
		out.println("		2021 Day16.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		//bitLine = new char[lenx*4];
		mp.put(0, "000");
		mp.put(1, "001"); mp.put(2, "010"); mp.put(3, "011"); mp.put(4, "100");
		mp.put(5, "101"); mp.put(6, "110"); mp.put(7, "111");

		mp2.put("000", 0); mp2.put("001", 1); mp2.put("010", 2); mp2.put("011", 3);
		mp2.put("100", 4); mp2.put("101", 5); mp2.put("110", 6); mp2.put("111", 7);


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
			for (int ii = 0; ii < blah.get(i).length(); ii++) {
				String character = ""+blah.get(i).charAt(ii);
				int decimal = Integer.parseInt(character, 16);
				String binary = Integer.toString(decimal, 2);
				StringBuilder sb = new StringBuilder();
				for (int jj = 0; jj < 4 - binary.length(); jj++) {
					sb.append("0");
				}
				sb.append(binary);
				bitLine += sb.toString();
			}
			out.println(bitLine);
			out.println(bitLine.length());
			out.println(blah.get(i).length());
			out.println(bitLine.length()%blah.get(i).length());
			out.println(bitLine.length() / blah.get(i).length());
			String bitLineS = new String(bitLine);
			bitLineOrig = new String(bitLineS);
			bitLineOrigPos = 0;
			int bla2 = 0;
			out.println(bitLineS);
			bla2 = getitit(bitLineS, bla2);
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(vvtot);
		out.println("");
	}
	public static int ind = 0;
	public static int getitit(String bitLineS, int subCounter) {
		ind++;

		int found = 0;
		for (int zz = 0; zz < (int)bitLineS.length(); zz++) {
			if (bitLineS.charAt(zz) == '1') {
				found = 1; break;
			}
		}
		if (found == 0) {return subCounter;}

		String version = bitLineS.substring(0, 3);
		out.print("version: "); out.println(version);
		vvtot += mp2.get(version);
		String type = bitLineS.substring(3, 3+3);
		out.print("type: "); out.println(type);

		if (mp2.get(type) == 4) { //literal packet
			String literal = bitLineS.substring(6); //, bitLineS.length()-1);
			int start = 0;
			if (literal.length() > 0) {
				String lit = new String("");
				while (true) {
					String onebit = literal.substring(start, start+1);
					String fourbit = literal.substring(start+1, start+1+4);
					lit += fourbit;
					if (onebit.equals("0")) {break;}
					start += 5;

				}
				bitLineOrigPos += start+5;
				subCounter += start+5+6;
			} else {
				bitLineOrigPos += start;
				subCounter += start+6;
			}

		} else { //operator packet
			int I = Integer.parseInt(bitLineS.substring(6, 6+1));
			if (I == 0) {
				//int L = strtol(bitLineS.substr(7, 15).c_str(), NULL, 2);
				int L = Integer.parseInt(bitLineS.substring(7, 7+15), 2);
				String valL = bitLineS.substring(7+15, L+7+15);
				int blah10;
				int Lorig = L;
				do {
					blah10 = 0;
					blah10 = getitit(valL, blah10);
					valL = valL.substring(blah10);//, valL.length()-1);
					L -= blah10;
				} while (valL.length() > 0);
				bitLineOrigPos += 7+15+Lorig;
				subCounter += 7+15+Lorig;
			} else if (I == 1) {
				int N = Integer.parseInt(bitLineS.substring(7, 7+11), 2);
				int st = 18; 
				bitLineOrigPos += 18;
				int subC = 18;
				for (int qq = 0; qq < N; qq++) {
					subC = getitit(bitLineS.substring(st), subC);
					st = subC;
				}
				subCounter += subC;
			} else {
				out.println("UNK");
			}
		}
		ind--;
		return subCounter;
	}

	public static class bins_s {
		int x;
		String bin;
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

