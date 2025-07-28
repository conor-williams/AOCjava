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
class year2022_day21 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	static Pattern p2 = Pattern.compile("(\\d+)\\s+(\\S)\\s+(\\d+)");
	static Map <String, Tuple<String, Integer>> mp = new HashMap<>();
	static class inst_s {
		String s1;
		String s2;
		String s3;
		char op;
		long num1;
	}
	static inst_s inst[] = new inst_s[3000];
	public static void main(String [] args) {
		out.println("		2022 Day21.1");
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
		Pattern p = Pattern.compile("(.*?):(.*)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");

		for (int i = 0; i < 3000; i++) {
			inst[i] = new inst_s();
		}
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			inst[i].s1 = m.group(1);
			String rest = m.group(2);
			mp.put(inst[i].s1, new Tuple(rest, 0));
		}
		String ans = new String();
		do {
			for (var en1: mp.entrySet()) {
				if (en1.getValue().second == 0 && alldigitsAndOp(en1.getValue().first) == 1 && hasOp(en1.getValue().first) == 1) {
					String xx1 = doCalc(en1.getValue().first);
					mp.put(en1.getKey(), new Tuple(xx1, en1.getValue().second));
				} else if (en1.getValue().second == 0 && alldigits(en1.getValue().first) == 1) {
					for (var en2: mp.entrySet()) {
						int ind = 0;
						if ((ind = en2.getValue().first.indexOf(en1.getKey())) != -1) {
							String xx2 = en2.getValue().first.replaceFirst(en1.getKey(), en1.getValue().first);
							mp.put(en2.getKey(), new Tuple(xx2, en2.getValue().second));
							mp.put(en1.getKey(), new Tuple(en1.getValue().first, 1));
							break;
						}
					}

				}


			}
			if (alldigits(mp.get("root").first) == 1) {
				ans = mp.get("root").first;
				break;
			}

		} while (true);
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	static String doCalc(String ch) {

		long num1 = 0, num2 = 0; char op = ' ';
		Matcher m2 = p2.matcher(ch);
		if (!m2.find()) {out.println(p2); out.println(ch); out.println("ERR");}
		num1 = Long.parseLong(m2.group(1));
		op = m2.group(2).charAt(0);
		num2 = Long.parseLong(m2.group(3));

		long ans = 0;
		switch (op) {
			case '+':
				ans = num1 + num2;
				break;
			case '-':
				ans = num1 - num2;
				break;
			case '*':
				ans = num1 * num2;
				break;
			case '/':
				ans = num1 / num2;
				break;
		}
		return String.valueOf(ans);
	}
	static int hasOp(String ch)
	{
		int found = 0;
		for (int i = 0; i < ch.length(); i++) {
			if (ch.charAt(i) == '+' || ch.charAt(i) == '-' || ch.charAt(i) == '*' || ch.charAt(i) == '/') {
				found = 1;
				break;
			}
		}
		return found;
	}



	static int alldigitsAndOp(String ch)
	{
		int found = 1;
		for (int i = 0; i < ch.length(); i++) {

			if (ch.charAt(i) == ' ' || (ch.charAt(i)>='0' && ch.charAt(i) <= '9') || ch.charAt(i) == '+' || ch.charAt(i) == '-' || ch.charAt(i) == '*' || ch.charAt(i) == '/') {
				//ok
			} else {
				found = 0;
				break;
			}
		}
		return found;
	}
	static int alldigits(String ch)
	{
		int found = 1;
		for (int i = 0; i < ch.length(); i++) {
			if (ch.charAt(i) == ' ' || ch.charAt(i) == '-' || (ch.charAt(i) >= '0' && ch.charAt(i) <= '9')) {
				//ok
			} else {
				found = 0;
				break;
			}
		}
		return found;
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

