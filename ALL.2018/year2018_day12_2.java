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
class year2018_day12_2 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day12.2");
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

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Pattern p = Pattern.compile("([#\\.]{5}) => (#|\\.)");
		Pattern p2 = Pattern.compile("initial state: ([#|\\.]+)");
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

		
		int theZero = 400;
		String dots = ".".repeat(theZero);
		initial = dots + initial;
		initial = initial + dots;

		//String dots = ".".repeat(6);
		Vector <String> repeatChecker = new Vector<>();
		int repeatStart = 0;
		for (int ii = 0; ii < 200; ii++) {
			///tmp1.setLength(initial.length()+10);
			int ind1 = initial.indexOf('#');
			int ind2 = initial.lastIndexOf('#')+1;
			//out.println(initial.substring(ind1, ind2));
			
			if (repeatChecker.contains(initial.substring(ind1, ind2))) {
				out.print("repeat @ "); 
				out.println(repeatStart = repeatChecker.indexOf(initial.substring(ind1, ind2)));
				break;

			} else {
				out.println("no repeat");
			}
			repeatChecker.add(initial.substring(ind1, ind2));
			//initial = dots + initial.substring(ind1, ind2) + dots;


			StringBuilder tmp1 = new StringBuilder(".".repeat(initial.length()+10));

			for (int kk = 0; kk < initial.length()-4; kk++) {
				String sub1 = initial.substring(kk, kk+5);
				char to = '.';
				if (mp.containsKey(sub1)) { 
					to = mp.get(sub1);
				}
				tmp1.setCharAt(kk+2, to);
			}
			//out.print("round: "); out.println(ii); out.println(tmp1);
			initial = tmp1.toString();
			//out.println(initial);
			/*
			if (ii>180) {
				out.print("ii: "); out.println(ii);

			long tot2 = 0;
			out.println(counthash);
			out.print("tot2: "); out.println(tot2);
			}
			*/
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
		}
		
		int counthash = 0;
		for (int jj = 0; jj < initial.length(); jj++) {
			if (initial.charAt(jj) == '#') {
				counthash++;
			}
		}

		long num = 50000000000L - repeatStart-1;
		long tot = 0;
		for (int ii = 0; ii < initial.length(); ii++) {
			if (initial.charAt(ii) == '#') {
				tot += ii-theZero;
			}
		}
		out.println(tot+(num*counthash));

		

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot + (num*counthash));
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

