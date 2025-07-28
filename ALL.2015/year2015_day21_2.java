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
class year2015_day21_2 {
	static int tot = 0;
	static class vals {
		int cost; int damage; int armor;
		public vals(int cost, int damage, int armor) {
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}

	}
	static int weaponsTot = 5;
	static vals weapons[] = new vals[weaponsTot+1];
	static int armorTot = 5;
	static vals armor[] = new vals[armorTot+1];

	static int ringsTot = 6;
	static vals rings[] = new vals[ringsTot+1];
	static int bossHit = 0;
	static int bossDamage = 0;
	static int bossArmor = 0;
	static int bossHitOrig = 0;
	static int bossDamageOrig = 0;
	static int bossArmorOrig = 0;

	static int playerHitOrig = 100;
	static int playerDamageOrig = 0;
	static int playerArmorOrig =0;
	static int playerCostOrig = 0;
	static int playerHit = 0;
	static int playerDamage = 0;
	static int playerArmor = 0;
	static int playerCost = 0;

	public static void main(String [] args) {
		out.println("		2015 Day21.2");
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
		weapons[0] = new vals(8, 4, 0);
		weapons[1] = new vals(10, 5, 0);
		weapons[2] = new vals(25, 6, 0);
		weapons[3] = new vals(40, 7, 0);
		weapons[4] = new vals(74, 8, 0);
		weapons[5] = new vals(-1, -1, -1);

		armor[0] = new vals(13, 0, 1);
		armor[1] = new vals(31, 0, 2);
		armor[2] = new vals(53, 0, 3);
		armor[3] = new vals(75, 0, 4);
		armor[4] = new vals(102, 0, 5);
		armor[5] = new vals(-1,-1,-1);

		rings[0] = new vals(25, 1, 0);
		rings[1] = new vals(50, 2, 0);
		rings[2] = new vals(100, 3, 0);
		rings[3] = new vals(20, 0, 1);
		rings[4] = new vals(40, 0, 2);
		rings[5] = new vals(80, 0, 3);
		rings[6] = new vals(-1,-1,-1);

		//	String firstpart = Pattern.quote("mul(");
		Pattern p = Pattern.compile("[A-Za-z ]+: (\\d+)");
		//	Matcher m = p.matcher("17-MAR-11 15.52.25.000000000");
		//	m.find();
		//
		//	month= m.group(2);

		// Vector <Tuple <Integer, String>> ve1 = new Vector<>();
		//BigInteger tot =  BigInteger.valueOf((long)0);
		//BigInteger one =  new BigInteger("1");
		//BigInteger zero =  new BigInteger("0");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			if (m.find()) {
				if (blah.get(i).charAt(0) == 'H') {
					bossHitOrig = Integer.valueOf(m.group(1));
				} else if (blah.get(i).charAt(0) == 'D') {
					bossDamageOrig = Integer.valueOf(m.group(1));
				} else if (blah.get(i).charAt(0) == 'A') {

					bossArmorOrig = Integer.valueOf(m.group(1));
				}
			}
		}


		int maxPlayerCost = 0;
		int arrWe[] = new int[weaponsTot];
		for (int weEnd = 1; weEnd < 2; weEnd++) {
			for (int j = 0; j < weEnd; j++) {
				arrWe[j] = 1;
			}
			for (int k = weEnd; k < weaponsTot; k++) {
				arrWe[k] = 0;
			}
			Arrays.sort(arrWe);
			int arrWeOrig[] = Arrays.copyOf(arrWe, arrWe.length);
			///////////////WEAPONS
			do {
				playerHit = playerHitOrig;
				playerCost = playerCostOrig;
				playerDamage = playerDamageOrig;
				playerArmor = playerArmorOrig;

				for (int we = 0; we < weaponsTot; we++) {
					if (arrWe[we] == 1) {
						playerCost += weapons[we].cost;
						playerDamage += weapons[we].damage;
						playerArmor += weapons[we].armor;
					}
				}

				int pH1 = playerHit;
				int pC1 = playerCost;
				int pD1 = playerDamage;
				int pA1 = playerArmor;

				///////////////////////ARMOR
				int arrArm[] = new int[armorTot];
				for (int arEnd = 0; arEnd < armorTot; arEnd++) {
					for (int j = 0; j < arEnd; j++) {
						arrArm[j] = 1;
					}
					for (int k = arEnd; k < armorTot; k++) {
						arrArm[k] = 0;
					}
					Arrays.sort(arrArm);
					int arrArmOrig[] = Arrays.copyOf(arrArm, arrArm.length);
					do {
						playerHit = pH1;
						playerCost = pC1;
						playerDamage = pD1;
						playerArmor = pA1;

						for (int ar = 0; ar < armorTot; ar++) {
							if (arrArm[ar] == 1) {
								playerCost += armor[ar].cost;
								playerDamage += armor[ar].damage;
								playerArmor += armor[ar].armor;
							}
						}
						int pH = playerHit;
						int pC = playerCost;
						int pD = playerDamage;
						int pA = playerArmor;
						/////////////////////////RINGS

						int arrRi[] = new int[ringsTot];

						for (int riEnd = 0; riEnd < 3; riEnd++) {
							for (int j = 0; j < riEnd; j++) {
								arrRi[j] = 1;
							}
							for (int k = riEnd; k < ringsTot; k++) {
								arrRi[k] = 0;
							}

							Arrays.sort(arrRi);
							int arrRiOrig[] = Arrays.copyOf(arrRi, arrRi.length);

							do {
								playerHit = pH;
								playerCost = pC;
								playerDamage = pD;
								playerArmor = pA;
								for (int ri = 0; ri < ringsTot; ri++) {
									if (arrRi[ri] == 1) {
										playerCost += rings[ri].cost;
										playerDamage += rings[ri].damage;
										playerArmor += rings[ri].armor;
									}
								}
								int ret = play();
								if (ret == 2) {
									if (playerCost > maxPlayerCost) {maxPlayerCost = playerCost;}
								}
								nextPermutation(arrRi);
							} while (!Arrays.equals(arrRi, arrRiOrig));
						}
						nextPermutation(arrArm);
					} while (!Arrays.equals(arrArm, arrArmOrig));
				}
				nextPermutation(arrWe);
			} while (!Arrays.equals(arrWe, arrWeOrig));

		}
		//		System.setOut(originalOut);
		out.print("**j_ans: ");
		out.print(maxPlayerCost);
		out.println("");
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

	static int play()
	{//play
		tot++;
		bossHit = bossHitOrig; bossDamage = bossDamageOrig; bossArmor = bossArmorOrig;
		do {
			bossHit -= playerDamage - bossArmor > 0? playerDamage -bossArmor: 1;
			if (bossHit <= 0) {
				return 1;
			}
			playerHit -= bossDamage - playerArmor > 0? bossDamage - playerArmor : 1;
			if (playerHit <= 0) {
				return 2;
			}
		} while (true);

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

