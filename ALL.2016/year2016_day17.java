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
//


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
class year2016_day17 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	public static char grid [][] = {
		{'#','#','#','#','#','#'},
		{'#',' ',' ',' ',' ','#'},
		{'#',' ',' ',' ',' ','#'},
		{'#',' ',' ',' ',' ','#'},
		{'#',' ',' ',' ','V',' '},
		{'#','#','#','#','#','#'},
	};

	//    public static int already [][] = new int[leny][lenx];

	public static String ans[] = new String[3000];
	public static int ansPos = 0;
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day17.1");
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
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		String ans2 = new String();
		for (int i = 0; i < blah.size(); i++) {
			String buf = new String();
			buf = generateMD5(blah.get(i));
			out.println(blah.get(i));
			out.println(buf);
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			int sx = 1; int sy = 1;
			for (int ii = 0; ii < 4; ii++) {
				if (buf.charAt(ii) == 'b' || buf.charAt(ii) == 'c' ||
						buf.charAt(ii) == 'd' || buf.charAt(ii) == 'e'
						|| buf.charAt(ii) == 'f') {
					switch(ii) {
						case 0:
							next(sx, sy-1, 0, 'U', blah.get(i));
							break;
						case 1:
							next(sx, sy+1, 0, 'D', blah.get(i));
							break;
						case 2:
							next(sx-1, sy, 0, 'L', blah.get(i));
							break;
						case 3:
							next(sx+1, sy, 0, 'R', blah.get(i));
							break;
					}
				} else {
				}

			}

			if (ansPos > 0) {
				int min=99999; int minPos = 0;
				for (int j = 0; j < ansPos; j++) {
					if (ans[j].length() < min) {min = ans[j].length(); minPos = j;}
				}
				ans2 = ans[minPos].substring(blah.get(i).length());
				break;
			}
		}

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans2);
		out.println("");
	}
	public static void next(int x, int y, int steps, char dir, String in) {


		if (grid[y][x] == '#') {
			return;
		} else if (grid[y][x] == ' ') {
			String buf = new String(in+dir);
			if (buf.length() > 190) {return;}
			String buf2 = generateMD5(buf);
			
			//printf("out6 is %s\n", out6);
			for (int i = 0; i < 4; i++) {
				if (buf2.charAt(i) == 'b' || buf2.charAt(i) == 'c' || buf2.charAt(i) == 'd' || buf2.charAt(i) == 'e' || buf2.charAt(i) == 'f') {
					//door open
					switch (i) {
						case 0: //UP
							next(x, y-1, steps+1, 'U', buf);
							break;
						case 1: //DOWN
							next(x, y+1, steps+1, 'D', buf);
							break;
						case 2: //LEFT
							next(x-1, y, steps+1, 'L', buf);
							break;
						case 3: //RIGHT
							next(x+1, y, steps+1, 'R', buf);
							break;
					}
				} else {
					//door locked
				}
			}
		} else if (grid[y][x] == 'V') {
			ans[ansPos] = new String(in+dir);
			ansPos++;
			//return;
		}
	}

	public static String generateMD5(String input) {
		try {
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");

			// Compute the hash
			byte[] hashBytes = md.digest(input.getBytes());

			// Convert byte array to hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hashBytes) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 algorithm not found", e);
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

