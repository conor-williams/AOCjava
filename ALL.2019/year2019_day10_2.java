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


//Scanner scanner = new Scanner(System.in); scanner.nextLine();
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

//                        for (Map.Entry<Character, Vector<Character>> entry : mp.entrySet()) {
// System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//}

@SuppressWarnings("unchecked")
class year2019_day10_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int sx = 33;
	public static int sy = 33;
	public static char grid [][] = new char[sy][sx];
	public static void main(String [] args) {
		out.println("		2019 Day10.2");
		Vector<String> blah = new Vector<>();
		int leny = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
				grid[leny] = line.toCharArray();
				leny++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
                System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
		Vector <Tuple <Double, Double>> asteroids = new Vector<>();
		for (int yy = 0; yy < sy; yy++) {
			for (int xx = 0; xx < sx; xx++) {
				if (grid[yy][xx] == '#') {
					Tuple <Double, Double> tu1 = new Tuple((double)xx, (double)yy);
					asteroids.add(tu1);
				}
			}
		}
		out.println(asteroids.size());

		int max = 0;
		Tuple <Double, Double> best = new Tuple<>(0.0, 0.0);
		Vector <CinqTuple <Double, Integer, Double, Double, Long>> seesAll = new Vector<>();
		for (int ii = 0; ii < asteroids.size(); ii++) {
			var tu1 = asteroids.get(ii);
			Set <Tuple <Double, Integer>> sees = new HashSet<>();
			Vector <CinqTuple <Double, Integer, Double, Double, Long>> seesTmp = new Vector<>();
			for (int jj = 0; jj < asteroids.size(); jj++) {
				if (ii == jj) {continue;}
				var tu2 = asteroids.get(jj);
				double top1 = tu2.first-tu1.first;
				double bottom1 = tu2.second-tu1.second;
				double tanangle = top1/bottom1;
				int quadrant = 0;
				if (top1 >= 0 && bottom1 <= 0) {//topright
					quadrant = 1;
					if (bottom1 == 0) {
						tanangle = -9999;
					}
				} else if (top1 >= 0 && bottom1 >= 0) {
					quadrant = 2;
				} else if (top1 <= 0 && bottom1 >= 0) {
					quadrant = 3;
				} else {
					quadrant = 4;
				}

				//if (top == 0 || bottom == 0) {continue;}
				Tuple <Double, Integer> tuzzz = new Tuple(tanangle, quadrant);
				sees.add(tuzzz);
				long man = Math.abs(Math.round(tu1.first) - Math.round(tu2.first)) + Math.abs(Math.round(tu2.second)-Math.round(tu1.second));
				CinqTuple <Double, Integer, Double, Double, Long> tuzzz2 = new CinqTuple(tanangle, quadrant, tu2.first, tu2.second, man);
				seesTmp.add(tuzzz2);
			}
			out.print("x,y: "); out.print(tu1.first); out.print(" "); out.println(tu1.second);
			out.println(sees.size());
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			if (sees.size() > max) {max = sees.size(); best = new Tuple(tu1.first, tu1.second); seesAll = new Vector<>(seesTmp);}
		}

		out.println(best.first);
		out.println(best.second);
		Vector <CinqTuple<Double, Integer, Double, Double, Long>> astq1 = new Vector<>();
		Vector <CinqTuple<Double, Integer, Double, Double, Long>> astq2 = new Vector<>();
		Vector <CinqTuple<Double, Integer, Double, Double, Long>> astq3 = new Vector<>();
		Vector <CinqTuple<Double, Integer, Double, Double, Long>> astq4 = new Vector<>();
		for (int ii = 0; ii < seesAll.size(); ii++) {
			var tu1 = seesAll.get(ii);
			if  (tu1.second == 1) {
				astq1.add(tu1);
			} else if (tu1.second == 2) {
				astq2.add(tu1);
			} else if (tu1.second == 3) {
				astq3.add(tu1);
			} else {
				astq4.add(tu1);
			}
		}
		/*
		   Collections.sort(astq1, (a,b) -> Double.compare(a.first, b.first));
		   Collections.sort(astq2, (a,b) -> Double.compare(a.first, b.first));
		   Collections.sort(astq3, (a,b) -> Double.compare(a.first, b.first));
		   Collections.sort(astq4, (a,b) -> Double.compare(a.first, b.first));
		   */
		astq1.sort(Comparator.comparingDouble((CinqTuple t) -> (double)t.first).reversed()
				.thenComparingLong(t -> (long) t.fifth));
		astq2.sort(Comparator.comparingDouble((CinqTuple t) -> (double)t.first).reversed()
				.thenComparingLong(t -> (long) t.fifth));
		astq3.sort(Comparator.comparingDouble((CinqTuple t) -> (double)t.first).reversed()
				.thenComparingLong(t -> (long) t.fifth));
		astq4.sort(Comparator.comparingDouble((CinqTuple t) -> (double)t.first).reversed()
				.thenComparingLong(t -> (long) t.fifth));

		int vapo = 1;
		long ans = 0;
after:
		while (astq1.size() > 0 || astq2.size() > 0 || astq3.size() > 0 || astq4.size() > 0) {
			for (int ii = 0; ii < 4; ii++) {
				Vector <CinqTuple< Double, Integer, Double, Double, Long>> tmp1v = new Vector<>();
				if (ii == 0) {
					out.println("here1");
					tmp1v = new Vector<>(astq1);
				} else if (ii == 1) {
					out.println("here2");
					tmp1v = new Vector<>(astq2);
				} else if (ii == 2) {
					out.println("here3");
					tmp1v = new Vector<>(astq3);
				} else if (ii == 3) {
					out.println("here4");
					tmp1v = new Vector<>(astq4);
				}

				double tanPrev = -999.0;
				for (int jj = 0; jj < tmp1v.size(); jj++) {
					var tu1 = tmp1v.get(jj);

					//out.print(tu1.first); out.print(" V "); out.println(tanPrev);
					if (tu1.first.equals(tanPrev)) {continue;}
					else {
						out.print("vaporize: "); out.print(tu1.third); out.print(" "); out.print(tu1.fourth); out.print(" tan: "); out.println(tu1.first);
						vapo++;
						if (vapo == 201) {ans = Math.round(tu1.third)*100 + Math.round(tu1.fourth); break after;}
						tmp1v.remove(jj);
						jj--;
					}

					tanPrev = tu1.first;
				}
			}
		}



		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(ans);
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


/*
   class CinqTuple<Double, Integer, Double, Double, Long> {
   public final Double first;
   public final Integer second;
   public final Double third;
   public final Double fourth;
   public final Long fifth;

   public CinqTuple(Double first, Integer second, Double third, Double fourth, Long fifth) {
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
   */
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

