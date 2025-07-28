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
class year2016_day21_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	static long tot = 0;
	static Vector<Character> V = new Vector<>();
	static class instr {
		int num1;
		int num2;
		char let1;
		char let2;
		int type;
	}
	public static void main(String [] args) {
		out.println("		2016 Day21.2");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//Pattern p = Pattern.compile("(L|R)(\\d+)");
		instr ins[] = new instr[500];
		for (int ii = 0; ii < 500; ii++) {
			ins[ii] = new instr();
		}

		for (int i = 0; i < blah.size(); i++) {
			String line1 = blah.get(i);
			int num1 = 0, num2 = 0; char let1 = ' ', let2 = ' ';
			Pattern p1 = Pattern.compile("swap position (\\d+) with position (\\d+)");
			Matcher m1 = p1.matcher(line1);
			if (m1.find()) {
				ins[i].num1 = Integer.valueOf(m1.group(1));
				ins[i].num2 = Integer.valueOf(m1.group(2));
				ins[i].type = 1;
				continue;
			}
			Pattern p2 = Pattern.compile("swap letter ([A-Za-z]) with letter ([A-Za-z])");
			Matcher m2 = p2.matcher(line1);
			if (m2.find()) {
				ins[i].let1 = m2.group(1).charAt(0);
				ins[i].let2 = m2.group(2).charAt(0);
				ins[i].type = 2;

				continue;

			}

			Pattern p4 = Pattern.compile("reverse positions (\\d+) through (\\d+)");
			Matcher m4 = p4.matcher(line1);
			if (m4.find()) {
				ins[i].num1 = Integer.valueOf(m4.group(1));
				ins[i].num2 = Integer.valueOf(m4.group(2));
				ins[i].type = 4;
				continue;
			}

			Pattern p5 = Pattern.compile("rotate right (\\d+)");
			Matcher m5 = p5.matcher(line1);
			if (m5.find()) {
				ins[i].num1 = Integer.valueOf(m5.group(1));
				ins[i].type = 5;
				continue;
			}

			Pattern p6 = Pattern.compile("rotate left (\\d+)");
			Matcher m6 = p6.matcher(line1);

			if (m6.find()) {
				ins[i].num1 = Integer.valueOf(m6.group(1));
				ins[i].type = 6;
				continue;
			}

			Pattern p7 = Pattern.compile("move position (\\d+) to position (\\d+)");
			Matcher m7 = p7.matcher(line1);
			if (m7.find()) {
				ins[i].num1 = Integer.valueOf(m7.group(1));
				ins[i].num2 = Integer.valueOf(m7.group(2));
				ins[i].type = 7;
				continue;
			}

			Pattern p8 = Pattern.compile("rotate based on position of letter ([A-Za-z])");
			Matcher m8 = p8.matcher(line1);
			if (m8.find()) {
				ins[i].let1 = m8.group(1).charAt(0);
				ins[i].type = 8;
				continue;
			}
			out.println("ERR");
			out.println(line1);
		}
		String LINE = new String("abcdefgh");
		for (int ii = 0; ii < LINE.length(); ii++) {
			V.add(LINE.charAt(ii));
		}
		String wantAns = "fbgdceah";
		Vector <Character> VpermOrig = new Vector(V);
		Vector <Character> VOrig = new Vector(V);
		Vector <Character> Vbefore = new Vector<>();
		do {
			Vbefore = new Vector(V);

			for (int i = 0; i < blah.size(); i++) {
				var xx1 = ins[i];
				int num1 = 0, num2 = 0; char let1 = ' ', let2 = ' ';
				if (xx1.type == 1) {
					num1 = xx1.num1;
					num2 = xx1.num2;
					char tmp1 = V.get(num1);
					char tmp2 = V.get(num2);
					V.set(num2, tmp1);
					V.set(num1, tmp2);
					continue;
				}
				if (xx1.type == 2) {
					let1 = xx1.let1;
					let2 = xx1.let2;
					num1 = V.indexOf(let1);
					num2 = V.indexOf(let2);

					char tmp1 = V.get(num1);
					char tmp2 = V.get(num2);
					V.set(num2, tmp1);
					V.set(num1, tmp2);
					continue;

				}

				if (xx1.type == 4) {
					num1 = xx1.num1;
					num2 = xx1.num2;
					reverseSubVector(V, num1, num2);
					continue;
				}

				if (xx1.type == 5) {
					num1 = xx1.num1;
					Collections.rotate(V, num1);
					continue;
				}

				if (xx1.type == 6) {
					num1 = xx1.num1;
					Collections.rotate(V, -num1);
					continue;
				}

				if (xx1.type == 7) {
					num1 = xx1.num1;
					num2 = xx1.num2;
					char tmp1 = V.get(num1);
					char tmp2 = V.get(num2);

					V.remove(num1);
					if (num1 > num2) {
						V.add(num2, tmp1);
					} else if (num1 < num2) {
						V.add(num2, tmp1);
					}
					continue;
				}

				if (xx1.type == 8) {
					let1 = xx1.let1;
					int tmp1 = V.indexOf(let1)+1;

					if (tmp1-1 >= 4) {
						Collections.rotate(V, tmp1);
						Collections.rotate(V, 1);
					} else {
						Collections.rotate(V, tmp1);

					}
					continue;
				}
				out.println("ERR");
			}

			//String ans1 = V.stream().map(String::valueOf).collect(Collectors.joining());
			//String ans1 = "fbgdceah";
			String VasString = V.stream().map(String::valueOf).collect(Collectors.joining());
			if (wantAns.equals(VasString)) {break;}

			nextPermutation(VOrig);
			V = new Vector(VOrig);
		} while (!V.equals(VpermOrig));


		//		System.setOut(originalOut);
		String ansBefore = Vbefore.stream().map(String::valueOf).collect(Collectors.joining());
		out.print("**j_ans: ");
		out.print(ansBefore);
		out.println("");
	}
	public static void nextPermutation(Vector <Character> nums) {
		int i = nums.size() - 2;
		while (i >= 0 && nums.get(i) >= nums.get(i + 1)) {
			i--;
		}
		if (i >= 0) {
			int j = nums.size() - 1;
			while (j >= 0 && nums.get(j) <= nums.get(i)) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
	}

	private static void swap(Vector <Character> nums, int i, int j) {
		char temp = nums.get(i);
		nums.set(i, nums.get(j));
		nums.set(j, temp);
	}

	private static void reverse(Vector <Character> nums, int start) {
		int end = nums.size() - 1;
		while (start < end) {
			swap(nums, start, end);
			start++;
			end--;
		}
	}

	public static void reverseSubVector(Vector<Character> vector, int a, int b) {
		if (a < 0 || b >= vector.size() || a > b) {
			throw new IllegalArgumentException("Invalid indices");
		}
		while (a < b) {
			Collections.swap(vector, a, b);
			a++;
			b--;
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

