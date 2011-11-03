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
		log.info("[RealName] RealName has been enabled");
	}

	public void onDisable() {
		log.info("[RealName] RealName has been disabled");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,
			String commandLabel, String[] args) {
		Server server = sender.getServer();
		Player player = (Player) sender;
		if (cmd.getLabel().equalsIgnoreCase("rname") && args.length == 1
				|| cmd.getLabel().equalsIgnoreCase("realname")
				&& args.length == 1) {
			Player[] plist = server.getOnlinePlayers();
			for (int i = plist.length - 1; i > -1; i--) {
				String pname = plist[i].getDisplayName();
				if (pname.contains(args[0])) {
					sender.sendMessage(ChatColor.YELLOW + pname
							+ "'s real name is " + plist[i].getName());
					return true;
				}
			}
			sender.sendMessage(ChatColor.RED + "No players found.");
			return true;
		}
		if (cmd.getLabel().equalsIgnoreCase("dname")
				|| cmd.getLabel().equalsIgnoreCase("displayname")
				&& args.length == 1) {
			if (server.getPlayer(args[0]) != null) {
				Player plr = server.getPlayer(args[0]);
				player.sendMessage(ChatColor.RED + "Player's display name is "
						+ plr.getDisplayName() + ".");
			} else {
				player.sendMessage(ChatColor.RED + "No Players Found");
			}
			return true;
		}
		return false;
	}
}
