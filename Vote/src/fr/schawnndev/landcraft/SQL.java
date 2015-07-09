package fr.schawnndev.landcraft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class SQL {
	
	private String ip = null, database = null, user = null, password = null; 
	private int port;
	
	public SQL (String host, int port, String database, String user, String password){
		this.ip = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	public static Connection connection;
	
	public Connection connect(){
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + this.ip + ":" + this.porta + "/" + this.database, this.user, this.password);
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
