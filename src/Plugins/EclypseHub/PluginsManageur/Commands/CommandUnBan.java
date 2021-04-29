package Plugins.EclypseHub.PluginsManageur.Commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.BanList.Type;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Plugins.EclypseHub.PluginsManageur.Main;

public class CommandUnBan implements CommandExecutor {

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (Main.instance.PlayerGradeNoSupport.contains(Main.instance.getGrade(p.getUniqueId()))) {
				if (args.length < 1 || args.length > 2) {
					p.sendMessage("§9§l» §bLa Commande est §f: §7/§6unban §7<§ePlayer§7>");
					return true;
				}

				OfflinePlayer pget = Bukkit.getOfflinePlayer(args[0]);
				Bukkit.broadcastMessage("§9§l» §3" + pget.getName() + " UnBan par §3" + p.getName());
				Bukkit.getBanList(Type.NAME).pardon(pget.getName());
				try {
					Main.instance.UpdateBanPlayer(pget.getUniqueId(), 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				p.sendMessage("§9§l» §bVous n'avez pas accès à la commande");
				return true;
			}
		}

		return false;
	}

}
