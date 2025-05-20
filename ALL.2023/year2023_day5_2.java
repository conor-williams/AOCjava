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
// /java -Xmx2g year2019_day3.java *i1.txt


class year2023_day5_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2023 Day5.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

		//	String firstpart = Pattern.quote("mul(");
		Pattern seeds = Pattern.compile("seeds:([\\s\\d]+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		Vector <TupleBig<BigInteger, BigInteger>> seedsVe = new Vector<>();
		Vector <Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>>> tre = new Vector <> ();
		Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> t1 = new Vector <> ();
		Vector <Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>>> tregaps = new Vector <> ();
		Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> t1gaps = new Vector <> ();
		BigInteger zero = new BigInteger("0");
		BigInteger one = new BigInteger("1");
		for (int i = 0; i < blah.size(); i++) {
			String ne = blah.get(i);
			if (i == 0) {
				Matcher m = seeds.matcher(ne);
				m.find();

				Scanner scanner = new Scanner(m.group(1));
				scanner.useDelimiter("[\t\\s ]+");
				while (scanner.hasNext()) {
					String ne2 = scanner.next();
					String ne3 = scanner.next();
					BigInteger neBI2 = new BigInteger(ne2);
					BigInteger neBI3 = new BigInteger(ne3);
					BigInteger plu23 = neBI2.add(neBI3).subtract(one);
					TupleBig<BigInteger, BigInteger> tb1 = new TupleBig<>(new BigInteger(ne2), plu23);
					seedsVe.add(tb1);
				}
				continue;
			}
			if (ne.length() == 0) {
				if (t1.size() > 0) {
					Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> t2 =
						new Vector <>(t1);
					t2.sort(Comparator.comparing(tuple -> tuple.second));
					tre.add(t2);
					t1.clear();
					Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> t2gaps =
						new Vector <>(t1gaps);
					t2gaps.sort(Comparator.comparing(tuple -> tuple.second));
					tregaps.add(t2gaps);
					t1gaps.clear();
				}
				//write vec...
				continue;
			} else if (Character.isDigit(ne.charAt(0))) {
				Scanner scanner = new Scanner(ne);
				scanner.useDelimiter("[\t\\s ]+");
				String x1 = scanner.next();
				String x2 = scanner.next();
				String x3 = scanner.next();

				BigInteger bix1 = new BigInteger(x1);
				BigInteger bix2 = new BigInteger(x2);
				BigInteger bix3 = new BigInteger(x3);
				BigInteger bix4 = bix2.add(bix3).subtract(one);
				BigInteger bix5 = bix1.subtract(bix2);

				QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger> t3 = new QuadTupleBig<>(bix1, bix2, bix4, bix5);
				QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger> t3gaps = new QuadTupleBig<>(bix1, bix2.subtract(one), bix4.add(one), bix5);
				t1.add(t3);
				t1gaps.add(t3gaps);
			}
		}
		Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> t3 =
			new Vector <>(t1);
		t3.sort(Comparator.comparing(tuple -> tuple.second));
		tre.add(t3);
		t1.clear();
		Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> t3gaps =
			new Vector <>(t1gaps);
		t3gaps.sort(Comparator.comparing(tuple -> tuple.second));
		tregaps.add(t3gaps);
		t1.clear();

		/*
		   BigInteger minLocation = new BigInteger("99999999999");
		   out.print("seeds: "); out.println(seedsVe.size());
		   BigInteger orignumnums = zero;
		   for (int ii = 0; ii < seedsVe.size(); ii++) 
		   out.print("seeds: ii: "); out.println(ii);
		   var tb4 = seedsVe.get(ii);
		//Vector seedsfromto
		//   BigInteger range = seedsVe.get(ii+1);
		//  Vector <BigInteger> val = new Vector <>();
		// val.insert(value);
		//val.insert(value.add(range));
		//for (int kk = tb4.first; kk <= tb4.second; kk++) 
		BigInteger start111 = tb4.first;
		BigInteger end111 = tb4.second;
		out.print("seeds: "); out.print(start111); out.print(" "); out.println(end111);
		*/


		BigInteger minLocation = new BigInteger("99999999999");
		out.print("seeds: "); out.println(seedsVe.size());
		BigInteger orignumnums = zero;
		for (int ii = 0; ii < seedsVe.size(); ii++) {
			out.print("seeds: ii: "); out.println(ii);
			var tb4 = seedsVe.get(ii);
			//Vector seedsfromto
			/*
			   BigInteger range = seedsVe.get(ii+1);
			   Vector <BigInteger> val = new Vector <>();
			   val.insert(value);
			   val.insert(value.add(range));
			   */
			//for (int kk = tb4.first; kk <= tb4.second; kk++) 
			BigInteger start111 = tb4.first;
			BigInteger end111 = tb4.second;
			out.print("seeds: "); out.print(start111); out.print(" "); out.println(end111);

			//Vector <Vector<TreTupleBig <BigInteger, BigInteger, BigInteger>> newVec = new Vector<>();
			Vector<TupleBig<BigInteger, BigInteger>> newVec = new Vector<>();
			TupleBig<BigInteger, BigInteger> vbig1 = new TupleBig<>(start111, end111);
			newVec.add(vbig1);
			out.print("tre.size(); "); out.println(tre.size());
			orignumnums = end111.subtract(start111).add(one);

			for (int jj = 0; jj < tre.size(); jj++) { //loops tru from soil -> location
				out.print("tre: "); out.println(jj);
				Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> ve2 = tre.get(jj);
				Vector <QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger>> ve2gaps = tregaps.get(jj);

				assert(tre.size() == tregaps.size());
				//Vector <Vector<TupleBig <BigInteger, BigInteger>> newVecTmp = new Vector<>();
				//Vector<TupleBig <BigInteger, BigInteger>> newVecTmp = new Vector<>();
				Vector<TupleBig<BigInteger, BigInteger>> vecTmp = new Vector<>();
				out.print("qqq (newVec) size:"); out.println(newVec.size());
				out.print("newVec.size is "); out.println(newVec.size());

				out.println("------------------NEW VEC----------------------");
				BigInteger sizesize = one;
				for (int qqq = 0; qqq < newVec.size(); qqq++) {
					out.println("       ----- new group...");
					var t55 = newVec.get(qqq);
					BigInteger value = t55.first;
					BigInteger end   = t55.second;
					sizesize = end.subtract(value).add(one);
					Vector<TupleBig<BigInteger, BigInteger>> vecTmp22 = new Vector<>();
					out.print("qqqlevel: "); out.print(qqq); out.print(" of "); out.println(newVec.size());
					out.print("value: "); out.print(value); out.print(" end: "); out.println(end);
					//Scanner scanner = new Scanner(System.in); scanner.nextLine();

					for (int kk = 0; kk < ve2.size(); kk++) { //loop tru all the poss of that level
						QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger> t4 = ve2.get(kk);

						out.print("look: "); out.print(t4.first); out.print(" "); out.print(t4.second); out.print(" ");
						out.print(t4.third); out.print(" "); out.println(t4.fourth);
						out.print(value); out.print(" V "); out.print(t4.second); out.print(" "); 
						out.print(end); out.print(" V "); out.println(t4.third);
						if (value.compareTo(t4.second) <= 0 && (end.compareTo(t4.second) >= 0) && end.compareTo(t4.third) <= 0) {
							out.println("overlap1 "); out.print(t4.second); out.print(" "); out.println(end);

							TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(t4.first, end.add(t4.fourth));
							out.print("to:: "); out.print(t4.first); out.print(" "); out.println(end.add(t4.fourth));
							vecTmp.add(xx);
							vecTmp22.add(xx);
						} else if (value.compareTo(t4.second) <= 0 && end.compareTo(t4.third) >= 0) {
							out.println("overlap2:: "); out.print(t4.second); out.print(" "); out.println(t4.third);
							TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(t4.first, t4.third.add(t4.fourth));
							out.print("to:: "); out.print(t4.first); out.print(" "); out.println(t4.third.add(t4.fourth));
							vecTmp.add(xx);
							vecTmp22.add(xx);
						} else if (value.compareTo(t4.second) >= 0 && end.compareTo(t4.third) <= 0) {
							out.println("overlap3: "); out.print(value); out.print(" "); out.println(end);
							TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(value.add(t4.fourth), end.add(t4.fourth));
							out.print("to:: "); out.print(value.add(t4.fourth)); out.print(" "); out.println(end.add(t4.fourth));
							vecTmp.add(xx);
							vecTmp22.add(xx);
						} else if ((value.compareTo(t4.second) >= 0 && value.compareTo(t4.third) <= 0) && end.compareTo(t4.third) >= 0) {
							out.println("overlap4:: "); out.print(value); out.print(" "); out.println(t4.third);

							TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(value.add(t4.fourth), t4.third.add(t4.fourth));
							out.print("to:: "); out.print(value.add(t4.fourth)); out.print(" "); out.println(t4.third.add(t4.fourth));
							vecTmp.add(xx);
							vecTmp22.add(xx);

						} else {
							out.println("		no overlap");
						}

						
					}

					out.println("now the straight translation---");

					QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger> prev = new QuadTupleBig<>(zero, zero, zero, zero);
					out.println("prev of last to start of next");
					assert(ve2gaps.size() == ve2.size());
					for (int kk = 0; kk < ve2gaps.size(); kk++) {
						QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger> t4 = ve2gaps.get(kk);
						QuadTupleBig <BigInteger, BigInteger, BigInteger, BigInteger> t45 = ve2.get(kk);
						assert (t4.first.compareTo(t45.first) == 0);
						assert (t4.fourth.compareTo(t45.fourth) == 0);
						assert (t4.second.compareTo(t45.second.subtract(one)) == 0);
						assert (t4.third.compareTo(t45.third.add(one)) == 0);
						out.print(value); out.print(" V "); out.print(prev.third); out.print(" "); 
						out.print(end); out.print(" V "); out.println(t4.second);

						if (t4.second.subtract(prev.third).compareTo(one) > 0) { //gap larger than 1
							if (value.compareTo(prev.third) <= 0 && end.compareTo(t4.second) >= 0) {
								out.println("direc trans 1");

								TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(prev.third, t4.second);
								out.print("ov:: "); out.print(prev.third); out.print(" "); out.println(t4.second);

								vecTmp.add(xx);
								vecTmp22.add(xx);
							} else if (value.compareTo(prev.third) <= 0 && end.compareTo(prev.third) >= 0 && end.compareTo(t4.second) <= 0 ) {
								out.println("direc trans 2");
								TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(prev.third, end);
								out.print("ov:: "); out.print(prev.third); out.print(" "); out.println(end);
								vecTmp.add(xx);
								vecTmp22.add(xx);
							} else if (value.compareTo(prev.third) >= 0 && value.compareTo(t4.second) <= 0 && end.compareTo(t4.second) >= 0) {
								out.println("direct trans 3");
								TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(value, t4.second);
								vecTmp.add(xx);
								vecTmp22.add(xx);
								out.print("ov: "); out.print(value); out.print(" "); out.println(t4.second);
							} else if (value.compareTo(prev.third) >= 0 && end.compareTo(t4.second) <= 0) {
								out.println("direct trans 4");
								TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(value, end);
								out.print("ov: "); out.print(value); out.print(" "); out.println(end);
								vecTmp.add(xx);
								vecTmp22.add(xx);
							} else {
								out.println("no diect. (norm)..");
							}
						} else {
							out.println("no gap...");
							//out.println(prev.third); out.println(t4.second); out.println(prev.third.subtract(t4.second));
							//out.println("----------");
						}

						
						if (kk == ve2gaps.size()-1) {
							out.println("direct trans 5");
							out.print("after: "); out.println(t4.third);
							if (value.compareTo(t4.third) >= 0 && end.compareTo(t4.third)  >= 0) {
								out.println("yes trans 5 part1");
								TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(value, end);
								vecTmp.add(xx);
								vecTmp22.add(xx);

								out.print("ov: "); out.print(value); out.print(" "); out.println(end);
							} else if (value.compareTo(t4.third) <= 0 && end.compareTo(t4.third) >= 0) {
								out.println("yes trans 5 part2");
								TupleBig <BigInteger, BigInteger> xx = new TupleBig<>(t4.third, end);
								out.print("ov:: "); out.print(t4.third); out.print(" "); out.println(end);
								vecTmp.add(xx);
								vecTmp22.add(xx);
							} else {
								out.println("no direct trans 5");
							}
						}

						prev = t4;
					}
					BigInteger size2 = zero;
					for (int zz = 0; zz < vecTmp22.size(); zz++) {
						TupleBig<BigInteger, BigInteger> tb22 = vecTmp22.get(zz);
						out.print(tb22.first); out.print(" "); out.println(tb22.second);
						BigInteger xtmp = tb22.second.subtract(tb22.first).add(one);
						size2 = xtmp.add(size2); 
					}
					out.print(size2); out.print(" <-new orig-> "); out.println(sizesize);
					assert(size2.compareTo(sizesize) == 0);
					vecTmp22.clear();
				}
				vecTmp.sort(Comparator.comparing(tuple -> tuple.second));
				Vector<TupleBig<BigInteger, BigInteger>> v3 = new Vector<>(vecTmp);
				vecTmp.clear();
				newVec = v3;
				BigInteger numnums = zero;
				for (int zz = 0; zz < newVec.size(); zz++) {
					TupleBig<BigInteger, BigInteger> tb1 = newVec.get(zz);
					out.print("from to "); out.print(tb1.first); out.print(" "); out.println(tb1.second);
					BigInteger xtmp = tb1.second.subtract(tb1.first).add(one);
					numnums = xtmp.add(numnums); 
					assert (tb1.first.compareTo(tb1.second) <= 0);
				}
				out.print(numnums); out.print(" <-gen original-> "); out.println(orignumnums);
				assert(numnums.compareTo(orignumnums) == 0);
				//value = value;
			}
			//out.print(value); out.print(" ");
			//out.println(value);
			//if (value < minLocation) {minLocation = value;}
			//if (value.compareTo(minLocation) == -1) {minLocation = value;}
			for (int zz = 0; zz < newVec.size(); zz++) {
				TupleBig<BigInteger, BigInteger> tb1 = newVec.get(zz);
				assert (tb1.first.compareTo(tb1.second) <= 0);
				if (tb1.first.compareTo(zero) != 0 && tb1.first.compareTo(minLocation) < 0) {minLocation = tb1.first;}
				if (tb1.second.compareTo(zero) != 0 && tb1.second.compareTo(minLocation) < 0) {minLocation = tb1.second;}

			}
			//write vec
		}
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(minLocation);
		out.println("");
	}
}

