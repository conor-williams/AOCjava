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
class year2015_day23_2 {
//	        public static int maxPath = 0;
    //    public static int lenx = 0;
     //   public static int leny = 0;
      //  public static char grid [][] = new char[leny][lenx];
    //    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2015 Day23.2");
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
		Pattern p = Pattern.compile("(hlf|tpl|inc|jmp) ([+-]?\\d+|[a-z])");
		Pattern p2 = Pattern.compile("(jie|jio) ([+-]?\\d+|[a-z]), ([+-]?\\d+|[a-z])");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Map <Character, Long> mp = new HashMap<>();
		mp.put('a', 1L);
		mp.put('b', 0L);
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			if (m.find()) {
				int num1 = 0;
				char ch = 'L';
				if (m.group(2).charAt(0) == '+' || m.group(2).charAt(0) == '-' || Character.isDigit(m.group(2).charAt(0))) {

					num1 = Integer.valueOf(m.group(2));
				} else {
					ch = m.group(2).charAt(0);
				}
				if (ch != 'L') {
					switch(blah.get(i).charAt(2)) {
						case 'f':
							mp.put(ch, mp.get(ch)/2);
							break;
						case 'l':
							mp.put(ch, mp.get(ch)*3);
							break;
						case 'c':
							mp.put(ch, mp.get(ch)+1);
							break;
					}
				} else {
					switch(blah.get(i).charAt(2)) {
						case 'p':
							i = i + num1 -1;
							break;
					}
				}
				continue;
			}
			Matcher m2 = p2.matcher(blah.get(i));
			if (m2.find()) {
				int num1 = 0;
				int num2 = 0;
				char ch1 = 'L';
				char ch2 = 'M';
				if (m2.group(2).charAt(0) == '+' || m2.group(2).charAt(0) == '-' || Character.isDigit(m2.group(2).charAt(0))) {

					num1 = Integer.valueOf(m2.group(2));
				} else {
					ch1 = m2.group(2).charAt(0);;
				}

				if (m2.group(3).charAt(0) == '+' || m2.group(3).charAt(0) == '-' || Character.isDigit(m2.group(3).charAt(0))) {

					num2 = Integer.valueOf(m2.group(3));
				} else {
					ch2 = m2.group(3).charAt(0);
				}

				if (blah.get(i).charAt(2) == 'e') {
					if (mp.get(ch1) % 2 == 0) {
						i = i + num2 -1;
					}
				} else if (blah.get(i).charAt(2) == 'o') {
					if (mp.get(ch1) == 1) {
						i = i + num2 -1;
					}
				}
				continue;
			}

		}
//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(mp.get('b'));
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

