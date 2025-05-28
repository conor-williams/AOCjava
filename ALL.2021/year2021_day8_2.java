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
class year2021_day8_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day8.2");
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

		Pattern p = Pattern.compile("([a-z\\s]+)\\|([a-z\\s]+)");
		long tot = 0;
		for (int i = 0; i < blah.size(); i++) {
			int gotZero = 0;
			int gotOne = 0;
			int gotTwo = 0;
			int gotThree = 0;
			int gotFour = 0;
			int gotFive = 0;
			int gotSix = 0;
			int gotSeven = 0;
			int gotEight = 0;
			int gotNine = 0;
			String zeroS = new String();
			String oneS = new String();
			String twoS = new String();
			String threeS = new String();
			String fourS = new String();
			String fiveS = new String();
			String sixS = new String();
			String sevenS = new String();
			String eightS = new String();
			String nineS = new String();

			Vector <String> zero = new Vector<>();
			Vector <String> one = new Vector<>();
			Vector <String> two = new Vector<>();
			Vector <String> three = new Vector<>();
			Vector <String> four = new Vector<>();
			Vector <String> five = new Vector<>();
			Vector <String> six = new Vector<>();
			Vector <String> seven = new Vector<>();
			Vector <String> eight = new Vector<>();
			Vector <String> nine = new Vector<>();
			Matcher m = p.matcher(blah.get(i));
			m.find();
			String left = m.group(1);
			String right = m.group(2);

			//out.println(blah.get(i)); out.println(left); out.println(right);
			Vector <String> leftNums = new Vector <>();
			Vector <String> all = new Vector <>();
			Scanner scanner = new Scanner(left);
			scanner.useDelimiter("[\t\\s ]+");
			while (scanner.hasNext()) {
				String ne = scanner.next();
				char []ln = ne.toCharArray();
				Arrays.sort(ln);
				String xx = new String(ln);
				leftNums.add(xx);
				all.add(xx);
			}
			out.println(leftNums.size());

			Vector <String> rightNums = new Vector <>();
			Scanner scanner2 = new Scanner(right);
			scanner2.useDelimiter("[\t\\s ]+");
			while (scanner2.hasNext()) {
				String ne = scanner2.next();
				char []rn = ne.toCharArray();
				Arrays.sort(rn);
				String xx = new String(rn);
				rightNums.add(xx);
				all.add(xx);
			}
			out.println(rightNums.size());

			for (int kk = 0; kk < all.size(); kk++) {
				//out.println(rightNums.get(kk));
				String bb = all.get(kk);

				if (bb.length() == 2) {
					if (!one.contains(bb)) {
						one.add(bb);
					}
					gotOne = 1;
					oneS = new String(bb);

				} else if (bb.length() == 4) {
					if (!four.contains(bb)) {
						four.add(bb);
					}
					gotFour = 1;
					fourS = new String(bb);
				} else if (bb.length() == 7) {
					if (!eight.contains(bb)) {
						eight.add(bb);
					}
					gotEight = 1;
					eightS = new String(bb);
				} else if (bb.length() == 3) {
					if (!seven.contains(bb)) {
						seven.add(bb);
					}
					gotSeven = 1;
					sevenS = new String(bb);
				} else if (bb.length() == 5) {
					if (!two.contains(bb)) {
						two.add(bb);
					}
					if (!three.contains(bb)) {
						three.add(bb);
					}
					if (!five.contains(bb)) {
						five.add(bb);
					}
				} else if (bb.length() == 6) {
					if (!zero.contains(bb)) {
						zero.add(bb);
					}
					if (!six.contains(bb)) {
						six.add(bb);
					}
					if (!nine.contains(bb)) {
						nine.add(bb);
					}
				}
			}
			if (gotEight == 0) {eightS = new String ("abcdefg"); gotEight = 1; eight.add(eightS);}

			if (eight.size() == 1 && zero.size() >= 1 && one.size() == 1) {
				for (int kk = 0; kk < zero.size(); kk++) {
					String in2 = interS(eight.get(0), zero.get(kk));
					String in1 = interS(in2, one.get(0));
					if (in1.length() == 0) {
						out.println("gotzero");
						gotZero = 1;
						zeroS = new String(zero.get(kk));
					} else if (in1.length() == 2) {
						out.println("gotnine");
						gotNine = 1;
						nineS = new String(zero.get(kk));
					} else if (in1.length() == 1) {
						out.println("gotsix");
						gotSix = 1;
						sixS = new String(zero.get(kk));
					}
				}
			}

			//poss
			if (one.size() == 1 && five.size() >= 1) {
				for (int kk = 0; kk < five.size(); kk++) {
					String inter = interS(one.get(0), five.get(kk));
					String un = unc(one.get(0), five.get(kk));
					if (inter.length() == 2) {
						gotThree = 1;
						three.add(five.get(kk));
						threeS = new String(five.get(kk));
					} else if (inter.length() == 1) {
					}
				}
			}

			if (gotThree == 1 && gotNine == 0 && three.size() == 1 && nine.size() >= 1) {
				for (int kk = 0; kk < nine.size(); kk++) {
					String unc1 = unc(three.get(0), nine.get(kk));

					if (unc1.length() == 1) {
						nineS = new String(nine.get(kk));
						gotNine = 1;
					} else if (unc1.length() == 3) {
						//six or zero
					}
				}
			}

			if (four.size() == 1 && nine.size() >= 1) {
				for (int kk = 0; kk < nine.size(); kk++) {
					String unc1 = interS(four.get(0), nine.get(kk));
					if (unc1.length() == 4) {
						nineS = new String(nine.get(kk));
						gotNine = 1;
					} else {
						if (one.size() == 1) {
							String unc2 = interS(unc1, one.get(0));
							if (unc2.length() == 2) {
								gotZero = 1;
								zeroS = new String(nine.get(kk));
							} else {
								gotSix = 1;
								sixS = new String(nine.get(kk));
							}
						}
					}
				}
			}

			if (gotEight == 1 && gotNine == 1 && gotFour == 1) {
				out.println("here2"); out.println(two.size());
				for (int kk = 0; kk < two.size(); kk++) {
					out.println("here1...");
					String unc1 = unc(nineS, two.get(kk));

					out.println(unc1.length());
					if (unc1.length() == 3) {
						gotTwo = 1;
						twoS = new String(two.get(kk));
					} else if (unc1.length() ==  1) {
						//three or five
						if (gotOne == 1) {
							String in2 = interS(unc1, oneS);
							if (in2.length() == 1) {
								gotFive = 1;
								fiveS = two.get(kk);
							}  else if (in2.length() == 0) {
								gotThree = 1;
								threeS = two.get(kk);
							}
						}
					}
				}

			}

			out.println("gots:: ");
			out.println(gotZero);
			out.println(gotOne);
			out.println(gotTwo);
			out.println(gotThree);
			out.println(gotFour);
			out.println(gotFive);
			out.println(gotSix);
			out.println(gotSeven);
			out.println(gotEight);
			out.println(gotNine);
			out.println("----------");


			char [] ans = new char[rightNums.size()];
			for (int kk = 0; kk < rightNums.size(); kk++) {
				String bb = rightNums.get(kk);
				if (bb.equals(zeroS)) {
					ans[kk] = '0';
					out.print(0);
				} else if (bb.equals(oneS)) {
					ans[kk] = '1';
					out.print(1);
				} else if (bb.equals(twoS)) {
					ans[kk] = '2';
					out.print(2);
				} else if (bb.equals(threeS)) {
					ans[kk] = '3';
					out.print(3);
				} else if (bb.equals(fourS)) {
					ans[kk] = '4';
					out.print(4);
				} else if (bb.equals(fiveS)) {
					ans[kk] = '5';
					out.print(5);
				} else if (bb.equals(sixS)) {
					ans[kk] = '6';
					out.print(6);
				} else if (bb.equals(sevenS)) {
					ans[kk] = '7';
					out.print(7);
				} else if (bb.equals(eightS)) {
					ans[kk] = '8';
					out.print(8);
				} else if (bb.equals(nineS)) {
					ans[kk] = '9';
					out.print(9);
				} else {
					ans[kk] = '?';
					out.print("?");
				}
			}

			out.println();
			String ansS = new String(ans);
			out.println(ansS);
			tot += Integer.valueOf(ansS);

			//Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();

			
		}
		/*
		   for (String item : one) {
		   out.println(item);
		   }
		   */

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
		out.println("");
	}

	public static String union(String s1, String s2){
		String s = (s1 + s2).toLowerCase(); //start with entire contents of both strings
		int i = 0;
		while(i < s.length()){
			char c = s.charAt(i);
			if(i != s.lastIndexOf(c)) //If c occurs multiple times in s, remove first one
				s = s.substring(0, i) + s.substring(i+1, s.length());
			else i++; //otherwise move pointer forward
		}
		return s;
	}
	public static String unc(String str1, String str2) {
		// Convert strings to sets for easy comparison
		HashSet<Character> set1 = new HashSet<>();
		HashSet<Character> set2 = new HashSet<>();

		for (char c : str1.toCharArray()) {
			set1.add(c);
		}
		for (char c : str2.toCharArray()) {
			set2.add(c);
		}

		// Create a copy of set1 and remove common elements
		HashSet<Character> uncommon = new HashSet<>(set1);
		uncommon.addAll(set2); // Combine both sets
		HashSet<Character> common = new HashSet<>(set1);
		common.retainAll(set2); // Find common elements
		uncommon.removeAll(common); // Remove common elements from the combined set

		// Convert the result back to a string
		StringBuilder result = new StringBuilder();
		for (char c : uncommon) {
			result.append(c);
		}

		return result.toString();
	}
	public static String interS(String str1, String str2) {
		HashSet<Character> set1 = new HashSet<>();
		HashSet<Character> intersection = new HashSet<>();

		// Add characters of the first string to a set
		for (char c : str1.toCharArray()) {
			set1.add(c);
		}

		// Check for common characters in the second string
		for (char c : str2.toCharArray()) {
			if (set1.contains(c)) {
				intersection.add(c);
			}
		}

		// Build the result string from the intersection set
		StringBuilder result = new StringBuilder();
		for (char c : intersection) {
			result.append(c);
		}

		return result.toString();
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

