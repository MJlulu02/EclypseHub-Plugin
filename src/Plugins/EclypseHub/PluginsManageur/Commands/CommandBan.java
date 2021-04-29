package Plugins.EclypseHub.PluginsManageur.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Plugins.EclypseHub.PluginsManageur.Main;

public class CommandBan implements CommandExecutor {

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (Main.instance.PlayerGradeNoSupport.contains(Main.instance.getGrade(p.getUniqueId()))) {
				if (args.length < 1 || args.length < 2) {
					p.sendMessage("§9§l» §bLa Commande est §f: §7/§6ban §7<§ePlayer§7> §7<§eRaison§7>");
					return true;
				}

				StringBuilder bc = new StringBuilder();
				for (String part : args) {
					bc.append(part + " ");
				}

				Player pget = Bukkit.getPlayer(args[0]);
				Bukkit.broadcastMessage(
						"§9§l» §3" + pget.getName() + " §bà était banni par §3" + p.getName() + " §bpour : §3" + bc);
				pget.kickPlayer("§4Banned by " + p.getName() + "\n§bRaison §7: §3" + bc);
				pget.setBanned(true);
				Main.instance.UpdateBanPlayer(pget.getUniqueId(), 1);
			} else {
				p.sendMessage("§9§l» §bVous n'avez pas accès à la commande");
				return true;
			}
		}

		return false;
	}

}
