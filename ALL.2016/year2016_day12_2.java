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


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2016_day12_2 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2016 Day12.2");
		out.println("	SLOW ~17secs");
		out.flush();
		Vector<String> blah = new Vector<>();
		//int leny = 0;
                //int lenx = 0;
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
                /*sx = lenx;
                sy = leny;
                grid = new char[sy][sx];
                already = new int[sy][sx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/

//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(cpy|jnz) ([a-z]|-?\\d+) ([a-z]|-?\\d+)");
		Pattern p2 = Pattern.compile("(inc|dec) ([a-z])");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		//int a = 0, b = 0, c = 0, d = 0;
		Map <Character, Integer> mp = new HashMap<>();
		mp.put('a', 0);
		mp.put('b', 0);
		mp.put('c', 1);
		mp.put('d', 0);

		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			Matcher m = p.matcher(ne);
			Matcher m2 = p2.matcher(ne);
			if (m.find()) {
				if (m.group(1).charAt(0) == 'c') {
					
					int cpyBy = 0;
					if (Character.isDigit(m.group(2).charAt(0))) {
						cpyBy = Integer.valueOf(m.group(2));
					} else {
						char whichReg = m.group(2).charAt(0);
						cpyBy = mp.get(whichReg);
					}

					char toReg = m.group(3).charAt(0);
					mp.put(toReg, cpyBy);
					continue;
				} else if (m.group(1).charAt(0) == 'j') {
//					jnz c 2
//					jnz 1 5
//
                                        int jnz = 0;
                                        if (Character.isDigit(m.group(2).charAt(0))) {
                                                jnz = Integer.valueOf(m.group(2));
                                        } else {
                                                char whichReg = m.group(2).charAt(0);
                                                jnz = mp.get(whichReg);
                                        }

					if (jnz != 0) {
						int jmp = Integer.valueOf(m.group(3));
						i += jmp;
						i--;
						continue;
					}
				} else {
					out.println(ne);
					out.println("ERRcj");
					Runtime.getRuntime().halt(0);
				}
			} else if (m2.find()) {
				if (m2.group(1).charAt(0) == 'i') {
					char ch = m2.group(2).charAt(0);
					mp.put(ch, mp.get(ch)+1);
				} else if (m2.group(1).charAt(0) == 'd') {
					char ch = m2.group(2).charAt(0);
					mp.put(ch, mp.get(ch)-1);
				} else {
					out.println(ne);
					out.println("ERRid");
					Runtime.getRuntime().halt(0);
				}
			} else {
				out.println(ne);
				out.println("ERR");
				Runtime.getRuntime().halt(0);
			}
		}
//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(mp.get('a'));
		out.println("");
	}
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
		if (!this.first.equals(tu2.first)) {return false;}
                if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

