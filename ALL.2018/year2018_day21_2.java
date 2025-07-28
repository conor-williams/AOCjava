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
class year2018_day21_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static String instr[] = new String[1000];
	public static int instNum = 0;
	static class in_s {
		int in = 0;
		int regA = 0;
		int regB = 0;	
		int regC = 0;
	}
	static in_s ins[] = new in_s[1000];
	public static void main(String [] args) {
		out.println("		2018 Day21.2");
		out.println("	SLOW ~20seconds");
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
		for (int ii = 0; ii < 1000; ii++) {
			instr[ii] = new String();
			ins[ii] = new in_s();
		}
		Pattern p = Pattern.compile("([A-Za-z]+) (\\d+) (\\d+) (\\d+)");
		Pattern p2 = Pattern.compile("#ip (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		String ipline = new String();
		int instREG = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (i == 0) {
				ipline = blah.get(0);
				Matcher m2 = p2.matcher(ipline);
				if (m2.find()) {
					instREG = Integer.valueOf(m2.group(1));
				}
			} else {
				instr[i-1] = blah.get(i);
				Matcher m = p.matcher(instr[i-1]);
				if (m.find()) {
					ins[i-1].in = mycmp(m.group(1));
					ins[i-1].regA = Integer.valueOf(m.group(2));
					ins[i-1].regB = Integer.valueOf(m.group(3));
					ins[i-1].regC = Integer.valueOf(m.group(4));
				}

			}
		}
		instNum = blah.size()-1;

		int regb[] = new int[10];
		long min = 99999999999L;
		int regA = 0, regB = 0, regC = 0, in = 0;
		int ans = 0;
		int prev = 0;
		Map <Integer, Integer> mp = new HashMap<>();
		{
			///if (REG0 % 10000 == 0) {out.print("loop REG0: "); out.println(REG0);}
			//long regb[] = new long[10];
			long count = -1;
			regb = new int[10];
			regb[0] = 1;
after:
			for (int k = 0; k < instNum; k++) {	
				count++;
				in = ins[k].in;
				regA = ins[k].regA;
				regB = ins[k].regB;
				regC = ins[k].regC;

				regb[instREG] = k;
				if (in != -1) {
					switch(in) {
						case 9: //addr
							regb[regC] = regb[regA] + regb[regB];
							break;
						case 11: //addi
							regb[regC] = regb[regA] + regB;
							break;
						case 15: //mulr
							regb[regC] = regb[regA] * regb[regB];
							break;
						case 7: //muli
							regb[regC] = regb[regA] * regB;
							break;
						case 5: //banr
							regb[regC] = regb[regA] & regb[regB];
							break;
						case 1: //bani
							regb[regC] = regb[regA] & regB;
							break;
						case 6: //borr
							regb[regC] = regb[regA] | regb[regB];
							break;
						case 3: //bori
							regb[regC] = regb[regA] | regB;
							break;
						case 8: //setr
							regb[regC] = regb[regA];
							break;
						case 2: //seti
							regb[regC] = regA;
							break;
						case 12: //gtir
							if (regA > regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 14: //gtir
							if (regb[regA] > regB) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 13: //gtrr
							if (regb[regA] > regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 4: //eqir
							if (regA == regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 0:
							if (regb[regA] == regB) {regb[regC] = 1;} else {regb[regC] = 0;}
							break;
						case 10:
							if (regb[regA] == regb[regB]) {regb[regC] = 1;} else {regb[regC] = 0;}
							mp.put(regb[regA], mp.getOrDefault(regb[regA], 0) + 1);
							
							//out.println(regb[regA]);

							if (mp.get(regb[regA]) > 1) {
								//out.println(regb[regA]);
								//out.println(prev);
								ans = prev;
								break after;
							} else {
								prev = regb[regA];
							}
							break;

						default:
							out.println("ERR1");
					}
				}
				if (regC ==  instREG) {k = regb[instREG];}
				//if (count > 4000) {break;}
			}
		}


		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
		out.println("");
	}

	public static int mycmp(String four) {
		switch(four) {
			case "addr":
				return 9;
			case "addi":
				return 11;
			case "mulr":
				return 15;
			case "muli":
				return 7;
			case "banr":
				return 5;
			case "bani":
				return 1;
			case "borr":
				return 6;
			case "bori":
				return 3;
			case "setr":
				return 8;
			case "seti":
				return 2;
			case "gtir":
				return 12;
			case "gtri":
				return 14;
			case "gtrr":
				return 13;
			case "eqir":
				return 4;
			case "eqri":
				return 0;
			case "eqrr":
				return 10;
			default:
				out.println("ERR2");
		}
		return -1;

		/*
		   if (four.equals("addr")) {return 9;}
		   else if (four.equals("addi")) {return 11;}
		   else if (four.equals("mulr")) {return 15;}
		   else if (four.equals("muli")) {return 7;}
		   else if (four.equals("banr")) {return 5;}
		   else if (four.equals("bani")) {return 1;}
		   else if (four.equals("borr")) {return 6;}
		   else if (four.equals("bori")) {return 3;}
		   else if (four.equals("setr")) {return 8;}
		   else if (four.equals("seti")) {return 2;}
		   else if (four.equals("gtir")) {return 12;}
		   else if (four.equals("gtri")) {return 14;}
		   else if (four.equals("gtrr")) {return 13;}
		   else if (four.equals("eqir")) {return 4;}
		   else if (four.equals("eqri")) {return 0;}
		   else if (four.equals("eqrr")) {return 10;}
		   else {out.println("ERR2");}
		   */
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

