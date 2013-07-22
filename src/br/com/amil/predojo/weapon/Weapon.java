package br.com.amil.predojo.weapon;

import br.com.amil.predojo.Player;

/**
 * Interface que determina que um objeto � uma arma
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public interface Weapon {
	
	/**
	 * Define quem � a personagem que est� utilizando esta arma
	 * @param user
	 */
	void setPlayer(Player user);
	
	/**
	 * @return Retorna a personagem utilizando a arma
	 */
	Player getPlayer();
	
	/**
	 * Determina contra quem esta arma � utilizada
	 * @param target
	 */
	void useAgainst(Player target);

}
