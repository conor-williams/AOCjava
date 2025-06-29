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


///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2017_day13 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day13.1");
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

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+): (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int max = 0;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			if (Integer.valueOf(m.group(1)) > max) {
				max = Integer.valueOf(m.group(1));
			}
		}
		Deque <Character> deq[] = new ArrayDeque[max+1];
		for (int ii = 0; ii <= max; ii++) {
			deq[ii] = new ArrayDeque<Character>();
		}
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int num = Integer.valueOf(m.group(1));
			int sz = Integer.valueOf(m.group(2));

			deq[num].addLast('S');
			for (int ii = 1; ii < sz; ii++) {
				deq[num].addLast('N');
			}
		}

		int dir[] = new int[max+1];
		long tot = 0;
		for (int pos = 0; pos <= max; pos++) {
			for  (int ii = 0; ii <= max; ii++) {
				out.println(deq[ii]);
			}
			out.println("--------");
			if (deq[pos].size() == 0) {
			} else if (deq[pos].getFirst() == 'S') {
				out.println("gotcha");
				out.println(pos);
				tot += pos * deq[pos].size();
			}

			for (int ii = 0; ii <= max; ii++) {
				if (deq[ii].size() != 0) {
					if (dir[ii] == 0) {
						Character xx = deq[ii].removeLast();
						deq[ii].addFirst(xx);
						if (deq[ii].getLast() == 'S') {
							dir[ii] = 1;
						}
					} else if (dir[ii] == 1) {
						Character xx = deq[ii].removeFirst();
						deq[ii].addLast(xx);
						if (deq[ii].getFirst() == 'S') {
							dir[ii] = 0;
						}
					}
				}
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
