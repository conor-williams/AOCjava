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

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2019_day14_2 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day14.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
		//int lenx = 0;
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
		Pattern p = Pattern.compile("([\\dA-Z, ]+) => ([\\d]+) ([A-Z]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		Map <String, Vector<Tuple <Double, String>>> mp = new HashMap<>();
		Map <String, Double> mpSpare = new HashMap<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int rsize = Integer.valueOf(m.group(2));
			out.println(rsize);
			String relem = m.group(3);
			out.println(relem);
			mpSpare.put(relem, 0.0);

			Scanner scanner = new Scanner(m.group(1));
			scanner.useDelimiter("[ ]");
			Vector <Tuple<Double, String>> var_elems = new Vector<>();
			out.println(blah.get(i));
			while (scanner.hasNext()) {
				String tmp1 = "";
				Double dou = Double.parseDouble(tmp1=scanner.next())/(double)rsize;
				String wat = scanner.next();
				if (wat.charAt(wat.length()-1) == ',') {
					wat = wat.substring(0, wat.length()-1);
				}
				out.print(dou); out.print(" <dou wat> "); out.println(wat);

				var_elems.add(new Tuple(dou, wat));
			}
			out.print("relemintomp: "); out.println(relem);
			mp.put(relem, var_elems);
		}
		var list = mp.get("FUEL");

		int go = 1;
		do {
			Vector <Tuple <Double, String>> ve1 = new Vector<>();
			for (int ii = 0; ii < list.size(); ii++) {
				var tu1 = list.get(ii);
				if (tu1.second.equals("ORE")) {
					ve1.add(tu1);
				} else {
					var ve2 = mp.get(tu1.second);
					for (int kkk = 0; kkk < ve2.size(); kkk++) {
						var tu2 = ve2.get(kkk);
						double do2 = tu2.first;
						String el2 = tu2.second;
						do2 *= tu1.first;
						ve1.add(new Tuple(do2, el2));
						
					}
				}
			}
			list = new Vector(ve1);
			int found = 0;
			for (int ii = 0; ii < list.size(); ii++) {
				var li2 = list.get(ii);
				if (!li2.second.equals("ORE")) {
					found = 1;
					break;
				}
			}
			if (found == 0) {
				go = 0;
			}

		} while (go == 1);
		Double sum = 0.0;
		for (int ii = 0; ii < list.size(); ii++) {
			var li2 = list.get(ii);
			sum += li2.first;
		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		//out.println(Math.round(Math.floor(1000000000000L / Math.ceil(sum))));
		out.println(Math.round(Math.floor(1000000000000L / sum)));
		out.print("	plus or minus one or plus none");
		//out.println(sum);
		//out.println(1000000000000L / Math.floor(sum));
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

