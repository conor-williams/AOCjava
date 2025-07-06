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


//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2021_day14_2 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static void main(String [] args) {
		out.println("		2021 Day14.2");
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
		Pattern p = Pattern.compile("([A-Za-z]+) -> ([A-Za-z]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		Map <String, String> mp = new HashMap<>();
		Map <String, Long> mp2 = new HashMap<>();
		Map <String, Tuple<String, String>> mpTo = new HashMap<>();
		Vector <String> veelems = new Vector<>();
		String polymer = new String("");
		for (int i = 0; i < blah.size(); i++) {
			if (i == 0) {
				polymer = blah.get(0);
				continue;
			} else if (blah.get(i).length() == 0) {
				continue;
			} else {
				Matcher m = p.matcher(blah.get(i));
				m.find();

				mp.put(m.group(1), m.group(1).charAt(0) + m.group(2) + m.group(1).charAt(1));
				mpTo.put(m.group(1), new Tuple(m.group(1).charAt(0) + m.group(2), m.group(2) + m.group(1).charAt(1)));
				mp2.put(m.group(1), 0L);
				veelems.add(m.group(2));
			}
		}
                for (var entry : mp.entrySet()) {
                       System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
		out.println(polymer);
		for (int ii = 0; ii < polymer.length()-1; ii++) {
			String wat = polymer.charAt(ii) + "" + polymer.charAt(ii+1);
			mp2.put(wat, mp2.get(wat)+1);
		}

		for (var entry: mp2.entrySet()) {
                           System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}

		//Scanner scanner1 = new Scanner(System.in); scanner1.nextLine();
		Map <String, Long> mpTmp = new HashMap<>();
		for (int zzz = 1; zzz <= 40; zzz++) {
			mpTmp = new HashMap<>();
			for (var entry: mp2.entrySet()) {
				String wat = entry.getKey();
				long val = entry.getValue();
				if (val != 0) {
					var tu1 = mpTo.get(wat);
					String one = tu1.first;
					String two = tu1.second;
					mpTmp.put(one, val+mpTmp.getOrDefault(one, 0L));
					mpTmp.put(two, val+mpTmp.getOrDefault(two, 0L));
					//mpTmp.put(wat, 0);
					out.print(wat); out.print(" -> "); out.print(one); out.print(" "); out.println(two);
				} 
			}

			mp2 = new HashMap(mpTmp);

                	for (var entry : mp2.entrySet()) {
                       		System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			}
			//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();
		}
		Map <Character, Long> mpEl = new HashMap<>();
                for (var entry : mp2.entrySet()) {
			mpEl.put(entry.getKey().charAt(0), entry.getValue()+mpEl.getOrDefault(entry.getKey().charAt(0), 0L));
			mpEl.put(entry.getKey().charAt(1), entry.getValue()+mpEl.getOrDefault(entry.getKey().charAt(1), 0L));
			/*
                           System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			   if (entry.getValue() != 0 && entry.getValue() < min) {min = entry.getValue();}
			   if (entry.getValue() != 0 && entry.getValue() >max) {max = entry.getValue();}
			   */
		}
		long min = 99999999999999999L;
		long max = 0;
                for (var entry : mpEl.entrySet()) {
		        mpEl.put(entry.getKey(), mpEl.get(entry.getKey())+1);
			mpEl.put(entry.getKey(), mpEl.get(entry.getKey())/2);
                        System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			//out.println(entry.getValue()/2);
		}
                for (var entry : mpEl.entrySet()) {
			   if (entry.getValue() != 0 && entry.getValue() < min) {min = entry.getValue();}
			   if (entry.getValue() != 0 && entry.getValue() > max) {max = entry.getValue();}
		}
		out.println(max);
		out.println(min);
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(max-min);
		out.println("");
	}
}

class Tuple<X,Y > {
	public X first;
	public Y second;

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

@SuppressWarnings("unchecked")
class TreTuple<X,Y, Z> {
        public X first;
        public Y second;
        public Z third;

        public TreTuple(Object o) {
                TreTuple tu2 = (TreTuple) o;
                this.first = (X)tu2.first;
                this.second = (Y)tu2.second;
                this.third = (Z)tu2.third;
        }
        public TreTuple(X first, Y second, Z third) {
                this.first = first;
                this.second = second;
                this.third = third;
        }
        @Override
        public boolean equals(Object o) {
                TreTuple tu2 = (TreTuple) o;
                if (this == o) return true;
                if (!(o instanceof TreTuple)) return false;
                if (!this.first.equals(tu2.first)) {return false;}
                if (!this.second.equals(tu2.second)) {return false;}
                if (!this.third.equals(tu2.third)) {return false;}
                return true;
        }
        @Override
        public int hashCode() {
                return Objects.hash(first, second, third);
        }

}

@SuppressWarnings("unchecked")
class QuadTuple<X,Y, Z, W> {
        public X first;
        public Y second;
        public Z third;
        public W fourth;

        public QuadTuple(X first, Y second, Z third, W fourth) {
                this.first = first;
                this.second = second;
                this.third = third;
                this.fourth = fourth;
        }
        @Override
        public boolean equals(Object o) {
                QuadTuple tu2 = (QuadTuple) o;
                if (this == o) return true;
                if (!(o instanceof QuadTuple)) return false;

                if (!first.equals(tu2.first)) {return false;}
                if (!second.equals(tu2.second)) {return false;}
                if (!third.equals(tu2.third)) {return false;}
                if (!fourth.equals(tu2.fourth)) {return false;}
                return true;
        }
        @Override
        public int hashCode() {
                return Objects.hash(first, second, third, fourth);
        }

}

@SuppressWarnings("unchecked")
class CinqTuple<X,Y, Z, V, W> {
        public X first;
        public Y second;
        public Z third;
        public V fourth;
        public W fifth;

        public CinqTuple(X first, Y second, Z third, V fourth, W fifth) {
                this.first = first;
                this.second = second;
                this.third = third;
                this.fourth = fourth;
                this.fifth = fifth;
        }
        @Override
        public boolean equals(Object o) {
                CinqTuple tu2 = (CinqTuple) o;
                if (this == o) return true;
                if (!(o instanceof CinqTuple)) return false;

                if (!first.equals(tu2.first)) {return false;}
                if (!second.equals(tu2.second)) {return false;}
                if (!third.equals(tu2.third)) {return false;}
                if (!fourth.equals(tu2.fourth)) {return false;}
                if (!fifth.equals(tu2.fifth)) {return false;}
                return true;
        }
        @Override
        public int hashCode() {
                return Objects.hash(first, second, third, fourth, fifth);
        }

}

