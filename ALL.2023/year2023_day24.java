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
class year2023_day24 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];
	static class line {
		double Ix;
		double Iy;
		double Iz;
		double Isx;
		double Isy;
		double Isz;
		double slopexy;
		double cxy;
	};
	static int MAX_LINES = 320;
	static line l[] = new line[MAX_LINES];
	static int tot = 0;
	static int ans123 = 0;

	public static void main(String [] args) {
		out.println("		2023 Day24.1");
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
		for (int ii = 0; ii < MAX_LINES; ii++) {
			l[ii] = new line();
		}
		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(-?\\d+), (-?\\d+), (-?\\d+) @ (-?\\d+), (-?\\d+), (-?\\d+)");
		//308205470708820, 82023714100543, 475164418926765 @ 42, 274, -194
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			l[i].Ix = (double)Long.parseLong(m.group(1));
			l[i].Iy = (double)Long.parseLong(m.group(2));
			l[i].Iz = (double)Long.parseLong(m.group(3));
			l[i].Isx = (double)Long.parseLong(m.group(4));
			l[i].Isy = (double)Long.parseLong(m.group(5));
			l[i].Isz = (double)Long.parseLong(m.group(6));
			l[i].slopexy = (double)((double)l[i].Isy/(double)l[i].Isx);
			l[i].cxy = (double)l[i].Iy - (double)((double)l[i].slopexy*(double)l[i].Ix);
		}
		intersect();

		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans123);
		out.println("");
	}
	static void intersect() {

		tot = 0;
		for (int i = 0; i < leny-1; i++ ) {
			for (int sec = i+1; sec < leny; sec++) {
				if (l[i].slopexy != l[sec].slopexy) {
					int ignore = 0;
					double interX =  (double)(l[sec].cxy - l[i].cxy) / (double)(l[i].slopexy - l[sec].slopexy);
					double interY =  (interX*l[i].slopexy) + (double)l[i].cxy;
					if (l[i].Isx < 0 && interX > l[i].Ix)  {ignore = 1;}
					if (l[i].Isx > 0 && interX < l[i].Ix)  {ignore = 1;}
					if (l[sec].Isx < 0 && interX > l[sec].Ix)  {ignore = 1;}
					if (l[sec].Isx > 0 && interX < l[sec].Ix)  {ignore = 1;}

					if (l[i].Isy < 0 && interY > l[i].Iy)  {ignore = 1;}
					if (l[i].Isy > 0 && interY < l[i].Iy)  {ignore = 1;}
					if (l[sec].Isy < 0 && interY > l[sec].Iy)  {ignore = 1;}
					if (l[sec].Isy > 0 && interY < l[sec].Iy)  {ignore = 1;}
					if (ignore == 0 && interX >= 200000000000000L && interX <= 400000000000000L &&
							interY >= 200000000000000L && interY <= 400000000000000L) {
						tot++;
							}
				} else {
				}
			}
		}
		ans123 = tot;
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

