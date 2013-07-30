package br.com.amil.predojo.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import br.com.amil.predojo.Match;

public class LogParser {
	
	private InputStream stream;
	private Match match;
	

	public LogParser(String log) {
		stream = LogParser.class.getResourceAsStream(log);
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
	
	public List<PlayerLogInformation> processLogFile() {
		
		List<PlayerLogInformation> result = readFile();

		return result;
	}
	
	
	private List<PlayerLogInformation> readFile() {
		
		List<PlayerLogInformation> result = new ArrayList<PlayerLogInformation>();
		
		InputStreamReader reader = new InputStreamReader(stream);
		
		char[] tmp = new char[100];
		
		StringBuilder builder = new StringBuilder();
		try {
			for(int len = reader.read(tmp); len != -1; len = reader.read(tmp)) {
				
				builder.append(tmp, 0, len);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] splitted = builder.toString().split("\r\n"); //Log lines separated.
		
		createLogInformationList(splitted, result);
		
		return result;
		
	}

	private List<LogInformation> createLogInformationList(String[] splitted, List<PlayerLogInformation> result) {
		if(splitted.length < 2) {
			return null; //there isn't match finished
		}
		
		processMatchLogInformation(splitted[0], splitted[splitted.length-1]);
		
		for (int i = 1; i < splitted.length -1; i++) {
			processPlayerLogInformation(result, splitted[i]);
		}
		
		return null;

		
	}

	private void processPlayerLogInformation(List<PlayerLogInformation> result, String log) {
		result.add(new PlayerLogInformation(getMatch(), log));
	}

	private void processMatchLogInformation(String... matchesLogInformation) {
		MatchLogInformation initMatch = new MatchLogInformation(matchesLogInformation[0]);
		MatchLogInformation endMatch = new MatchLogInformation(matchesLogInformation[1]);
		
		this.match = new Match(initMatch.getMatch(), initMatch.getOccuredTime(), endMatch.getOccuredTime());
		
	}

	public Match getMatch() {
		return match;
	}

}
