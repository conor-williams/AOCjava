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
class year2021_day18_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	public static int lenyOrig = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static String alfa = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static int placeHolder[] = new int[65];
	public static int placeHolderPos = 0;
	public static String lines[] = new String[100];
	public static String linesOrig[] = new String[100];

	public static void main(String [] args) {
		out.println("		2021 Day18.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));


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
		for (int i = 0; i < blah.size(); i++) {
			lines[i] = new String(blah.get(i));
			linesOrig[i] = new String(blah.get(i));
		}
		int combo[] = new int[leny];
		int comboOrig[] = new int[leny];
		combo[0] = 1;
		combo[1] = 2;

		lenyOrig = leny;
		leny = 2;
		int var_max = 0;

		comboOrig = combo.clone();
		do {
			int var_one = 0;
			int var_two = 0;
			for (int i = 0; i < lenyOrig; i++) {
				lines[i] = new String(linesOrig[i]);
			}
			for (int i = 0; i < lenyOrig; i++) {
				if (combo[i] == 1) {
					lines[0] = new String(linesOrig[i]);
					var_one = 1;
				} else if (combo[i] == 2) {
					lines[1] = new String(linesOrig[i]);
					var_two = 1;
				}
				if (var_one == 1 && var_two == 1) {
					break;
				}
			}
			placeHolderPos = 0;
			String line2 = new String(lines[0]);

			for (int g = 1; g < leny; g++) {
				line2 = "[" + line2 + "," + lines[g] + "]";
				int num1 = 0, num2 = 0;
				int go = 1;
				while (go == 1) {
					go = 0;
					int count = 0;
					for (int i = 0; i < line2.length(); i++) {
						if (line2.charAt(i) == '[') {
							count++;
						} else if (line2.charAt(i) == ']') {
							count--;
						}
						if (count >= 5 && line2.charAt(i) == '[' && isalfa(line2.charAt(i+1)) && 
								line2.charAt(i+2) == ',' && isalfa(line2.charAt(i+3))) {//]
							int lpos = i;
							num1 = getalfa(line2.charAt(i+1));
							num2 = getalfa(line2.charAt(i+3));
							int rpos = i+5;

							String subl;
							String left = new String(line2);
							subl = left.substring(0, lpos);
							var sb1 = new StringBuilder(subl);
							sb1.reverse();
							subl = sb1.toString();
							for (int k = 0; k < subl.length(); k++) {
								if (isalfa(subl.charAt(k))) {
									int sum1 = num1 + getalfa(subl.charAt(k));
									if (sum1 < 10) {
										var sb2 = new StringBuilder(subl);
										sb2.setCharAt(k, (char)(sum1+48));
										subl = sb2.toString();
									} else {
										var sb3 = new StringBuilder(subl);
										sb3.setCharAt(k, alfa.charAt(sum1));
										subl = sb3.toString();
									}
									break;
								}
							}
							var sb4 = new StringBuilder(subl);
							sb4.reverse();
							subl = sb4.toString();

							String subr;
							String right = new String(line2);
							subr = right.substring(rpos);
							for (int k = 0; k < subr.length(); k++) {
								if (isalfa(subr.charAt(k))) {
									int sum1 = num2 + getalfa(subr.charAt(k));
									if (sum1 < 10) {
										var sb5 = new StringBuilder(subr);
										sb5.setCharAt(k, (char)(sum1+48));
										subr = sb5.toString();
									} else {
										var sb6 = new StringBuilder(subr);
										sb6.setCharAt(k, alfa.charAt(sum1));
										subr = sb6.toString();
									}
									break;
								}
							}
							String tmp1 = subl + "0" + subr;
							line2 = new String(tmp1); i = -1; count = 0; continue;
						} else {
							continue;
						}
					}


after1:
					for (int k = 0; k < line2.length(); k++) {
						if (isalfa(line2.charAt(k))) {
							int sum1 = getalfa(line2.charAt(k));
							if (sum1 >= 10) {
								int pos = sum1/2;
								char split1 = alfa.charAt(pos);
								char split2 = alfa.charAt(sum1 - pos);
								String tmp4 = "[" + split1 + "," + split2 + "]";
								var sb8 = new StringBuilder(line2);
								sb8.replace(k, k+1, tmp4);
								line2 = sb8.toString();
								go = 1;
								break after1;
							}
						}
					}
				}
			}
			for (int i = 0; i < line2.length(); i++) {
				if (Character.isDigit(line2.charAt(i))) {
					placeHolder[placeHolderPos] = line2.charAt(i)-48;
					var sb8 = new StringBuilder(line2);
					sb8.setCharAt(i, alfa.charAt(placeHolderPos));
					line2 = sb8.toString();
					placeHolderPos++;
					if (placeHolderPos > 62) { out.println("ERR1"); }
				}
			}

			int go1 = 1;
			while (go1 == 1) {
				go1 = 0;
after2:
				for (int i = 0; i < line2.length(); i++) {
					if (line2.charAt(i) == '[' &&  isalfa(line2.charAt(i+1)) && line2.charAt(i+2) == ',' && isalfa(line2.charAt(i+3)) ) {
						int sum1 = 3*getPlaceHolder(line2.charAt(i+1)) + 2*(getPlaceHolder(line2.charAt(i+3)));
						placeHolder[getalfa(line2.charAt(i+1))] = sum1;
						String tmp4 = "" + line2.charAt(i+1);
						var sb9 = new StringBuilder(line2);
						sb9.replace(i, i+5, tmp4);
						line2 = sb9.toString();
						go1 = 1;
						break after2;

					}
				}
			}

			int ans = 0;
after3:
			for (int ii = 0; ii < line2.length(); ii++) {
				if (isalfa(line2.charAt(ii))) {
					//	ans = getPlaceHolder(line2.charAt(ii)) ;
					if (getPlaceHolder(line2.charAt(ii)) > var_max) {var_max = getPlaceHolder(line2.charAt(ii));}
				}
			}
			nextPermutation(combo);
		} while (!Arrays.equals(combo, comboOrig));
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(var_max);
		out.println("");
	}
	public static int getPlaceHolder(char alf) {
		int pos = getalfa(alf);
		return placeHolder[pos];
	}

	public static boolean isalfa(char alf) {
		for (int i = 0; i < 62; i++) {
			if (alf == alfa.charAt(i)) {
				return true;
			}
		}
		return false;
	}

	public static int getalfa(char alf) {
		int found = 0;
		for (int i = 0; i < 62; i++) {
			if (alf == alfa.charAt(i)) {
				found = 1;
				return i;
			}
		}
		if (found == 0) {out.println("ERR2");}
		return 0;
	}

	public static void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private static void reverse(int[] nums, int start) {
		int end = nums.length - 1;
		while (start < end) {
			swap(nums, start, end);
			start++;
			end--;
		}
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

