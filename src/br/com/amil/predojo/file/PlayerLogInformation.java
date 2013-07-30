package br.com.amil.predojo.file;

import br.com.amil.predojo.Match;
import br.com.amil.predojo.Player;
import br.com.amil.predojo.Weapon;
import br.com.amil.predojo.exception.PreDojoException;

public class PlayerLogInformation extends MatchLogInformation {
	
	private Player killer;
	private Player dead;

	public PlayerLogInformation(Match match, String log) {
		super(log);
		
		match.addPlayer(getKiller());
		match.addPlayer(getDead());

	}
	
	public Player getKiller() {
		return killer;
	}
	public void setKiller(Player killer) {
		this.killer = killer;
	}
	public Player getDead() {
		return dead;
	}
	public void setDead(Player dead) {
		this.dead = dead;
	}

	@Override
	protected void process(String log) {
		//23/04/2013 15:36:04 - Roman killed Nick using M16
		String[] playersInfo = log.split(" killed ");
	
		String playerName = playersInfo[0];
		if("<WORLD>".equals(playerName)) {
			setKiller(new Player(playerName, false));
			
		} else {
			setKiller(new Player(playerName));
			
		}
		
		
		String deadPlayer = playersInfo[1].substring(0, playersInfo[1].indexOf(" "));
		setDead(new Player(deadPlayer));
		
		String weapon = playersInfo[1].substring(playersInfo[1].lastIndexOf(" "), playersInfo[1].length());
		try {
			getKiller().setWeapon(new Weapon(weapon));
		} catch (PreDojoException e) {
			e.printStackTrace();
		}

	}
	
}
