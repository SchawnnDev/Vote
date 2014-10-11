package fr.schawnndev.landcraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.schawnndev.landcraft.commands.*;
import fr.schawnndev.landcraft.VoteEvents;

public class Main extends JavaPlugin {
	
	public String host = getConfig().getString("Host");
	public String database = getConfig().getString("Database");
	public String user = getConfig().getString("Identifiant");
	public String password = getConfig().getString("MotDePasse");
	public String kit = getConfig().getString("KitName");
	 PluginManager pm = getServer().getPluginManager();

	public void onEnable() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	//	if (host.length() != 0 && database.length() != 0 && user.length() != 0 && password.length() != 0) {
		new SQL(this).connect();
		//	} else {
	//		Bukkit.getLogger().severe("[Vote LandCraft] MySQL => Idenfiants faux sont egals a 0 ! (Config.yml)");
	//	}
		if (Bukkit.getPluginManager().getPlugin("Essentials") == null) {
			Bukkit.getLogger().warning("[Vote LandCraft] Il manque Essentials sur le serveur!");
			Bukkit.getPluginManager().disablePlugin(this);
		}
		getCommand("getpoints").setExecutor(new Vote(this));		
		getCommand("setpoints").setExecutor(new Vote(this));
		getCommand("vote").setExecutor(new Vote(this));
		pm.registerEvents(new VoteEvents(), this);
	}
	
	public void onDisable() {
		if(SQL.hasConnection())
			SQL.disconnectMySQL();
	}

}


