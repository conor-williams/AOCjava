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
class year2022_day15_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int SZ = 4000000;
	//public static int SZ = 20;
	public static void main(String [] args) {
		out.println("		2022 Day15.2");
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
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)");

		Map<Integer, Map<Integer, Boolean>> map2D = new HashMap<>();

		Map <Integer, CinqTuple<Integer, Integer, Integer, Integer, Integer>> mp = new HashMap<>();
		int numB = blah.size();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			int sensorX = Integer.valueOf(m.group(1));
			int sensorY = Integer.valueOf(m.group(2));

			int beaconX = Integer.valueOf(m.group(3));
			int beaconY = Integer.valueOf(m.group(4));

			int man = Math.abs(sensorX - beaconX) + Math.abs(sensorY - beaconY);
			mp.put(i, new CinqTuple(sensorX, sensorY, beaconX, beaconY, man));
		}
		//// alternate...
		Vector <Integer> already = new Vector<>();
		int qqq1 = -1;
		Vector <Tuple<Double, Double>> lines2 = new Vector<>();
after2:
		for (var en1: mp.entrySet()) {
			qqq1++;
			//out.print("edge bea sen: "); out.println(qqq);

			var entry1 = en1.getValue();
			int sensorX1 = entry1.first;
			int sensorY1 = entry1.second;
			int beaconX1 = entry1.third;
			int beaconY1 = entry1.fourth;
			int man1 = entry1.fifth;
			int qqq2 = -1;
			for (var en2: mp.entrySet()) {
				qqq2++;
				if (en1 == en2) {continue;}
				var entry2 = en2.getValue();
				int sensorX2 = entry2.first;
				int sensorY2 = entry2.second;
				int beaconX2 = entry2.third;
				int beaconY2 = entry2.fourth;
				int man2 = entry2.fifth;

				int man3 = Math.abs(sensorX2 - sensorX1) + Math.abs(sensorY2 - sensorY1);
				if (man1 + man2 + 2 == man3) {
					if (already.contains(qqq1) || already.contains(qqq2)) {
						continue;
					} else {
						already.add(qqq1); already.add(qqq2);
						out.print(qqq1); out.print(" "); out.println(qqq2);
						Vector <Tuple <Integer, Integer>> tu1 = new Vector<>();
						out.print("man1: "); out.println(man1);
						int yy1_1 = sensorY1 -(man1+1); int xx1_1 = sensorX1;
						int yy1_2 = sensorY1 +(man1+1); int xx1_2 = sensorX1;
						int yy1_3 = sensorY1; int xx1_3 = sensorX1 - (man1+1);
						int yy1_4 = sensorY1; int xx1_4 = sensorX1 + (man1+1);
						tu1.add(new Tuple(xx1_1, yy1_1));
						out.println(xx1_1); out.print(" "); out.println(yy1_1);
						tu1.add(new Tuple(xx1_2, yy1_2));
						out.println(xx1_2); out.print(" "); out.println(yy1_2);
						tu1.add(new Tuple(xx1_3, yy1_3));
						out.println(xx1_3); out.print(" "); out.println(yy1_3);
						tu1.add(new Tuple(xx1_4, yy1_4));
						out.println(xx1_4); out.print(" "); out.println(yy1_4);

						Vector <Tuple <Integer, Integer>> tu2 = new Vector<>();
						out.print("man2: "); out.println(man2);
						int yy2_1 = sensorY2 -(man2+1); int xx2_1 = sensorX2;
						int yy2_2 = sensorY2 +(man2+1); int xx2_2 = sensorX2;
						int yy2_3 = sensorY2; int xx2_3 = sensorX2 - (man2+1);
						int yy2_4 = sensorY2; int xx2_4 = sensorX2 + (man2+1);
						tu2.add(new Tuple(xx2_1, yy2_1));
						out.println(xx2_1); out.print(" "); out.println(yy2_1);
						tu2.add(new Tuple(xx2_2, yy2_2));
						out.println(xx2_2); out.print(" "); out.println(yy2_2);
						tu2.add(new Tuple(xx2_3, yy2_3));
						out.println(xx2_3); out.print(" "); out.println(yy2_3);
						tu2.add(new Tuple(xx2_4, yy2_4));
						out.println(xx2_4); out.print(" "); out.println(yy2_4);
						//Scanner scanner = new Scanner(System.in); scanner.nextLine();

						Vector <Tuple<Double, Double>> lines1 = new Vector<>();
						for (int ii = 0; ii < tu1.size(); ii++) {
							for (int jj = ii+1; jj < tu1.size(); jj++) {
								if (ii == jj) {continue;}
								//if (tu1.get(ii).first == tu1.get(jj).first) {continue;}
								//if (tu1.get(ii).second == tu1.get(jj).second) {continue;}
								double slope = (double)((double)tu1.get(jj).second - (double)tu1.get(ii).second) / (double) ((double)tu1.get(jj).first - (double)tu1.get(ii).first);
								double intercept = (double)tu1.get(ii).second - ((double)slope * (double)tu1.get(ii).first);
								if (slope != Double.POSITIVE_INFINITY && slope != Double.NEGATIVE_INFINITY
								 	&& intercept != Double.POSITIVE_INFINITY && intercept != Double.NEGATIVE_INFINITY) {
									lines1.add(new Tuple(slope, intercept));
									out.println("lines1 (slope intercept): "); out.print(slope); out.print(" "); out.println(intercept);
								}
							}
						}
						out.print("lines1.size: "); out.println(lines1.size());

						for (int ii = 0; ii < tu2.size(); ii++) {
							for (int jj = ii+1; jj < tu2.size(); jj++) {
								if (ii == jj) {continue;}
								double slope = (double)((double)tu2.get(jj).second - (double)tu2.get(ii).second) / (double) ((double)tu2.get(jj).first - (double)tu2.get(ii).first);
								double intercept = (double)tu2.get(ii).second - ((double)slope * (double)tu2.get(ii).first);
								if (tu2.get(ii).first == tu2.get(jj).first) {continue;}
								if (tu2.get(ii).second == tu2.get(jj).second) {continue;}
								if (lines1.contains(new Tuple(slope, intercept))) {
									out.println("lines2 (slope intercept): "); out.print(slope); out.print(" "); out.println(intercept);
									//out.print("slope: "); out.println(slope);
									//out.print("slope: "); out.println(intercept);
									/*
									out.println(tu2.get(jj).second);
									out.println(tu2.get(jj).first);
									out.println(tu2.get(ii).second);
									out.println(tu2.get(ii).first);
									*/
									lines2.add(new Tuple(slope, intercept));
								}
							}
						}
						out.print("lines2.size: "); out.println(lines2.size());
						if (lines2.size() == 2) {
							out.println("yatzee...");
							var tu1_1 = lines2.get(0);
							var tu2_2 = lines2.get(1);
							out.println(tu1_1.first);
							out.println(tu1_1.second);
							out.println(tu2_2.first);
							out.println(tu2_2.second);
							double x = (tu2_2.second - tu1_1.second) / (tu1_1.first - tu2_2.first);
							int x1 = (int)x;
							int y = (int)((tu1_1.first * x) + tu1_1.second);
							int y2 = (int)((tu2_2.first * x) + tu2_2.second);
							out.print("ansx: "); out.println(x);
							out.print("ansy: "); out.println(y);
							out.print("ansy2: "); out.println(y2);
							break after2;
						}
						

					}
				}
			}
		}

		//int gg [][]  = new int[40][40];
		int min = 0;
		int max = SZ;
		int qqq = -1;
		long ans = -1;
