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
import java.util.stream.IntStream;
import java.util.Comparator;



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
///MyClass[] array = IntStream.range(0, 5) .mapToObj(i -> new MyClass()) .toArray(MyClass[]::new);
@SuppressWarnings("unchecked")
class year2025_day10 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2025 Day10.1");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				//leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("\\[([\\.#]+)\\] ([\\(\\)\\d, ]+) \\{([\\d,]+)\\}");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");

		Vector <String> lights = new Vector<>();
		Vector <Vector <Vector <Integer>>> toggles = new Vector<>();

		for (int i = 0, nn = blah.size(); i < nn; i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			lights.add(m.group(1));
			out.println(m.group(2));
			//out.println(m.group(3));
			Scanner scanner = new Scanner(m.group(2));
			out.println(m.group(2));
			scanner.useDelimiter("[ ]+");
			Vector <Vector <Integer>> togglesPer = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				out.println("ne: [" + ne + "]");
				StringBuffer sb = new StringBuffer(ne);
				/*
				   if (sb.charAt(0) == ' ') {
				   sb.deleteCharAt(0);
				   } 
				   if (sb.charAt(sb.length()-1) == ' ') {
				   sb.deleteCharAt(sb.length()-1);
				   }
				   */
				//out.println("sb: " + sb);
				sb.deleteCharAt(0);
				sb.deleteCharAt(sb.length() -1);
				//out.println("sb2: " + sb.toString());

				String ne2 = sb.toString();
				Scanner scanner2 = new Scanner(ne2);
				scanner2.useDelimiter("[,]");
				Vector <Integer> var_ints = new Vector<>();

				while (scanner2.hasNext()) {
					String ne3 = scanner2.next();
					out.println(ne3);
					var_ints.add(Integer.valueOf(ne3));
				}
				out.println(var_ints.size());
				togglesPer.add(var_ints);
			}
			/*
			Collections.sort(togglesPer, new Comparator<Vector<Integer>>() {
				@Override
				public int compare(Vector<Integer> v1, Vector<Integer> v2) {
					// Null safety: treat null as size 0
					int size1 = (v1 == null) ? 0 : v1.size();
					int size2 = (v2 == null) ? 0 : v2.size();
					return Integer.compare(size1, size2);
				}
			});
			for (int ii = 0; ii < togglesPer.size(); ii++) {
				out.println(togglesPer.get(ii));
			}
			*/
			toggles.add(togglesPer);

		}

		int sum = 0;
		for (int ii = 0, n = blah.size(); ii < n; ii++) {
			Vector <Vector<Integer>> ve = toggles.get(ii);
			int len = ve.size(); //lights.get(ii).length();
			String lightsStart = ".".repeat(lights.get(ii).length());

			int min = 99999;
jump:
			for (int end = 1; end <= len; end++) {
				int arr[] = new int[len];
				for (int i = 0; i < end; i++) {
					arr[i] = 1;
				}
				for (int i = end; i < len; i++) {	
					arr[i] = 0;
				}
				Arrays.sort(arr);
				int arrOrig[] = new int[len];
				arrOrig = Arrays.copyOf(arr, len);
				do {
					StringBuffer li = new StringBuffer(lightsStart); //= new StringBuffer(lights.get(ii));
					
					//out.print("but: ");
					for (int i = 0; i < len; i++) {
						if (arr[i] == 1) {
							//press its buttons
							var ins = ve.get(i);
							//out.print(i + " ");
							//out.println(li);
							for (int kk = 0, n3 = ins.size(); kk < n3; kk++) {
								int qq = ins.get(kk);
								if (li.charAt(qq) == '.') {
									li.setCharAt(qq, '#');
								} else {
									li.setCharAt(qq, '.');
								}
							}

						}
					}
					//out.println();
					if (lights.get(ii).equals(li.toString())) {
						//got it
						out.println(li + " end: " + end);
						sum += end;
						break jump;
					}
				} while (nextPermutation(arr) && !Arrays.equals(arr, arrOrig));
			}

		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(sum);
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

