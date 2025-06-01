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
class year2017_day10 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day10.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

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
		Vector <Integer> var_ints = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\t\\s, ]+");
			while (scanner.hasNext()) {
				String ne = scanner.next();
				var_ints.add(Integer.valueOf(ne));
			}
		}
		int NUM = 256;

		Deque <Integer> dek = new ArrayDeque<>();
		for (int ii = 0; ii < NUM; ii++) {
			dek.addLast(ii);
		}
		int skipSize = 0;
		long totRot = 0;
		for (int ii = 0; ii < var_ints.size(); ii++) {
			int sz = var_ints.get(ii);
			//out.println(dek.size()); out.println(sz);
			reversePortionDeq(dek, 0, sz);

			out.print("len: "); out.println(sz);
			out.print("rotate: "); out.println(sz+skipSize);
			int sz2 = (sz+skipSize) % NUM;
			out.print("sz2: "); out.println(sz2);

			out.print("NUM-sz2: "); out.println(NUM-sz2);
			out.println("before rot: ");
			out.println(dek);
			assert (NUM-sz2) >= 0;
			totRot += (NUM-sz2);
			for (int kk = 0; kk < NUM-(sz2); kk++) {
				int val = dek.removeLast();
				dek.addFirst(val);
			}
			out.println(dek);
			///Scanner scanner = new Scanner(System.in); scanner.nextLine();
			skipSize++;
		}
		totRot = totRot % NUM;
		for (int zz =0; zz < totRot; zz++) {
			int val = dek.removeFirst();
			dek.addLast(val);
		}
		out.println(dek);
		int val1 = dek.removeFirst();
		int val2 = dek.removeFirst();
		long ans = val1 * val2;

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}
	public static void reversePortionDeq(Deque<Integer> deque, int start, int end) {
		//if (start < 1 || end > deque.size() || start >= end) {
		//   throw new IllegalArgumentException("Invalid range");
		//}

		// Convert Deque to a LinkedList for easier manipulation
		start++;
		LinkedList<Integer> list = new LinkedList<>(deque);

		// Reverse the specified portion
		while (start < end) {
			int temp = list.get(start - 1);
			list.set(start - 1, list.get(end - 1));
			list.set(end - 1, temp);
			start++;
			end--;
		}

		// Clear the original deque and add the modified list back
		deque.clear();
		deque.addAll(list);
	}
	public static String reverseSub(String str, int start, int end) {
		// Extract parts of the string
		String before = str.substring(0, start);
		String toReverse = str.substring(start, end);
		String after = str.substring(end);

		// Reverse the subsection
		StringBuilder reversed = new StringBuilder(toReverse).reverse();

		// Combine and return the result
		return before + reversed + after;
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
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

