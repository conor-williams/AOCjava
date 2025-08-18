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
class year2024_day17_2 {
	//	        public static int maxPath = 0;
	static long firstVal = -1;
	static long secondVal = -1;
	static long thirdVal = -1;
	public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	public static long regs[] = new long[3];
	public static Vector<inst_s> ops = new Vector<>();
	public static Vector <Tuple<Long, Long>> ve = new Vector<>();
	static String theProg = new String();
	static Vector<Long> thirdV = new Vector<>();
	static String revProg = new String();

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day17.2");
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


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("Register ([A-Za-z]): (\\d+)");
		Pattern p2 = Pattern.compile("Program: ([\\d,]+)");

		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");

		theProg = new String();
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {
				continue;
			} else if (blah.get(i).charAt(0) == 'R') {
				char reg; long val;
				Matcher m = p.matcher(blah.get(i));
				if (m.find()) {
					reg = m.group(1).charAt(0);
					val = Long.parseLong(m.group(2));
					regs[reg-65] = val;
				}
			} else if (blah.get(i).charAt(0) == 'P') {
				theProg = blah.get(i).substring(blah.get(i).indexOf(" ")+1) + ",";
				Matcher m2 = p2.matcher(blah.get(i));
				m2.find();
				int pos = 0;
				long oper = 0;
				long operand = 0;
				String ne = m2.group(1);
				Scanner scanner = new Scanner(ne);
				scanner.useDelimiter("[,]");
				while (scanner.hasNext()) {
					if ((pos % 2) == 1) {
						operand = Long.parseLong(scanner.next());
						inst_s tmp = new inst_s();
						tmp.operand = operand;
						tmp.oper = oper;
						//ops[pos/2] = new inst_s(tmp);
						ops.add(tmp);
						//numInst++;
					} else {
						oper = Long.parseLong(scanner.next());
					}
					pos++;
				}
			}
		}
		/*
		for (int ii = 0; ii < ops.size(); ii++) {
			out.print(ops.get(ii).oper); out.print(" "); out.println(ops.get(ii).operand);
		}
		out.println("--------");
		*/
		//Scanner scanner = new Scanner(System.in); scanner.nextLine();


		Set<Long> poss = new HashSet<>();
		out.println(part2(0, 1));

		out.print("117440 yields:"); out.println(machine(117440));
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		//out.print(ans);
		out.println("");
	}

	public static class inst_s {
		long oper;
		long operand;
	}

	static long part2(long A, int PP) {
		Set <Long> result = new HashSet<>();
		for (int n = 0; n < 8; n++) {
			long A2 = (A << 3) | n;
			String output = machine(A2);

			//out.print(output); out.print(" V "); out.println(theProg.substring(theProg.length()-PP*2));
			if (output.equals(theProg.substring((theProg.length()-(PP*2))))) {

				if (output.equals(theProg)) {
					result.add(A2);
					//out.println("gotcha..");
				} else {
					long possible = part2(A2, PP+1);
					/*
					if (possible == 164278764924605L) {
						out.println("got it...");
						Runtime.getRuntime().halt(0);
					}
					*/
					/*
					if (possible != 0) {
						result.add(possible);
					}
					*/
				}
			}
		}
		if (result.size() > 0) {
			out.print("**j_ans: ");
			out.print(Collections.min(result));
			out.println();
			Runtime.getRuntime().halt(0);
			//return Collections.min(result);
		} else {
			return 0;
		}
		return 0;
	}
	static String machine(long incomingREGSZERO) {
		String vv = new String("");
		String out_var = new String("");
		regs[0] = incomingREGSZERO;
		regs[1] = 0;
		regs[2] = 0;
		long xx = -1, yy = -1, zz = -1;

		for (int i = 0; i < ops.size(); i++) {
			inst_s tmp = ops.get(i);
			long oper = tmp.oper;
			long operand = tmp.operand;
			long comboval = 0;
			long literalval;
			literalval = operand;
			//out.print("oper: "); out.println(oper);

			switch ((int)operand) {
				case 0:
				case 1:
				case 2:
				case 3:
					comboval = operand;
					break;
				case 4:
					comboval =  regs[0];
					break;
				case 5:
					comboval = regs[1];
					break;
				case 6:
					comboval = regs[2];
					break;
				case 7:
					comboval = 7;
					break;
				default:
					out.println("not here?...");
					Runtime.getRuntime().halt(0);
			}

			switch ((int)(oper)) {
				case 0:
					//adv
					//xx = (int)((regs[0]/(long)Math.pow(2,comboval))%Integer.MAX_VALUE);
					xx = (regs[0]/(long)Math.pow(2,comboval));// & 0xFFFFFFFF;
					regs[0] = xx;
					break;
				case 1:
					//bxl
					regs[1] = regs[1]^literalval;
					break;
				case 2:
					//bst
					regs[1] = comboval % 8;
					break;
				case 3:
					//jnz
					if (regs[0] == 0) {
						//nothing
					} else {
						i = (int)(literalval-1);
					}
					break;
				case 4:
					//bxc
					regs[1] = regs[1] ^ regs[2];
					break;
				case 5:
					//comboval = comboval%8;
					vv = String.valueOf(comboval%8);
					out_var +=  vv + ',';
				//	out.println(out_var);

					break;
				case 6:
					//bdv
					yy = (regs[0]/(long)Math.pow(2,comboval));// & 0xFFFFFFFF;
					regs[1] = yy;
					break;
				case 7:
					//cdv
					zz = (regs[0]/(long)Math.pow(2,comboval));// & 0xFFFFFFFF;
					regs[2] = zz;
					break;
			}
		}
		return out_var;
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

