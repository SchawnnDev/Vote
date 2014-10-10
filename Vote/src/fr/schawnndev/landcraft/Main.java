package fr.schawnndev.landcraft;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private String host = getConfig().getString("Host");
	private String database = getConfig().getString("Database");
	private String user = getConfig().getString("Identifiant");
	private String password = getConfig().getString("MotDePasse");

	public void onEnable() {
		//TODO:	PluginManager pm = getServer().getPluginManager();
		getConfig().options().copyDefaults(true);
		saveConfig();
		if(host.length() != 0 && database.length() != 0 && user.length() != 0 && password.length() != 0){
		new SQL(host, 3306, database, user, password);
		} else {
			Bukkit.getLogger().severe("[Vote LandCraft] MySQL => Idenfiants faux sont egals a 0 ! (Config.yml)");
		}
	}
	
	public void onDisable() {
		if(SQL.hasConnection())
			SQL.disconnectMySQL();
	}

}


