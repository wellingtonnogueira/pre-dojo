package br.com.amil.predojo.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sound.midi.MidiDevice.Info;

import br.com.amil.predojo.Match;

public class ProcessFile {
	
	private InputStream stream;
	private Match match;
	

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
	
	public List<LogInformation> processLogFile() {
		
		List<LogInformation> result = new ArrayList<LogInformation>();
		
		readFile(result);
		
		return result;
	}
	
	
	private void readFile(List<LogInformation> result) {
		
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
		
		String[] splitted = builder.toString().split("\r\n");
		
		createLogInformationList(splitted, result);
		
	}

	private List<LogInformation> createLogInformationList(String[] splitted, List<LogInformation> result) {
		if(splitted.length < 2) {
			return null; //there isn't match finished
		}
		
		processMatchLogInformation(result, splitted[0], splitted[splitted.length-1]);
		
		for (int i = 1; i < splitted.length -1; i++) {
			processPlayerLogInformation(result, splitted[i]);
		}
		
		return null;

		
	}

	private void processPlayerLogInformation(List<LogInformation> result, String log) {
		result.add(new PlayerLogInformation(match, log));
	}

	private void processMatchLogInformation(List<LogInformation> result, String... matchesLogInformation) {
		MatchLogInformation initMatch = new MatchLogInformation(matchesLogInformation[0]);
		MatchLogInformation endMatch = new MatchLogInformation(matchesLogInformation[1]);
		
		match = new Match(initMatch.getMatch(), initMatch.getOccuredTime(), endMatch.getOccuredTime());
		
	}

}
