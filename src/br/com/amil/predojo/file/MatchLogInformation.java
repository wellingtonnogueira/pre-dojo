package br.com.amil.predojo.file;

public class MatchLogInformation extends LogInformation {
	
	public MatchLogInformation(String log) {
		super(log);
	}

	private Integer matchId;

	public Integer getMatch() {
		return matchId;
	}

	public void setMatch(Integer match) {
		this.matchId = match;
	}

	@Override
	protected void process(String log) {
		//get match id
		setMatch(Integer.parseInt(log.replaceAll("[^\\d()].", "")));
			
	}

}
