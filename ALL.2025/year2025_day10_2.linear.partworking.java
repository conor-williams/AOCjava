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
import java.util.Comparator;



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
class year2025_day10_2 {
	//	        public static int maxPath = 0;
	//    public static int lenx = 0;
	//   public static int leny = 0;
	//  public static char grid [][] = new char[leny][lenx];
	//    public static int already [][] = new int[leny][lenx];

	public static Vector <Integer> jo;
	public static int[] me;
	public static Vector <int []> possibles[];
	public static Vector <Integer> coMax;
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2025 Day10.2");
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
		Pattern p = Pattern.compile("\\[([\\.#]+)\\] ([\\(\\)\\d, ]+) \\{([\\d,]+)\\}");

		Vector <String> lights = new Vector<>();
		Vector <Vector <Vector <Integer>>> toggles = new Vector<>();
		Vector <Vector <Integer>> jolt = new Vector<>();
		Vector <Integer> maxes = new Vector<>();

		for (int i = 0, nn = blah.size(); i < nn; i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			lights.add(m.group(1));
			Scanner scanner = new Scanner(m.group(3));
			scanner.useDelimiter("[,]");
			Vector vints = new Vector<>();
			while (scanner.hasNext()) {
				String ne4 = scanner.next();
				vints.add(Integer.valueOf(ne4));
			}
			int maxV = (int)Collections.max(vints);
			//out.println("maxV: " + maxV);
			maxes.add(maxV);
			jolt.add(vints);

			//group 2
			scanner = new Scanner(m.group(2));
			//out.println(m.group(2));
			scanner.useDelimiter("[ ]+");
			Vector <Vector <Integer>> togglesPer = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				StringBuffer sb = new StringBuffer(ne);
				sb.deleteCharAt(0);
				sb.deleteCharAt(sb.length() -1);

				String ne2 = sb.toString();
				Scanner scanner2 = new Scanner(ne2);
				scanner2.useDelimiter("[,]");
				Vector <Integer> var_ints = new Vector<>();

				while (scanner2.hasNext()) {
					String ne3 = scanner2.next();
					var_ints.add(Integer.valueOf(ne3));
				}
				//out.println(var_ints.size());
				togglesPer.add(var_ints);
				/*
				   for (int qqq = 0; qqq < maxV; qqq++) {
				   togglesPer.add(var_ints);
				   }
				   */
			}
			//out.println("size: " + togglesPer.size());

			toggles.add(togglesPer);

		}

		int sum = 0;
		Vector <Integer> sizes = new Vector<>();
		long sum5 = 0;
		int validSystems = 0;
		Vector <Integer> ignore = new Vector<>();
		ignore.add(0);
		ignore.add(1);
		ignore.add(8);
		ignore.add(13);
		ignore.add(26);
		ignore.add(44);
		/*
		ignore.add(14);
		ignore.add(56);
		ignore.add(60);
		ignore.add(87);
		ignore.add(156);
		ignore.add(164);
		ignore.add(173);
		ignore.add(177);
		*/
