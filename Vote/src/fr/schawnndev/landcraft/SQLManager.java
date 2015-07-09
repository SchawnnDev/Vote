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
			ResultSet rs = st.executeQuery("SELECT * FROM test WHERE player_name='"+ player.getName() +"'");
			while(rs.next())
				if(rs != null)
					return true;
			
			st.close();
			rs.close();
		} catch (SQLException e) {
			Bukkit.getLogger().severe("[Vote LandCraft] Erreur MySQL => " + e.getMessage());
		}
		
		return false;
	}
	
	public static boolean hasPoints(Player player) {
		if (isPlayerInDB(player) && getPoints(player) >= 1)	
			return true;
		
		return false;
	}
	
	public static void supprimerPoints(Player player){
		if(hasPoints(player))
			setPoints(player, 0);
	}
	
	public static void setPoints(Player player, int points){

		try {
			Statement st = SQL.getConnexion().createStatement();
			
			if(isPlayerInDB(player))
				st.executeUpdate("UPDATE test SET pointsFactions='"+points+"' WHERE player_name='"+player.getName()+"'");

			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static int getPoints(Player player) {
		int points = 0;
		
		try {
			Statement st = SQL.getConnexion().createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM test WHERE player_name='" + player.getName() + "'");

			while (rs.next()) 
                           points = rs.getInt(2);

			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
	}
	
	
	
	
}
