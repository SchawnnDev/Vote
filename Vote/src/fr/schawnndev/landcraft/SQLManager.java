package fr.schawnndev.landcraft;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SQLManager {

	public static boolean isPlayerInDB(Player player){
		try {
			Statement st = SQL.getConnexion().createStatement();
			ResultSet rs = null;
			rs = st.executeQuery("SELECT * FROM test WHERE player_name='"+ player.getName() +"'");
			while(rs.next()){
				if(rs != null)
					return true;
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			Bukkit.getLogger().severe("[Vote LandCraft] Erreur MySQL => " + e.getMessage());
		}
		
		return false;
	}
	
	public static boolean hasPoints(Player player) {
		if(getPoints(player) == 1)
			return true;
		else 
			return false;
	}
	
	public static void supprimerPoints(Player player){
		if(hasPoints(player)){
			setPoints(player, 0);
		}
	}
	
	public static void setPoints(Player player, int points){
		int a = 0;
		a = points;
		try {
			Statement st = SQL.getConnexion().createStatement();
			
			if(isPlayerInDB(player)){
				st.executeUpdate("UPDATE test SET pointsFactions='"+a+"' WHERE player_name='"+player.getName()+"'");
			} else {
				
			}
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static int getPoints(Player player) {
		int points = 0;
		String name = player.getName();
		try {
			Statement st = SQL.getConnexion().createStatement();
			ResultSet rs = null;

			rs = st.executeQuery("SELECT * FROM test WHERE player_name='" + name + "'");

			while (rs.next()) {
				points = rs.getInt(2); //TODO: getInt
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
	}
	
	
	
	
}