next:
		for (int ii = 0, n = blah.size(); ii < n; ii++) {
			out.print("looking @ system: " + ii + " of (" + n + " - 1) --- ");
			Vector <Vector<Integer>> ve = toggles.get(ii);
			///if (ignore.contains(ii)) {out.println("\tignore"); continue next;}
			int len = ve.size();
			//if (len != 6) {continue;}
			//if (ii != 60) {continue;}
			if (sizes.contains(len)) {
			} else {
				sizes.add(len);
			}
			Vector<Integer> joltStart = new Vector<>();
			for (int rrr = 0, n5 = lights.get(ii).length(); rrr < n5; rrr++) {
				joltStart.add(0);
			}
			Vector <Integer> tiStart = new Vector<>();
			for (int ddd = 0, n9 = ve.size(); ddd < n9; ddd++) {
				tiStart.add(0);
			}
			jo = new Vector(jolt.get(ii));
			int co[] = new int[len];

			out.print(" eqns: (" + jo.size() + ") buttons: (" + ve.size() + ") ");
			if (ignore.contains(ii)) {out.println("\t***ignore"); continue next;}
			Vector<Vector <Integer>> cos = new Vector<>();
			Vector <Integer> eqs = new Vector<>();
			for (int qq = 0, n22 = jo.size(); qq < n22; qq++) {
				eqs.add(jo.get(qq));
				Vector <Integer> coOne = new Vector<>();
				for (int zz = 0; zz < len; zz++) {//()
					if (ve.get(zz).contains(qq)) {
						coOne.add(zz);
					}

				}
				cos.add(coOne);
			}

			coMax = new Vector<>();
			
			for (int rr = 0; rr < ve.size(); rr++) {
				Vector <Integer> pos = new Vector<>();
				var x1 = ve.get(rr);
				for (int yy = 0 ; yy < x1.size(); yy++) {
					pos.add(x1.get(yy));
				}
				int minM = 9999;
				for (int aaa = 0; aaa < pos.size(); aaa++) {
					if (jo.get(pos.get(aaa)) < minM) {minM = jo.get(pos.get(aaa));}
				}
				coMax.add(minM);

			}
			
			boolean hasChanged = false;
aft66:
			for (int kkk = 0; kkk < cos.size()-1; kkk++) {//cos is buttons per equation
				var fir = cos.get(kkk);
aft55:
				for (int rrr = kkk+1; rrr < cos.size(); rrr++) {
					var sec = cos.get(rrr);
					if (fir.size() == sec.size()) {
						continue;
					} else if (fir.size() > sec.size()) {
						for (int zz = 0; zz < sec.size(); zz++) {
							if (fir.contains(sec.get(zz))) {
								//ok
							} else {
								continue aft55;
							}
						}
						//all sec in fir -- change eqn
						//var fir2 = new Vector(fir);
						for (int zz = 0; zz < sec.size(); zz++) {
							fir.remove(fir.indexOf(sec.get(zz)));
						}

						//fir = new Vector(fir2);
						cos.set(kkk, fir);
						jo.set(kkk, (jo.get(kkk) - jo.get(rrr)));
						kkk = -1;
						hasChanged = true;
						continue aft66;
					} else if (sec.size() > fir.size()) {
						for (int zz = 0; zz < fir.size(); zz++) {
							if (sec.contains(fir.get(zz))) {
								//ok
							} else {
								continue aft55;
							}
						}
						//var sec2 = new Vector(sec);
						for (int zz = 0; zz < fir.size(); zz++) {
							sec.remove(sec.indexOf(fir.get(zz)));
						}
						//sec = new Vector(sec2);
						cos.set(rrr, sec);
						jo.set(rrr, (jo.get(rrr) - jo.get(kkk)));
						kkk = -1;
						hasChanged = true;
						continue aft66;
					}
				}
			}

			/*
			for (int rr = 0, n55 = cos.size(); rr < n55; rr++) {
				var xx1 = cos.get(rr);
				int minM = 9999;
				for (var item: xx1) {
					out.print(item  + " ");
					if (jo.get(item) < minM) {minM = jo.get(item);}
				}
			}
			*/
			/*
			if (hasChanged) {
				out.println();
				out.println("\t\tsystem now: ");
				out.println(blah.get(ii));
				for (int kkk = 0; kkk < cos.size(); kkk++) {
					var gg = cos.get(kkk);
					for (var item: gg) {
						out.print(item + " + ");
					}
					out.println(" == " + jo.get(kkk));
				}
				out.println("\t\t--------");
			}
			*/

			if (true) { //gaussian prepare
				double [] b = new double[jo.size()];
				for (int fff = 0, n66 = jo.size(); fff < n66; fff++) {
					b[fff] = (double)jo.get(fff);
				}
				//out.println("y: jo.size: " + jo.size());
				//out.println("x: ve.size: " + ve.size());
				//out.println("cos.size: " + jo.size());
				double [][] A = new double[jo.size()][ve.size()];
				for (int ggg = 0, n77 = cos.size(); ggg < n77; ggg++) {
					var x22 = cos.get(ggg);
					for (int hhh = 0, n88 = ve.size(); hhh < n88; hhh++) {
						A[ggg][hhh] = 0.0;
					}
					for (int hhh = 0, n88 = x22.size(); hhh < n88; hhh++) {
						A[ggg][x22.get(hhh)] = 1.0;
					}
				}

				//out.println("calling solve...");
				//double [] c = gaussianSolve(A, b);

				//Vector <int []> possibles[];
				possibles = new Vector[jo.size()];
				for (int ppp = 0, n66 = jo.size(); ppp < n66; ppp++) {
					possibles[ppp] = new Vector<>();
				}
				for (int numEqn = 0, n555 = jo.size(); numEqn < n555; numEqn++) {
					var xxg = cos.get(numEqn);
					if (xxg.size() == 1) {
						forit1(xxg, jo.get(numEqn), ve.size(), numEqn);
					} else if (xxg.size() == 2) {
						forit2(xxg, jo.get(numEqn), ve.size(), numEqn);
					} else if (xxg.size() == 3) {
						forit3(xxg, jo.get(numEqn), ve.size(), numEqn);
					} else if (xxg.size() == 4) {
						forit4(xxg, jo.get(numEqn), ve.size(), numEqn);
					} else if (xxg.size() == 5) {
						forit5(xxg, jo.get(numEqn), ve.size(), numEqn);
					} else if (xxg.size() == 6) {
						//out.println("\tsix: " + "skip");
						out.print("\t(six) ");
						out.flush();
						forit6(xxg, jo.get(numEqn), ve.size(), numEqn);
					} else {
						out.println("\tsix+: " + "skip");
						continue next;
						//out.println("ERR6+ not handled"); Runtime.getRuntime().halt(0);
					}


					//out.println(cos.get(numEqn) );

					//Scanner scanner = new Scanner(System.in); scanner.nextLine();
				}

				/*
				for (int qqq = 0; qqq < jo.size(); qqq++) {
					var xx2 = possibles[qqq];
					out.println("			possibles[" + qqq + "]");
					for (var item: xx2) {
						for (int qq = 0; qq < item.length; qq++) {
							out.print(item[qq] + " ");
						}
						out.println();
					}
				}
				out.print(" mer egns(" + jo.size() + ") but:(" + ve.size() +")");
				out.flush();
				*/
				long mi = mergePossibles(jo.size(), ve.size());
				out.println("\tmin: (" + mi + ")");
				sum5 += mi;
				validSystems++;


			}
			//Scanner scanner = new Scanner(System.in); scanner.nextLine();
			//out.println();

		}
		//out.println("sizes: " + sizes);

		out.println("validSystems: " + validSystems );
		out.println("invalidSystems: " + Integer.valueOf(blah.size()-validSystems));
		///System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(sum5);
		out.println("");
	}
	public static long mergePossibles(int numEqns, int numVars) {
		Vector <int []> merges = new Vector(possibles[0]);
		if (numEqns == 1) {
			return 99;
		} 

		for (int zz = 1; zz < numEqns; zz++) {
			Vector <int []> mergesTmp = new Vector<>();
			// merge two with merges;
			var two = new Vector(possibles[zz]);
			//out.println(merges.size() + " merges " + two.size());
			for (int fir = 0, n77 = merges.size(); fir < n77; fir++) {
				var firAr = merges.get(fir);
				/*
				   if (two.size() == 0) {
				   mergesTmp.add(firAr);
				   continue;
				   }
				   */
				for (int sec = 0, n88 = two.size(); sec < n88; sec++) {
					int[] secAr = (int[])two.get(sec);
					//out.println(secAr);
					if (mergeTwoArrays(firAr, secAr)) {
						mergesTmp.add(me.clone());
					}
				}
			}
			merges = new Vector(mergesTmp);
		}
		int min = 9999999;
		out.print(" merges: (" + merges.size() + ") ");
after33:
		for (int zz = 0, n999 = merges.size(); zz < n999; zz++) {
			var one = merges.get(zz);
			/*
			out.println("merge: " +  zz);
			for (int kk = 0; kk < one.length; kk++) {
				out.print(kk + " ");
			}
			out.println();
			*/
			int sum = 0;
			for (int vv = 0; vv < numVars; vv++) {
				if (one[vv] == -1) {continue after33;}
				sum += one[vv];
			}
			if (sum < min) {
				min = sum;
			}
		}
		return min;

	}
	public static boolean mergeTwoArrays(int [] one, int [] two) {
		me = new int[one.length];

		//out.println(one); out.println(two); out.println("--------");
		for (int kk = 0; kk < one.length; kk++) {
			int digOne = one[kk];
			int digTwo = two[kk];
			if (digOne == -1 && digTwo == -1) {
				me[kk] = -1;
			} else if (digOne == -1 && digTwo != -1) {
				me[kk] = digTwo;
			} else if (digOne != -1 && digTwo == -1) {
				me[kk] = digOne;
			} else if (digOne == digTwo) {
				me[kk] = digOne;
			} else {//not the same
				//quit
				/*
				   int meRet[] = int[one.length];
				   for (int zz = 0; zz < one.length; zz++) {
				   meRet[zz] = -1;
				   }
				   */
				return false;
			}
		}
		return true;
	}

	public static void forit1(Vector<Integer> parts, int answer, int numVars, int numEqn) {
		int [] ret1 = new int[numVars];
		for (int ii = 0; ii < numVars; ii++) {
			ret1[ii] = -1;
		}
		ret1[parts.get(0)] = answer;
		//Vector <int[]> ret2 = new Vector<>();
		//ret2.add(ret1);
		possibles[numEqn].add(ret1); 
	}
	public static void forit2(Vector<Integer> parts, int answer, int numVars, int numEqn) {
		//Vector <int[]> ret2 = new Vector<>();

		int co[] = new int[numVars];
		int fir = parts.get(0);
		int sec = parts.get(1);
		int minuses[] = new int[numVars];
		for (int ii = 0; ii < numVars; ii++) {
			minuses[ii] = -1;
		}
		//   out.println("co[" + fir + "] " + "co[" + sec + "]" + " == " + answer);
		//   out.println("max: " + coMax.get(fir));
		//   out.println("max: " + coMax.get(sec));

		int min1 = coMax.get(fir) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fir);
		int min2 = coMax.get(sec) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(sec);
		for (co[fir] = 0; co[fir] <= min1; co[fir]++) {
			for (co[sec] = 0; co[sec] <= min2; co[sec]++) {
				if (co[fir] + co[sec] == answer) {
					int one[] = minuses.clone();
					one[fir] = co[fir];
					one[sec] = co[sec];
					//ret2.add(one);
					possibles[numEqn].add(one.clone());
				}
			}
		}
		///possibles[numEqn].add(ret2);

	}
	public static void forit3(Vector<Integer> parts, int answer, int numVars, int numEqn) {
		int co[] = new int[numVars];
		int fir = parts.get(0);
		int sec = parts.get(1);
		int thi = parts.get(2);

		int minuses[] = new int[numVars];
		for (int ii = 0; ii < numVars; ii++) {
			minuses[ii] = -1;
		}

		int min1 = coMax.get(fir) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fir);
		int min2 = coMax.get(sec) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(sec);
		int min3 = coMax.get(thi) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(thi);
		for (co[fir] = 0; co[fir] <= min1; co[fir]++) {
			for (co[sec] = 0; co[sec] <= min2; co[sec]++) {
				if (co[fir] + co[sec] > answer) {break;}
				for (co[thi] = 0; co[thi] <= min3; co[thi]++) {
					if (co[fir] + co[sec] + co[thi] == answer) {
						int one[] = minuses.clone();
						one[fir] = co[fir];
						one[sec] = co[sec];
						one[thi] = co[thi];
						possibles[numEqn].add(one.clone());
						//ret2.add(one);
					}
				}
			}
		}
		//possibles[numEqn].add(ret2);

	}
	public static void forit4(Vector<Integer> parts, int answer, int numVars, int numEqn) {
		//Vector <int[]> ret2 = new Vector<>();

		int co[] = new int[numVars];
		int fir = parts.get(0);
		int sec = parts.get(1);
		int thi = parts.get(2);
		int fou = parts.get(3);
		int minuses[] = new int[numVars];
		for (int ii = 0; ii < numVars; ii++) {
			minuses[ii] = -1;
		}
		int min1 = coMax.get(fir) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fir);
		int min2 = coMax.get(sec) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(sec);
		int min3 = coMax.get(thi) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(thi);
		int min4 = coMax.get(fou) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fou);
		for (co[fir] = 0; co[fir] <= min1; co[fir]++) {
			for (co[sec] = 0; co[sec] <= min2; co[sec]++) {
				if (co[fir] + co[sec] > answer) {break;}
				for (co[thi] = 0; co[thi] <= min3; co[thi]++) {
					if (co[fir] + co[sec] + co[thi] > answer) {break;}
					for (co[fou] = 0; co[fou] <= min4; co[fou]++) {
						if (co[fir] + co[sec] + co[thi] + co[fou] == answer) {
							int one[] = minuses.clone();
							one[fir] = co[fir];
							one[sec] = co[sec];
							one[thi] = co[thi];
							one[fou] = co[fou];
							possibles[numEqn].add(one.clone());
							//ret2.add(one);
						}
					}
				}
			}
		}
		//possibles[numEqn].add(ret2);
	}
	public static void forit5(Vector<Integer> parts, int answer, int numVars, int numEqn) {
		//Vector <int[]> ret2 = new Vector<>();

		int co[] = new int[numVars];
		int fir = parts.get(0);
		int sec = parts.get(1);
		int thi = parts.get(2);
		int fou = parts.get(3);
		int fiv = parts.get(4);
		int minuses[] = new int[numVars];
		for (int ii = 0; ii < numVars; ii++) {
			minuses[ii] = -1;
		}
		int min1 = coMax.get(fir) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fir);
		int min2 = coMax.get(sec) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(sec);
		int min3 = coMax.get(thi) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(thi);
		int min4 = coMax.get(fou) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fou);
		int min5 = coMax.get(fiv) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fiv);
		for (co[fir] = 0; co[fir] <= min1; co[fir]++) {
			for (co[sec] = 0; co[sec] <= min2; co[sec]++) {
				if (co[fir] + co[sec] > answer) {break;}
				for (co[thi] = 0; co[thi] <= min3; co[thi]++) {
					if (co[fir] + co[sec] + co[thi] > answer) {break;}
					for (co[fou] = 0; co[fou] <= min4; co[fou]++) {
						if (co[fir] + co[sec] + co[thi] + co[fou] > answer) {break;}
						for (co[fiv] = 0; co[fiv] <= min5; co[fiv]++) {
							if (co[fir] + co[sec] + co[thi] + co[fou] + co[fiv] == answer) {
								int one[] = minuses.clone();
								one[fir] = co[fir];
								one[sec] = co[sec];
								one[thi] = co[thi];
								one[fou] = co[fou];
								one[fiv] = co[fiv];
								possibles[numEqn].add(one.clone());
								//ret2.add(one);
							}
						}
					}
				}
			}
		}
		//possibles[numEqn].add(ret2);
	}
	public static void forit6(Vector<Integer> parts, int answer, int numVars, int numEqn) {
		//Vector <int[]> ret2 = new Vector<>();

		int co[] = new int[numVars];
		int fir = parts.get(0);
		int sec = parts.get(1);
		int thi = parts.get(2);
		int fou = parts.get(3);
		int fiv = parts.get(4);
		int six = parts.get(5);
		int minuses[] = new int[numVars];
		for (int ii = 0; ii < numVars; ii++) {
			minuses[ii] = -1;
		}
		int min1 = coMax.get(fir) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fir);
		int min2 = coMax.get(sec) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(sec);
		int min3 = coMax.get(thi) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(thi);
		int min4 = coMax.get(fou) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fou);
		int min5 = coMax.get(fiv) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(fiv);
		int min6 = coMax.get(six) > jo.get(numEqn)? jo.get(numEqn) : coMax.get(six);
		for (co[fir] = 0; co[fir] <= min1; co[fir]++) {
			for (co[sec] = 0; co[sec] <= min2; co[sec]++) {
				if (co[fir] + co[sec] > answer) {break;}
				for (co[thi] = 0; co[thi] <= min3; co[thi]++) {
					if (co[fir] + co[sec] + co[thi] > answer) {break;}
					for (co[fou] = 0; co[fou] <= min4; co[fou]++) {
						if (co[fir] + co[sec] + co[thi] + co[fou] > answer) {break;}
						for (co[fiv] = 0; co[fiv] <= min5; co[fiv]++) {
							if (co[fir] + co[sec] + co[thi] + co[fou] + co[fiv] > answer) {break;}
							for (co[six] = 0; co[six] <= min6; co[six]++) {
								if (co[fir] + co[sec] + co[thi] + co[fou] + co[fiv] + co[six] == answer) {
									int one[] = minuses.clone();
									one[fir] = co[fir];
									one[sec] = co[sec];
									one[thi] = co[thi];
									one[fou] = co[fou];
									one[fiv] = co[fiv];
									one[six] = co[six];
									possibles[numEqn].add(one.clone());
									//ret2.add(one);
								}
							}
						}
					}
				}
			}
		}
		//possibles[numEqn].add(ret2);
	}



	public static boolean nextPermutation(int[] nums) {
		int i = nums.length - 2;
		while (i >= 0 && nums[i] >= nums[i + 1]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			swap(nums, i, j);
		}
		reverse(nums, i + 1);
		return true;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	private static void reverse(int[] nums, int start) {
		int end = nums.length - 1;
		while (start < end) {
			swap(nums, start, end);
			start++;
			end--;
		}
	}

	// Gaussian elimination with partial pivoting
	public static double[] gaussianSolve(double[][] A, double[] b) {
		int n = A.length;
		///int n = b.length;
		for (int yy = 0; yy < A.length; yy++) {
			for (int xx = 0; xx < A[0].length; xx++) {
				out.print(A[yy][xx] + " ");
			}
			out.println();
		}

		// Augment matrix A with vector b
		double[][] augmented = new double[n][A[0].length + 1];
		//double[][] augmented = new double[n][n + 1];
		for (int i = 0; i < n; i++) {
			System.arraycopy(A[i], 0, augmented[i], 0, A[0].length);
			augmented[i][A[0].length] = b[i];
		}

		// Forward elimination
		for (int i = 0; i < n; i++) {
			// Partial pivoting
			int maxRow = i;
			for (int k = i + 1; k < n; k++) {
				if (Math.abs(augmented[k][i]) > Math.abs(augmented[maxRow][i])) {
					maxRow = k;
				}
			}
			// Swap rows
			double[] temp = augmented[i];
			augmented[i] = augmented[maxRow];
			augmented[maxRow] = temp;

			// Check for zero pivot (no unique solution)
			if (Math.abs(augmented[i][i]) < 1e-10) {
				out.println("no unique....");
				for (int yy = 0; yy < A.length; yy++) {
					for (int xx = 0; xx < A[0].length+1; xx++) {
						out.print(augmented[yy][xx] + " ");
					}
					out.println();
				}

				double ret[] = new double[b.length];
				for (int zzz = 0; zzz < b.length; zzz++) {
					ret[zzz] = 0.0;
				}
				return ret;

				//throw new ArithmeticException("No unique solution exists.");
			}

			// Eliminate below
			for (int k = i + 1; k < n; k++) {
				double factor = augmented[k][i] / augmented[i][i];
				//for (int j = i; j <= n; j++) 
				for (int j = i; j <= A[0].length; j++) {
					augmented[k][j] -= factor * augmented[i][j];
				}
			}
		}

		// Back substitution
		double[] x = new double[n];
		for (int i = n - 1; i >= 0; i--) {
			double sum = augmented[i][n];
			for (int j = i + 1; j < n; j++) {
				sum -= augmented[i][j] * x[j];
			}
			x[i] = sum / augmented[i][i];
		}

		return x;
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

