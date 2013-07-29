package br.com.amil.predojo.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import br.com.amil.predojo.file.LogInformation;
import br.com.amil.predojo.file.ProcessFile;

public class TestProcessFile {
	
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
		
		List<LogInformation> processLog = pf.processLogFile();
		
		assertNotNull(processLog);
	}

}
