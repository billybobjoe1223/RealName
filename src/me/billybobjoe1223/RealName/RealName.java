package me.billybobjoe1223.RealName;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class RealName extends JavaPlugin {
	public static PermissionHandler permissionHandler;
	Logger log = Logger.getLogger("Minecraft");
	public void onEnable() {
		setupPermissions();
		log.info("[RealName] RealName has been enabled");
	}
	public void onDisable() {
		log.info("[RealName] RealName has been disabled");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Server server = sender.getServer();
		Player[] players = server.getOnlinePlayers();
		Player player = (Player) sender;
 		if(cmd.getLabel().equalsIgnoreCase("rname") && args.length == 1) {
			if(permissionHandler.has(player, "realname.rname")) {
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
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
			}
			return true;
		}
 		return false;
	}
	private void setupPermissions() {
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        log.info("Permission system not detected, defaulting to OP");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    log.info("[RealName] Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}
}
