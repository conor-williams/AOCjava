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
// /java -Xmx2g year2019_day3.java *i1.txt


//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2015_day7_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day7.2");
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

		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("([a-z]+|\\d+) -> ([a-z]+)");
		Pattern p2 = Pattern.compile("([a-z]+|\\d+) (AND|OR|LSHIFT|RSHIFT) ([a-z]+|\\d+) -> ([a-z]+)");
		Pattern p3 = Pattern.compile("NOT ([a-z]+|\\d+) -> ([a-z]+)");
		//	month= m.group(2);

		Map <String, Integer> mp = new HashMap<>();
		instruction [] inst = new instruction[400];

		//while (restart == 1) 
		int numInsts = blah.size();
		for (int i = 0; i < blah.size(); i++) {
			inst[i] = new instruction();
			//out.println(blah.get(i));
			Matcher m1 = p1.matcher(blah.get(i));
			Matcher m2 = p2.matcher(blah.get(i));
			Matcher m3 = p3.matcher(blah.get(i));
			if (m2.find()) {
				if (Character.isDigit(m2.group(1).charAt(0))) { 
					inst[i].num = Integer.parseInt(m2.group(1));
				} else {
					inst[i].reg1 = m2.group(1);
				}
				if (Character.isDigit(m2.group(3).charAt(0))) { 
					inst[i].num =  Integer.parseInt(m2.group(3));
				} else {
					inst[i].reg2 = m2.group(3);
				}
				switch (m2.group(2).charAt(0)) {
					case 'A':
						if (inst[i].num != -1) {
							inst[i].type = 2;
							assert !inst[i].reg2.equals("");
						} else {
							inst[i].type = 8;
							assert inst[i].num == -1;
							assert !inst[i].reg1.equals("");
							assert !inst[i].reg2.equals("");
						}
						inst[i].symbol = "AND";
						break;
					case 'O':
						inst[i].type = 3;
						assert inst[i].num == -1;
						assert !inst[i].reg1.equals("");
						assert !inst[i].reg2.equals("");
						inst[i].symbol = "OR";
						break;
					case 'L':
						if (inst[i].num == -1) {
							inst[i].type = 4;
							assert !inst[i].reg1.equals("");
							assert !inst[i].reg2.equals("");
						} else {
							inst[i].type = 10;
						}
						inst[i].symbol = "LSHIFT";
						break;
					case 'R':
						if (inst[i].num == -1) {
							inst[i].type = 5;
							assert !inst[i].reg1.equals("");
							assert !inst[i].reg2.equals("");
						} else {
							inst[i].type = 9;
						}
						inst[i].symbol = "RSHIFT";
						break;
					default:
						out.println("ERROR");
						Runtime.getRuntime().halt(0);
				}
				inst[i].reg3 = m2.group(4);
			} else if (m3.find()) {
				if (Character.isDigit(m3.group(1).charAt(0))) { 
					inst[i].num = Integer.valueOf(m3.group(1));
					out.println("never here2");
					Runtime.getRuntime().halt(0);
				} else {
					inst[i].reg1 = m3.group(1);
				}
				inst[i].type = 6;
				inst[i].reg3 = m3.group(2);
				inst[i].symbol = "NOT";

			} else if (m1.find()) {
				if (Character.isDigit(m1.group(1).charAt(0))) { 
					inst[i].num = Integer.valueOf(m1.group(1));
				} else {
					inst[i].reg1 = m1.group(1);
				}
				inst[i].reg3 = m1.group(2);
				if (inst[i].reg1.equals("")) {
					inst[i].type = 1;
				} else {
					inst[i].type = 7;
				}
				inst[i].symbol = "ASS";
			} else {
				out.println("NOT FOUND ERROR");
				Runtime.getRuntime().halt(0);
			}

		}
		int restart = 1;
		int already = 0;
		while (restart == 1) {
			for (int ii = 0; ii < numInsts; ii++) {
				instruction in = inst[ii];
				String r1 = in.reg1;
				String r2 = in.reg2;
				String r3 = in.reg3;
				int num = in.num;
				int type = in.type;
				int ans = -23;
				int v1, v2;
			//	String r1 = "";
			//	String r2 = "";
			//	String r3 = "";


				switch (type) {
					case 1:
						if (mp.containsKey(r3)) {break;}
						ans = num;
						assert num != -1;
						assert inst[ii].symbol.equals("ASS");
						break;
					case 2:
						if (mp.containsKey(r3)) {break;}
						if (r1.equals("")) {
							if (!mp.containsKey(r2)) {break;}
						} else if (r2.equals("")) {
							if (!mp.containsKey(r1)) {break;}
						}
						if (!r1.equals("")) {
							v1 = mp.get(r1);
							ans = (num & v1) & 0xffff;
						} else {
							v2 = mp.get(r2);
							ans = (num & v2) & 0xffff;
							assert num != -1;
						}
						assert inst[ii].symbol.equals("AND");
						break;
					case 3:
						if (mp.containsKey(r3)) {break;}
						if (!mp.containsKey(r1) || !mp.containsKey(r2)) {
						} else {
							v1 = mp.get(r1); v2 = mp.get(r2);
							ans = (v1 | v2) & 0xffff;
						}
						assert inst[ii].symbol.equals("OR");
						break;
					case 4:
						if (mp.containsKey(r3)) {break;}
						if (!mp.containsKey(r1)) {
						} else {
							v1 = mp.get(r1);
							ans = (v1 << num) & 0xffff;
							assert num != -1;
						}
						assert inst[ii].symbol.equals("LSHIFT");
						break;

					case 5:
						if (mp.containsKey(r3)) {break;}
						if (!mp.containsKey(r1)) {
						} else {
							v1 = mp.get(r1);
							ans = (v1 >> num) & 0xffff;
							assert num != -1;
						}
						assert inst[ii].symbol.equals("RSHIFT");
						break;
					case 6:
						if (mp.containsKey(r3)) {break;}
						if (!mp.containsKey(r1)) {
						} else {
							v1 = mp.get(r1);
							int f2 = ~v1;
							String xx1 = Integer.toUnsignedString(f2);
							BigInteger xx2 = new BigInteger(xx1);
							BigInteger xx3 = xx2.and(new BigInteger("65535"));
							int xx4 = xx3.intValue();
							ans = xx4;
							
							//out.print("NOT: "); out.print(r1); out.print(" ");out.print(v1); out.print(" ans: "); out.println(ans);

						}
						assert inst[ii].symbol.equals("NOT");
						break;
					case 7:
						if (mp.containsKey(r3)) {break;}
						if (r1.equals("")) {out.println("err"); Runtime.getRuntime().halt(0);}
						if (!mp.containsKey(r1)) {
						} else {
							v1 = mp.get(r1);
							out.print("case7: "); out.println(v1);
							//Scanner scanner4 = new Scanner(System.in); scanner4.nextLine();
							ans = v1;
						}
						assert inst[ii].symbol.equals("ASS");
						break;
					case 8:
						if (mp.containsKey(r3)) {break;}
						if (!mp.containsKey(r1) || !mp.containsKey(r2)) {
						} else {
							v1 = mp.get(r1); v2 = mp.get(r2);
							ans = (v1 & v2) & 0xffff;
						}
						assert inst[ii].symbol.equals("AND");
						break;
					case 9:
						if (mp.containsKey(r3)) {break;}
						int ok = 0;
						if (r1.equals("")) {
							if (mp.containsKey(r2)) {
								ok = 1;	
							}
						} else if (r2.equals("")) {
							if (mp.containsKey(r1)) {
								ok = 1;
							}
						}

						if (ok == 1) {
							if (r1.equals("")) {
								v2 = mp.get(r2);
								ans = (num >> v2) & 0xffff;
								assert num != -1;
								out.println("h here");
								Runtime.getRuntime().halt(0);
							} else {
								v1 = mp.get(r1);
								ans = (v1 >> num) & 0xffff;
								assert num != -1;
							}
						}
						assert inst[ii].symbol.equals("RSHIFT");
						break;
					case 10:
						if (mp.containsKey(r3)) {break;}
						int ok1 = 0;
						if (r1.equals("")) {
							if (mp.containsKey(r2)) {
								ok1 = 1;	
							}
						} else if (r2.equals("")) {
							if (mp.containsKey(r1)) {
								ok1 = 1;
							}
						}

						if (ok1 == 1) {
							if (r1.equals("")) {
								v2 = mp.get(r2);
								ans = (num << v2) & 0xffff;
								assert num != -1;
								out.println("h here2");
								Runtime.getRuntime().halt(0);
							} else {
								v1 = mp.get(r1);
								ans = (v1 << num) & 0xffff;
								assert num != -1;
							}
						}
						assert inst[ii].symbol.equals("LSHIFT");
						break;
					default:
						out.println("ERR ERR");
						out.println(type);
						Runtime.getRuntime().halt(0);

				}

				//out.print("r3: "); out.println(r3);

				if (!mp.containsKey(r3) && ans != -23) {
					mp.put(r3, ans);
				} else {
					//out.println("should not be here...");
				}
			}
			mp.entrySet().forEach(entry -> {
				out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()); });

			out.print("mp.size(): "); out.println(mp.size());
			//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();


			restart = 0;
			if (!mp.containsKey("a") && already == 0) {
				//out.println("here1");
				restart = 1;
			} else if (mp.containsKey("a") && already == 0) {
				out.println("here2 aaaaa:");
				int aa = mp.get("a");
				out.println(aa);
				mp.clear();
				restart = 1;
				already = 1;
				mp.put("b", aa);

				///Scanner scanner = new Scanner(System.in); scanner.nextLine();
			} else if (!mp.containsKey("a") && already == 1) {
				//out.println("here3");
				restart = 1;
			} else if (mp.containsKey("a") && already == 1) {
				out.println("here4");
			}
		}
		///mp.entrySet().forEach(entry -> {
		//out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue()); });
		//mp.keySet().forEach(key -> {out.println("Key: " + key);});
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(mp.get("a"));
		out.println("");
	}
}
class instruction {
	public int type = 0;
	public String reg1 = "";
	public String reg2 = "";
	public String reg3 = "";
	public int num = -1;
	public String symbol = "";
};


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

				//out.print("NOT, int value: "); out.println(fir);
				/*
				   int f2 = ~fir;// & 0xff;
				   String xx1 = Integer.toUnsignedString(f2);
				   BigInteger xx2 = new BigInteger(xx1);
				   BigInteger xx3 = xx2.and(new BigInteger("65535"));
				   int xx4 = xx3.intValue();
				///out.println(xx4);
				final int f1 = xx4;
				mp.compute(m3.group(2), (key, val) -> val == null ? f1: f1);
				*/
