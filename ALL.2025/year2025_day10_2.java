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

	public static Vector <int[]> possibles = new Vector<>();
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int level = 0;
	//public static int minScore;
	public static Map<String, Vector <int[]>> already;
	public static Map<String, Integer> alreadyScores;
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
		Pattern p = Pattern.compile("\\[([\\.#]+)\\] ([\\(\\)\\d, ]+) \\{([\\d,]+)\\}");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");

		Vector <String> lights = new Vector<>();
		Vector <Vector <Vector <Integer>>> toggles = new Vector<>();
		Vector <Vector <Integer>> jolt = new Vector<>();

		for (int i = 0, nn = blah.size(); i < nn; i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			lights.add(m.group(1));
			//out.println(m.group(2));
			//out.println(m.group(3));
			Scanner scanner = new Scanner(m.group(3));
			scanner.useDelimiter("[,]");
			Vector vints = new Vector<>();
			while (scanner.hasNext()) {
				String ne4 = scanner.next();
				vints.add(Integer.valueOf(ne4));
			}
			jolt.add(vints);

			scanner = new Scanner(m.group(2));
			//out.println(m.group(2));
			scanner.useDelimiter("[ ]+");
			Vector <Vector <Integer>> togglesPer = new Vector<>();
			while (scanner.hasNext()) {
				String ne = scanner.next();
				//out.println("ne: [" + ne + "]");
				StringBuffer sb = new StringBuffer(ne);
				sb.deleteCharAt(0);
				sb.deleteCharAt(sb.length() -1);

				String ne2 = sb.toString();
				Scanner scanner2 = new Scanner(ne2);
				scanner2.useDelimiter("[,]");
				Vector <Integer> var_ints = new Vector<>();

				while (scanner2.hasNext()) {
					String ne3 = scanner2.next();
					//out.println(ne3);
					var_ints.add(Integer.valueOf(ne3));
				}
				//out.println(var_ints.size());
				togglesPer.add(var_ints);


			}
			toggles.add(togglesPer);

		}

		long sum = 0;

		int minScore = 0;
		Vector<Integer> slow = new Vector();
		//slow.add(12);
		slow.add(25);
		slow.add(33);
		slow.add(60);
		slow.add(171);
		slow.add(172);
		slow.add(144);

		//181
		for (int ii = 0, n = blah.size(); ii < n; ii++) {
			Vector <Vector<Integer>> ve = toggles.get(ii);
			/*
			if (ii == 0 || ii == 1 || ii == 2) {
			} else if (!slow.contains(ii)) {
				//out.println("	** ignore slow");
				continue;
			}
			*/
			//out.print("trying: " + ii + " of (" + n + "): min: ");
			int len = ve.size(); 
			Vector <Integer> jo = jolt.get(ii);

			//long start = System.nanoTime();
			already = new HashMap();
			alreadyScores = new HashMap();
			solveAll(ve, jo.size());
			minScore = outsideSolve(jo, ve);
			if (minScore > 9999) {
				out.println("**************** ERR " + ii);
				continue;
				//Runtime.getRuntime().halt(0);
			}
			int wh = ii+1;
			out.println("Line " + wh + "/" + n + ": answer " + minScore);
			//long end = System.nanoTime();
			//long elapsed = end - start;
			//out.println("time: " + elapsed + "ms");
			sum+= minScore;

			//out.println(minScore);

		}
		/*
		for (int kk = 0; kk < toobig.size(); kk++) {
			out.println("toobig.add(" + toobig.get(kk) + ");");
		}
		*/
		out.println("slow: " + slow);

		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(sum);
		out.println("");
	}
	public static Vector<Integer> halve(Vector <Integer> jo) {
		Vector <Integer> jo2 = new Vector<>();
		for (int kk = 0, n44 = jo.size(); kk < n44; kk++) {
			if (jo.get(kk) == 0) {
				jo2.add(0);
			} else {
				jo2.add(jo.get(kk) / 2);
			}
		}
		return jo2;
	}

	public static String toHashAndDot(Vector <Integer> jo) {
		Vector <Integer> jo3 = new Vector<>();
		StringBuffer xx = new StringBuffer();
		for (int bb = 0, n33 = jo.size(); bb < n33; bb++) {
			if (jo.get(bb) % 2 == 0) {
				xx.append(".");
				jo3.add(jo.get(bb));
			} else {
				xx.append("#");
				jo3.add(jo.get(bb)-1);
			}
		}
		String toget = xx.toString();
		return toget;
	}
	public static int outsideSolve(Vector <Integer> jo, Vector<Vector<Integer>> ve) {
		level++;
		if (!checkValid(jo)) {
			level--;
			return 999999;
		} else {
			if (checkAllZeroes(jo)) {
				level--;
				return 0;
			} else {
			}
		}

		String toget = toHashAndDot(jo);
		///GOOD out.println("toget: " + toget);
		possibles.clear();
		Vector <int[]> poss1 = new Vector();
		//if (alreadyScores.containsKey(toget)) {level--; return alreadyScores.get(toget);}
		if (already.containsKey(toget)) {
			int le = ve.size();
			var xx3 = already.get(toget);
			for (int kkk = 0, n22 = xx3.size(); kkk < n22; kkk++) {
				poss1.add(xx3.get(kkk).clone());
			}
		} else {
			return 99999;
			/*
			out.println("looking for: " + toget);
			out.println("oops Err");
			solve(toget, ve);
			out.println("solve: " + possibles.size());
			Runtime.getRuntime().halt(0);
			*/
			/*
			solve(toget, ve);
			for (int kkk = 0, n22 = possibles.size(); kkk < n22; kkk++) {
				poss1.add(possibles.get(kkk).clone());
			}
			*/
		}
		/*
		solve(toget, ve);
		for (int kkk = 0, n22 = possibles.size(); kkk < n22; kkk++) {
			poss1.add(possibles.get(kkk).clone());
		}
		*/

		//int le = ve.size();
		/*
		for (int kkk = 0, n22 = possibles.size(); kkk < n22; kkk++) {
			//poss1.add(Arrays.copyOf(possibles.get(kkk), le));
			poss1.add(possibles.get(kkk).clone());
			// GOOD out.print(Arrays.toString(possibles.get(kkk)) + " ");
		}
		*/
		/*
		if (poss1.size() == 0) {
			out.println("**POSS ZERO level:" + level);
		}
		*/
		//out.println(toget + " " + "poss: " + poss1.size());

		//out.println("jo.size() : " + jo.size());
		//out.println("joOrig: " + joOrig);
		//out.println("-----------");
		///for (int ppp = 0; ppp < level; ppp++) {
		//	out.print("\t");
		//}
		//out.println("num possibles (" + poss1.size() + "):");
		Vector <Integer> scores = new Vector<>();
		Vector<Vector <Integer>> joAlready = new Vector<>();
		Vector <Integer> joOrig = new Vector(jo);
		for (int zzz = 0, n44 = poss1.size(); zzz < n44; zzz++) {
			/*
			for (int ppp = 0; ppp < level; ppp++) {
				out.print("\t");
			}
			*/
			jo = new Vector(joOrig);
			//out.println("poss: " + zzz + " leve: " + level + " jo: " + jo);
			//for (int ppp = 0; ppp < level; ppp++) {
			//	out.print("\t");
			//} 
			///out.println("poss " + zzz + " of (" + poss1.size()+")");
			//out.println(Arrays.toString(possibles.get(zzz)));

			//out.println("jo before buttons pressed: " + jo);
			int buttonPresses = 0;
			for (int qq = 0; qq < poss1.get(zzz).length; qq++) {
				if (poss1.get(zzz)[qq] == 0) {
					//out.println("no press of button" + qq);
					//no press of this button
				} else {
					//out.println("pressing button: " + qq);
					buttonPresses++;
					var xx2 = ve.get(qq);
					//out.println("button qq: " + xx2);
					//out.println("ve pressed: (button): " + qq);
					for (int xx3 = 0; xx3 < xx2.size(); xx3++) {
						int newVal = (int)jo.get(xx2.get(xx3)) -1;
						jo.set(xx2.get(xx3), newVal);
					}
					//out.println();
				}
			}
			jo = halve(jo);
			if (!joAlready.contains(jo.clone())) {
				joAlready.add(jo);
				int ret3 = outsideSolve(jo, ve);
				int sc = buttonPresses + 2*ret3;
				scores.add(sc);
				//REALLY GOOD DEBUG
				/*
				 for (int kk = 0; kk < level; kk++) {
				   out.print("\t");
				   }
				   out.println(jo + " sc: " + sc);
				   */
			}
		} 
		level--;
		if (scores.size() == 0) {
			//out.println("\t\tscores size 0");
			//alreadyScores.put(toget, 99999); 
			return 99999;
		}

		//alreadyScores.put(toget, Collections.min(scores));
		return Collections.min(scores);

	}
	static boolean checkAllZeroes(Vector<Integer> jo) {
		for (int cc = 0, n56 = jo.size(); cc < n56; cc++) {
			if (jo.get(cc) != 0) {
				return false;
			}
		}
		return true;
	}

	static boolean checkValid(Vector <Integer> jo) {
		for (int cc = 0, n56 = jo.size(); cc < n56; cc++) {
			if (jo.get(cc) < 0) {
				return false;
			}
		}
		return true;
	}

	static void solveAll(Vector<Vector<Integer>> ve, int sz) {
		String lightsStart = ".".repeat(sz);

		int len = ve.size();
		for (int end = 0; end <= len; end++) {
			int arr[] = new int[len];
			for (int i = 0; i < end; i++) {
				arr[i] = 1;
			}
			for (int i = end; i < len; i++) {	
				arr[i] = 0;
			}
			Arrays.sort(arr);
			int arrOrig[] = new int[len];
			arrOrig = Arrays.copyOf(arr, len);

			do {
				//out.println(Arrays.toString(arr) + " end: " + end + " len: " + len);
				StringBuffer li = new StringBuffer(lightsStart);
				for (int i = 0; i < len; i++) {
					if (arr[i] == 1) {
						//press its buttons
						//out.println("press button: " + i);
						var ins = ve.get(i);
						for (int kk = 0, n3 = ins.size(); kk < n3; kk++) {
							int qq = ins.get(kk);
							if (li.charAt(qq) == '.') {
								li.setCharAt(qq, '#');
							} else {
								li.setCharAt(qq, '.');
							}
						}

					}
				}
				//possibles.add(arr.clone());
				String got = li.toString();
				if (already.get(got) == null) {
					Vector <int[]> oneAdd = new Vector<>();
					oneAdd.add(arr.clone());
					already.put(got, oneAdd);
				} else {
					Vector <int[]> oldAdd = new Vector<>(already.get(got));
					oldAdd.add(arr.clone());
					already.put(got, oldAdd);
				}

			} while (nextPermutation(arr) && !Arrays.equals(arr, arrOrig));
		}
		return;

	}
	static void solve(String toget, Vector<Vector<Integer>> ve) {
		String lightsStart = ".".repeat(toget.length());

		int len = ve.size();
		for (int end = 0; end <= len; end++) {
			int arr[] = new int[len];
			for (int i = 0; i < end; i++) {
				arr[i] = 1;
			}
			for (int i = end; i < len; i++) {	
				arr[i] = 0;
			}
			Arrays.sort(arr);
			int arrOrig[] = new int[len];
			arrOrig = Arrays.copyOf(arr, len);

			do {
				//out.println(Arrays.toString(arr) + " end: " + end + " len: " + len);
				StringBuffer li = new StringBuffer(lightsStart);
				for (int i = 0; i < len; i++) {
					if (arr[i] == 1) {
						//press its buttons
						//out.println("press button: " + i);
						var ins = ve.get(i);
						for (int kk = 0, n3 = ins.size(); kk < n3; kk++) {
							int qq = ins.get(kk);
							if (li.charAt(qq) == '.') {
								li.setCharAt(qq, '#');
							} else {
								li.setCharAt(qq, '.');
							}
						}

					}
				}
				//possibles.add(arr.clone());

				//out.println("-----");
				if (toget.equals(li.toString())) {
					//out.println("***got 1...");
					possibles.add(arr.clone());
				}
			} while (nextPermutation(arr) && !Arrays.equals(arr, arrOrig));
		}
		//already.put(toget, (Vector<int[]>)possibles.clone());
		return;

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

