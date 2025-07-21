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
class year2017_day18_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int tot = 0;
	public static String inst[] = new String[100];

	public static void main(String [] args) {
		out.println("		2017 Day18.2");
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

		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("(snd|rcv) ([-?A-Za-z\\d]+)");
		Pattern p2 = Pattern.compile("(set|add|mul|mod|jgz) ([-?A-Za-z\\d]+) ([-?A-Za-z\\d]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);



		leny = blah.size();
		char reg1 = 'a', reg2 = 'a'; int num1 = 0, num2 = 0;
		long regs[] = new long[26];
		long lastSound = 0;
		long ans = 0;
after:
		for (int i = 0; i < leny; i++) {
			Matcher m1 = p1.matcher(blah.get(i));
			Matcher m2 = p2.matcher(blah.get(i));
			if (m1.find()) {
				int chch = 0;
				if (isNumeric(m1.group(2))) {
					num1 = Integer.valueOf(m1.group(2));
				} else {
					reg1 = m1.group(2).charAt(0);
					chch = 1;
				}

				if (m1.group(1).equals("snd")) {
					if (chch == 1) {
						lastSound = regs[(int)(reg1-97)];
					}
				} else if (m1.group(1).equals("rcv")) {
					if (chch == 1) {
						if (regs[reg1-97] != 0) {ans = lastSound; break after;} else {continue;}
					}
				} else {
					out.println("ERR1");
				}
			} else if (m2.find()) {
				int numCh = 0;
				if (!isNumeric(m2.group(2)) && !isNumeric(m2.group(3))) {
					numCh = 2;
					reg1 = m2.group(2).charAt(0);
					reg2 = m2.group(3).charAt(0);
				} else if (!isNumeric(m2.group(2))) {
					numCh = 1;
					reg1 = m2.group(2).charAt(0);
					num1 = Integer.valueOf(m2.group(3));
				} else if (isNumeric(m2.group(2)) && isNumeric(m2.group(3))) {
					numCh = 0;
					num1 = Integer.valueOf(m2.group(2));
					num2 = Integer.valueOf(m2.group(3));
				} else {
					out.println("ERR44");
					numCh = 10;
				}

				switch (m2.group(1)) {
					case "set":
						if (numCh == 0) {
						} else if (numCh == 1) {
							regs[reg1-97] = num1; continue;
						} else if (numCh == 2) {
							regs[reg1-97] = regs[reg2-97]; continue;
						} else {
							out.println("ERR4");
						}
						break;
					case "add":
						if (numCh == 0) {
						} else if (numCh == 1) {
							regs[reg1-97] += num1;
						} else if (numCh == 2) {
							out.println(blah.get(i));
							out.println(reg1);
							out.println(reg2);
							regs[reg1-97] += regs[reg2-97];
						} else {
							out.println("ERR4");
						}
						break;
					case "mul":
						if (numCh == 0) {
						} else if (numCh == 1) {
							regs[reg1-97] *= num1;
						} else if (numCh == 2) {
							regs[reg1-97] *= regs[reg2-97];
						} else {
							out.println("ERR4");
						}
						break;
					case "mod":
						if (numCh == 0) {
						} else if (numCh == 1) {
							regs[reg1-97] %= num1;
						} else if (numCh == 2) {
							regs[reg1-97] %= regs[reg2-97];
						} else {
							out.println("ERR4");
						}
						break;
					case "jgz":
						if (numCh == 0) {
						} else if (numCh == 1) {
							if (regs[reg1-97] > 0) {i+=num1 -1; continue;} else {continue;}
						} else if (numCh == 2) {
							if (regs[(int)(reg1-97)] > 0) {i+= regs[(int)(reg2-97)] -1; continue;} else {continue;}
						} else {
							out.println("ERR4");
						}
						break;
					default:
						out.println("ERR2");
				}
			} else {
				out.println(blah.get(i));
				out.println("ERR3");
			}
		}
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print("Not completed yet - threadsP");
		out.println("");
	}
	public static boolean isNumeric(String str) {
		return str != null && str.matches("-?\\d+(\\.\\d+)?");
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

