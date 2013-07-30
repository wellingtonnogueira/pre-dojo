package br.com.amil.predojo.ranking;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import br.com.amil.predojo.Award;
import br.com.amil.predojo.Player;
import br.com.amil.predojo.Weapon;

/**
 * Classe que armazena as estatísticas de ranking de cada personagem
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class RankingStatistics {

	/** Personagem rankeável */
	private Player player;
	
	/** Quantidade de execuções */
	private int killed = 0;
	
	/** Quantidade de mortes desta personagem */
	private int dead = 0;
	
	/** Map das armas utilizadas */
	private Map<Weapon, Integer> usedWeapon;

	public RankingStatistics(Player player) {
		setPlayer(player);
		this.usedWeapon = new HashMap<Weapon, Integer>();

	}

	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}

	public void setUsedWeapon(Weapon weapon) {
		if (!usedWeapon.containsKey(weapon)) {
			usedWeapon.put(weapon, 0);
		}
		usedWeapon.put(weapon, usedWeapon.get(weapon) + 1);
	}

	/**
	 * @return Retorna apenas a arma mais utilizada 
	 * (em caso de mais de uma arma ter sido usada a mesma quantidade de vezes, retornará a última)
	 */
	public Weapon getMostUsedWeapon() {
		Set<Weapon> keySet = usedWeapon.keySet();
		Weapon mostUsedWeapon = null;

		int timesUsed = Integer.MIN_VALUE;
		for (Weapon key : keySet) {
			Integer temp = usedWeapon.get(key);
			if (temp > timesUsed) {
				timesUsed = temp;
				mostUsedWeapon = key;
			}
		}

		return mostUsedWeapon;
	}

	public int getKilled() {
		return killed;
	}

	public int getDead() {
		return dead;
	}

	/** Incrementa a quantidade de execuções */
	public void increaseKill() {
		killed++;
	}

	/** Incrementa a quantidade de mortes */
	public void increaseDead() {
		dead++;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dead;
		result = prime * result + killed;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RankingStatistics other = (RankingStatistics) obj;
		if (dead != other.dead)
			return false;
		if (killed != other.killed)
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RankingStatistics [" +
				"player=" + player + 
				", killed=" + killed + 
				", dead=" + dead 
				+ (getMostUsedWeapon() != null ? ", most used weapon=" + getMostUsedWeapon()  : "") + 
				getAwards() +
				"]";
	}

	private String getAwards() {
		StringBuilder b = new StringBuilder();
		b.append(" { Awards = ");
		for (Award award : getPlayer().getAwards()) {
			b.append(award);
			b.append(" ");
		}
		b.append("}");
		return b.toString();
	}

}