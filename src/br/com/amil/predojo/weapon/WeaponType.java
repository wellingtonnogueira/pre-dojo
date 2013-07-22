package br.com.amil.predojo.weapon;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.predojo.Player;
import br.com.amil.predojo.ranking.RankingHandler;

/**
 * Tipos de armas existentes no jogo.
 * 
 * @author wellington.nogueira@gmail.com
 *
 */
public enum WeaponType implements Weapon {
	
	knife,
	glock,
	uzi,
	M16,
	AK47,
	AR15,
	granade,
	drown;

	/** listener para uso da arma */
	private List<WeaponListener> listeners = new ArrayList<WeaponListener>();
	
	/** Personagem utilizando a arma */
	private Player user;

	private WeaponType() {
		
		//Criação do listener
		WeaponListener listener = new WeaponListener() {
			@Override
			public void weaponUsed(WeaponEvent event) {
				RankingHandler handler = RankingHandler.getInstance();
				
				//Atualiza o ranking
				handler.updateRanking(event.getUser(), event.getTarget(), event.getSource());
				
			}
		};
		
		listeners.add(listener);
	}
	

	@Override
	public void setPlayer(Player user) {
		this.user = user;
	}
	
	@Override
	public Player getPlayer() {
		return this.user;
	}

	@Override
	public void useAgainst(Player target) {
		fireWeaponListener(target);
	}

	/** Dispara o evento de uso da arma a ser escudado pelo listener */
	private void fireWeaponListener(Player target) {
		
		//Se a personagem não for rankeável, não é adicionada às estatísticas para ranking.
		WeaponEvent event = new WeaponEvent(this, user.isRankable()? user : null, target);
		
		for (WeaponListener listener : listeners) {
			listener.weaponUsed(event);
		}
	}

}
