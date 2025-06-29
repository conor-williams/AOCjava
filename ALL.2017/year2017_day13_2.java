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
class year2017_day13_2 {
	//	        public static int maxPath = 0;
	//       public static int sx = 0;
	//      public static int sy = 0;
	//     public static char grid [][] = new char[sy][sx];
	//    public static int already [][] = new int[sy][sx];

	public static Deque <Character> deq[] = new ArrayDeque[10];
	public static Deque <Character> deqOrig[] = new ArrayDeque[10];
	public static int dir[] = new int[10];
	public static int dirOrig[] = new int[10];
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2017 Day13.2");
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
		//Deque <Character> deq[] = new ArrayDeque[max+1];
		deq = new ArrayDeque[max+1];
		//Deque <Character> deqOrig[] = new ArrayDeque[max+1];
		deqOrig = new ArrayDeque[max+1];
		for (int ii = 0; ii <= max; ii++) {
			deq[ii] = new ArrayDeque<Character>();
			deqOrig[ii] = new ArrayDeque<Character>();
		}
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int num = Integer.valueOf(m.group(1));
			int sz = Integer.valueOf(m.group(2));

			deq[num].addLast('S');
			deqOrig[num].addLast('S');
			for (int ii = 1; ii < sz; ii++) {
				deq[num].addLast('N');
				deqOrig[num].addLast('N');
			}
		}

		dir = new int[max+1];
		dirOrig = new int[max+1];
		int size = 0;
		int found = 0;
		do {
			size++;
			found = 0;
			for (int ii = 0; ii <= max; ii++) {
				if (deq[ii].size() != 0) {
					//out.print("ii: "); out.print(ii);
					//out.print("size: "); out.print(deq[ii].size());
					//out.print(" * 2 - 2: "); out.print((deq[ii].size() * 2) - 2);
					//out.print(" size+ii "); out.println(size+ii);
					if ((size+ii) % ((deq[ii].size()*2) -2) == 0) {
						found = 1;
						//out.println("found...");
						break;
					}
				}
			}
			///out.print("found: "); out.println(found);
		} while (found == 1);
		/*
		do {
			//if (size % 1000 == 0) {out.println(size);}
			//out.println(size); out.println("----------------------");
			moveOnOne(max);
			for (int ii = 0; ii <= max; ii++) {
				deq[ii] = new ArrayDeque<>(deqOrig[ii]);
			}
			for (int ii = 0; ii <= max; ii++) {
				dir[ii] = dirOrig[ii];
			}

			tt = check(max);
			size++;
		} while (tt != 0);
		*/
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(size);
		out.println("");
	}

	public static long check(int max) {
		long tot = 0;
		for (int pos = 0; pos <= max; pos++) {
	                //for  (int ii = 0; ii <= max; ii++) { out.println(deq[ii]); } out.println("--------");
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();

			if (deq[pos].size() == 0) {
			} else if (deq[pos].getFirst() == 'S') {
				//tot += pos * deq[pos].size();
				tot++;
				return tot;
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
		return tot;
	}
	public static void moveOnOne(int max) {
		for (int pos = 0; pos < 1; pos++) {
			for (int ii = 0; ii <= max; ii++) {
				if (deqOrig[ii].size() != 0) {
					if (dirOrig[ii] == 0) {
						Character xx = deqOrig[ii].removeLast();
						deqOrig[ii].addFirst(xx);
						if (deqOrig[ii].getLast() == 'S') {
							dirOrig[ii] = 1;
						}
					} else if (dirOrig[ii] == 1) {
						Character xx = deqOrig[ii].removeFirst();
						deqOrig[ii].addLast(xx);
						if (deqOrig[ii].getFirst() == 'S') {
							dirOrig[ii] = 0;
						}
					}
				}
			}
		}
	}
	public static void moveOn(int size, int max) {
		for (int pos = 0; pos <= size; pos++) {
	                //for  (int ii = 0; ii <= max; ii++) { out.println(deq[ii]); } out.println("--------");
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();

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
