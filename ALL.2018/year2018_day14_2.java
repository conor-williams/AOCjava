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
// int max = var_chars.stream().max(Integer::compare).orElseThrow();
// int position = var_chars.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2018_day14_2 {
//	        public static int maxPath = 0;
 //       public static int sx = 0;
  //      public static int sy = 0;
   //     public static char grid [][] = new char[sy][sx];
    //    public static int already [][] = new int[sy][sx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2018 Day14.2");
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
		int after = 0;
		for (int i = 0; i < blah.size(); i++) {
			after = Integer.valueOf(blah.get(i));
		}
		Vector <Character> var_ans = new Vector<>();
		//String var_ans = new String("");
		int after2 = after;
		for (int ii = 0; ; ii++) {
			//var_ans.add(var_chars.get(after+ii));

			if (after > Math.pow(10, ii)) {
				var_ans.add((char)((int)((after/Math.pow(10, ii)) % 10)+48));
			} else {
				break;
			}
		}

		Collections.reverse(var_ans);
		//out.println(var_ans);
		int numnums = var_ans.size();


		//out.println(var_ans);
		//out.println(var_ans); Scanner scanner = new Scanner(System.in); scanner.nextLine();

		Vector <Character> var_chars = new Vector<>();
		var_chars.add((char)(3+48));
		var_chars.add((char)(7+48));

		int pos1 = 0;
		int pos2 = 1;

		//while (var_chars.size() <= after+10) 
		int ansans = 0;
		//out.println(numnums);
		//while (true) 
		for (int zzz = 0; zzz < 40000000 ; zzz++) {
			//if (zzz % 5000 == 0) {out.println(zzz);}
			int val = var_chars.get(pos1)-48 + var_chars.get(pos2)-48;
			//out.print("val: "); out.println(val);
			if (val < 10) {
				var_chars.add((char)(val+48));
			} else {
				int singles = val % 10;
				int tens = (val/10)%10;

				var_chars.add((char)(tens+48));
				var_chars.add((char)(singles+48));
			}
			int sz = var_chars.size();
			int timesPos1 = (var_chars.get(pos1) + 1 -48) % sz;
			int timesPos2 = (var_chars.get(pos2) + 1 -48) % sz; 

			pos1 = (pos1 += timesPos1) % sz;
			pos2 = (pos2 += timesPos2) % sz;


			if (sz > numnums+1) {
				int found = 0;
				for (int kk = 0; kk < numnums; kk++) {
					//out.print("var_chars.size()"); out.println(var_chars.size());
					//out.print("kk"); out.println(kk);
					//out.print("numnums"); out.println(numnums);
					//out.println(var_chars.size()-(kk-numnums));
					///out.print(var_chars.get(var_chars.size()-numnums+kk));

					///out.print(" V ");
					///out.println(var_ans.get(kk));

					if (!var_chars.get(sz-numnums-1+kk).equals(var_ans.get(kk))) {
						found = 1;
						break;
					}
				}
				if (found == 0) {
					//out.println("foundit");
					ansans = sz-numnums-1;
					break;
				}
			}
			//out.println(var_chars);

		}

		/*
		int ansans = 0;
		for (int kk = 0; kk < var_chars.size(); kk++) {
			int found = 0;
			for (int ii = 0; ii < numnums; ii++) {
			if (var_chars.get(kk) == var_ans.get(0) &&
					var_chars.get(kk+1) == var_ans.get(1) &&
					var_chars.get(kk+2) == var_ans.get(2) &&
					var_chars.get(kk+3) == var_ans.get(3) &&
					var_chars.get(kk+4) == var_ans.get(4)) {
				ansans = kk;
			}
		}
		*/

//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.println(ansans);
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
        public final X first;
        public final Y second;
        public final Z third;
        public final W fourth;

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
        public final X first;
        public final Y second;
        public final Z third;
        public final V fourth;
        public final W fifth;

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

