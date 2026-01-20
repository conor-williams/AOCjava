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
class year2025_day8 {
//	        public static int maxPath = 0;
    //    public static int lenx = 0;
     //   public static int leny = 0;
      //  public static char grid [][] = new char[leny][lenx];
    //    public static int already [][] = new int[leny][lenx];

	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2025 Day8.1");
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
		Pattern p = Pattern.compile("(\\d+),(\\d+),(\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		Vector <TreTuple <Integer, Integer, Integer>> jbox = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			TreTuple <Integer, Integer, Integer> one = new TreTuple(
					Integer.valueOf(m.group(1)),
					Integer.valueOf(m.group(2)),
					Integer.valueOf(m.group(3)));
			jbox.add(one);
		}
		Vector <TreTuple <Double, 
		       TreTuple <Integer, Integer, Integer>,
		       TreTuple <Integer, Integer, Integer>>> jboxdists = new Vector<>();
		for (int ii = 0, n = jbox.size(); ii < n; ii++) {
			var tu1 = jbox.get(ii);
			for (int jj = ii+1, nn = jbox.size(); jj < nn; jj++) {
				var tu2 = jbox.get(jj);
				double dist = Math.sqrt(Math.pow(tu1.first - tu2.first, 2) +
					Math.pow(tu1.second - tu2.second, 2) + 
					Math.pow(tu1.third - tu2.third, 2));
				jboxdists.add(new TreTuple(dist, tu1, tu2));
			}
		}
		out.println("jbox dists size: " + jboxdists.size());
		Collections.sort(jboxdists, Comparator.comparing(t -> t.first));
		out.println(jboxdists.get(0).first);
		out.println((jboxdists.get(0).second).first);
		out.println((jboxdists.get(0).second).second);
		out.println((jboxdists.get(0).second).third);

		out.println((jboxdists.get(0).third).first);
		out.println((jboxdists.get(0).third).second);
		out.println((jboxdists.get(0).third).third);

		//out.println(jboxdists.get(1).first);
		//out.println(jboxdists.get(2).first);

		Vector <Vector <TreTuple<Integer, Integer, Integer>>> groups = new Vector<>();
		/*
		for (int ii = 0, n = jbox.size(); ii < n; ii++) {
			var one = jbox.get(ii);
			Vector <TreTuple<Integer, Integer, Integer>> gg = new Vector<>();
			gg.add(one);
			groups.add(gg);
		}
		*/
		for (int ii = 0, n = jboxdists.size(); ii < 1000; ii++) {
			var fir = jboxdists.get(ii).second;	
			var sec = jboxdists.get(ii).third;	
			out.println(fir.first + "," + fir.second + "," + fir.third);
			out.println(sec.first + "," + sec.second + "," + sec.third);
			boolean isFound = false;
			boolean firFound = false;
			boolean secFound = false;
			for (int zz = 0; zz < groups.size(); zz++) {
				var xx = groups.get(zz);
				boolean firInGroup = xx.contains(fir);
				boolean secInGroup = xx.contains(sec);
				if (firFound == false && firInGroup) {
					firFound = true;
				}
				if (secFound == false && secInGroup) {
					secFound = true;
				}
				if (firInGroup && secInGroup) {
					out.println("both already in same group\n");
					isFound = true;
					break;
				} else if (firInGroup || secInGroup) {
					boolean firstAdded = false;
					boolean secondAdded = false;
					if (!firInGroup) {firstAdded = true; xx.add(fir);}
					if (!secInGroup) {secondAdded = true; xx.add(sec);}
					//look for the other one and transfer it in...
					for (int qqq = 0, n4 = groups.size(); qqq < n4; qqq++) {
						if (qqq == zz) {continue;}
						if (firstAdded) {//find other group that has fir
							if (groups.get(qqq).contains(fir)) {
								for (var item: groups.get(qqq)) { 
									if (!xx.contains(item)) {
										xx.add(item);
									}
								}
								groups.remove(qqq);
								break;
							}
						} else if (secondAdded) {
							if (groups.get(qqq).contains(sec)) {
								for (var item: groups.get(qqq)) { 
									if (!xx.contains(item)) {
										xx.add(item);
									}
								}
								groups.remove(qqq);
								break;
							}
							/*
							if (groups.get(qqq).contains(sec)) {
								xx.addAll(groups.get(qqq));
								groups.remove(qqq);
								break;
							}
							*/
						}
					}

					/*
					for (int qqq = 0; qqq < groups.size(); qqq++) {
						if (qqq == zz) {continue;}
						var qq = groups.get(zz);
						if (firstAdded) {
							if (qq.contains(fir)) {
								for (int pp = 0, n3 = qq.size(); pp < n3; pp++) {
									if (!xx.contains(qq.get(pp))) {
										xx.add(qq.get(pp));
									}
								}
								groups.remove(qqq);
							}
							break;
						} else if (secondAdded) {
					out.println("transfer from group....");
					Scanner scanner = new Scanner(System.in); scanner.nextLine();
							if (qq.contains(sec)) {
								for (int pp = 0, n3 = qq.size(); pp < n3; pp++) {
									if (!xx.contains(qq.get(pp))) {
										xx.add(qq.get(pp));
									}
								}
								groups.remove(qqq);
							}
							break;
						}
					}
					*/

					out.println("adding one....");
					groups.set(zz, xx);
					isFound = true;
					break;
				}	
			}
			if (isFound == false) {
				Vector <TreTuple<Integer, Integer, Integer>> newGroup = 
					new Vector<>();
				if (!firFound) {
					out.println("not fir found");
					newGroup.add(fir);
				} 
				if (!secFound) {
					out.println("not sec found");
					newGroup.add(sec);
				}
				groups.add(newGroup);
				out.println("new Group" + fir.first + " " + sec.first);
			}
			out.println("sizes of: " + groups.size() + " groups");
			for (int iii = 0, n2 = groups.size(); iii < n2; iii++) {
				out.println(groups.get(iii).size());
			}
			out.println("---------");
		}
		out.println("sizes of: " + groups.size() + " groups");
		Vector <Integer> sizes = new Vector<>();
		for (int ii = 0; ii < groups.size(); ii++) {
			sizes.add(groups.get(ii).size());
		}
		Collections.sort(sizes, Comparator.reverseOrder());
		long ans = sizes.get(0) * sizes.get(1) * sizes.get(2);
		/*
		for (int ii = 0, n = groups.size(); ii < n; ii++) {
			out.print("group: " + ii);
			out.println(groups.get(ii).size());

			for (int jj = 0; jj < groups.get(ii).size(); jj++) {
				var tu1 = groups.get(ii).get(jj);
				out.println(tu1.first + "," + tu1.second + "," + tu1.third);
			}
			out.println("------");

		}
		*/

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

