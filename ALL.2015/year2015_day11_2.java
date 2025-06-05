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
// /java -Xss1g -Xmx2g year2019_day3.java *i1.txt


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2015_day11_2 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day11.2");
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

//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		String s = blah.get(0);
		String ans = new String("");
		int first = 0;
		while ( 1 == 1 ) {
			s = Long.toString(Long.valueOf(1+s,36)+1,36).substring(1);
			int found = 0;
			for (int ii = 0; ii < s.length()-2; ii++) {
				char ch = s.charAt(ii);
				if (s.charAt(ii+1) == ch+1 && s.charAt(ii+2) == ch+2) {
					found = 1;
				       	break;
				} 
			} 
			if (found == 0) {
				///notgood
				continue;
			}
			found = 0;
			for (int ii = 0; ii < s.length(); ii++) {
				if (s.charAt(ii) == 'i' || s.charAt(ii) == 'o' ||
						s.charAt(ii) == 'l') {
					found = 1;
					break;
				}
			}
			if (found == 1) {
				//notgood
				continue;
			}
			found = 0;
			for (char ch = 'a'; ch <= 'z'; ch++) {
				for (int ii = 0; ii < s.length()-1; ii++) {
					if (s.charAt(ii) == ch &&
							s.charAt(ii+1) == ch) {
						found++;
						break;
					}
				}
			}
			if (found >= 2) {
				if (first == 0) {
					first = 1;
					continue;
				} else {
	 				ans = new String(s);
					break;
				}
				//good
			} else {
				continue;
			}
		}

//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
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

