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
class year2021_day22_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//    public static int already [][] = new int[leny][lenx];
	static int PAD = 50;
	static int SX = 101;
	static char grid [][][] = new char[SX][SX][SX];
	static class T {
		int ar[][] = new int[3][2];
	};
	static Vector <Tuple <T, Boolean>> vecAll = new Vector<>();

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2021 Day22.2");
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
		Pattern p = Pattern.compile("(on|off) x=(-?\\d+)..(-?\\d+),y=(-?\\d+)..(-?\\d+),z=(-?\\d+)..(-?\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Boolean off = false;
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			if (m.group(1).charAt(1) == 'f') {
				off = true;
			} else {
				off = false;
			}
			int xfrom = Integer.valueOf(m.group(2));
			int xto = Integer.valueOf(m.group(3)) + 1;
			int yfrom = Integer.valueOf(m.group(4));
			int yto = Integer.valueOf(m.group(5)) + 1;
			int zfrom = Integer.valueOf(m.group(6));
			int zto = Integer.valueOf(m.group(7)) + 1;

			T posArray = new T();
			posArray.ar[0][0] = xfrom;
			posArray.ar[0][1] = xto;
			posArray.ar[1][0] = yfrom;
			posArray.ar[1][1] = yto;
			posArray.ar[2][0] = zfrom;
			posArray.ar[2][1] = zto;

			//T.ar = {{xfrom, xto}, {yfrom, yto}, {zfrom, zto}};
			vecAll.add(new Tuple(posArray, !off));

		}
		Vector <Tuple <T, Boolean>> done = new Vector<>();
		done.add(vecAll.get(0));
		vecAll.remove(0);


		long area = 0;
		{
			var p_arr = done.get(0).first;
			if (done.get(0).second == true) {
				area += (long)(Math.abs(p_arr.ar[0][1]-p_arr.ar[0][0])) * (long)(Math.abs(p_arr.ar[1][1]-p_arr.ar[1][0])) * (long)(Math.abs(p_arr.ar[2][1]-p_arr.ar[2][0]));
			}
		}

		Vector <Integer> cors[] = new Vector[4];
		for (int qqq=0; qqq < 4; qqq++) {
			cors[qqq] = new Vector<>();
		}
		int kkcounter = 0;
		int go = 0;
		for (var tuA: vecAll) {
			go = 0;
			kkcounter++;
			var p2 = tuA.first;
			boolean intersectionOffOnNew = tuA.second;
			if (intersectionOffOnNew == true) {
				area += (long)(Math.abs(p2.ar[0][0]-p2.ar[0][1])) * (long)(Math.abs(p2.ar[1][0]-p2.ar[1][1])) * (long)(Math.abs(p2.ar[2][0]-p2.ar[2][1]));
			}
			int nointer = 0;
			Vector <Tuple <T, Boolean>> shapes = new Vector<>();;
			for (var tuD: done) {
				go = 0;
				//intersect
				nointer = 0;
				var p3 = tuD.first;
				boolean intersectionSecondOffOn = tuD.second;

				T inters = new T();
				for (int ii = 0; ii < 3; ii++) {
					//p2 is the old list
					//p3 is the incoming new one
					if (p2.ar[ii][0] <= p3.ar[ii][0] && p2.ar[ii][1] <= p3.ar[ii][0]) {
						nointer = 1;
						go = 1;
						break;
					} else if (p3.ar[ii][0] <= p2.ar[ii][0] && p3.ar[ii][1] <= p2.ar[ii][0]) {
						nointer = 1;
						go = 1;
						break;
					} else if (p2.ar[ii][0] <= p3.ar[ii][0] && p2.ar[ii][1] <= p3.ar[ii][1]) {
						inters.ar[ii][0] = p3.ar[ii][0];
						inters.ar[ii][1] = p2.ar[ii][1];
					} else if (p3.ar[ii][0] <= p2.ar[ii][0] && p3.ar[ii][1] <= p2.ar[ii][1]) {
						inters.ar[ii][0] = p2.ar[ii][0];
						inters.ar[ii][1] = p3.ar[ii][1];
					} else if (p2.ar[ii][0] <= p3.ar[ii][0] && p3.ar[ii][1] <= p2.ar[ii][1]) {
						inters.ar[ii][0] = p3.ar[ii][0];
						inters.ar[ii][1] = p3.ar[ii][1];
					} else if (p3.ar[ii][0] <= p2.ar[ii][0] && p2.ar[ii][1] <= p3.ar[ii][1]) {
						inters.ar[ii][0] = p2.ar[ii][0];
						inters.ar[ii][1] = p2.ar[ii][1];
					} else {
						go = 1;
						break;
					}
				}

				if (go == 0) {
					cors[0].clear(); cors[1].clear(); cors[2].clear();
					{ //max 4 xes, 4 yes 4 zes 
						for (int ii = 0; ii < 3; ii++) {
							for (int jj = 0; jj < 2; jj++) {
								if (p3.ar[ii][jj] != inters.ar[ii][jj]) {
									if (cors[ii].indexOf(p3.ar[ii][jj]) == -1) {cors[ii].add(p3.ar[ii][jj]);}
								}
								if (cors[ii].indexOf(inters.ar[ii][jj]) == -1) {cors[ii].add(inters.ar[ii][jj]);}
							}
							Collections.sort(cors[ii]);
						}

					}
				}
				//make all the shapes from the nearest x,y,z
				if (go == 0)
				{
					for (int xx = 0; xx < cors[0].size()-1; xx++) {
						int firstx = cors[0].get(xx);
						int secondx = cors[0].get(xx+1);
						for (int yy = 0; yy < cors[1].size()-1; yy++) {
							int firsty = cors[1].get(yy);
							int secondy = cors[1].get(yy+1);
							for (int zz = 0; zz < (int)cors[2].size()-1; zz++) {
								int firstz = cors[2].get(zz);
								int secondz = cors[2].get(zz+1);

								if (intersectionOffOnNew == true && intersectionSecondOffOn == true) {
									if (firstx == inters.ar[0][0] && firsty == inters.ar[1][0] && firstz == inters.ar[2][0] &&
											secondx == inters.ar[0][1] && secondy == inters.ar[1][1] && secondz == inters.ar[2][1]) {
										area -= (long)(Math.abs(secondx-firstx)) * (long)(Math.abs(secondy-firsty)) * (long)(Math.abs(secondz-firstz));
									} else {
										T tmp2 = new T();
										tmp2.ar[0][0] = firstx;
										tmp2.ar[0][1] = secondx;
										tmp2.ar[1][0] = firsty;
										tmp2.ar[1][1] = secondy;
										tmp2.ar[2][0] = firstz;
										tmp2.ar[2][1] = secondz;

										shapes.add(new Tuple(tmp2, intersectionSecondOffOn));
									}
								} else if (intersectionOffOnNew == false && intersectionSecondOffOn == true) {
									if (firstx == inters.ar[0][0] && firsty == inters.ar[1][0] && firstz == inters.ar[2][0] &&
											secondx == inters.ar[0][1] && secondy == inters.ar[1][1] && secondz == inters.ar[2][1]) {
										area -= (long)(Math.abs(secondx-firstx)) * (long)(Math.abs(secondy-firsty)) * (long)(Math.abs(secondz-firstz));
									} else {
										T tmp2 = new T();
										tmp2.ar[0][0] = firstx;
										tmp2.ar[0][1] = secondx;
										tmp2.ar[1][0] = firsty;
										tmp2.ar[1][1] = secondy;
										tmp2.ar[2][0] = firstz;
										tmp2.ar[2][1] = secondz;
										shapes.add(new Tuple(tmp2, intersectionSecondOffOn));
									}
								} else {
									out.println("ERR1");
								}
							}
						}
					}

				}
				if (nointer == 1) {
					shapes.add(tuD);
				}
				continue;
			}
			if (tuA.second == true) {
				shapes.add(tuA);
			}
			done = new Vector(shapes);
			shapes.clear();

		}
		//
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(area);
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

