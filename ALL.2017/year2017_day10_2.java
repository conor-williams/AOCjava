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
class year2017_day10_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day10.2");
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
			String ne = blah.get(0);
			for (int ii = 0; ii < ne.length(); ii++) {
				if (ne.charAt(ii) == ',') {
					var_ints.add(44);
				} else {
					int ival = (int)ne.charAt(ii);
					var_ints.add(ival);
				}
			}
		}
		var_ints.add(17);
		var_ints.add(31);
		var_ints.add(73);
		var_ints.add(47);
		var_ints.add(23);

		int N = 256;
	        int [] initial = new int[260];
       		int [] initial2 = new int[10000];
        	for (int k = 0; k < N; k++) {
                	initial[k] = k;
        	}

	        int startpos; int skip = 0;
       		startpos = 0; int len = 0;
		for (int P = 0; P < 64; P++) {
		        for (int z = 0; z < var_ints.size(); z++) {
                		len = var_ints.get(z);
				initial2 = new int[10000];
                		for (int k = 0; k < len; k++) {
                        		int p = (startpos+k)%N;
		                        initial2[p] = initial[p];
                		}
		                for (int k = 0 ; k < len; k++) {
               				int p = (startpos+k)%N;
		                        int q = (startpos+((len-k-1)))%N;
                		        initial[p] = initial2[q];
                		}
                		startpos = (startpos+len+skip)%N;
                		skip++;
        		}
    		}
		int [] ans = new int[20];
		for (int i = 0; i < 16; i++) {
		        for (int j = 0; j < 16; j++) {
                		ans[i] ^= initial[i*16+j];
        		}
    		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
	        for (int i = 0; i < 16; i++) {
		//	out.print("%02x", ans[i]);
			out.print(String.format("%02x", ans[i]));
		}
		out.println();
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

