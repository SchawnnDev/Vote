package fr.schawnndev.landcraft.commands;

import fr.schawnndev.landcraft.*;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote implements CommandExecutor {

	Main plugin;

	public Vote(Main pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player player = (Player) sender;
		String name = player.getName();
		String cmd = "essentials:kit " + plugin.kit + " " + name;

		if (label.equalsIgnoreCase("vote")) {
				if (SQLManager.hasPoints(player)) {
					player.sendMessage("§aVoila ton kit, merci pour le vote et à la prochaine fois!");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
					SQLManager.setPoints(player, 0);
				} else {
					player.sendMessage("§cIl faut que tu votes! http://play.land-craft.eu/#voter");
				}
				
			if(args.length < 0){
				player.sendMessage("§cTrop d'arguments!");
			}
		}
		if (label.equalsIgnoreCase("getPoints")) {
			try {
				player.sendMessage("§Points: §c" + SQLManager.getPoints(player));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			return true;
		}

		if (label.equalsIgnoreCase("setPoints")) {
			if (player.isOp() == true) {
				if (args.length == 1) {
					try {
						SQLManager.setPoints(player, Integer.parseInt(args[0]));
						player.sendMessage("§aYeah!! Points set: " + args[0]);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else {
					player.sendMessage("§c/setPoints <Int>");
				}
			} else {
				player.sendMessage("§cTu n'es pas OP !");
			}
			return true;
		}
		return false;
	}

}
