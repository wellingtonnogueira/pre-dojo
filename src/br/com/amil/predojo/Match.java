package br.com.amil.predojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe que representa a rodada.
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class Match implements Runnable {
	
	private List<Player> playersList = new ArrayList<Player>();
	private long matchId;
	
	public Match(Player... players) {
		this.playersList.addAll(Arrays.asList(players));
	}
	
	public void start() {
		long id = System.currentTimeMillis() % 10000000;
		this.matchId = id;
		System.out.println("New match " + id + " has started");
		
		run();
	}
	
	@Override
	public void run() {
		
		//Run the match
		
	}
	
	public void end() {
		System.out.println("Match " + this.matchId + " has ended");
	}

}
