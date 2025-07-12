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
class year2022_day15 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2022 Day15.1");
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
		Pattern p = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		Map<Integer, Map<Integer, Integer>> map2D = new HashMap<>();

		/*
		   map2D.putIfAbsent(1, new HashMap<>());
		   map2D.get(1).put(2, "Value at (1, 2)");

		   System.out.println(map2D.get(1).get(2)); // Output: Value at (1, 2)
		   */
		//if (map2D.containsKey(1) && map2D.get(1).containsKey(2)) {
		//System.out.println("Key (1, 2) exists!");
		//}
		//
		//Sensor at x=1638847, y=3775370: closest beacon is at x=2498385, y=3565515
		//int gg[][] = new int[40][40];
		int ROW = 2000000;
		//int ROW = 10;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int sensorX = Integer.valueOf(m.group(1));
			int sensorY = Integer.valueOf(m.group(2));

			int beaconX = Integer.valueOf(m.group(3));
			int beaconY = Integer.valueOf(m.group(4));

			int man = Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);

			//out.print("man: "); out.println(man);
			for (int ii = man, kk=0; ii >= 0; ii--, kk++) {
				int yy1 = sensorY-(ii);
				if (yy1 == ROW) {	
					map2D.putIfAbsent(yy1, new HashMap<>());
				}
				int yy2 = sensorY+(ii);
				if (yy2 == ROW) {
					map2D.putIfAbsent(yy2, new HashMap<>());
				}
				int start = sensorX-kk;
				int end = sensorX+kk+1;
				//out.print(start); out.print(" "); out.println(end);
				if (yy2 == ROW || yy1 == ROW) {
					for (int xx = start; xx < end; xx++) {
						if (yy1 == ROW ) {
							if (map2D.get(yy1).get(xx) == null) {
								map2D.get(yy1).put(xx, 1);
							} else if (map2D.get(yy1).get(xx) == 3 || map2D.get(yy1).get(xx) == 4) {
								//
							} else {
								map2D.get(yy1).put(xx, 1);
							}
						}

						//gg[yy1][xx] = 1;

						if (yy2 == ROW) {
							if (map2D.get(yy2).get(xx) == null) {
								map2D.get(yy2).put(xx, 1);
							} else if (map2D.get(yy2).get(xx) == 3 || map2D.get(yy2).get(xx) == 4) {
							} else {
								map2D.get(yy2).put(xx, 1);
							}
						}
						//gg[yy2][xx] = 1;
					}
				}
			}
			if (sensorY == ROW) {
				map2D.get(sensorY).put(sensorX, 3);
			}
			if (beaconY == ROW) {
				map2D.get(beaconY).put(beaconX, 4);
			}
			//gg[sensorY][sensorX] = 3;
			//gg[beaconY][beaconX] = 4;

		}
		var mp2 = map2D.get(ROW);
		int count = 0;
		for (var entry : mp2.entrySet()) {
			//out.print(entry.getValue());
			if (entry.getValue() == 1) {
				count++;
			}
		}
		//out.println();
		/*
		   for (int yy = 0; yy < 40; yy++) {
		   for (int xx = 0; xx < 40; xx++) {
		   out.print(gg[yy][xx]);
		   }
		   out.println();
		   }
		   out.println();
		   */
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(count);
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

