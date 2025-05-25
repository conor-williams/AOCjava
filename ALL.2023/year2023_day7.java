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
// /java -Xmx2g year2019_day3.java *i1.txt


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2023_day7 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day7.1");
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
		Pattern p = Pattern.compile("([A-Z\\d]+) (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		BigInteger one =  new BigInteger("1");
		BigInteger zero =  new BigInteger("0");
		Map <String, Integer> mp = new HashMap<>();
		@SuppressWarnings({"unchecked"})
		Vector <String> [] cardGroup = new Vector[7];
		for (int ii = 0; ii < 7; ii++) {
			cardGroup[ii] = new Vector<>();
		}
		for (int i = 0; i < blah.size(); i++) {
			out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			out.print("m.group(1): "); out.println(m.group(1));
			char [] ar = m.group(1).toCharArray();
			for (int ii = 0; ii < 5; ii++) {
				switch(ar[ii]) {
					case '9':
					case '8':
					case '7':
					case '6':
					case '5':
					case '4':
					case '3':
					case '2':
					case '1':
						break;
					case 'T':
						ar[ii] = 'a';
						break;
					case 'J':
						ar[ii] = 'b';
						break;
					case 'Q':
						ar[ii] = 'c';
						break;
					case 'K':
						ar[ii] = 'd';
						break;
					case 'A':
						ar[ii] = 'e';
						break;
					default:

						out.println("--------------------");
						out.println(ar);
						out.println(ii);
						out.println("ERR");
						out.println(blah.get(i));
						Runtime.getRuntime().halt(0);
				}
			}


			String cards = new String(ar);
			out.print("card: "); out.println(cards);
			Integer bid = Integer.valueOf(m.group(2));
			mp.put(cards, bid);

			Arrays.sort(ar);
			
			int gotHand = 0;
			if (ar[0] == ar[1] && ar[1] == ar[2] &&
					ar[2] == ar[3] && ar[3] == ar[4]) {
				cardGroup[6].add(cards);
				gotHand = 1;
			} 
			if (gotHand == 0) {
				for (int ii = 0; ii < 5-3; ii++) {
					if (ar[ii] == ar[ii+1] && ar[ii+1] == ar[ii+2] && ar[ii+2] == ar[ii+3]) {
						cardGroup[5].add(cards);
						gotHand = 1;
						break;
						
					}
				}
			}
			if (gotHand == 0) {
				int ii = 0;
				if ((ar[ii] == ar[ii+1] && ar[ii+1] == ar[ii+2] && ar[ii+3] == ar[ii+4]) || 
					(ar[ii] == ar[ii+1] && ar[ii+2] == ar[ii+3]  && ar[ii+3] == ar[ii+4])) {
						cardGroup[4].add(cards);
						gotHand = 1;
				}
			}
			if (gotHand == 0) {
				for (int ii = 0; ii < 5-2; ii++) {
					if (ar[ii] == ar[ii+1] && ar[ii+1] == ar[ii+2]) {
						cardGroup[3].add(cards);
						gotHand = 1;
						break;
					}
				}
			}
			if (gotHand == 0) {
				if ((ar[0] == ar[1]  && ar[2] == ar[3]) ||
				    (ar[0] == ar[1]  && ar[3] == ar[4]) ||
				    (ar[1] == ar[2]  && ar[3] == ar[4])) {
					cardGroup[2].add(cards);
					gotHand = 1;
				  }
			}
			if (gotHand == 0) {
				for (int ii = 0; ii < 5-1; ii++) {
					if (ar[ii] == ar[ii+1]) {
						cardGroup[1].add(cards);
						gotHand = 1;
						break;
					}
				}
			}
			if (gotHand == 0) {
				cardGroup[0].add(cards);
				gotHand = 1;
			}
			if (gotHand == 0) {
				out.println("Err Err");
				Runtime.getRuntime().halt(0);
			}

		}

		for (int ii = 0; ii < 7; ii++) {
			Collections.sort(cardGroup[ii]);
			out.println(cardGroup[ii].size());
		}
		//int rank = blah.size();

		int pos = 1;
		BigInteger tot = zero;
		for (int ii = 0; ii < 7; ii++) {
			for (int jj = 0; jj < cardGroup[ii].size(); jj++) {
				
				String cc = cardGroup[ii].get(jj);
				out.println(cc);
				
				Integer xx = mp.get(cc);
				String vv = Integer.toString(xx);
				BigInteger bi = new BigInteger(vv);

				String po = String.valueOf(pos);
				BigInteger pobi = new BigInteger(po);
				BigInteger val =  bi.multiply(pobi);
				tot = tot.add(val);
			
				pos++;
			}
		}

		System.setOut(originalOut);

		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
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

