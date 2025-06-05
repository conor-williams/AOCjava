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
class year2024_day11 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2024 Day11.1");
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
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Deque <String> dek = new ArrayDeque<>();
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[\t\\s ]+");
			while (scanner.hasNext()) {
				String ne = scanner.next();
				dek.addLast(ne);
			}
		}


		for (int ii = 0; ii < 25; ii++) {
			Deque <String> dekTmp = new ArrayDeque<>();
			for (int jj = 0; jj < dek.size(); jj++) {
				String ne = dek.removeFirst();
				//out.print("ne: "); out.println(ne);
				jj--;

				if (ne.equals("0")) {
					dekTmp.addLast("1");
				} else if (ne.length() % 2 == 0) {
					int sz = ne.length() /  2;
					String s1 = ne.substring(0, sz).replaceFirst("^0+", "");
					if (s1.length() == 0) {
						s1 = "0";
					}
					dekTmp.addLast(s1);
					String s2 = ne.substring(sz).replaceFirst("^0+", "");
					if (s2.length() == 0) {
						s2 = "0";
					}
					dekTmp.addLast(s2);
				} else {
					Long lo = Long.parseLong(ne);
					lo *= 2024;
					dekTmp.addLast(lo.toString());
				}
			}
			//out.println(dekTmp);
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			dek = new ArrayDeque<>(dekTmp);

		}
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(dek.size());
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

