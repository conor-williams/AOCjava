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
import java.util.stream.IntStream;

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
///MyClass[] array = IntStream.range(0, 5) .mapToObj(i -> new MyClass()) .toArray(MyClass[]::new);
@SuppressWarnings("unchecked")
class year2017_day24_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	static int max = 0;
	//static int tot2 = 0;
	static port_s port[] = new port_s[60];
	static port_s already[] = new port_s[1000];
	static int alreadyPos = 0;
	static int maxind = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//  public static int already [][] = new int[leny][lenx];

	public static void main(String [] args) {
		out.println("		2017 Day24.2");
		out.flush();
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				//if (lenx == 0) {lenx = line.length();}
				blah.add(line);
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		//		PrintStream originalOut = System.out;
		//		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		for (int ii = 0; ii < 60; ii++) {
			port[ii] = new port_s();
		}
		for (int ii = 0; ii < 1000; ii++) {
			already[ii] = new port_s();
		}
		//port = IntStream.range(0, 110) .mapToObj(i -> new port_s()) .toArray(port_s[]::new);
		//already = IntStream.range(0, 2000) .mapToObj(i -> new port_s()) .toArray(port_s[]::new);
		//
		Pattern p = Pattern.compile("(\\d+)/(\\d+)");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			port[i].from = Integer.valueOf(m.group(1));
			port[i].to = Integer.valueOf(m.group(2));
		}
		for (int i = 0; i < leny; i++) {
			if (port[i].from == 0) {
				int xx1 = 0+port[i].to;
				next(0, port[i].to, xx1);

			}
			if (port[i].to == 0) {
				int xx1 = 0+port[i].from;
				next(0, port[i].from, xx1);
			}

		}

		//
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(max);
		out.println("");
	}
	static int ind = 0;
	static void next(int from, int to, int xx1) {
		ind++;
		int found = 0;
		for (int i = 0; i < alreadyPos; i++) {
			if ((already[i].from == from && already[i].to == to) || (already[i].from == to && already[i].to == from)) {
				found = 1; break;
			}
		}

		if (found == 1) {
			ind--;
			return;
		} else {
			already[alreadyPos].from = from;
			already[alreadyPos].to = to;
			alreadyPos++;
		}
		int remAlreadyPos = alreadyPos;
		//if (xx1 > max) {max = xx1;}
		if (ind > maxind) {maxind = ind;}
		if (ind >= maxind && xx1 > max) {max = xx1;}
		for (int i = 0; i < leny; i++) {
			if (port[i].from == to) {
				int xx2 = xx1 + port[i].from + port[i].to;
				next(port[i].from, port[i].to, xx2);
				alreadyPos = remAlreadyPos;
				//tot2 = totrem;
			} else if (port[i].to == to) {
				int xx2 = xx1 + port[i].to + port[i].from;
				next(port[i].to, port[i].from, xx2);
				alreadyPos = remAlreadyPos;
				//tot2 = totrem;
			}

		}
		ind--;
	}

}
class port_s {
	int from, to;
	port_s(int from, int to) {
		this.from = from;
		this.to = to;
	}
	port_s() {
		this.from = 0;
		this.to = 0;
	}
	@Override
	public boolean equals(Object o) {
		port_s other = (port_s) o;
		if (this.from != other.from) {return false;}
		if (this.to != other.to) {return false;}
		return true;
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

