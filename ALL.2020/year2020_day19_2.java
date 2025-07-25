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
class year2020_day19_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	public static String rules[] = new String[200];
	public static int numRules = 0;
	public static String matches[] = new String[400];
	public static int numMatches = 0;

	public static void main(String [] args) {
		out.println("		2020 Day19.2");
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
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+): ([\\d+ \"\\|a-zA-Z]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int readingRules = 1;
		leny = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (readingRules == 1 && blah.get(i).length() == 0) {
				readingRules = 0;
				numRules = i;
				leny = 0;
				continue;
			} else if (readingRules == 1) {
				Matcher m = p.matcher(blah.get(i));
				m.find();
				///out.println(blah.get(i));
				int ruleNum = Integer.valueOf(m.group(1));
				//out.println(m.group(2));
				//Scanner scanner = new Scanner(System.in); scanner.nextLine();
				rules[ruleNum] = new String("(" + m.group(2) + ")");
			} else {
				matches[leny] = blah.get(i);
			}
			leny++;
		}

		numMatches = leny;

		//String rule0 = new String(rules[42]);
		String rule42 = getTheRule(rules[42]);
		out.print("rule42:"); out.println(rule42);
		//Scanner scanner1 = new Scanner(System.in); scanner1.nextLine();
		String rule31 = getTheRule(rules[31]);
		out.print("rule31:"); out.println(rule31);
		//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();


		rules[8] = new String("(FOURTYTWOEIGHT)");
		rules[11] = new String("(FOURTYTWOELEVEN THIRTYONE)");
		String rule0 = getTheRule(rules[0]);
		out.print("rule0:"); out.println(rule0);
		//Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();

		String rule0Orig = new String(rule0);
		int count = 0;
		for (int ii = 1; ii < 6; ii++) {
			rule0 = new String(rule0Orig);
			String rrii = new String();
			for (int zz = 0; zz<ii; zz++) {
				rrii += rule42;
			}
			String wat1 = new String("FOURTYTWOEIGHT");
			//int pos = rule0.indexOf(wat1);
			//rule0.replace(pos, pos+wat1.length(), rrii);
			out.print("wat1: "); out.println(wat1);
			out.print("rrii: "); out.println(rrii);

			rule0 = rule0.replaceFirst(wat1, rrii);
			String rule0tmp = new String(rule0);
			for (int jj = 1; jj < 6; jj++) {//42.11
				rule0 = new String(rule0tmp);
				String rrjj = new String();
				for (int zz = 0; zz<jj; zz++) {
					rrjj += rule42;
				}
				String wat2 = new String("FOURTYTWOELEVEN");
				///int pos = rule0.indexOf(wat2);
				rule0 = rule0.replaceFirst(wat2, rrjj);
				//rule0.replace(pos, pos_wat2.length(), rrjj);

				int kk = jj;
				{//31.11
					String rrkk = new String();
					for (int zz = 0; zz<kk; zz++) {
						rrkk += rule31;
					}
					String wat3 = new String("THIRTYONE");
					//int pos = rule0.indexOf(wat3);
					//rule0.replace(pos, pos+wat3.length(), rrkk);
					rule0 = rule0.replaceFirst(wat3, rrkk);
					{
						String rule0_2 = "^" + rule0 + "$";
						
						Pattern p4 = Pattern.compile(rule0_2);
						for (int i = 0; i < numMatches; i++) {
							if (matches[i].charAt(0) != 'Q') {
								Matcher m3 = p4.matcher(matches[i]);

								if (m3.find()) {

									StringBuilder sb = new StringBuilder(matches[i]);
									sb.setCharAt(0, 'Q');
									matches[i] = sb.toString();
									count++;
								}
							}
						}
					}
				}
			}
		}
		//cout << "** count is: " << count << endl;

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(count);
		out.println("");
	}
	public static String getTheRule(String rule0)
	{
		Pattern p2 = Pattern.compile("(\\d+)");
		//out.print("rule0: "); out.println(rule0);
		int go = 0;
		do {
			go = 0;
			Matcher m2 = p2.matcher(rule0);
			if (m2.find()) {
				int pos = Integer.valueOf(m2.group(1));
				if (pos == 0) { out.println("zero error"); Runtime.getRuntime().halt(0); }
				rule0 = rule0.replaceFirst(m2.group(1), rules[pos]);
				go = 1;
			}
		} while (go == 1);

		rule0 = rule0.replaceAll("\\(\"a\"\\)", "a");
		rule0 = rule0.replaceAll("\\(\"b\"\\)", "b");
		rule0 = rule0.replaceAll("\\( \\)", "");
		rule0 = rule0.replaceAll(" ", "");

		//rule0 = "^"+rule0+"$";

		return rule0;
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

