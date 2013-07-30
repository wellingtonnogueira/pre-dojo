package br.com.amil.predojo.ranking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import br.com.amil.predojo.Award;
import br.com.amil.predojo.Match;
import br.com.amil.predojo.Player;
import br.com.amil.predojo.file.PlayerLogInformation;
import br.com.amil.predojo.weapon.Weapon;

/**
 * Classe que controla o Ranking geral.
 * Só haverá uma instância da mesma.
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class RankingHandler {
	
	/** Instancia de Ranking */
	private static Map<Match, RankingHandler> matchRanking = new HashMap<Match, RankingHandler>();

	/** Lista de Ranking */
	private Map<Player, RankingStatistics> ranking;
	
	
	public static RankingHandler getRankingHandler(Match match) {
		RankingHandler rankingHandler = matchRanking.get(match);
		if(rankingHandler == null) {
			matchRanking.put(match, rankingHandler = new RankingHandler());
		}
		return rankingHandler;
	}
	
	private RankingHandler() {
		ranking = new HashMap<Player, RankingStatistics>();
	}
	
	public void updateRanking(PlayerLogInformation playerLogInfo) {
		updateRanking(playerLogInfo.getKiller(), playerLogInfo.getDead(), playerLogInfo.getKiller().getWeapon());
		//TODO 
	}
	
	/**
	 * Atualiza status do Ranking
	 * 
	 * @param weapon Arma utilizada
	 * @param killer Assassino
	 * @param target Alvo
	 */
	public void updateRanking(Player killer, Player target, Weapon weapon) {
		RankingStatistics killerStatistics = ranking.get(killer);
		
		if(killer.isRankable()) { //if null means the killer was the World
			if(killerStatistics == null) {
				killerStatistics = new RankingStatistics(killer);
				ranking.put(killer, killerStatistics);
			}
			killerStatistics.increaseKill();
			killerStatistics.setUsedWeapon(weapon);
			
		}
		
		RankingStatistics killedStatistics = ranking.get(target);
		if(killedStatistics == null) {
			killedStatistics = new RankingStatistics(target);
			ranking.put(target, killedStatistics);
		}
		killedStatistics.increaseDead();
	}
	
	/**
	 * Obtêm a lista de Ranking
	 * @return Retorna a lista de Ranking
	 */
	public Collection<RankingStatistics> getRanking() {
		ArrayList<RankingStatistics> sortedRanking = new ArrayList<RankingStatistics>(ranking.values());
		Collections.sort(sortedRanking, new RankingComparator<RankingStatistics>());
		
		applyNoDeadsAward(sortedRanking);
		
		return sortedRanking;
	}
	
	/**
	 * Comparador que garante a ordem de quem mais matou para quem menos matou.
	 * 
	 * @author wellington.nogueira@gmail.com
	 *
	 * @param <T>
	 */
	public static class RankingComparator<T extends RankingStatistics> implements Comparator<T> {
		
		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		public int compare(T o1, T o2) {
			if(o1.getKilled() < o2.getKilled())	{ //player 1 killed more
				return 1;
			} else if(o1.getKilled() > o2.getKilled()) { //player 2 killed more
				return -1;
			} else { //players 1 and 2 killed both the same times
				if(o1.getDead() > o2.getDead()) { //player 1 was dead more times
					return 1;
				} else if(o1.getDead() < o2.getDead()) { //player 2 was dead more times
					return -1;
				}
				return 0; //impossible determine who was better...
			}
		}
	}
	
	private void applyNoDeadsAward(Collection<RankingStatistics> ranking) {
		if(!ranking.isEmpty()) {
			RankingStatistics statistics = (RankingStatistics)ranking.toArray()[0];
			if(statistics.getDead() == 0) {
				statistics.getPlayer().addAward(Award.noDeads);
			}
		}
		
	}

}
