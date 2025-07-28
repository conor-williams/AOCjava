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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

// /java -Xmx2g year2019_day3.java *i1.txt



//                        grid = Arrays.stream(gridTmp).map(char[]::clone).toArray(char[][]::new);
// pe.sort(Comparator.comparingInt((TreTuple t) -> (int)t.third).thenComparingInt((TreTuple t) -> (int)t.second).thenComparingInt((TreTuple t) -> (int)t.first));///tuples.sort(Comparator.comparingInt(tuple -> tuple[0]));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (var entry : mp.entrySet()) {
                                // System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}
//// Arrays.stream(array).forEach(row -> Arrays.fill(row, 0));
@SuppressWarnings("unchecked")
class year2021_day21 {
//	        public static int maxPath = 0;
    //    public static int lenx = 0;
     //   public static int leny = 0;
      //  public static char grid [][] = new char[leny][lenx];
    //    public static int already [][] = new int[leny][lenx];

	static int startP1 = 0;
	static int startP2 = 0;
	static Deque<Integer> qp1 = new ArrayDeque<>();
	static Deque<Integer> qp2 = new ArrayDeque<>();
	public static void main(String [] args) {
		out.println("		2021 Day21.1");
		out.flush();
		Vector<String> blah = new Vector<>();
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
                /*
                grid = new char[leny][lenx];
                already = new int[leny][lenx];
                for (int i = 0; i < blah.size();i++) {
                        grid[i] = blah.get(i).toCharArray();
                }
		*/


		//	String firstpart = Pattern.quote("mul(");
		Pattern p1 = Pattern.compile("Player (\\d+) starting position: (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m1 = p1.matcher(blah.get(i));
			m1.find();

			if (m1.group(1).equals("1")) {
				startP1 = Integer.valueOf(m1.group(2));
			} else {
				startP2 = Integer.valueOf(m1.group(2));
			}
		}
		for (int i = startP1; i < startP1+10; i++) {
			int j = i % 10; if (j == 0) {j = 10;}
			qp1.addLast(j);
		}
		for (int i = startP2; i < startP2+10; i++)  {
			int j = i % 10; if (j == 0) {j = 10;}
			qp2.addLast(j);
		}
		int roll1 = 1, roll2 = 2, roll3 = 3;
		int round = 0;
		int amt = 0;
		int scorep1 = 0, scorep2 = 0;
		while (true) {
			amt = (roll1+roll2+roll3) % 10;
			if (round %2 == 0) {
				int val1 = 0;
				if (amt == 0) {
				} else {
					for (int k = 0; k < amt; k++) {
						int v1 = qp1.getFirst();
						qp1.removeFirst();
						qp1.addLast(v1);
					}
				}
				val1 = qp1.getFirst();
				scorep1 += val1;
			} else {
				int val2 = 0;
				if (amt == 0) {
				} else {
					for (int k = 0; k < amt; k++) {
						int v2 = qp2.getFirst();
						qp2.removeFirst();
						qp2.addLast(v2);
					}
				}
				val2 = qp2.getFirst();
				scorep2 += val2;
			}
			roll1+=3; roll2+=3; roll3+=3;
			round+=3;
			if (scorep1 >= 1000 || scorep2 >= 1000) {
				break;
			}
		}
		long ans = 0;
		if (scorep1 >= 1000) {
			ans = scorep2 * round;
		} else {
			ans = scorep1 * round;
		}
//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
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

