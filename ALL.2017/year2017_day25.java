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
// int max = var_ints.stream().max(Integer::compare).orElseThrow();
// int position = var_ints.indexOf(max);

class year2017_day25 {
	public static int [] theLine = new int[12425180*2];
	public static void main(String [] args) {
		out.println("		2017 Day25.1");
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

		int line = 0;
		int firstState = 0;
		int curState = 0;
		int steps = 0;

		int curVal1 = 0;
		int writeVal1 = 0;
		int move1 = 0;
		int nextState1 = 0;

		int curVal2 = 0;
		int writeVal2 = 0;
		int move2 = 0;
		int nextState2 = 0;
		state [] states = new state[10];
		for (int ii = 0; ii < 10; ii++) {
			states[ii] = new state();
		}
		int numStates = 0;
		for (int i = 0; i < blah.size(); i++) {
			if (i == 0) {
				Pattern p = Pattern.compile("Begin in state ([A-Z]).");
				Matcher m  = p.matcher(blah.get(0));
				m.find();
				firstState = m.group(1).charAt(0) - 'A';
				continue;
			} else if (i == 1) {
				Pattern p = Pattern.compile("Perform a diagnostic checksum after (\\d+) steps.");
				Matcher m = p.matcher(blah.get(1));
				m.find();
				steps = Integer.valueOf(m.group(1));
				continue;
			} else if (i == 2) {
				continue;
			}

			if (blah.get(i).length() == 0) {
				out.print("			writing curState is "); out.println(curState);
				if (curVal1 == 0) {
					states[curState].zeroWrite = writeVal1;
					states[curState].zeroLeftOrRight = move1;
					states[curState].zeroNextState = nextState1;

					states[curState].oneWrite = writeVal2;
					states[curState].oneLeftOrRight = move2;
					states[curState].oneNextState = nextState2;
				} else {
					states[curState].zeroWrite = writeVal2;
					states[curState].zeroLeftOrRight = move2;
					states[curState].zeroNextState = nextState2;

					states[curState].oneWrite = writeVal1;
					states[curState].oneLeftOrRight = move1;
					states[curState].oneNextState = nextState1;
				}

				numStates++;
				line = 0;
				continue;
			}
			if (line == 0) {
				Pattern p = Pattern.compile("In state ([A-Z]):");
				Matcher m = p.matcher(blah.get(i));
				m.find();
				curState = m.group(1).charAt(0) - 'A';
				out.print("curState: "); out.println(curState);
				line++;
				continue;
			} else if (line == 1) {
				Pattern p = Pattern.compile("\\s+If the current value is (\\d+):");
				Matcher m = p.matcher(blah.get(i));
				m.find();
				curVal1 = m.group(1).charAt(0) - '0';
				out.print("curVal1: "); out.println(curVal1);
				line++;
				continue;
			} else if (line == 2) {
				Pattern p = Pattern.compile("\\s+- Write the value (\\d+).");
				Matcher m = p.matcher(blah.get(i));
				m.find();
				writeVal1 = Integer.valueOf(m.group(1));
				out.print("writeVal1: "); out.println(writeVal1);
				line++;
				continue;
			} else if (line == 3) {
				Pattern p = Pattern.compile("\\s+- Move one slot to the (right|left).");
				Matcher m = p.matcher(blah.get(i));
				m.find();


				if (m.group(1).equals("right")) {
					move1 = 2;
				} else {
					move1 = 1;
				}
				out.print("move1: "); out.println(move1);
				line++;
				continue;
			} else if (line == 4) {
				Pattern p = Pattern.compile("\\s+- Continue with state ([A-Z]).");
				Matcher m = p.matcher(blah.get(i));
				m.find();

				nextState1 = m.group(1).charAt(0) - 'A';
				out.print("nextState1: "); out.println(nextState1);
				line++;
				continue;
			} else if (line == 5) {
				Pattern p = Pattern.compile("\\s+If the current value is (\\d+):");
				Matcher m = p.matcher(blah.get(i));
				m.find();
				curVal2 = m.group(1).charAt(0) - '0';
				out.print("curVal2: "); out.println(curVal2);
				line++;
				continue;
			} else if (line == 6) {
				Pattern p = Pattern.compile("\\s+- Write the value (\\d+).");
				Matcher m = p.matcher(blah.get(i));
				m.find();
				writeVal2 = Integer.valueOf(m.group(1));
				out.print("writeVal2: "); out.println(writeVal2);
				line++;
				continue;
			} else if (line == 7) {
				Pattern p = Pattern.compile("\\s+- Move one slot to the (right|left).");
				Matcher m = p.matcher(blah.get(i));
				m.find();

				if (m.group(1).equals("right")) {
					move2 = 2;
				} else {
					move2 = 1;
				}
				out.print("move2: "); out.println(move2);
				line++;
				continue;
			} else if (line == 8) {
				Pattern p = Pattern.compile("\\s+- Continue with state ([A-Z]).");
				Matcher m = p.matcher(blah.get(i));
				m.find();
				nextState2 = m.group(1).charAt(0) - 'A';
				out.print("nextState2: "); out.println(nextState2);
				line++;
				continue;
			}
		}

		if (line != 0) {
			if (curVal1 == 0) {
				states[curState].zeroWrite = writeVal1;
				states[curState].zeroLeftOrRight = move1;
				states[curState].zeroNextState = nextState1;

				states[curState].oneWrite = writeVal2;
				states[curState].oneLeftOrRight = move2;
				states[curState].oneNextState = nextState2;
			} else {
				states[curState].zeroWrite = writeVal2;
				states[curState].zeroLeftOrRight = move2;
				states[curState].zeroNextState = nextState2;

				states[curState].oneWrite = writeVal1;
				states[curState].oneLeftOrRight = move1;
				states[curState].oneNextState = nextState1;
			}
			numStates++;
		}

		//theLine
		//public static int [] theLine = new int[12425180*2];
		int theLinePos = steps;
		int activeState = firstState;
		for (int ii = 0; ii < steps ; ii++) {
			state xx = states[activeState];
			if (theLine[theLinePos] == 0) {
				theLine[theLinePos] = xx.zeroWrite;
				if (xx.zeroLeftOrRight == 2) {
					theLinePos++;
				} else {
					theLinePos--;
				}
				activeState = xx.zeroNextState;
			} else {
				theLine[theLinePos] = xx.oneWrite;
				if (xx.oneLeftOrRight == 2) {
					theLinePos++;
				} else {
					theLinePos--;
				}
				activeState = xx.oneNextState;
			}
		}
		int tot = 0;
		for (int ii = 2; ii < (steps*2)-2; ii++) {
			if (theLine[ii] == 1) {
				tot++;
			}
		}

				
		
		for (int ii = 0; ii < numStates; ii++) {

			/*
			out.println(states[ii].zeroWrite); out.print(" <zeroWrite ");
			out.println(states[ii].zeroLeftOrRight); out.print(" <zeroLeftOrRight ");
			out.println(states[ii].zeroNextState); out.print(" <zeroNextState ");

			out.println(states[ii].oneWrite); out.print(" <oneWrite ");
			out.println(states[ii].oneLeftOrRight); out.print(" <oneLeftOrRight ");
			out.println(states[ii].oneNextState); out.print(" <oneNextState ");
			out.println();
			*/
			/*
			out.print(states[ii].zeroWrite); out.print(" <zeroWrite ");
			out.print(states[ii].zeroLeftOrRight); out.print(" <zeroLeftOrRight ");
			out.print(states[ii].zeroNextState); out.print(" <zeroNextState ");

			out.print(states[ii].oneWrite); out.print(" <oneWrite ");
			out.print(states[ii].oneLeftOrRight); out.print(" <oneLeftOrRight ");
			out.print(states[ii].oneNextState); out.print(" <oneNextState ");
			out.println();
			*/
		}
		
		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(tot);
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

class state {
	public int zeroWrite = 0;
	public int zeroLeftOrRight = 0;
	public int zeroNextState = 0;

	public int oneWrite = 0;
	public int oneLeftOrRight = 0;
	public int oneNextState = 0;
}

