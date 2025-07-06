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
class year2017_day14 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static char grid [][] = new char[128][128];
	public static void main(String [] args) {
		out.println("		2017 Day14.1");
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
		for (int rowPos = 0; rowPos < 128; rowPos++) {
			Vector <Integer> var_ints = new Vector<>();
			for (int i = 0; i < blah.size(); i++) {
				String ne = blah.get(0);
				ne += "-" + String.valueOf(rowPos);
				for (int ii = 0; ii < ne.length(); ii++) {
					if (ne.charAt(ii) == ',') {
						var_ints.add(44);
					} else {
						int ival = (int)ne.charAt(ii);
						var_ints.add(ival);
					}
				}
			}
			//var_ints.add((int)'-'); var_ints.add(rowPos+48);
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
			String hexAddr = "";
			for (int i = 0; i < 16; i++) {
				//out.println(ans[i]);
				hexAddr += String.format("%02x", ans[i]);
			}
			String binAddr = hexToBinary(hexAddr);
			for (int xx = 0; xx < 128; xx++) {
				if (binAddr.charAt(xx) == '1') {
					grid[rowPos][xx] = '#';
				} else {
					grid[rowPos][xx] = '.';
				}
			}
		}
		int count = 0;
		for (int yy = 0; yy < 128; yy++) {
			for (int xx = 0; xx < 128; xx++) {
				if (grid[yy][xx] == '#') {
					count++;
				}
			}
		}
		/*
		for (int yy = 0; yy < 8; yy++) {
			for (int xx = 0; xx < 8; xx++) {
				out.print(grid[yy][xx]);
			}
			out.println();
		}
		out.println();
		*/

		out.print("**j_ans: ");
		out.println(count);
		out.println();
	}
	public static String hexToBinary(String hex)
	{

		// variable to store the converted
		// Binary Sequence
		String binary = "";

		// converting the accepted Hexadecimal
		// string to upper case
		hex = hex.toUpperCase();

		// initializing the HashMap class
		HashMap<Character, String> hashMap
			= new HashMap<Character, String>();

		// storing the key value pairs
		hashMap.put('0', "0000");
		hashMap.put('1', "0001");
		hashMap.put('2', "0010");
		hashMap.put('3', "0011");
		hashMap.put('4', "0100");
		hashMap.put('5', "0101");
		hashMap.put('6', "0110");
		hashMap.put('7', "0111");
		hashMap.put('8', "1000");
		hashMap.put('9', "1001");
		hashMap.put('A', "1010");
		hashMap.put('B', "1011");
		hashMap.put('C', "1100");
		hashMap.put('D', "1101");
		hashMap.put('E', "1110");
		hashMap.put('F', "1111");

		int i;
		char ch;

		// loop to iterate through the length
		// of the Hexadecimal String
		for (i = 0; i < hex.length(); i++) {
			// extracting each character
			ch = hex.charAt(i);

			// checking if the character is
			// present in the keys
			if (hashMap.containsKey(ch))

				// adding to the Binary Sequence
				// the corresponding value of
				// the key
				binary += hashMap.get(ch);

			// returning Invalid Hexadecimal
			// String if the character is
			// not present in the keys
			else {
				binary = "Invalid Hexadecimal String";
				return binary;
			}
		}

		// returning the converted Binary
		return binary;
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

