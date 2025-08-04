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
class year2015_day24 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	static long tot = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	static class qua {
		long num1;
		long QE;
	}
	static qua mine[] = new qua[5000];
	static int minePos = 0;
	static int weights[] = new int[1];
	public static void main(String [] args) {
		out.println("		2015 Day24.1");
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
		for (int ii = 0; ii < 5000; ii++) {
			mine[ii] = new qua();
		}
		weights = new int[blah.size()];
		for (int i = 0; i < blah.size(); i++) {
			weights[i] = Integer.valueOf(blah.get(i));
		}
		long weTot = 0;
		Arrays.sort(weights);
		for (int i = 0; i < leny; i++) {
			weTot += weights[i];
		}
		weTot = weTot/3;
		long mymin = Long.MAX_VALUE;
		tot = 0;

		for (int end = 0; end < leny; end++) {
			int arr[] = new int[leny];
			for (int i = 0; i < end; i++) {
				arr[i] = 1;
			}
			for (int i = end; i < leny;i++ ) {
				arr[i] = 0;
			}

			Arrays.sort(arr);
			int arrOrig[] = new int[leny];
			arrOrig = Arrays.copyOf(arr, leny);


jump:
			do {
				long mysum = 0; long QE = 1; long num1 = 0;

				num1 = end;
				if (num1 > mymin) {break;}
				for (int j = 0; j < leny; j++) {
					if (arr[j] == 1) {
						mysum += weights[j];
						QE *= weights[j];
					}
				}
				if (mysum == weTot && (mymin == Long.MAX_VALUE || num1 <= mymin)) {
					int pos = 0; long tmpW[] = new long[leny];
					for (int k = 0; k < leny; k++) {
						if (arr[k] == 0) {
							tmpW[pos] = weights[k]; pos++;
						}
					}
					for (int end1 = 0; end1 < pos; end1++) {
						int arr1[] = new int[leny];
						for (int l = 0; l < end1; l++) {
							arr1[l] = 1;
						}
						for (int m = end1; m < pos; m++) {
							arr1[m] = 0;
						}
						int arr2[] = new int[pos];
						for (int ii = 0; ii < pos; ii++) {
							arr2[ii] = arr1[ii];
						}
						Arrays.sort(arr2);
						int arr1Orig[] = new int[pos];
						arr1Orig = Arrays.copyOf(arr2, pos);
						do {
							long mysum1 = 0;
							long QE2 = 1;
							for (int n = 0; n < pos; n++) {
								if (arr2[n] == 1) {
									mysum1 += tmpW[n];
									QE2 *= tmpW[n];
								}
							}
							if (mysum1 == weTot) {
								int found = 0;
								for (int z = 0; z < minePos; z++) {
									if (mine[z].num1 == num1 && mine[z].QE == QE) {
										found = 1;
										break;
									}
								}
								if (num1 < mymin) {mymin = num1;}
								if (found == 0 || minePos == 0) {
									mine[minePos].num1 = num1;
									mine[minePos].QE = QE; minePos++;
									tot++;
									continue jump;
								}
							}
						} while (nextPermutation(arr2) && !Arrays.equals(arr2, arr1Orig));
					}
				}
				
			}  while (nextPermutation(arr) && !Arrays.equals(arr, arrOrig));
		}
		long min = Long.MAX_VALUE;
		for (int i = 0; i < minePos; i++) {
			if (mine[i].num1 < min) {min = mine[i].num1;}
		}
		long minQE = Long.MAX_VALUE;
		for (int i = 0; i < minePos; i++) {
			if (mine[i].QE < minQE) {minQE = mine[i].QE;}
		}

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minQE);
		out.println("");
	}
	public static boolean nextPermutation(int[] nums) {
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
		return true;
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

