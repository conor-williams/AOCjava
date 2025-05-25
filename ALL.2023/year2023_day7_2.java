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

class year2023_day7_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day7.2");
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

		Pattern p = Pattern.compile("([A-Z\\d]+) (\\d+)");
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
						break;
					case 'T':
						ar[ii] = 'a';
						break;
					case 'J':
						ar[ii] = '1';
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
			//out.print("card: "); out.println(cards);
			Integer bid = Integer.valueOf(m.group(2));
			mp.put(cards, bid);

			int [] whatsin = new int [15];

			for (int ii = 0; ii < 5; ii++) {
				switch (ar[ii]) {
					case '1': whatsin[1]++;
						  break;
					case '2': whatsin[2]++;
						  break;
					case '3': whatsin[3]++;
						  break;
					case '4': whatsin[4]++;
						  break;
					case '5': whatsin[5]++;
						  break;
					case '6': whatsin[6]++;
						  break;
					case '7': whatsin[7]++;
						  break;
					case '8': whatsin[8]++;
						  break;
					case '9': whatsin[9]++;
						  break;
					case 'a': whatsin[10]++;
						  break;
					case 'c': whatsin[12]++;
						  break;
					case 'd': whatsin[13]++;
						  break;
					case 'e': whatsin[14]++;
						  break;
					default:
						  out.println("err err err");
						  Runtime.getRuntime().halt(0);

				}
			}

			int max = 0;
			int maxii = 0;
			if (whatsin[1] > 0) {
				for (int ii = 2; ii <= 14; ii++) {
					if (whatsin[ii] > max) {max = whatsin[ii]; maxii = ii;}
				}

			}
			if (max > 0) {
				whatsin[maxii] += whatsin[1];
				whatsin[1] = 0;
			}
			Arrays.sort(whatsin);

			for (int ii = 14; ii >= 1; ii--) {
				out.print(whatsin[ii]); out.print(" ");
			}
			out.println();
			int TYPE = 0;
aft:
			for (int j = 14; j>=1; j--) {
				if (whatsin[j] == 5) {
					TYPE = 6; break aft;
				} else if (whatsin[j] == 4) {
					TYPE = 5; break aft;
				} else if (whatsin[j] == 3) {
					for (int k = j-1; k>=1; k--) {
						if (whatsin[k] == 2) {
							TYPE = 4;
							break aft;
						}
					}
					TYPE = 3;
					break aft;
				} else if (whatsin[j] == 2) {
					for (int k = j-1; k>=1; k--) {
						if (whatsin[k] == 2) {
							TYPE = 2;
							break aft;
						}
					}
					TYPE = 1; break aft;
				} else {
					TYPE = 0; break aft ;}
			}
			cardGroup[TYPE].add(cards);
		}

		for (int ii = 0; ii < 7; ii++) {
			Collections.sort(cardGroup[ii]);
			//out.println(cardGroup[ii].size());
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

