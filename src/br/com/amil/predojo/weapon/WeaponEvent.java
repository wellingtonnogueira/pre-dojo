package br.com.amil.predojo.weapon;

import br.com.amil.predojo.Player;


/**
 * Evento disparado quando uma arma é utilizada.
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public class WeaponEvent {
	private Weapon source;
	private Player user;
	private Player target;

	public WeaponEvent(Weapon source, Player user, Player target) {
		this.source = source;
		this.user = user;
		this.target = target;
	}

	public Weapon getSource() {
		return source;
	}

	public Player getUser() {
		return user;
	}

	public Player getTarget() {
		return target;
	}
}
