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


//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//		System.setOut(originalOut);
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

class year2022_day7 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static Map <String, Vector<String>> mpAft = new HashMap<>();
	public static Map <String, fileordir> mp = new HashMap<>();
	public static BigInteger one =  new BigInteger("1");
	public static BigInteger zero =  new BigInteger("0");
	public static void main(String [] args) {
		out.println("		2022 Day7.1");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	


		Pattern p1 = Pattern.compile("([\\d]+) ([a-z.]+)");
		Pattern p2 = Pattern.compile("(dir) ([a-z.]+)");


		//String curDir = "/";/// String upOneDir = "/";
		fileordir root = new fileordir();
		root.size = zero;
		root.type = 'D';
		root.name = "/";
		root.norm = "root";
		mp.put("/", root);
		String [] leveldirs = new String[20];
		leveldirs[0] = "/";
		int level = 0;
		for (int i = 0; i < blah.size(); i++) {
			String in = blah.get(i);
			if (in.charAt(0) == '$') {
				if (in.charAt(2) == 'c') {
					if (in.charAt(5) == '/') {
						level = 0;
					} else if (in.charAt(5) == '.') {
						level--;
						//curDir = leveldirs[level];
					} else {
						level++;
						String dir1 = in.substring(5); out.println(dir1);
						leveldirs[level] = dir1;
					}
				} else if (in.charAt(2) == 'l') {

					i++;
					String inS = blah.get(i);

					while (inS.charAt(0) != '$' && i < blah.size()) {
						out.println(inS);
						Matcher m1 = p1.matcher(inS);
						Matcher m2 = p2.matcher(inS);
						String path = "";
						for (int qqq = 0; qqq <= level; qqq++) {
							path = path.concat(leveldirs[qqq]);
						}
						if (m1.find()) {
							out.println("file");
							out.println("name: "); out.println(m1.group(2));
							out.println("size: "); out.println(m1.group(1));
							out.println(Integer.valueOf(m1.group(1)));
							fileordir file1 = new fileordir();
							file1.name = path + m1.group(2);
							file1.norm = m1.group(2);
							file1.size = new BigInteger(m1.group(1));
							file1.type = 'F';
							mp.put(file1.name, file1);

							Vector <String> v1 = new Vector<>();
							if (mpAft.containsKey(path)) {
								v1 = mpAft.get(path);
							}
							v1.add(file1.name);
							mpAft.put(path, v1);

							/*
							if (level -1 >= 0) {
								if (mpAft.containsKey(leveldirs[level-1]+leveldirs[level])) {
									v1 = mpAft.get(leveldirs[level-1]+leveldirs[level]);
								}
								v1.add(file1.name);
								mpAft.put(leveldirs[level-1]+leveldirs[level], v1);
							} else {
								if (mpAft.containsKey("/"+leveldirs[level])) {
									v1 = mpAft.get("/"+leveldirs[level]);
								}
								v1.add(file1.name);
								mpAft.put("/"+leveldirs[level], v1);
							}
							*/

						} else if (m2.find()) {
							out.println("dir");
							if (!m2.group(1).equals("dir")) { 
								out.println("expecting dir");
								Runtime.getRuntime().halt(0);
							}
							out.println(m2.group(2));

							fileordir dir1 = new fileordir();
							dir1.name = path + m2.group(2);
							dir1.norm = m2.group(2);
							dir1.size = zero;
							dir1.type = 'D';
							mp.put(dir1.name, dir1);

							Vector <String> v1 = new Vector<>();
							if (mpAft.containsKey(path)) {
								v1 = mpAft.get(path);
							}
							v1.add(dir1.name);
							mpAft.put(path, v1);
							/*
							if (level -1 >= 0) {
								if (mpAft.containsKey(leveldirs[level-1]+leveldirs[level])) {
									v1 = mpAft.get(leveldirs[level-1]+leveldirs[level]);
								}
								v1.add(dir1.name);
								mpAft.put(leveldirs[level-1]+leveldirs[level], v1);
							} else {
								if (mpAft.containsKey("/"+leveldirs[level])) {
									v1 = mpAft.get("/"+leveldirs[level]);
								}
								v1.add(dir1.name);
								mpAft.put("/"+leveldirs[level], v1);
							}
							*/
						}
						i++;
						if (i >= blah.size()) {break;}
						inS = blah.get(i);
					}
					if (i < blah.size() - 1) {i--;}
				}
			}
			/*
			if (!upOneDir.equals(curDir)) {
				upOneDir = curDir;

			}
			*/
		}

		out.println("filesystem:");
		for (Map.Entry<String, Vector<String>> entry : mpAft.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
		out.println("indiv");
		for (Map.Entry<String, fileordir> entry : mp.entrySet()) {
			System.out.println("Key: " + entry.getKey());
			out.print(entry.getValue().size); out.print(" "); out.print(entry.getValue().type); out.print(" "); out.println(entry.getValue().name);
		}


		out.println("----------");
		BigInteger tottot = zero;
		for (Map.Entry<String, Vector<String>> entry: mpAft.entrySet()) {
			var ke = entry.getKey();
			BigInteger back = calcUnder(ke);
			out.println(back);
			if (back.compareTo(new BigInteger("100000")) <= 0) {
				out.print("adding: "); out.println(back);
				tottot = tottot.add(back);
			}
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		}



		out.print("**j_ans: ");
		out.print(tottot);
		out.println("");
	}
	public static BigInteger calcUnder(String df) {
		BigInteger tot = zero;
		//if (!mpAft.containsKey(df)) {return zero;}
		//for (Map.Entry<String, Vector<String>> entry: mpAft.entrySet()) 
		{
			out.print("df: "); out.println(df);
			var ve = mpAft.get(df);
			for (int ii = 0; ii < ve.size(); ii++) {
				out.println(df);
				out.println(ve.get(ii));
				if (mp.get(ve.get(ii)).type == 'F') {
					tot = tot.add(mp.get(ve.get(ii)).size);
				} else {
					out.println(ve.get(ii));
					tot = tot.add(calcUnder(ve.get(ii)));
				}
			}
		}
		return tot;
		
	}
}


class fileordir {
	public BigInteger size = new BigInteger("0");
	public char type = 'F';
	public String name;
	//public String dir;
	public String norm;
	static int number = 0;
}

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

