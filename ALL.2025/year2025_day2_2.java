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
import java.util.stream.IntStream;


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
///MyClass[] array = IntStream.range(0, 5) .mapToObj(i -> new MyClass()) .toArray(MyClass[]::new);
@SuppressWarnings("unchecked")
class year2025_day2_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2025 Day2.2");
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

		Pattern p = Pattern.compile("(\\d+)-(\\d+)");

		double numInv = 0;
		long sum = 0;
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			Vector <Integer> var_ints = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				Matcher m = p.matcher(ne);
				m.find();
				String from = m.group(1);
				String to = m.group(2);
				//	double range = Double.parseDouble(to) - Double.parseDouble(from);
				out.println("\t\t" + from.length() + " lens " + to.length());
				out.println("\t\t" + from + " -> " + to);
				long fromLong = Long.parseLong(from);
				long toLong = Long.parseLong(to);
				
				Vector <Long> already = new Vector<>();
				for (int ti = 2; ti <= to.length(); ti++) {
					String fromTmp = new String(from);
					String toTmp = new String(to);
					//out.println("trying ti: " + ti);
					
					if (fromTmp.length() % ti != 0 && toTmp.length() == fromTmp.length()) {
						//out.println("bad lengths");
						out.println("------------");
						continue;
					}
					if (fromTmp.length() % ti != 0 && (fromTmp.length()+1) % ti == 0) {
						///out.println("in here...");
						StringBuffer sb = new StringBuffer(fromTmp);
						for (int ii = 0; ii < sb.length(); ii++) {
							if (ii == 0) {
								sb.setCharAt(0, '1');
							} else {
								sb.setCharAt(ii, '0');
							}
						}
						sb.append('0');
						fromTmp = sb.toString();
						//System.out.println("modified: " + fromTmp + " -> " + toTmp);
					}

					String subFrom = fromTmp.substring(0, fromTmp.length()/ti);
					String subTo = new String();

					if (toTmp.length() % ti == 0) {
						subTo = toTmp.substring(0, (toTmp.length()/ti));
					} else {
						subTo = toTmp.substring(0, (toTmp.length()/ti) + 1);
					}
					StringBuffer sb2 = new StringBuffer(subTo);
					for (int ii = 0; ii < subTo.length(); ii++) {
						sb2.setCharAt(ii, '9');
					}
					subTo = sb2.toString();

					int to1 =  Integer.valueOf(subTo);
					//out.println("to1: " + to1);
					//out.println("subFrom: " + subFrom);
					int from1 = Integer.valueOf(subFrom);
					//out.println(from1 + " ---> " + to1);
					//int mul = from.length() / 2;
					//out.println("half: " + from1 + " -> " + to1);
					for (long ii = from1; ii <= to1; ii++) {
						int mul = Long.toString(ii).length();// out.println("mul is: " + mul);
						long val = 0;
						//val += ii;
						for (int kk = 0; kk < ti; kk++) {
							val += (ii * (long)Math.pow(10, kk*mul));
						}
						//out.println("ii: " + ii + "val is: " + val);
						if (val > toLong) {
							break;
						}
						if (val >= fromLong) {
							out.println("inv: " + val);
							numInv++;
							if (!already.contains(val)) {
								already.add(val);
								sum += val;
							}
						}
					}
					out.println("------------");
				}
			}
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(sum);
		out.println("");
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