class QuadTupleBig<X,Y, Z, W> {
	public final X first;
	public final Y second;
	public final Z third;
	public final W fourth;

	public QuadTupleBig(X first, Y second, Z third, W fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}
	@Override
	public boolean equals(Object o) {
		QuadTupleBig tu2 = (QuadTupleBig) o;
		if (this == o) return true;
		if (!(o instanceof QuadTupleBig)) return false;

		//BigInteger fir1 = ()first;
		/*
		   int fir2 = (int)tu2.first;
		   int sec1 = (int)second;
		   int sec2 = (int)tu2.second;
		   int thi1 = (int)third;
		   int thi2 = (int)tu2.third;
		   */
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
class TreTupleBig<X,Y, Z> {
	public final X first;
	public final Y second;
	public final Z third;

	public TreTupleBig(X first, Y second, Z third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
	@Override
	public boolean equals(Object o) {
		TreTupleBig tu2 = (TreTupleBig) o;
		if (this == o) return true;
		if (!(o instanceof TreTupleBig)) return false;

		//BigInteger fir1 = ()first;
		/*
		   int fir2 = (int)tu2.first;
		   int sec1 = (int)second;
		   int sec2 = (int)tu2.second;
		   int thi1 = (int)third;
		   int thi2 = (int)tu2.third;
		   */
		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		if (!third.equals(tu2.third)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}
class TreTuple<X,Y, Z> {
	public final X first;
	public final Y second;
	public final Z third;

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
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		int thi1 = (int)third;
		int thi2 = (int)tu2.third;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		if (thi1 != thi2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

}

class TupleBig<X,Y > {
	public final X first;
	public final Y second;

	public TupleBig(X first, Y second) {
		this.first = first;
		this.second = second;
	}
	@Override
	public boolean equals(Object o) {
		TupleBig tu2 = (TupleBig) o;
		if (this == o) return true;
		if (!(o instanceof TupleBig)) return false;
		if (!first.equals(tu2.first)) {return false;}
		if (!second.equals(tu2.second)) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
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
		int fir1 = (int)first;
		int fir2 = (int)tu2.first;
		int sec1 = (int)second;
		int sec2 = (int)tu2.second;
		if (fir1 != fir2) {return false;}
		if (sec1 != sec2) {return false;}
		return true;
	}
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

}

