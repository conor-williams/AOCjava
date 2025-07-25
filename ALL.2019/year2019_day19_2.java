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
@SuppressWarnings("unchecked")
class year2019_day19_2 {
	public static int FIRSTY = 200;
	public static int SECONDY = 1000;
	public static int [][] keypad = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	public static int SX = 3000;
	public static int SY = 3000;
	public static int grid[][] = new int[SY+5][SX+5];
	public static int var_inputX = 0;
	public static int var_inputY = FIRSTY;
	public static int var_inputXprev = 0;
	public static int var_inputYprev = 0;
	public static int numSteps = 0;
	public static int FRMX = 1000;
	public static int TOX = 2000;
	public static int FRMY = 1000;
	public static int TOY = 2000;
	public static int var_moves = 0;
	public static int xline1_1 = -1;
	public static int xline1_2 = -1;
	public static int xline2_1 = -1;
	public static int xline2_2 = -1;
	public static int yline1_1 = -1;
	public static int yline1_2 = -1;
	public static int yline2_1 = -1;
	public static int yline2_2 = -1;
	public static int CHECK = 0;

	public static void main(String [] args) {
		out.println("		2019 Day19.2");
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


		for (int yy = 0; yy < 3000; yy++) {
			for (int xx = 0; xx < 3000; xx++) {
				grid[yy][xx] = 3;
			}
		}


		Vector <Long> vi = new Vector<>();
		Vector <Long> viOrig = new Vector<>();
		for (int i = 0; i < blah.size(); i++) {
			Scanner scanner = new Scanner(blah.get(i));
			scanner.useDelimiter("[,]");
			while (scanner.hasNext()) {
				String ne = scanner.next();
				//vi.add(Long.parseLong(ne));
				viOrig.add(Long.parseLong(ne));
			}
		}

		for (int i = blah.size(); i < 10000; i++) {
			//vi.add((long)0);
			viOrig.add((long)0);
		}

		//padit..
		long ans = 0;
		int go = 1;
		while (go == 1) {
			go = 0;
			long outout = 0;
			long input = 2;
			int relBase = 0;
			vi = (Vector<Long>)viOrig.clone();

after:
			for (int jj = 0; jj < vi.size(); jj++) {
				String wholecode = String.format("%05d", vi.get(jj));
				int opcode = Integer.valueOf(wholecode.substring(3,5));

				Character firstMode = wholecode.charAt(2);
				Character secondMode = wholecode.charAt(1);
				Character thirdMode = wholecode.charAt(0);

				long firstParam = 0;
				long secondParam = 0;
				int thirdParam = 0;

				if (opcode == 1 || opcode == 2 || opcode == 5 || opcode == 6
						|| opcode == 7 || opcode == 8) {
					if (firstMode == '0') {
						firstParam = vi.get(Math.toIntExact(vi.get(jj+1)));
					} else if (firstMode == '1') {
						firstParam = vi.get(jj+1);
					} else if (firstMode == '2') {
						int relTmp = relBase + Math.toIntExact(vi.get(jj+1));
						firstParam = vi.get(relTmp);
					}

					if (secondMode == '0') {
						secondParam = vi.get(Math.toIntExact(vi.get(jj+2)));
					} else if (secondMode == '1') {
						secondParam = vi.get(jj+2);
					} else if (secondMode == '2') {
						int relTmp = relBase + Math.toIntExact(vi.get(jj+2));
						secondParam = vi.get(relTmp);
					}

					if (thirdMode == '0') {
						thirdParam = Math.toIntExact(vi.get(jj+3));
					} else if (thirdMode == '1') {
						thirdParam = jj+3;
					} else if (thirdMode == '2') {
						int relTmp = relBase + Math.toIntExact(vi.get(jj+3));
						thirdParam = relTmp;
						//thirdParam = vi.get(relTmp);
					}

				} else if (opcode == 4 || opcode == 9) {
					if (firstMode == '0') {
						firstParam = vi.get(Math.toIntExact(vi.get(jj+1)));
					} else if (firstMode == '1') {
						firstParam = vi.get(jj+1);
					} else if (firstMode == '2') {
						int relTmp = relBase + Math.toIntExact(vi.get(jj+1));
						firstParam = vi.get(relTmp);
					}

				} else if (opcode == 3 || opcode == 99) {
					if (firstMode == '0') {
						firstParam = Math.toIntExact(vi.get(jj+1));
					} else if (firstMode == '1') {
						firstParam = jj+3;
					} else if (firstMode == '2') {
						int relTmp = relBase + Math.toIntExact(vi.get(jj+1));
						firstParam = relTmp;
						//thirdParam = vi.get(relTmp);
					}

				} else {
					out.print("wholecode: "); out.println(wholecode);
					out.println("ERROR");
					Runtime.getRuntime().halt(0);
				}

				if (opcode == 1) {
					vi.set(thirdParam, firstParam+secondParam);
					jj+=3;
				} else if (opcode == 2) {
					vi.set(thirdParam, firstParam*secondParam);
					jj+=3;
				} else if (opcode == 9) {
					relBase += firstParam;
					//out.print("relBase: "); out.println(relBase);
					jj++;
				} else if (opcode == 3) {
					if (var_moves % 2 == 0) {
						var_inputXprev = var_inputX;
						var_inputYprev = var_inputY;
					}
					if (var_moves % 2 == 0) {
						vi.set(Math.toIntExact(firstParam), (long)var_inputX);
					} else {
						vi.set(Math.toIntExact(firstParam), (long)var_inputY);
					}

					if (var_moves % 2 == 0) {
						++var_inputX;
						if (CHECK == 1) {
							if (var_inputX / TOX == 1) {

								var_inputY++;
								var_inputX = FRMX;
							} 
						}
						//var_inputX %= 54;
					}
					var_moves++;
					/*
lastX: 1166
fY: 1712
y99: 1811
yatzee
*/


					jj++;
				} else if (opcode == 4) {
					outout = firstParam;
					numSteps++;
					grid[var_inputYprev][var_inputXprev] = (int)outout;

					//out.print("OUTOUT: "); out.println(outout);
					jj++;
				} else if (opcode == 5) {
					if (firstParam != 0) {
						jj = (int)(secondParam-(long)1);
					} else {
						jj+=(long)2;
					}
				} else if (opcode == 6) {
					if (firstParam == 0) {
						jj = (int)(secondParam-(long)1);
					} else {
						jj+=(long)2;
					}
				} else if (opcode == 7) {
					if (firstParam < secondParam) {
						vi.set(thirdParam, (long)1);
						//vi.set(Math.toIntExact(vi.get(jj+3)), (long)1);
					} else {
						vi.set(thirdParam, (long)0);
						//vi.set(Math.toIntExact(vi.get(jj+3)), (long)0);
					}
					jj+=3;
				} else if (opcode == 8) {
					if (firstParam == secondParam) {
						//vi.set(Math.toIntExact(vi.get(jj+3)), (long)1);
						vi.set((int)thirdParam, (long)1);
					} else {
						//vi.set(Math.toIntExact(vi.get(jj+3)), (long)0);
						vi.set((int)thirdParam, (long)0);

					}
					jj+=3;
				} else if (opcode == 99) {

					if (CHECK == 0) {
						if (var_inputY == FIRSTY) {
							for (int x = 0; ;x++) {
								if (grid[var_inputY][x] == 0 && grid[var_inputY][x+1] == 1) {
									//xline1_1 = x+1;
									//yline1_1 = FIRSTY;
									xline1_1 = 0;
									yline1_1 = 0;
								}
								if (grid[var_inputY][x] == 1 && grid[var_inputY][x+1] == 0) {
									//xline2_1 = x;
									//yline2_1 = FIRSTY;
									xline2_1 = 0;
									yline2_1 = 0;
								}
								if (grid[var_inputY][x] == 3) {
									go = 1;
									//out.println("got a 3 = break after");
									break;	
								}
							}
						}
						if (xline2_1 == -1) {
							go = 1;
							break after;
						} else if (var_inputY == FIRSTY) {
							out.print(FIRSTY); out.println(" points got");
							out.print(xline1_1); out.print(" "); out.println(xline2_1);
							out.print(yline1_1); out.print(" "); out.println(yline2_1);
							var_inputY = SECONDY;
							var_inputX = 0;
							go = 1;
							break after;
						}

						if (var_inputY == SECONDY) {
							for (int x = 0; ;x++) {
								if (grid[var_inputY][x] == 0 && grid[var_inputY][x+1] == 1) {
									xline1_2 = x+1;
									yline1_2 = SECONDY;
								}
								if (grid[var_inputY][x] == 1 && grid[var_inputY][x+1] == 0) {
									xline2_2 = x;
									yline2_2 = SECONDY;
								}
								if (grid[var_inputY][x] == 3) {
									go = 1;
									break;
								}
							}
						}
						if (xline2_2 == -1) {
							go = 1;
							break after;
						} 
						out.print(xline1_1); out.print(" "); out.println(xline2_1);
						out.print(yline1_1); out.print(" "); out.println(yline2_1);
						out.print(xline1_2); out.print(" "); out.println(xline2_2);
						out.print(yline1_2); out.print(" "); out.println(yline2_2);
						out.println("now the calc");


						double slope1 = (double)((double)yline1_2- (double)yline1_1) / (double) ((double) xline1_2- (double)xline1_1);
						double slope1_2 = (double)((double)yline1_1- (double)yline1_2) / (double) ((double) xline1_1- (double)xline1_2);
						out.println("slopes: ");

						if (slope1 != slope1_2) {
							out.println("slopesERR");
						}
						double intercept1 = (double)yline1_2 - ((double)slope1 * (double)xline1_2);
						double intercept1_2 = (double)yline1_1 - ((double)slope1_2 * (double)xline1_1);
						intercept1 = 0.0;
						intercept1_2 = 0.0;
						if (intercept1 != intercept1_2) {
							out.println(intercept1);
							out.println(intercept1_2);
							out.println("intercept ERR");
							//Runtime.getRuntime().halt(0);
						}

						double slope2 = (double)((double)yline2_2- (double)yline2_1) / (double) ((double) xline2_2- (double)xline2_1);
						double intercept2 = (double)yline2_2 - ((double)slope2 * (double)xline2_2);
						intercept2 = 0.0;
						out.print("line1: "); out.print(slope1); out.print(" "); out.println(intercept1);
						out.print("line2: "); out.print(slope2); out.print(" "); out.println(intercept2);

						int count = 0;

						for (int yy = 200; yy < 2000; yy++) {
							double yy1 = yy;
							//double xx1 = - ((intercept1 - (double)yline1_2)) / ((double)slope1);
							double xx1 = Math.floor(((double)yy1 - intercept1) / ((double)slope1));
							//out.println("is x whole"); out.println(xx1);
							//Scanner scanner2 = new Scanner(System.in); scanner2.nextLine();

							double yy2 = yy1-99;
							double xx2 = xx1+99;

							int zz = (int)(((slope2 * xx2) + intercept2) - (double)yy2);

							if (zz == 0) {
								if (count == 0) {
									FRMY = (int) yy2-30;
									FRMX = (int) xx1-30;
									TOY = (int)yy1+30;
									TOX = (int)xx2+30;
									break;
								}
								count++;
								//ans = (int) (xx1*10000)+(int)yy1;
								//out.println(ans);
							} else {}
							/*
							if (count == 15) {
								TOY = (int)yy1;
								TOX = (int)xx2+10;
								break;
							}
							*/

						}
						go = 0;
						CHECK = 1;
						var_inputY = FRMY;
						var_inputX = FRMX;
						//break after;
					} 


					if (CHECK == 1) {
						//out.println("CHECK is 1"); out.print("var_inputY: "); out.println(var_inputY); 
						if (var_inputY == TOY+1) {

							int fY = -1;
							int lastX = -1;
							int go1 = 0;
							do {
								int y = FRMY;
								{
									for (int x = TOX; x>=0; x--) {
										if (grid[y][x] == 1) {
											lastX = x; fY = y; break;
										}
									}
								}
								if (fY == -1 && lastX == -1) {go1=1; FRMY++; continue;}

								int y99 = fY+99;
								if (y99 > TOY) {
									out.println("ERR22");
									Runtime.getRuntime().halt(0);
								}
								if (y99 < FRMY) {go1=1; FRMY++; continue;}
								//out.print("lastX: "); out.println(lastX);
								//out.print("fY: "); out.println(fY);
								//out.print("y99: "); out.println(y99);

								int found33 = 0;
								if (grid[y99][lastX-99] == 1 && grid[y99][lastX-100] == 0) {
									out.println("yatzee");
									//long ans2 = (long)(lastX-99) * (long)10000;
									//ans += fY;
									found33 = 1;

								}

								if (found33 == 1) {
									long ans2 = (long)(lastX-99) * (long)10000;
									ans2 += fY;
									ans = ans2;
									break after;
								} else if (y99 > TOY) {
									out.println("widen...");
									break;
								} else if (found33 == 0) {
									FRMY++;
									go1 = 1;
								}
							} while (go1 == 1);
							break after;	
						} else {
							go = 1;
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

