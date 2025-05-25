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


//System.setOut(originalOut);
//PrintStream originalOut = System.out;
//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));
//Scanner scanner = new Scanner(System.in); scanner.nextLine();
class year2019_day7_2 {
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static void main(String [] args) {
		out.println("		2019 Day7.2");
		Vector<String> blah = new Vector<>();
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
			while ((line = br.readLine()) != null) {
				blah.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		Vector <Integer> vi = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			while (scanner.hasNext()) {
				String ne = scanner.next();
				vi.add(Integer.valueOf(ne));
			}
		}
		int [] phase = {5, 6, 7, 8, 9};
		//int [] phase = {9, 8, 7, 6, 5};
		int [] phaseOrig = {5, 6, 7, 8, 9};
		int [] input = new int[5];
		input[0] = 0;
		int qqq = 0;
		int maxWithPhase = 0;
		int outout = 0;
		Vector <Integer> viOrig = new Vector<>(vi);
		@SuppressWarnings("unchecked")
		Vector <Integer> [] viS = new Vector[5];

		int [] jj = new int[5];
		do {
			for (int ii = 0; ii < 5; ii++) {
				viS[ii] = new Vector<>(viOrig);
			}
			for (int ii = 1; ii < 5; ii++) {
				jj[ii] = -2;
			}
			jj[0] = 0;
			qqq = 0;
			nextPermutation(phase);
			out.println(Arrays.toString(phase));
			//vi = new Vector<>(viOrig);
			int firstInput[] = {1, 1, 1, 1, 1};
			input[0] = 0;

			for (; jj[qqq] < viS[qqq].size(); jj[qqq]++) {
				//if (jj[qqq] < 3) {out.print("jj[qqq]:" ); out.print(jj[qqq]); out.print(" starting new qqq: "); out.println(qqq); Scanner scanner3 = new Scanner(System.in); scanner3.nextLine();}
				String wholecode = String.format("%05d", viS[qqq].get(jj[qqq]));
				int opcode = Integer.valueOf(wholecode.substring(3,5));

				Character firstMode = wholecode.charAt(2);
				Character secondMode = wholecode.charAt(1);
				Character thirdMode = wholecode.charAt(0);

				int firstParam = 0;
				int secondParam = 0;

				if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6
						|| opcode == 7 || opcode == 8) {
					if (firstMode == '0') {
						firstParam = viS[qqq].get(viS[qqq].get(jj[qqq]+1));
					} else {
						firstParam = viS[qqq].get(jj[qqq]+1);
					}
					if (secondMode == '0') {
						secondParam = viS[qqq].get(viS[qqq].get(jj[qqq]+2));
					} else {
						secondParam = viS[qqq].get(jj[qqq]+2);
					}
				} else if (opcode == 4) {
					if (firstMode == '0') {
						firstParam = viS[qqq].get(viS[qqq].get(jj[qqq]+1));
					} else {
						firstParam = viS[qqq].get(jj[qqq]+1);
					}
				} else if (opcode == 3 || opcode == 99) {
				} else {
					out.print("ERROR: "); out.println(wholecode);
				}

				if (opcode == 1) {
					viS[qqq].set(viS[qqq].get(jj[qqq]+3), firstParam+secondParam);
					jj[qqq]+=3;
				} else if (opcode == 2) {
					viS[qqq].set(viS[qqq].get(jj[qqq]+3), firstParam*secondParam);
					jj[qqq]+=3;
				} else if (opcode == 3) {
					out.println("in");
					if (firstMode == '0') {
						if (firstInput[qqq] == 1) {
							//out.print("setting phase to: "); out.println(input[qqq]);
							out.println("from phase");
							viS[qqq].set(viS[qqq].get(jj[qqq]+1), phase[qqq]);
							firstInput[qqq] = 0;
						} else {
							//out.print("setting input to: "); out.println(input[qqq]);
							out.println("from input...");
							viS[qqq].set(viS[qqq].get(jj[qqq]+1), input[qqq]);
						}
					} else if (firstMode == '1') {
						out.println("whathere...");
					}
					//Scanner scanner = new Scanner(System.in); scanner.nextLine();
					jj[qqq]++;
				} else if (opcode == 4) {
					outout = firstParam;

					out.print("outout: "); out.println(outout);
					int qqqPrev = qqq;
					qqq++;
					qqq %= 5;
					input[qqq] = outout;

					//Scanner scanner = new Scanner(System.in); scanner.nextLine();
					out.print(" qqq now: "); out.print(qqq); 
					out.print("jj[qqq] was: "); out.print(jj[qqq]);
					jj[qqq]++;
					out.print(" jj[qqq] now "); out.println(jj[qqq]);
					//Scanner scanner = new Scanner(System.in); scanner.nextLine();
				} else if (opcode == 5) {
					if (firstParam != 0) {
						jj[qqq] = secondParam-1;
					} else {
						jj[qqq]+=2;
					}
				} else if (opcode == 6) {
					if (firstParam == 0) {
						jj[qqq] = secondParam-1;
					} else {
						jj[qqq]+=2;
					}
				} else if (opcode == 7) {
					if (firstParam < secondParam) {
						viS[qqq].set(viS[qqq].get(jj[qqq]+3), 1);
					} else {
						viS[qqq].set(viS[qqq].get(jj[qqq]+3), 0);
					}
					jj[qqq]+=3;
				} else if (opcode == 8) {
					if (firstParam == secondParam) {
						viS[qqq].set(viS[qqq].get(jj[qqq]+3), 1);
					} else {
						viS[qqq].set(viS[qqq].get(jj[qqq]+3), 0);
					}
					jj[qqq]+=3;
				} else if (opcode == 99) {
					break;
				}
			}
			if (outout > maxWithPhase) {maxWithPhase = outout;}
		} while (!Arrays.equals(phase, phaseOrig));
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxWithPhase);
		out.println("");
		//for (int ii = 0; ii < vi.size(); ii++) {
		//	out.print(","); out.print(vi.get(ii));
		//}
		//out.println();
	}
	public static void nextPermutation(int[] nums) {
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

