package Plugins.EclypseHub.PluginsManageur.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Plugins.EclypseHub.PluginsManageur.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandStaffHelp implements CommandExecutor {

	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;

			if (args.length == 0) {
				p.sendMessage("§9§l» §bLa Commande est §f: §7/§6staffhelp <message>");
			}

			if (args.length >= 1) {

				StringBuilder bc = new StringBuilder();
				for (String part : args) {
					bc.append(part + " ");
				}

				for (Player pls : Bukkit.getOnlinePlayers()) {
					if (Main.instance.PlayerGradeSupport.contains(Main.instance.getGrade(pls.getUniqueId()))) {
						TextComponent message = new TextComponent();
						message.setText("§9§l» §2Envoyer un message privé");
						message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + p.getName() + " "));
						message.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
								new ComponentBuilder(ChatColor.DARK_AQUA + "Envoyer un message privé à §b" + p.getName()).create()));
						
						TextComponent teleport = new TextComponent();
						teleport.setText("§9§l» §4Téléportation ");
						teleport.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getName()));
						teleport.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
								new ComponentBuilder(ChatColor.DARK_AQUA + "Téléportation à §b" + p.getName()).create()));
						
						pls.sendMessage("§9§l» §3" + p.getName() +" §bà besoin d'aide. Voici son message §7: §f" + bc);
						pls.sendMessage(" ");
						pls.spigot().sendMessage(teleport);
						pls.sendMessage(" ");
						pls.spigot().sendMessage(message);
						pls.sendMessage(" ");
						
					}
				}

				p.sendMessage("§9§l» §3Votre demande d'aide à bien était envoyé");

			}

		}

		return false;
	}

}