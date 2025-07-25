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
class year2021_day19_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static int m[][][] = {
		{{1, 0, 0}, {0,1,0}, {0,0,1}},
		{{1, 0, 0}, {0,1,0}, {0,0,-1}},
		{{1, 0, 0}, {0,-1,0}, {0,0,1}},
		{{1, 0, 0}, {0,-1,0}, {0,0,-1}},
		{{-1, 0, 0}, {0,1,0}, {0,0,1}},
		{{-1, 0, 0}, {0,1,0}, {0,0,-1}},
		{{-1, 0, 0}, {0,-1,0}, {0,0,1}},
		{{-1, 0, 0}, {0,-1,0}, {0,0,-1}},
		//xzy
		{{1, 0, 0}, {0,0,1}, {0,1,0}},
		{{1, 0, 0}, {0,0,1}, {0,-1,0}},
		{{1, 0, 0}, {0,0,-1}, {0,1,0}},
		{{1, 0, 0}, {0,0,-1}, {0,-1,0}},
		{{-1, 0, 0}, {0,0,1}, {0,1,0}},
		{{-1, 0, 0}, {0,0,1}, {0,-1,0}},
		{{-1, 0, 0}, {0,0,-1}, {0,1,0}},
		{{-1, 0, 0}, {0,0,-1}, {0,-1,0}},
		//yxz
		{{0, 1, 0}, {1,0,0}, {0,0,1}},
		{{0, 1, 0}, {1,0,0}, {0,0,-1}},
		{{0, 1, 0}, {-1,0,0}, {0,0,1}},
		{{0, 1, 0}, {-1,0,0}, {0,0,-1}},
		{{0, -1, 0}, {1,0,0}, {0,0,1}},
		{{0, -1, 0}, {1,0,0}, {0,0,-1}},
		{{0, -1, 0}, {-1,0,0}, {0,0,1}},
		{{0, -1, 0}, {-1,0,0}, {0,0,-1}},
		//yzx
		{{0, 1, 0}, {0,0,1}, {1,0,0}},
		{{0, 1, 0}, {0,0,1}, {-1,0,0}},
		{{0, 1, 0}, {0,0,-1}, {1,0,0}},
		{{0, 1, 0}, {0,0,-1}, {-1,0,0}},
		{{0, -1, 0}, {0,0,1}, {1,0,0}},
		{{0, -1, 0}, {0,0,1}, {-1,0,0}},
		{{0, -1, 0}, {0,0,-1}, {1,0,0}},
		{{0, -1, 0}, {0,0,-1}, {-1,0,0}},
		//zxy
		{{0, 0, 1}, {1,0,0}, {0,1,0}},
		{{0, 0, 1}, {1,0,0}, {0,-1,0}},
		{{0, 0, 1}, {-1,0,0}, {0,1,0}},
		{{0, 0, 1}, {-1,0,0}, {0,-1,0}},
		{{0, 0, -1}, {1,0,0}, {0,1,0}},
		{{0, 0, -1}, {1,0,0}, {0,-1,0}},
		{{0, 0, -1}, {-1,0,0}, {0,1,0}},
		{{0, 0, -1}, {-1,0,0}, {0,-1,0}},
		//zyx
		{{0, 0, 1}, {0,1,0}, {1,0,0}},
		{{0, 0, 1}, {0,1,0}, {-1,0,0}},
		{{0, 0, 1}, {0,-1,0}, {1,0,0}},
		{{0, 0, 1}, {0,-1,0}, {-1,0,0}},
		{{0, 0, -1}, {0,1,0}, {1,0,0}},
		{{0, 0, -1}, {0,1,0}, {-1,0,0}},
		{{0, 0, -1}, {0,-1,0}, {1,0,0}},
		{{0, 0, -1}, {0,-1,0}, {-1,0,0}}
	};
	public static Vector <Integer> deters = new Vector<>();
	public static beacon_s beacon[] = new beacon_s[30];
	public static void main(String [] args) {
		out.println("		2021 Day19.2");
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
		/*
		   grid = new char[leny][lenx];
		   already = new int[leny][lenx];
		   for (int i = 0; i < blah.size();i++) {
		   grid[i] = blah.get(i).toCharArray();
		   }
		   */


		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("(-?\\d+),(-?\\d+),(-?\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		int pos = 0;
		int numB = 0;
		for (int zz =0; zz < 30; zz++) {
			beacon[zz] = new beacon_s();
		}
		Vector <TreTuple<Integer, Integer, Integer>> veFirst = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			if (blah.get(i).length() == 0) {beacon[pos].beaconPos = numB; pos++; numB = 0; continue;}
			if (blah.get(i).charAt(0) == '-' && 
					blah.get(i).charAt(1) == '-') {continue;}
			Matcher m = p.matcher(blah.get(i));
			m.find();
			beacon[pos].x[numB][0] = (Integer.valueOf(m.group(1)));
			beacon[pos].y[numB][0] = (Integer.valueOf(m.group(2)));
			beacon[pos].z[numB][0] = (Integer.valueOf(m.group(3)));
			if (pos == 0) {
				veFirst.add(new TreTuple(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), Integer.valueOf(m.group(3))));
			}
			numB++;
			if (blah.size() == i+1) {
				beacon[pos].beaconPos = numB;
				pos++;
			}
		}
		int numBeacons = pos;
		out.println(numBeacons);
		for (int l = 0; l < 48; l++) {
			if (determinant(l)) {
				deters.add(l);
			}
		}
		//rotate all beacons...
		for (int jj = 0; jj < numBeacons; jj++) {
			for (int kk = 0; kk < beacon[jj].beaconPos; kk++) {
				int threebyone[] = new int[3];
				threebyone[0] = beacon[jj].x[kk][0];
				threebyone[1] = beacon[jj].y[kk][0];
				threebyone[2] = beacon[jj].z[kk][0];
				for (int ii = 0; ii < deters.size(); ii++) {
					matmul(m[deters.get(ii)], threebyone, jj, kk, ii);
				}
			}
		}
		/*
		   for (int i = 0; i < beaconNum; i++) {
		   for (int k = 0; k < 24; k++) {
		   for (int j = 0; j < beacon[i].beaconPos; j++) {
		   beaconOrig[i].x[j][k] = beacon[i].x[j][k];
		   beaconOrig[i].y[j][k] = beacon[i].y[j][k];
		   beaconOrig[i].z[j][k] = beacon[i].z[j][k];
		   }
		   }

		   }
		   */
		Vector <TreTuple <Integer, Integer, Integer>> allBeacons = new Vector<>(veFirst);
		Vector <Integer> found = new Vector<>();
		/*
		   int rem = veFirst.size() - 11;
		   Vector <TreTuple<Integer, Integer, Integer>> veve = new Vector<>();
		   for (int ii = 0; ii < rem;  ii++) {
		   veve.add(veFirst.get(ii));
		   }
		   */


		Vector<Integer> distances[] = new Vector[numBeacons];
		for (int ii = 0; ii < numBeacons; ii++) {
			distances[ii] = new Vector<>();
		}
		for (int ii = 0; ii < numBeacons; ii++) {
			for (int one = 0; one < beacon[ii].beaconPos; one++) {
				int distXone = beacon[ii].x[one][0];
				int distYone = beacon[ii].y[one][0];
				int distZone = beacon[ii].z[one][0];

				for (int two = 0; two < beacon[ii].beaconPos; two++) {
					if (one == two) {continue;}
					int distXtwo = beacon[ii].x[two][0];
					int distYtwo = beacon[ii].y[two][0];
					int distZtwo = beacon[ii].z[two][0];

					distances[ii].add(Math.abs(distXone-distXtwo) + Math.abs(distYone-distYtwo) + Math.abs(distZone-distZtwo));
				}
			}
		}

		Vector <Tuple <Integer, Integer>> pairs = new Vector<>();
		for (int ii = 0; ii < numBeacons; ii++) {
			for (int jj = 0; jj < numBeacons; jj++) {
				if (ii == jj) {continue;}
				Vector <Integer> d1 = new Vector(distances[ii]);

				Vector <Integer> d2 = new Vector(distances[jj]);

				d1.retainAll(d2);

				if (d1.size() >= 121) {
					out.print(d1.size()); out.print(" size: ");  out.print(ii); out.print(" pair "); out.println(jj);
					pairs.add(new Tuple(ii, jj));
				}
			}
		}
		Vector <TreTuple> scanners = new Vector();
		scanners.add(new TreTuple(0, 0, 0));

		Vector <Integer> found2 = new Vector();
		found2.add(0);
		do {
			veFirst.clear();
			int which = found2.get(0);
			for (int zz = 0; zz < beacon[which].beaconPos; zz++) {
				veFirst.add(new TreTuple(beacon[which].x[zz][0], beacon[which].y[zz][0], beacon[which].z[zz][0]));
			}

			for (int zzz = 0; zzz < veFirst.size(); zzz++) {
				var tu1 = veFirst.get(zzz);
				int sX = tu1.first;
				int sY = tu1.second;
				int sZ = tu1.third;

				out.print("side: "); out.println(zzz);
				for (int qqqq = 0; qqqq < pairs.size(); qqqq++) {
					int i = -1;
					if (pairs.get(qqqq).first == found2.get(0)) {
						i = pairs.get(qqqq).second;

						if (found.contains(i)) {continue;}
						//out.println("trying beacon set: "); out.println(i);
						int end2 = beacon[i].beaconPos;
after:
						for (int k = 0; k < 24; k++) {
							for (int j = 0; j < end2-11; j++) {
								int distX = (sX - beacon[i].x[j][k]);
								int distY = (sY - beacon[i].y[j][k]);
								int distZ = (sZ - beacon[i].z[j][k]);

								//apply that dist to every beacon in i
								for (int kk = 0; kk < 24; kk++) {
									Vector <TreTuple <Integer, Integer, Integer>> veNext = new Vector<>();
									//int end1 = beacon[i].beaconPos;
									for (int jj = 0; jj < end2; jj++) {
										int newDistX = beacon[i].x[jj][kk] + distX;
										int newDistY = beacon[i].y[jj][kk] + distY;
										int newDistZ = beacon[i].z[jj][kk] + distZ;

										veNext.add(new TreTuple(newDistX, newDistY, newDistZ));
									}
									Vector <TreTuple<Integer, Integer, Integer>> intersection = new Vector(veFirst);
									intersection.retainAll(veNext);
									if (intersection.size() == 12) {
										out.print("beacon group: "); out.print(i); out.print(" yatzee orient: "); out.println(kk);
										for (int qqq = 0; qqq < veNext.size(); qqq++) {
											if (!allBeacons.contains(veNext.get(qqq))) {
												allBeacons.add(veNext.get(qqq));
											}
										}
										for (int jj = 0; jj < beacon[i].beaconPos; jj++) {
											var tu2 = veNext.get(jj);
											beacon[i].x[jj][0] = tu2.first;
											beacon[i].y[jj][0] = tu2.second;
											beacon[i].z[jj][0] = tu2.third;
										}
										scanners.add(new TreTuple(distX, distY, distZ));
										found.add(i);
										found2.add(i);
										//Scanner scanner = new Scanner(System.in); scanner.nextLine();
										break after;
									}
								}
							}
						}
					}
				}
			}
			found2.remove(0);
		} while (found2.size() > 0);

		int maxMan = 0;
		for (int ii = 0; ii < scanners.size()-1; ii++) {
			for (int jj = ii+1; jj < scanners.size(); jj++) {
				var tu1 = scanners.get(ii);
				var tu2 = scanners.get(jj);
				out.print(tu1.first); out.print(" "); out.print(tu1.second); out.print(" "); out.println(tu1.third);
				out.print(tu2.first); out.print(" "); out.print(tu2.second); out.print(" "); out.println(tu2.third);
				int man = Math.abs((int)tu1.first-(int)tu2.first) + Math.abs((int)tu1.second-(int)tu2.second) + Math.abs((int)tu1.third-(int)tu2.third);
				if (man > maxMan) {maxMan = man;}
			}
		}
		System.setOut(originalOut);

		out.print("**j_ans: ");
		out.print(maxMan);
		out.println("");
	}
	public static void matmul(int ma[][], int threebyone[], int beaconPos, int jpos, int pos) {

		beacon[beaconPos].x[jpos][pos] = 0;
		beacon[beaconPos].y[jpos][pos] = 0;
		beacon[beaconPos].z[jpos][pos] = 0;
		int result[] = new int[3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 0) {
					beacon[beaconPos].x[jpos][pos] += ma[i][j] * threebyone[j];
				} else if (i==1) {
					beacon[beaconPos].y[jpos][pos] += ma[i][j] * threebyone[j];
				} else if (i==2) {
					beacon[beaconPos].z[jpos][pos] += ma[i][j] * threebyone[j];
				}
				result[i] +=  ma[i][j] * threebyone[j];
			}
		}
	}

	public static class beacon_s {
		int x[][] = new int[50][24];
		int y[][] = new int[50][24];
		int z[][] = new int[50][24];

		int beaconPos;

	}
	public static boolean determinant(int l) {
		//              a             e          i           g          h
		int a1 = m[l][0][0] * (m[l][1][1] * m[l][2][2] - m[l][1][2] * m[l][2][1]);
		//             b              d         l          g             f
		int b1 = m[l][0][1] * (m[l][1][0] * m[l][2][2] - m[l][2][0] * m[l][1][2]);
		//           c              d           h                 e        g
		int c1 = m[l][0][2] * (m[l][1][0] * m[l][2][1] - m[l][1][1] * m[l][2][0]);
		int det = a1 - b1 + c1;
		if (det == 1) {return true;}
		else {return false;}
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

