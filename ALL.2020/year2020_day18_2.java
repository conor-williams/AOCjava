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
import java.util.Stack;

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
class year2020_day18_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static Stack<BigInteger> st[] = new Stack[100];
	public static void main(String [] args) {
		out.println("		2020 Day18.2");
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
		for (int ii = 0; ii < 100; ii++) {
			st[ii] = new Stack<>();
		}
		BigInteger tot =  BigInteger.valueOf((long)0);
		BigInteger one =  new BigInteger("1");
		BigInteger zero =  new BigInteger("0");
		BigInteger m1 = new BigInteger("-1");
		BigInteger m2 = new BigInteger("-2");
		BigInteger m3 = new BigInteger("-3");
		for (int i = 0; i < blah.size(); i++) {
			String ne = new String(blah.get(i));
			//st.push(m3);
			int depth = 0;

			String lineX = new String(ne);
			lineX = firstParseHandlePlus(lineX);
			lineX = firstParseHandleMul(lineX);
			var sb22 = new StringBuilder(lineX);
			//sb22.replace(0, 0, "(");
			//sb22.replace(sb22.length()-1, sb22.length()-1, ")");
			//lineX = sb22.toString();
			//ne = new String(lineX);
			ne = "(" + lineX + ")";
			int len = ne.length();
			for (int ii = 0; ii < ne.length(); ii++) {
				if (Character.isDigit(ne.charAt(ii))) {
					st[depth].push(BigInteger.valueOf(ne.charAt(ii)-48));
					
				} else if (ne.charAt(ii) == '+') {
					st[depth].push(m1);
				} else if (ne.charAt(ii) == '*') {
					st[depth].push(m2);
				} else if (ne.charAt(ii) == '(') {
					depth++;
				} else if (ne.charAt(ii) == ')' || ii == len-1) {
					BigInteger i1 = st[depth].pop();
					if (st[depth].empty()) {if (depth != 0) {depth--;} st[depth].push(i1); continue;}
					BigInteger op = st[depth].pop();
					BigInteger i2 = st[depth].pop();

					if (op.compareTo(m1) == 0) {
						st[depth].clear();
						depth--;
						st[depth].push(i2.add(i1));
					} else if (op.compareTo(m2) == 0) {
						st[depth].clear();
						depth--;
						st[depth].push(i2.multiply(i1));
					}
				} else if (ne.charAt(ii) == ' ') {
					continue;
				}
			}
			BigInteger tmp1 = st[depth].pop();
			st[depth].clear();
			tot = tot.add(tmp1);
		}

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}

	public static String firstParseHandleMul(String lineX)
	{
		while (true) {
			int pluPos = lineX.indexOf("*");
			if (pluPos != -1) {
				StringBuilder sb = new StringBuilder(lineX);
				sb.setCharAt(pluPos, '%');
				lineX = sb.toString();
				//lineX[pluPos] = '%';
				String left = lineX.substring(0, pluPos);
				left = new StringBuilder(left).reverse().toString();
				if (Character.isDigit(left.charAt(1))) {
					String with = "" + left.charAt(1) + '(';
					var sb2 = new StringBuilder(left);
					sb2.replace(1, 1+1, with);
					left = sb2.toString();
				} else if (left.charAt(1) == ')') {
					int numBr = 0;
					int le = left.length();
					for (int i = 1; i < le; i++) {
						if (left.charAt(i) == '(') {
							numBr--;
						} else if (left.charAt(i) == ')') {
							numBr++;
						}
						if (numBr == 0) {
							String with = "((";
							StringBuilder sb3 = new StringBuilder(left);
							sb3.replace(i, i+1, with);
							left = sb3.toString();
							break;
						}
					}
				} else {
					out.println("ERR0"); Runtime.getRuntime().halt(0);
				}
				var sb4 = new StringBuilder(left);
				sb4.reverse();
				//reverse(left.begin(), left.end());
				left = sb4.toString();

				String right = lineX.substring(pluPos);
				if (Character.isDigit(right.charAt(2))) {
					String with = "" + right.charAt(2) + ')';
					var sb5 = new StringBuilder(right);
					sb5.replace(2, 2+1, with);
					right = sb5.toString();

				} else if (right.charAt(2) == '(') {
					int numBr = 0;
					int re = right.length();
					for (int i = 2; i < re; i++) {
						if (right.charAt(i) == '(') {
							numBr++;
						} else if (right.charAt(i) == ')') {
							numBr--;
						}
						if (numBr == 0) {
							String with = "))";
							var sb6 = new StringBuilder(right);
							sb6.replace(i, i+1, with);
							right = sb6.toString();
							break;
						}
					}
				} else {
					out.println("ERR1"); Runtime.getRuntime().halt(0);

				}
				lineX = left+right;
			} else {
				break;
			}

		}
		lineX = lineX.replaceAll("%", "*");
		return lineX;
	}

	public static String firstParseHandlePlus(String lineX)
	{
		while (true) {
			int pluPos = lineX.indexOf("+");
			if (pluPos != -1) {
				var sb1 = new StringBuilder(lineX);
				sb1.setCharAt(pluPos, '%');
				lineX = sb1.toString();
				String left = lineX.substring(0, pluPos);
				var sb2 = new StringBuilder(left);
				sb2.reverse();
				left = sb2.toString();
				if (Character.isDigit(left.charAt(1))) {
					String with = "" + left.charAt(1) +  '(';
					var sb3 = new StringBuilder(left);
					sb3.replace(1, 1+1, with);
					left = sb3.toString();
				} else if (left.charAt(1) == ')') {
					int numBr = 0;
					int le = left.length();
					for (int i = 1; i < le; i++) {
						if (left.charAt(i) == '(') {
							numBr--;
						} else if (left.charAt(i) == ')') {
							numBr++;
						}
						if (numBr == 0) {
							String with = "((";
							var sb4 = new StringBuilder(left);
							sb4.replace(i, i+1, with);
							left = sb4.toString();
							break;
						}
					}
				} else {
					out.println("ERR2"); Runtime.getRuntime().halt(0);
				}
				var sb99 = new StringBuilder(left);
				sb99.reverse();
				left = sb99.toString();

				String right = lineX.substring(pluPos);
				if (Character.isDigit(right.charAt(2))) {
					String with = "" + right.charAt(2) + ')';
					var sb7 = new StringBuilder(right);
					sb7.replace(2, 2+1, with);
					right = sb7.toString();
				} else if (right.charAt(2) == '(') {
					int numBr = 0;
					int re = right.length();
					for (int i = 2; i < re; i++) {
						if (right.charAt(i) == '(') {
							numBr++;
						} else if (right.charAt(i) == ')') {
							numBr--;
						}
						if (numBr == 0) {
							String with = "))";
							var sb8 = new StringBuilder(right);
							sb8.replace(i, i+1, with);
							right = sb8.toString();
							break;
						}
					}
				} else {
					out.println("ERR3"); Runtime.getRuntime().halt(0);

				}
				lineX = left+right;
			} else {
				break;
			}

		}
		lineX = lineX.replaceAll("%", "+");
		return lineX;
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

