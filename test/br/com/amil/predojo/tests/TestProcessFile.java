package br.com.amil.predojo.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.amil.predojo.file.PlayerLogInformation;
import br.com.amil.predojo.file.ProcessFile;
import br.com.amil.predojo.ranking.RankingHandler;

public class TestProcessFile {
	
	private static RankingHandler rankingHandler;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rankingHandler = RankingHandler.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
		rankingHandler.clearRanking();
	}
	
	@Test
	public void testGetFileDoesNotExist() {
		ProcessFile pf = new ProcessFile("teste");
		
		assertNull(pf.getLogStream());
	}
	
	@Test
	public void testGetFileExists() {
		ProcessFile pf = new ProcessFile("/log.log");
		
		assertNotNull(pf.getLogStream());
	}
	
	@Test
	public void testProcessLogFile() {
		ProcessFile pf = new ProcessFile("/log.log");
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		assertNotNull(processLog);
	}
	
	@Test
	public void testProcessEmptyLogFile() {
		ProcessFile pf = new ProcessFile("/log2.log"); //File without content
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		assertTrue(processLog.isEmpty());
	}

	@Test
	public void testProcessLogFileWithoutPlayerInteraction() {
		ProcessFile pf = new ProcessFile("/log3.log"); //File without players interaction
		
		List<PlayerLogInformation> processLog = pf.processLogFile();
		
		assertNotNull(processLog);
	}
}
