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
class year2018_day12 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day12.1");
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

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([#\\.]{5}) => (#|\\.)");
		Pattern p2 = Pattern.compile("initial state: ([#|\\.]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		String initial = "";
		Map <String, Character> mp = new HashMap<>();
		for (int i = 0; i < blah.size(); i++) {
			if (i == 0) {
				Matcher m = p2.matcher(blah.get(0));
				m.find();	
				initial = m.group(1); 
				continue;
			}
			if (i == 1) {continue;}
			
			out.println(blah.get(i));
			Matcher m = p.matcher(blah.get(i));
			m.find();
			//out.println(m.group(1));
			//out.println(m.group(2));
			mp.put(m.group(1), m.group(2).charAt(0));
		}

		
		int theZero = 15;
		String dots = ".".repeat(theZero);
		initial = dots + initial;
		initial = initial + dots;

		for (int ii = 0; ii < 20; ii++) {
			StringBuilder tmp1 = new StringBuilder();
			tmp1.setLength(initial.length()+10);
			for (int kk = 0; kk < initial.length()-4; kk++) {
				String sub1 = initial.substring(kk, kk+5);
				char to = '.';
				if (mp.containsKey(sub1)) { 
					to = mp.get(sub1);
				}
				tmp1.setCharAt(kk+2, to);
			}
			out.print("round: "); out.println(ii);
			out.println(tmp1);
			initial = tmp1.toString();
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		}
		
		long tot = 0;
		for (int ii = 0; ii < initial.length(); ii++) {
			if (initial.charAt(ii) == '#') {
				tot += ii-theZero;
			}
		}


		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
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

