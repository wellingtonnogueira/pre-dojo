package br.com.amil.predojo.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import br.com.amil.predojo.ranking.RankingStatistics;

public class ProcessFile {
	
	private InputStream stream;


	public ProcessFile(String log) {
		stream = ProcessFile.class.getResourceAsStream(log);
		if(stream == null) {
			try {
				stream = new FileInputStream(log);
			} catch (FileNotFoundException e) {
				
			}
		}
	}
	
	public InputStream getLogStream() {
		return stream;
	}
	
	public List<RankingStatistics> processLogFile() {
		return null;
	}
	
	
	public static void main(String[] args) {
		InputStream stream = ProcessFile.class.getResourceAsStream("/log.log");
		System.out.println(stream);
	}

}
