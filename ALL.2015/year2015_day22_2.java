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
class year2015_day22_2 {
	static int tot = 0;
	static FileWriter writer;
	static int LIM = 300000;
	static class spellsvals  {
		int cost; int damage; int armor; int hit; int turns; int getCost;
		spellsvals(int cost, int damage, int armor, int hit, int turns, int getCost) {
			this.cost = cost; this.damage = damage; this.armor = armor; this.hit = hit; this.turns = turns; this.getCost = getCost;
		}
	};
	static class vals2 {
		int cost; int damage; int armor; int hit; int turns; int getCost; int timerCount = -1;
		vals2() {
			this.cost = 0; this.damage = 0; this.armor = 0; this.hit = 0; this.turns = 0; this.getCost = 0; this.timerCount = -1;
		}
		vals2(int cost, int damage, int armor, int hit, int turns, int getCost, int timeCount) {
			this.cost = cost; this.damage = damage; this.armor = armor; this.hit = hit; this.turns = turns; this.getCost = getCost; this.timerCount = timerCount;
		}
		vals2(vals2 o) {
			this.cost = o.cost;
			this.damage = o.damage;
			this.armor = o.armor;
			this.hit = o.hit;
			this.turns = o.turns;
			this.getCost = o.getCost;
			this.timerCount = o.timerCount;
		}

		@Override
		public String toString() {
			return cost+" "+damage+" "+armor+ " " + hit + " " + turns + " " + getCost + " "+ timerCount + "\n";
		}
	};
	static int spellsTot = 5;
	static spellsvals spells[] = new spellsvals[spellsTot];
	static class game {
		int pMana;
		int pCost;
		int pDamage;
		int pArmor;
		int pHit;
		vals2 pCounters[] = new vals2[spellsTot];
		int pGetCost;

		int bHit;
		int bDamage;
		int bArmor;
		int bMana;
		@Override
		public String toString() {
			return " " + pMana + " " + pCost + " " + pDamage + " " + pArmor + " " + pHit + " " + pGetCost +  " " + bHit + " " + bDamage + " " + bArmor + " " + bMana + "\n" + pCounters[0] + " " + pCounters[1] + " " + pCounters[2] + " " + pCounters[3] + " " + pCounters[4] + "\n";
			//return " " + pMana + " " + pCost + " " + " " + pArmor + " " + pHit + " " + pGetCost + " " + bHit + " " + bDamage + " " + bArmor + " " + bMana + " ";
		}
		game () {
			this.pMana = 0;
			this.pCost = 0;
			this.pDamage = 0;
			this.pArmor = 0;
			this.pHit = 0;
			for (int ii = 0; ii < spellsTot; ii++) {
			       this.pCounters[ii] = new vals2();
			}
			this.pGetCost = 0;

			this.bHit = 0;
			this.bDamage = 0;
			this.bArmor = 0;
			this.bMana = 0;
			/*
			for (int ii = 0; ii < 5; ii++) {
				this.pCounters[ii] = new vals2();
			}
			*/
		}
		game (game o) {
			this.pMana = o.pMana;
			this.pCost = o.pCost;
			this.pDamage = o.pDamage;
			this.pArmor = o.pArmor;
			this.pHit = o.pHit;
			for (int ii = 0; ii < spellsTot; ii++) {
				this.pCounters[ii] = new vals2(o.pCounters[ii]);
			}
			this.pGetCost = o.pGetCost;

			this.bHit = o.bHit;
			this.bDamage = o.bDamage;
			this.bArmor = o.bArmor;
			this.bMana = o.bMana;
		}



	}

	static game games[] = new game[LIM];
	static int gamesPos = 0;

	static game newGames[] = new game[LIM];
	static int newGamesPos = 0;

	static int playerHitOrig = 50; static int playerManaOrig = 500; static int playerDamageOrig = 0; static int playerArmorOrig = 0;
	static int bossHitOrig = 0; static int bossDamageOrig = 0; static int bossArmorOrig = 0;
	static int minManaSpent = 10000;
	public static void main(String [] args) {
		out.println("		2015 Day22.2");
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
		//PrintStream originalOut = System.out;
		//System.setOut(new PrintStream(new java.io.OutputStream() { public void write(int b) { } }));

		spells[0] = new spellsvals(53, 4, 0, 0, 1, 0);
		spells[1] = new spellsvals(73, 2, 0, 2, 1, 0);
		spells[2] = new spellsvals(113, 0, 7, 0, 6, 0);
		spells[3] = new spellsvals(173, 3, 0, 0, 6, 0);
		spells[4] = new spellsvals(229, 0, 0, 0, 5, 101);

		for (int ii = 0; ii < LIM; ii++) {
			games[ii] = new game();
			newGames[ii] = new game();
		}

		Pattern p = Pattern.compile("([A-Za-z ]+): (\\d+)");
		for (int i = 0; i < blah.size(); i++) {
			Matcher m = p.matcher(blah.get(i));
			m.find();
			if (blah.get(i).charAt(0) == 'H') {
				bossHitOrig = Integer.valueOf(m.group(2));
			} else {
				bossDamageOrig = Integer.valueOf(m.group(2));
			}
		}
		//out.println(bossHitOrig); out.println(bossDamageOrig);

		games[0] = new game();

		games[0].pMana = playerManaOrig;
		games[0].pArmor = playerArmorOrig;
		games[0].pHit = playerHitOrig;
		games[0].bHit = bossHitOrig;
		games[0].bDamage = bossDamageOrig;
		games[0].bArmor = bossArmorOrig;
		gamesPos = 1;

		int count = -1;
		int record = 0;
		int max = 0;
		do {
			count++;
			while (gamesPos != 0) {
				cast(); 
			}

			if (newGamesPos > max) {max = newGamesPos;}

			for (int i = 0; i < newGamesPos; i++) {
				games[i] = new game(newGames[i]);
				//out.println(games[i]);
				gamesPos++;
			}

			if (newGamesPos > max) {max = newGamesPos;}
			newGamesPos = 0;

			///////////////////////TAKE TURN
			while (gamesPos != 0) {
				turns();
			} 

			for (int i = 0; i < newGamesPos; i++) {
				games[i] = new game(newGames[i]);
				gamesPos++;
			}
			newGamesPos = 0;
		} while (gamesPos != 0);

		//System.setOut(originalOut);
		//out.println(max);
		out.print("**j_ans: ");
		out.print(minManaSpent);
		out.println("");
	}


