package br.com.amil.predojo;

public enum Award {
	
	fiveKills("5 Kills"),
	noDeads("No Deads"),
	streak("Streak");
	
	private String awardName;

	private Award(String awardName) {
		this.awardName = awardName;
	}
	
	@Override
	public String toString() {
		return awardName;
	}

}
