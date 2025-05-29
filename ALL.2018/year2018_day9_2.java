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
import java.util.ArrayDeque;
import java.util.Deque;
// /java -Xmx2g year2019_day3.java *i1.txt


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2018_day9_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day9.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
//		PrintStream originalOut = System.out;
//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(\\d+) players; last marble is worth (\\d+)");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");

		long maxScore = 0;
		for (int i = 0; i < blah.size(); i++) {
			Deque <Integer> dek = new ArrayDeque<>();
			
			dek.addLast(1);
			dek.addLast(3);
			dek.addLast(0);
			dek.addLast(4);
			dek.addLast(2);
			dek.addLast(5);

			int nextNum = 6;

			Matcher m = p.matcher(blah.get(i));
			m.find();
			Integer players = Integer.valueOf(m.group(1));
			//out.println(m.group(2));
			Integer lastMarble = Integer.valueOf(m.group(2)) * 100;
			//out.println(lastMarble);
			long [] score = new long[players+1];
			int player = 6;
			maxScore = 0;
			do {
				if (nextNum % 23 == 0) {
					score[player] += nextNum;
					int val = 0;
					for (int ii = 0; ii < 8; ii++) {
						val = dek.removeLast();
						dek.addFirst(val);
						//if (val == 9) {out.println(ii);}
					}
					int xx = dek.removeFirst();
					//out.print("xx: "); out.println(xx);
					score[player] += xx;
					int val2 = dek.removeFirst();
					dek.addLast(val2);
				} else {
					int fir = dek.removeFirst();
					dek.addLast(fir);
					dek.addLast(nextNum);
				}
				if (nextNum == lastMarble) {break;}
				nextNum++;
				/*
				for (Iterator itr = dek.iterator(); itr.hasNext();) {
			        	System.out.print(itr.next() + " ");
        			}
				Scanner scanner = new Scanner(System.in); scanner.nextLine();
				*/
				player++;
				player = ((player-1) % players) + 1;
			} while (1 == 1);

			
			for (int ii = 0; ii < players; ii++) {
				if (score[ii+1] > maxScore) {maxScore = score[ii+1];}
			}
			//out.println(maxScore);
		}
//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxScore);
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