	static void cast() {
		//PLAYER
		gamesPos--;
		game xx = new game(games[gamesPos]);

		xx.pHit--;
		if (xx.pHit <= 0) {
			return;
		}
		int notafford = 0;
		int notyet = 0;
		for (int sp = 0; sp < spellsTot; sp++) {

			if (xx.pCounters[sp].timerCount < 0 && minManaSpent > xx.pCost) {
				game tmpGame = new game(xx);

				switch (sp) {
					case 0:
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						break;
					case 4:
						break;

				}
				tmpGame.pMana = xx.pMana - spells[sp].cost;
				if (tmpGame.pMana <= 0) {notafford++; continue;}
				tmpGame.pCost = xx.pCost + spells[sp].cost;
				//tmpGame.pArmor = xx.pArmor;
				/*
				for (int j = 0; j < spellsTot; j++) {
					tmpGame.pCounters[j] = new vals2(xx.pCounters[j]);
				}
				*/
				//tmpGame.pDamage = xx.pDamage;
				//tmpGame.pHit = xx.pHit;
				tmpGame.pCounters[sp].cost = spells[sp].cost;
				tmpGame.pCounters[sp].damage = spells[sp].damage;
				tmpGame.pCounters[sp].armor = spells[sp].armor;
				tmpGame.pCounters[sp].hit = spells[sp].hit;
				tmpGame.pCounters[sp].getCost = spells[sp].getCost;
				tmpGame.pCounters[sp].turns = spells[sp].turns;
				tmpGame.pCounters[sp].timerCount = spells[sp].turns;

				/*
				tmpGame.bHit = xx.bHit;
				tmpGame.bDamage = xx.bDamage;
				tmpGame.bMana = xx.bMana;
				tmpGame.bArmor = xx.bArmor;
				*/

				if (sp == 1) {
					tmpGame.bHit -= spells[sp].damage;
					tmpGame.pHit = xx.pHit + spells[sp].hit;
				} else if (sp == 0) {
					tmpGame.bHit -= spells[sp].damage;
				} else if (sp == 3) {
					//tmpGame.pDamage = xx.pDamage;
				} else if (sp == 2) {
					tmpGame.pArmor = xx.pArmor + spells[sp].armor;
				}

				//out.println(newGamesPos);
				newGames[newGamesPos] = new game(tmpGame); newGamesPos++;
			} else if (xx.pCounters[sp].timerCount >= 0) {
				notyet++;
			}

		}
		if (notafford == spellsTot) {}
		if (notyet == spellsTot) {
			newGames[newGamesPos] = new game(games[gamesPos]); newGamesPos++;
		}
	}

	static void turns() {
		int ret1 = bossTurn();
		if (ret1 != 0) {
			gamesPos--;
			return;
		}
		int ret2 = playerTurn();
		if (ret2 == 0) {
			newGames[newGamesPos] = new game(games[gamesPos-1]); newGamesPos++;
		}
		gamesPos--;
	}

	static int bossTurn() {//play
		int c = gamesPos-1;

		applySpells();

		if (games[c].bHit <= 0) {
			if (games[c].pCost < minManaSpent) {minManaSpent  = games[c].pCost;}
			//out.println("player wins..");
			return 1;
		}
		games[c].pHit -= (games[c].bDamage - games[c].pArmor) > 0? (games[c].bDamage - games[c].pArmor) : 1;	
		if (games[c].pHit <= 0) {
			//out.println("boss wins");
			return 2;
		}
		return 0;
	}

	static int playerTurn() {//play
		int c = gamesPos -1;

		applySpells();
		if (games[c].pDamage > 0) {
			games[c].bHit -= games[c].pDamage;
		}
		if (games[c].bHit <= 0) {
			//out.println("player wins...");
			if (games[c].pCost < minManaSpent) {minManaSpent = games[c].pCost;}
			return 1;
		}

		return 0;
	}
	static void applySpells() {
		int c = gamesPos-1;
		for (int i = 0; i < spellsTot; i++) {
			if (games[c].pCounters[i].timerCount > 0) {
				games[c].pCounters[i].timerCount--;
				switch (i) {
					case 0:
						break;
					case 1:
						break;
					case 2:
						break;
					case 3:
						games[c].bHit -= games[c].pCounters[i].damage;
						break;
					case 4:
						games[c].pMana += games[c].pCounters[i].getCost;
						break;

				}
				if (games[c].pCounters[i].timerCount == 0) {
					if (games[c].pCounters[i].armor > 0) {
					} else {
						games[c].pCounters[i].timerCount--;
					}
					if (games[c].pCounters[i].armor != 0) {
					}
				}
			} else if (games[c].pCounters[i].timerCount == 0) {
				games[c].pCounters[i].timerCount--;
				games[c].pArmor  -= games[c].pCounters[i].armor;
			}
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

