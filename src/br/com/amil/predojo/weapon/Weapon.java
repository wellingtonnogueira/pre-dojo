package br.com.amil.predojo.weapon;

import br.com.amil.predojo.Player;

/**
 * Interface que determina que um objeto é uma arma
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class Weapon {
	
	private String weaponName;
	private Player user;

	public Weapon(String weaponName) {
		this.setWeaponName(weaponName);
	}
	
	/**
	 * Define quem é a personagem que está utilizando esta arma
	 * @param user
	 */
	public void setPlayer(Player user) {
		this.user = user;
	}
	
	/**
	 * @return Retorna a personagem utilizando a arma
	 */
	public Player getPlayer() {
		return this.user;
	}
	
	public String getWeaponName() {
		return weaponName;
	}

	public void setWeaponName(String weaponName) {
		this.weaponName = weaponName;
	}
	
	@Override
	public String toString() {
		return getWeaponName();
	}

}
