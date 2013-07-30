package br.com.amil.predojo;

import java.util.Set;
import java.util.TreeSet;

import br.com.amil.predojo.exception.PreDojoException;
import br.com.amil.predojo.weapon.Weapon;

/**
 * Classe que representa o jogador/personagem
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class Player {
	
	/** Nome da personagem */
	private String name;
	
	/** Arma que a personagem está equipada */
	private Weapon weapon;
	
	/** Define se a personagem é ranqueável */
	private boolean rankable;
	
	private Set<Award> awards = new TreeSet<Award>();
	
	public Player(String name) {
		this(name, true);
	}

	public Player(String name, boolean rankable) {
		this.name = name;
		this.rankable = rankable;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public void addAward(Award award) {
		getAwards().add(award);
	}
	
	public Set<Award> getAwards() {
		return awards;
	}

	public void setWeapon(Weapon weapon) throws PreDojoException {
		
		if(this.weapon != null) {
			this.weapon.setPlayer(null);
		}
		this.weapon = weapon;
		if(weapon != null) this.weapon.setPlayer(this);
	}

	public String getName() {
		return name;
	}

	public boolean isRankable() {
		return rankable;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return name;
	}
	

}
