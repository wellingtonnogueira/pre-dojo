package br.com.amil.predojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.amil.predojo.ranking.RankingHandler;

/**
 * Classe que representa a rodada.
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class Match {
	
	private long matchId;
	private Date start;
	private Date end;
	private Set<Player> playersList = new HashSet<Player>();
	
	public Match(long matchId, Date start, Date end) {
		this.setMatchId(matchId);
		this.start = start;
		this.end = end;
	}
	
	public void addPlayer(Player player) {
		this.playersList.add(player);
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public long getMatchId() {
		return matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

}
