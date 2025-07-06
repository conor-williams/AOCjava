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
class year2021_day14 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day14.1");
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
                /*sx = lenx;
                sy = leny;
                grid = new char[sy][sx];
                already = new int[sy][sx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("([A-Za-z]+) -> ([A-Za-z]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
//PBVHVOCOCFFNBCNCCBHK
//FV -> C
		Map <String, String> mp = new HashMap<>();
		Map <String, String> mp2 = new HashMap<>();
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
				mp2.put(m.group(1), m.group(2));
				veelems.add(m.group(2));
			}
		}
                for (var entry : mp.entrySet()) {
                       System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
		out.println(polymer);
		//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();
		for (int zzz = 1; zzz <= 10; zzz++) {
			String trans = new String("");
			Vector <Tuple<Integer, String>> indx = new Vector<>();
                	for (var entry : mp.entrySet()) {
                               //System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
			       //if (polymer.indexOf(entry.getKey()) != -1 && polymer.indexOf(entry.getKey()) < minIndex) {minIndex = polymer.indexOf(entry.getKey()); trans = entry.getKey();}
				int ind = -1;
				//while (polymer.indexOf(entry.getKey(), ind) != -1) {
				while (true) {
					//out.print("here1"); out.println(entry.getKey());
					//out.print("ind is ");out.println(ind);
				        if ((ind = polymer.indexOf(entry.getKey(), ind+1)) != -1) {
						indx.add(new Tuple(ind, entry.getKey()));
					} else {
						break;
					}
				}
			}
			indx.sort(Comparator.comparingInt((Tuple t) -> (int)t.first).reversed());
			for (int ii = 0; ii < indx.size(); ii++) {
				out.print(indx.get(ii).first); out.print(" ");
			}
			out.println();
			//Collections.sort(indx);
			
			for (int ii = 0; ii < indx.size(); ii++) {
				//out.println(indx.get(ii));
				var tu1 = indx.get(ii);
				out.print(tu1.second); out.print(" -> "); out.println(mp.get(tu1.second));
				//polymer = polymer.replaceFirst(tu1.second, mp.get(tu1.second));
				polymer = polymer.substring(0, tu1.first) + mp.get(tu1.second) + polymer.substring(tu1.first+2);
				//polymer.substring(tu1.first, 2)
				out.println(polymer);
				//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			}
			//out.println(trans);
			///out.println(polymer);
			out.print("step: "); out.println(zzz);
			out.print("len: "); out.println(polymer.length());
			out.println(polymer);
			//Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();
		}

		int count[] = new int[veelems.size()];
		for (int ii = 0; ii < polymer.length(); ii++) {
			for (int kk = 0; kk < veelems.size(); kk++) {
				if (polymer.charAt(ii) == veelems.get(kk).charAt(0)) {
					count[kk]++;
					break;
				}
			}
		}

		Arrays.sort(count);
		int fir = 0;
		for (int ii = 0; ii < veelems.size(); ii++) {
			if (count[ii] == 0) {
				continue;
			} else {
				fir = ii;
				out.println(fir);
				break;
			}
		}
		out.println(count[0]);
		out.println(count[veelems.size()-1]);
		out.println(count);
		int num = Math.abs(count[fir] - count[veelems.size()-1]);
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(num);
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

