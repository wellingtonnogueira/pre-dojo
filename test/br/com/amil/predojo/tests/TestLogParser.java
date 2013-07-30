package br.com.amil.predojo.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import br.com.amil.predojo.Player;
import br.com.amil.predojo.file.PlayerLogInformation;
import br.com.amil.predojo.file.LogParser;
import br.com.amil.predojo.ranking.RankingHandler;
import br.com.amil.predojo.ranking.RankingStatistics;
import br.com.amil.predojo.weapon.Weapon;

public class TestLogParser {
	
	@Test
	public void testGetFileDoesNotExist() {
		LogParser pf = new LogParser("teste");
		
		assertNull(pf.getLogStream());
	}
	
	@Test
	public void testGetFileExists() {
		LogParser pf = new LogParser("/log.log");
		
		assertNotNull(pf.getLogStream());
	}
	
	@Test
	public void testProcessLogFile() {
		LogParser pf = new LogParser("/log.log");
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		assertNotNull(processLog);
	}
	
	@Test
	public void testProcessEmptyLogFile() {
		LogParser pf = new LogParser("/log2.log"); //File without content
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		assertTrue(processLog.isEmpty());
	}

	@Test
	public void testProcessLogFileWithoutPlayerInteraction() {
		LogParser pf = new LogParser("/log3.log"); //File without players interaction
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		assertNotNull(processLog);
	}
	
	@Test
	public void testGenerateRanking() {
		LogParser pf = new LogParser("/log.log"); //File without players interaction
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		RankingHandler handler = RankingHandler.getRankingHandler(pf.getMatch());
		
		for (PlayerLogInformation playerLogInfo : processLog) {
			handler.updateRanking(playerLogInfo);
		}
		
		for (RankingStatistics stat : handler.getRanking()) {
			System.out.println(stat);
		}
		
		assertTrue(!handler.getRanking().isEmpty());
	}
	
	@Test
	public void testGenerateRankingLog2() {
		LogParser pf = new LogParser("/log2.log"); //File without players interaction
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		RankingHandler handler = RankingHandler.getRankingHandler(pf.getMatch());
		
		for (PlayerLogInformation playerLogInfo : processLog) {
			handler.updateRanking(playerLogInfo);
		}
		
		for (RankingStatistics stat : handler.getRanking()) {
			System.out.println(stat);
		}
		
		assertTrue(handler.getRanking().isEmpty());
	}
	
	@Test
	public void testGenerateRankingLog3() {
		
		LogParser pf = new LogParser("/log3.log"); //File without players interaction
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		Collection<RankingStatistics> listToCompare = new ArrayList<RankingStatistics>();
		
		listToCompare.add(createPlayerMock("Roman", "AK47", 4, 1));
		listToCompare.add(createPlayerMock("Raul", "M16", 2, 3));
		listToCompare.add(createPlayerMock("Nick", "AR15", 1, 4));
		
		
		
		System.out.println();
		
		RankingHandler handler = RankingHandler.getRankingHandler(pf.getMatch());
		
		for (PlayerLogInformation playerLogInfo : processLog) {
			handler.updateRanking(playerLogInfo);
		}
		
		for (RankingStatistics stat : handler.getRanking()) {
			System.out.println(stat);
		}
		
		Collection<RankingStatistics> ranking = handler.getRanking();
		assertTrue(Arrays.equals(ranking.toArray(), listToCompare.toArray()));
		
		
	}

	private RankingStatistics createPlayerMock(String playerName, String weaponName, int numberOfKills, int numberOfDeads) {
		RankingStatistics romanStatistic = new RankingStatistics(new Player(playerName));
		if(weaponName != null) {
			romanStatistic.setUsedWeapon(new Weapon(weaponName));
		}
		for(int i = 0; i < numberOfKills; ++i) romanStatistic.increaseKill();
		for(int i = 0; i < numberOfDeads; ++i) romanStatistic.increaseDead();
		return romanStatistic;
	}
}