after:
		for (var en: mp.entrySet()) {
			qqq++;
			//out.print("edge bea sen: "); out.println(qqq);

			var entry = en.getValue();
			int sensorX = entry.first;
			int sensorY = entry.second;
			int beaconX = entry.third;
			int beaconY = entry.fourth;
			int man = entry.fifth;

			for (int ii = man+1, kk=0; ii >= 0; ii--, kk++) 
			{
				int go = 0;
				int yy1 = sensorY-(ii);
				int yy2 = sensorY+(ii);
				int start = sensorX-kk;
				int end = sensorX+kk+1;
				int xx = 0;
				for (int zz = 0; zz < 2; zz++) {
					if (zz == 0) {xx = start;} else if (zz == 1) {xx = end-1;}
					if (xx >= 0 && xx <= max) {
						int found3 = 0;
						int found4 = 0;
						int man3 = 0; 
						int man4 = 0;
						int man2 = 0;
						for (var en2: mp.entrySet()) {
							var entry2 = en2.getValue();
							int senX = entry2.first;
							int senY = entry2.second;
							man2 = entry2.fifth;


							if (yy1 > 0 && yy1 <= SZ) {
								man3 = Math.abs(senX - xx) + Math.abs(senY-yy1);
							}
							if (yy2 > 0 && yy2 <= SZ) {
								man4 = Math.abs(senX - xx) + Math.abs(senY-yy2);
							}

							if (man3 <= man2) { 
								found3++;
							}
							if (man4 <= man2) {
								found4++;
							}

						}
						if (found3 == 0) {
							ans = (long)((long)(xx * 4000000L) + (long)yy1);
							out.println("found3 0");
							out.print(xx); out.print(" "); out.println(yy1);
							out.println(qqq);
							break after;

						} 
						if (found4 == 0) {
							ans = (long)((long)(xx * 4000000L) + (long)yy2);
							out.println("found4 0");
							out.print(xx); out.print(" "); out.println(yy2);
							out.println(qqq);
							break after;
						}
					}
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

