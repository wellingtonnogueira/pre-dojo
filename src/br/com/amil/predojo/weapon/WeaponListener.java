package br.com.amil.predojo.weapon;

import java.util.EventListener;

/**
 * Listener para o uso da arma
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public interface WeaponListener extends EventListener {
	/**
	 * Notifica que a arma foi utilizada
	 * 
	 * @param event
	 */
	void weaponUsed(WeaponEvent event);
}
