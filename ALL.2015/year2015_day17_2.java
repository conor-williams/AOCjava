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
class year2015_day17_2 {
//	        public static int maxPath = 0;
    //    public static int lenx = 0;
        public static int leny = 0;
      //  public static char grid [][] = new char[leny][lenx];
    //    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day17.2");
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
                /*
                grid = new char[leny][lenx];
                already = new int[leny][lenx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/


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
		Vector <Integer> buckets = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			buckets.add(Integer.valueOf(blah.get(i)));
		}
		int ar[] = new int[blah.size()];
		int vol = 150;
		int tot = 0;
		int min = 9999;
		int count[] = new int[100];
		for (int end = 0; end < leny; end++) {
			for (int i = 0; i < end; i++) {
				ar[i] = 1;
			}
			for (int i = end; i < leny; i++) {
				ar[i] = 0;
			}

			int arOrig[] = Arrays.copyOf(ar, ar.length);
			do {
				int sumB = 0;
				for (int i = 0; i < leny; i++) {
					if (ar[i] == 1) {
						sumB += buckets.get(i);

					}
				}
				if (sumB == vol) {
					tot++;
					if (end < min) {min = end;}
					count[end]++;
				}
				nextPermutation(ar);
			} while (!Arrays.equals(ar, arOrig));
		}


//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(count[min]);
		out.println("");
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

