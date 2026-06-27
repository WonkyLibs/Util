package com.wonkglorg.minecraft.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Players{
	
	/**
	 * This method checks the online player first (as bukkit does not cache offline players right after they joined the first time until an auto saves happens or they disconnect leading to {@link Bukkit#getOfflinePlayer(String)} to fail in these circumstances
	 *
	 * @param name the name of the player
	 * @return the offline player instance
	 */
	public static OfflinePlayer getOfflinePlayer(String name) {
		Player onlinePlayer = Bukkit.getPlayer(name);
		if(onlinePlayer != null){
			return onlinePlayer;
		}
		return Bukkit.getOfflinePlayer(name);
	}
	/**
	 * This method checks the online player first (as bukkit does not cache offline players right after they joined the first time until an auto saves happens or they disconnect leading to {@link Bukkit#getOfflinePlayer(String)} to fail in these circumstances
	 *
	 * @param uuid the uuid  of the player
	 * @return the offline player instance
	 */
	public static OfflinePlayer getOfflinePlayer(UUID uuid) {
		Player onlinePlayer = Bukkit.getPlayer(uuid);
		if(onlinePlayer != null){
			return onlinePlayer;
		}
		return Bukkit.getOfflinePlayer(uuid);
	}
	
}
