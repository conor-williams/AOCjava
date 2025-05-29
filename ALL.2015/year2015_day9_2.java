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


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2015_day9_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day9.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([A-Za-z]+) to ([A-Za-z]+) = (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <Tuple <String, String>, Integer> mp = new HashMap<>();
		Vector <String> sources = new Vector<>();
		Vector <String> dests = new Vector<>();
		List <String> all = new ArrayList<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();

			String source = m.group(1);
			String dest = m.group(2);
			Integer dist  = Integer.valueOf(m.group(3));
			Tuple <String, String> tu1 = new Tuple(source, dest);
			Tuple <String, String> tu2 = new Tuple(dest, source);
			mp.put(tu1, dist);
			mp.put(tu2, dist);
			if (!all.contains(source)) {
				all.add(source);
			}
			if (!all.contains(dest)) {
				all.add(dest);
			}
		}


		List <String> allOrig = new ArrayList<>(all);
		/*
		for (int ii = 0; ii < allOrig.size(); ii++) {
			out.println(allOrig.get(ii));
		}
		*/
		long max = 0;
		do {
			long di = 0;
			for (int ii = 0; ii < all.size()-1; ii++) {
				//out.print(all.get(ii));  out.print(" -> "); out.println(all.get(ii+1));
				Tuple <String, String> tu1 = new Tuple(all.get(ii), all.get(ii+1));
				if (mp.containsKey(tu1)) {
					di += mp.get(tu1);
				} else {
					di = 99999999;
					break;
				}
			}
			if (di > max) {max = di;}
			//out.println("--------");
			nextPermutation(all);
		} while (!all.equals(allOrig));

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(max);
		out.println("");
	}

	public static boolean nextPermutation(List<String> list) {
		int n = list.size();
		if (n <= 1) return false;

		// Step 1: Find the largest index `i` such that list[i] < list[i + 1]
		int i = n - 2;
		while (i >= 0 && list.get(i).compareTo(list.get(i + 1)) >= 0) {
			i--;
		}

		// If no such index exists, the list is in descending order (last permutation)
		if (i < 0) {
			Collections.reverse(list);
			return false;
		}

		// Step 2: Find the largest index `j` such that list[j] > list[i]
		int j = n - 1;
		while (list.get(j).compareTo(list.get(i)) <= 0) {
			j--;
		}

		// Step 3: Swap elements at indices `i` and `j`
		Collections.swap(list, i, j);

		// Step 4: Reverse the sublist from `i + 1` to the end
		Collections.reverse(list.subList(i + 1, n));

		return true;
	}
}

class Tuple<X,Y > {
	public final X first;
	public final Y second;

	public Tuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		Tuple tu2 = (Tuple) o;
		if (this == o) return true;
		if (!(o instanceof Tuple)) return false;
		if (!(this.first).equals(tu2.first)) {return false;}
		if (!(this.second).equals(tu2.second)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

