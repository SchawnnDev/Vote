package fr.schawnndev.landcraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VoteEvents implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		if(SQLManager.hasPoints(e.getPlayer()))
			e.getPlayer().sendMessage("§3[§bVotes§3]§a N'oublies pas de faire /vote pour avoir ta récompense!");
		
	}

}
