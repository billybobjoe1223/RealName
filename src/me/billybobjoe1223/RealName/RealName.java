package me.billybobjoe1223.RealName;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class RealName extends JavaPlugin {
	Logger log = Logger.getLogger("Minecraft");
	public void onEnable() {
		log.info("RealName has been enabled");
	}
	public void onDisable() {
		log.info("RealName has been disabled");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Server server = sender.getServer();
		Player[] players = server.getOnlinePlayers();
		if(cmd.getLabel().equalsIgnoreCase("rname")) {
			if(args.length == 1) {
				int i;
				for(i = players.length - 1; i >= 0; i--) {
					if(args[0].equalsIgnoreCase(players[i].getDisplayName())) {
						sender.sendMessage(ChatColor.RED + "Player's real name is " + players[i].getName());
						return true;
					} else {
						if(i == 0) {
							sender.sendMessage(ChatColor.RED + "No Players Found");
						}
					}
				} 
				return true;
			}
		}
		return false;
	}
}
