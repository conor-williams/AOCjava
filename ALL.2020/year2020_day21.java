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
class year2020_day21 {
	static Map <String, Vector<Vector<String>>> mp = new HashMap<>();
	public static void main(String [] args) {
		out.println("		2020 Day21.1");
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
		Pattern p = Pattern.compile("(.*) \\(contains (.*)\\)");

		Vector <String> allIngreds = new Vector<>();
		Vector <String> allAllergs = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String ingred = m.group(1);
			String allers = m.group(2);
			out.println(ingred);
			out.println(allers);

			Scanner scanner = new Scanner(ingred);
			scanner.useDelimiter("[\t\\s ]");
			Vector <String> var_ingred = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				var_ingred.add(ne);
			}

			Scanner scanner2 = new Scanner(allers);
			scanner2.useDelimiter("[, ]+");
			Vector <String> var_allers = new Vector<>();
			while (scanner2.hasNext()) {
				String ne = scanner2.next();
				var_allers.add(ne);
			}
			for (int ii = 0; ii < var_allers.size(); ii++) {
				if (!allAllergs.contains(var_allers.get(ii))) {
					allAllergs.add(var_allers.get(ii));
				}
			}
			for (int ii = 0; ii < var_ingred.size(); ii++) {
				if (!allIngreds.contains(var_ingred.get(ii))) {
					allIngreds.add(var_ingred.get(ii));
				}
			}
			for (int ii = 0; ii < var_allers.size(); ii++) {
				Vector<Vector <String>> xx = new Vector<>();
				if (mp.get(var_allers.get(ii)) == null) {
					xx.add(var_ingred);
				} else {
					xx = mp.get(var_allers.get(ii));
					xx.add(var_ingred);
				}
				mp.put(var_allers.get(ii), xx);
			}
		}

		int count = 0;
		Map <String, Vector<String>> mp2 = new HashMap<>();
		for (var en: mp.entrySet()) {
			out.println(count);
			count++;
			out.println("-----------");
			out.println(en.getKey()); //out.print(" "); 

			Vector <String> res = new Vector<>(en.getValue().get(0));
			if (en.getValue().size() > 1) {
				for (int ii = 1; ii < en.getValue().size(); ii++) {
					out.println(en.getValue().get(ii));

					res.retainAll(en.getValue().get(ii));
				}
			} else {
			}
			mp2.put(en.getKey(), res);

			out.println("-----------");
		}
		out.println("-- - - - - - --");
		Map <String, String> mp3 = new HashMap<>();
		int go = 1;
		while (go == 1) {
			go = 0;
after:
			for (var en2: mp2.entrySet()) {
				if (en2.getValue().size() == 1) {
					String valToRemove = en2.getValue().get(0);
					mp3.put(en2.getKey(), valToRemove);

					for (var en3: mp2.entrySet()) {
						var xx2 = en3.getValue();
						if (!xx2.contains(valToRemove)) {continue;}
						for (int ii = 0; ii < xx2.size(); ii++) {
							if (xx2.get(ii).equals(valToRemove)) {
								xx2.remove(ii);
								break;
							}
						}
						mp2.put(en3.getKey(), xx2);
					}
					mp2.remove(en2.getKey());
					go = 1;
					break after;
				}
			}
		}
		out.println("        ---  ---   ");
		Vector <String> allWithAllers = new Vector<>();
		for (var en3: mp3.entrySet()) {
			out.print(en3.getKey()); out.print(" "); out.println(en3.getValue());
			allWithAllers.add(en3.getValue());
		}
		Vector <String> allWithoutAllers = new Vector<>(allIngreds);

		for (int ii = 0; ii < allWithAllers.size(); ii++) {
			int ind = allWithoutAllers.indexOf(allWithAllers.get(ii));
			allWithoutAllers.remove(ind);
		}
		out.println(allWithoutAllers);

		for (int kk = 0; kk < allWithoutAllers.size(); kk++) {
			for (int kk1 = 0; kk1 < allWithoutAllers.size(); kk1++) {
				if (kk == kk1) {continue;}
				if (allWithoutAllers.get(kk).contains(allWithoutAllers.get(kk1))) {
					out.println("same...");
				}
			}
		}

		int count2 = 0;
		for (int ii = 0; ii < allWithoutAllers.size(); ii++) {
			for (int i = 0; i < blah.size(); i++) {
				Pattern p10 = Pattern.compile("(^|\\s)(" + allWithoutAllers.get(ii) + ") ");
				out.println(p10);
				Matcher m10 = p10.matcher(blah.get(i));
				if (m10.find()) {
					count2++;
				}
			}
		}
		out.println(allIngreds.size());

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(count2);
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

