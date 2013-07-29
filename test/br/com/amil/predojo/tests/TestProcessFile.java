package br.com.amil.predojo.tests;

import static org.junit.Assert.*;

import org.junit.Test;

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

}
