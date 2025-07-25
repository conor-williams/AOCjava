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
class year2023_day19_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	public static class numbs {
		char var1;
		int val;
	}
	public static class instruction {
		String name;
		char var1;
		char cmd;
		int numb;
		String yes;
		String no;
	}
	public static Map <String, instruction> mp = new HashMap<>();
	public static Map <String, numbs> NUMBS = new HashMap<>();
	public static long tot = 0;

	public static void main(String [] args) {
		out.println("		2023 Day19.2");
		out.flush();
		if (false) {
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
			//Pattern p = Pattern.compile("([A-Za-z]+) ([A-Za-z])([>|<])(\\d+):(.*})");
			Pattern p = Pattern.compile("([A-Za-z]+) ([A-Za-z])([>|<])(\\d+):(.*$)");
			//Pattern p2 = Pattern.compile("([A-Za-z ]+),([A-Za-z><\\d:,]+)");
			Pattern p2 = Pattern.compile("([A-Za-z ?]+),(.*$)");
			Pattern p3 = Pattern.compile("([A-Za-z])([>|<])(\\d+):(.*$)");
			Pattern p4 = Pattern.compile("(.*?),(.*$)");
			//Pattern p6 = Pattern.compile("([A-Za-z])([<|>])(\\d+):([A-Za-z]+),(.*$)");
			Pattern p6 = Pattern.compile("([A-Za-z])([<|>])(\\d+):(.*),(.*$)");
			Pattern p7 = Pattern.compile("\\{([A-Za-z])=(\\d+),([A-Za-z])=(\\d+),([A-Za-z])=(\\d+),([A-Za-z])=(\\d+)\\}");
			//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
			//	m.find();
			//
			//	month= m.group(2);

			// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
			//BigInteger tot =  BigInteger.valueOf((long)0);
			//BigInteger one =  new BigInteger("1");
			//BigInteger zero =  new BigInteger("0");
			int ANON = 0;

			int icont = 0;
			for (int i = 0; i < blah.size(); i++) {
				if (blah.get(i).length() == 0) {icont = i+1; break;}
				String line1 = blah.get(i);
				for (int ii = 0; ii < line1.length(); ii++) {
					if (line1.charAt(ii) == '}') {
						var sb = new StringBuilder(line1);
						//sb.deleteCharAt(ii, ' ');
						sb.deleteCharAt(ii);
						line1 = sb.toString();
					} 
					else if (line1.charAt(ii) == '{') {
						var sb = new StringBuilder(line1);
						sb.setCharAt(ii, ' ');
						line1 = sb.toString();
					}
				}
				instruction t = new instruction();
				Matcher m = p.matcher(line1);
				m.find();
				out.println(p);
				out.println(line1);
				t.name = m.group(1);
				t.var1 = m.group(2).charAt(0);
				t.cmd = m.group(3).charAt(0);
				checkCMD(t.cmd);
				t.numb = Integer.valueOf(m.group(4));
				out.println(m.group(5));

				Matcher m2 = p2.matcher(m.group(5));
				m2.find();
				String rest1 = m2.group(1);
				String rest2 = m2.group(2);

				/*
				   out.println("-----");
				   out.println(line1);
				   out.println(p2);
				   out.println(m2.group(1));
				   out.println(m2.group(2));
				   out.println("-----");
				   */

				{
					int foundcomma = 0;
					for (int ii = 0; ii < rest1.length(); ii++) {
						if (rest1.charAt(ii) == ',') {foundcomma = 1; break;}
					}
					if (foundcomma == 0) {
						t.yes = new String(rest1);
					}

					int foundcomma2 = 0;
					for (int ii = 0; ii < rest2.length(); ii++) {
						if (rest2.charAt(ii) == ',') {foundcomma2 = 1; break;}
					}
					if (foundcomma2 == 0) { t.no = new String(rest2);

						/*
						   if (t.no.length() > 3) {
						   if (t.no.charAt(t.no.length() -2) == 'c' && t.no.charAt(t.no.length()-1) == 'c') {
						   out.println(t.no);
						   Runtime.getRuntime().halt(0);
						   }
						   }
						   */
					} else {
						instruction t2 = new instruction();
						String rest3 = new String();
						Matcher m3 = p3.matcher(rest2);

						m3.find();
						t2.var1 = m3.group(1).charAt(0);
						t2.cmd = m3.group(2).charAt(0);
						/*
						   out.println("------");
						   out.println(rest2);
						   out.println(p3);
						   out.println(m3.group(1));
						   out.println(m3.group(2));
						   out.println("----");
						   Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();
						   */

						checkCMD(t2.cmd);
						t2.numb = Integer.valueOf(m3.group(3));
						rest3 = new String(m3.group(4));

						String rest4 = new String();
						String rest5 = new String();

						Matcher m4 = p4.matcher(rest3);
						m4.find();
						rest4 = m4.group(1);
						rest5 = m4.group(2);
						out.println("------");
						out.println(rest3);
						out.println(m4.group(1));
						out.println(m4.group(2));
						out.println("----");
						//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();
						{
							int foundcomma4 = 0;
							for (int ii = 0; ii < rest4.length(); ii++) {
								if (rest4.charAt(ii) == ',') {foundcomma4 = 1; break;
								}
							}
							if (foundcomma4 == 0) {
								t2.yes = new String(rest4);
							} else {}

							int foundcomma5 = 0;
							for (int ii = 0; ii < rest5.length(); ii++) {
								if (rest5.charAt(ii) == ',') {
									foundcomma5 = 1; break;
								}
							}
							if (foundcomma5 == 0) {
								t2.no = new String(rest5);
								if (t2.no.length() > 3) {
									if (t2.no.charAt(t2.no.length() -2) == 'c' && t2.no.charAt(t2.no.length()-1) == 'c') {
										out.println(t2.no);
										Runtime.getRuntime().halt(0);
									}
								}

							} else {
								instruction t3 = new instruction();
								Matcher m6 = p6.matcher(rest5);
								m6.find();
								t3.var1 = m6.group(1).charAt(0);
								t3.cmd = m6.group(2).charAt(0);
								checkCMD(t3.cmd);
								t3.numb = Integer.valueOf(m6.group(3));
								t3.yes = new String(m6.group(4));
								/*
								   if (t3.yes.charAt(t3.yes.length()-2) == 'c' && t3.yes.charAt(t3.yes.length()-1) == 'c') {
								   out.println(t3.yes);
								   Runtime.getRuntime().halt(0);
								   }
								   if (t3.no.charAt(t3.no.length() -2) == 'c' && t3.no.charAt(t3.no.length()-1) == 'c') {
								   out.println(t3.no);
								   Runtime.getRuntime().halt(0);
								   }
								   */
								t3.no = new String(m6.group(5));
								out.println(t3.no);

								{
									String tmpname3 = new String();
									tmpname3 = "X" + ANON; ANON++;
									t3.name = new String(tmpname3);
									String name3 = new String(t3.name);
									t2.no = new String(tmpname3);
									mp.put(name3, t3);
								}
							}
						}
						String tmpname2 = "Y" + ANON; ANON++;
						t2.name = new String(tmpname2);
						String name2 = new String(t2.name);
						t.no = new String(t2.name);
						mp.put(name2, t2);
					}
				}
				String name1 = new String(t.name);
				mp.put(name1, t);
			}
			{ 
				{
					for (int i = icont; i < blah.size(); i++) {
						String line1 = blah.get(i);
						out.println(line1);
						int lenx = line1.length();

						Matcher m7 = p7.matcher(line1);
						m7.find();
						numbs num1 = new numbs(); num1.var1 = m7.group(1).charAt(0); num1.val = Integer.valueOf(m7.group(2));
						numbs num2 = new numbs(); num2.var1 = m7.group(3).charAt(0); num2.val = Integer.valueOf(m7.group(4));
						numbs num3 = new numbs(); num3.var1 = m7.group(5).charAt(0); num3.val = Integer.valueOf(m7.group(6));
						numbs num4 = new numbs(); num4.var1 = m7.group(7).charAt(0); num4.val = Integer.valueOf(m7.group(8));

						String tmpY1 = "" + num1.var1;
						String tmpY2 = "" + num2.var1;
						String tmpY3 = "" + num3.var1;
						String tmpY4 = "" + num4.var1;
						NUMBS.put(tmpY1, num1);
						NUMBS.put(tmpY2, num2);
						NUMBS.put(tmpY3, num3);
						NUMBS.put(tmpY4, num4);
						out.println(num1.val);
						out.println(num2.val);
						out.println(num3.val);
						out.println(num4.val);
						String startIN = "in";
						processInstruction(startIN);
						out.println("---");
						//Scanner scanner = new Scanner(System.in); scanner.nextLine();
					}
				}
			}

			System.setOut(originalOut);

		}
		out.print("**j_ans: ");
		out.print("TODO TODO");
		out.println("");
	}
	public static void checkCMD(char cm) {
		if (cm == '>' || cm == '<') {} else {
			out.println("CMDERR"); Runtime.getRuntime().halt(0);
		}
	}

	public static void processInstruction(String myInstName)
	{
		out.print("inst: "); out.println(myInstName);
		instruction Xt = mp.get(myInstName);
		String numY = "" + Xt.var1;
		String ans1 = new String();
		switch (Xt.cmd) {
			case '>':
				if (NUMBS.get(numY).val > Xt.numb) {out.println("yes"); ans1 = new String(Xt.yes);}
				else {out.println("no"); ans1 = new String(Xt.no);}
				break;
			case '<':
				if (NUMBS.get(numY).val < Xt.numb) {out.println("yes"); ans1 = new String(Xt.yes);}
				else {out.println("no"); ans1 = new String(Xt.no); }
				break;
			default:
				out.println("ERR1");
				break;
		}

		if (ans1.charAt(0) == 'R' || ans1.charAt(0) == 'A') {
			if (ans1.charAt(0) == 'A') {
				String xxx = "x";
				String mmm = "m";
				String aaa = "a";
				String sss = "s";
				tot += NUMBS.get(xxx).val + NUMBS.get(mmm).val + NUMBS.get(aaa).val + NUMBS.get(sss).val;
			} else if (ans1.charAt(0) == 'R') {
			}
			//printf("END GAME STUFF %c", ans1[0]);
			out.print("END GAME STUFF: "); out.println(ans1.charAt(0));
		} else {
			String ansString = new String(ans1); 
			processInstruction(ans1);

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

