package fr.schawnndev.landcraft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class SQL {
	
	String ipa = null, databasea = null, usera = null, passworda = null, p = ":"; 
	int porta;
	
	public SQL (String host, int port, String database, String user, String password){
		this.ipa = host;
		this.porta = port;
		this.databasea = database;
		this.usera = user;
		this.passworda = password;
	}
	
	public static Connection connection;
	
	public Connection connect(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + this.ipa + ":" + this.porta + "/" + this.databasea, this.usera, this.passworda);
			Bukkit.getLogger().info("[Vote LandCraft] MySQL => En fonctionnement !");
		} catch (SQLException e) {
		Bukkit.getLogger().severe("[Vote LandCraft] Erreur MySQL => " + e.getMessage());
		}
		
		return connection;
	}
	
	public static void disconnectMySQL(){
		try {
			if(!connection.isClosed() || hasConnection())
				connection.close();
		} catch (SQLException e) {
			Bukkit.getLogger().severe("[Vote LandCraft] Erreur MySQL => " + e.getMessage());
		}
	}
	
	public static boolean hasConnection() {
		if(connection != null)
			return true;
		
		return false;
		
	}
	
	public static Connection getConnexion(){
		return connection;
	}

}
