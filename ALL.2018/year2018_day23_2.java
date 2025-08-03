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
class year2018_day23_2 {
	public static int leny = 0;
	static class xyzr_s {
		long x1, y1, z1, r1;
	}
	static xyzr_s xyzr[] = new xyzr_s[1010];

	public static void main(String [] args) {
		out.println("		2018 Day23.2");
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

		Pattern p = Pattern.compile("pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)");

		for (int i = 0; i < 1010; i++) {
			xyzr[i] = new xyzr_s();
		}
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();

			xyzr[i].x1 = Long.parseLong(m.group(1));
			xyzr[i].y1 = Long.parseLong(m.group(2));
			xyzr[i].z1 = Long.parseLong(m.group(3));
			xyzr[i].r1 = Long.parseLong(m.group(4));
		}

		PriorityQueue<Node2> Q = new PriorityQueue<>();
		//Queue<Tuple <Long, Long>> Q = new LinkedList<>();
		for (int i = 0; i < leny; i++) {
			long d = Math.abs(xyzr[i].x1) + Math.abs(xyzr[i].y1) + Math.abs(xyzr[i].z1);
			long max = 0L > d-xyzr[i].r1 ? 0L : d-xyzr[i].r1;
			Q.add(new Node2(max, 1L));
			Q.add(new Node2(d + xyzr[i].r1 + 1, -1L));
		}

		long count = 0;
		long maxCount = 0;
		long result = 0;
		while (!Q.isEmpty()) {
			var current = Q.poll();
			count += current.second;
			if (count > maxCount) {
				result = current.first;
				maxCount = count;
			}
		}
		out.print("**j_ans: ");
		out.print(result);
		out.println("");

	}
	static class Node2 implements Comparable<Node2> {
		long first;
		long second;
		//Node parent;

		Node2(long first, long second) {
			this.first = first;
			this.second = second;
		}
		@Override
		public int compareTo(Node2 other) {
			return Long.compare(this.first, other.first);
			//return Long.compare(other.first, this.first);
		}

	}

}

class SpecialTuple<X,Y > implements Comparable<SpecialTuple> {
	public X first;
	public Y second;

	public SpecialTuple(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		SpecialTuple tu2 = (SpecialTuple) o;
		if (this == o) return true;
		if (!(o instanceof SpecialTuple)) return false;
		if (!this.first.equals(tu2.first)) {return false;}
		if (!this.second.equals(tu2.second)) {return false;}

		return true;
	}
	@Override
	public int compareTo(SpecialTuple other) {
		return Long.compare((long)this.first, (long)other.first);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
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

/*
   static void findMax(long minx, long maxx, long miny, long maxy, long minz, long maxz, long ROUND) {
   out.println(ROUND);
   if (ROUND == 1) {
   out.println(Math.abs(minx) + Math.abs(miny) + Math.abs(minz));
   return;
   }
   long bestX = 0;
   long bestY = 0;
   long bestZ = 0;
   int curMax = 0;
   for (long xx = minx; xx <= maxx+ROUND; xx+=ROUND) {
   for (long yy = miny; yy<= maxy+ROUND; yy+=ROUND) {
   for (long zz = miny; zz<=maxz+ROUND; zz+=ROUND) {

   int rrr = totithone(xx, yy, zz);
   if (rrr > curMax) {rrr = curMax; bestX = xx; bestY = yy; bestZ = zz;}
   }
   }
   }
   minx = bestX -ROUND;
   miny = bestY -ROUND;
   minz = bestZ -ROUND;

   maxx = bestX + ROUND;
   maxy = bestY + ROUND;
   maxz = bestZ + ROUND;
   findMax(minx, maxx, miny, maxy, minz, maxz, ROUND/10);

   return;
   }
   */
