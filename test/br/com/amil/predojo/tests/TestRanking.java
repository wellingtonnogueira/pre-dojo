package br.com.amil.predojo.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.amil.predojo.Player;
import br.com.amil.predojo.exception.PreDojoException;
import br.com.amil.predojo.ranking.RankingHandler;
import br.com.amil.predojo.ranking.RankingStatistics;
import br.com.amil.predojo.weapon.Weapon;

/**
 * @author wellington.nogueira@gmail.com
 *
 */
public class TestRanking {
	
	private static RankingHandler rankingHandler;
	
	static Player[] players = new Player [] {
		new Player("Roman"),
		new Player("Nick"),
		new Player("<WORLD>", false)
	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//Acontece uma �nica vez antes dos testes
		rankingHandler = RankingHandler.getInstance();
	}
	
	@Before
	public void setUp() throws Exception {
		for (Player player : TestRanking.players) {
			player.setWeapon(null);
		}
	}

	@After
	public void tearDown() throws Exception {
		//Acontece uma vez ap�s de cada @Test
		rankingHandler.clearRanking();
	}
	
	@Test
	public void testRomanKillsNick() {
		System.out.println("Test1: Roman kills 1 / Nick deads 2");
		//Create results for comparison
		List<RankingStatistics> rankingCompare = new ArrayList<RankingStatistics>();
		
		RankingStatistics romanStatistics = new RankingStatistics(players[0]);
		RankingStatistics nickStatistics = new RankingStatistics(players[1]);
		
		romanStatistics.increaseKill();
		romanStatistics.setUsedWeapon(new Weapon("M16"));
		nickStatistics.increaseDead();
		
		nickStatistics.increaseDead();
		
		rankingCompare.add(romanStatistics);
		rankingCompare.add(nickStatistics);
		
		ArrayList<RankingStatistics> temp = new ArrayList<RankingStatistics>(rankingCompare);
		Collections.sort(temp, new RankingHandler.RankingComparator<RankingStatistics>());
		
		//Test
		Player roman = players[0];
		Player nick = players[1];
		Player world = players[2];
		
		try {
			roman.setWeapon(new Weapon("M16"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		try {
			nick.setWeapon(new Weapon("knife"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		try {
			world.setWeapon(new Weapon("drown"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		
//		roman.getWeapon().useAgainst(nick);
//		world.getWeapon().useAgainst(nick);
		
		Collection<RankingStatistics> ranking = rankingHandler.getRanking();
		
		for (RankingStatistics statistics : ranking) {
			System.out.println(statistics);
		}

		assertArrayEquals(temp.toArray(), ranking.toArray());
		
	}
	
	@Test
	public void testRomanKillsNickAndNickKillsRoman() {
		System.out.println("Test2: Roman kills 1 dead 2 / Nick kills 1");
		//Create results for comparison
		List<RankingStatistics> rankingCompare = new ArrayList<RankingStatistics>();
		
		RankingStatistics romanStatistics = new RankingStatistics(players[0]);
		RankingStatistics nickStatistics = new RankingStatistics(players[1]);
		
		romanStatistics.increaseKill();
		romanStatistics.setUsedWeapon(new Weapon("AK47"));
		nickStatistics.increaseDead();
		
		nickStatistics.increaseKill();
		nickStatistics.setUsedWeapon(new Weapon("knife"));
		romanStatistics.increaseDead();
		
		romanStatistics.increaseDead();
		
		rankingCompare.add(romanStatistics);
		rankingCompare.add(nickStatistics);
		
		ArrayList<RankingStatistics> temp = new ArrayList<RankingStatistics>(rankingCompare);
		Collections.sort(temp, new RankingHandler.RankingComparator<RankingStatistics>());
		
		//Test
		Player roman = players[0];
		Player nick = players[1];
		Player world = players[2];
		
		try {
			roman.setWeapon(new Weapon("AK47"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		try {
			nick.setWeapon(new Weapon("knife"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		try {
			world.setWeapon(new Weapon("drown"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		
//		roman.getWeapon().useAgainst(nick);
//		nick.getWeapon().useAgainst(roman);
//		world.getWeapon().useAgainst(roman);
		
		Collection<RankingStatistics> ranking = rankingHandler.getRanking();
		
		for (RankingStatistics statistics : ranking) {
			System.out.println(statistics);
		}

		assertArrayEquals(temp.toArray(), ranking.toArray());
		
	}
	
	@Test
	public void testPlayersTryingToUseTheSameWeapon() {
		System.out.println("Test3: Roman and Nick are trying to use the same weapon");
		Player roman = players[0];
		Player nick = players[1];
		
		try {
			roman.setWeapon(new Weapon("glock"));
		} catch (PreDojoException e) {
			//Nothing to do. Certainly the weapon will be equiped by Roman
		}
		try {
			nick.setWeapon(new Weapon("glock"));
		} catch (PreDojoException e) {
			System.out.println(e.getMessage());
		}
		
		//Como n�o foi poss�vel definir uma arma j� utilizada, a mesma deve ser null
		assertNull(nick.getWeapon()); 
		
	}

}
