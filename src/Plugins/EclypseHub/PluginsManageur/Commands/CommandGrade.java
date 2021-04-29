package Plugins.EclypseHub.PluginsManageur.Commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import Plugins.EclypseHub.PluginsManageur.Main;

public class CommandGrade implements CommandExecutor {

	@SuppressWarnings({ "static-access" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		Player p = (Player) sender;

		if (p instanceof Player) {

			Player pget = Bukkit.getPlayer(args[1]);

			if (Main.instance.PlayerGradeNoSupport.contains(Main.instance.getGrade(p.getUniqueId()))) {

				if (args.length == 0)

				{
					p.sendMessage("§9§l» §bLa Commande est §f: §7/§6grade §7<§eCreate§7/§eAdd§7/§eRemove§7>");
					return true;
				}

				if (args[0].equalsIgnoreCase("Create"))

				{

					if (args.length < 2 || args.length > 2) {
						p.sendMessage("§9§l» §bLa Commande est §f: §7/§6grade §7<§eCreate§7> §7<§eGrade§7>");
						return true;
					}

					if (f.getString("Groups.").contains(args[1]) && args.length == 2) {
						p.sendMessage("§9§l» §bCe grade existe déjà");
						return true;
					}
					
					AddCos(args[1]);
					p.sendMessage("§9§l» §bLe grade à bien était crée");
					return true;

				}

				else if (args[0].equalsIgnoreCase("Add"))

				{

					if (args.length < 3 || args.length > 3) {
						p.sendMessage(
								"§9§l» §bLa Commande est §f: §7/§6grade §7<§eAdd§7> §7<§ePlayer§7> §7<§eGrade§7>");
						return true;
					}

					else if (pget == null)

					{
						p.sendMessage("§9§l» §bLe Joueur n'est pas connecté");
						return true;
					}

					else if (f.getString("Groups").contains(args[2]) && args.length == 3)

					{
						Main.instance.UpdateGradeProfil(pget.getUniqueId(), args[2]);
						p.sendMessage("§9§l» §bLe grade du joueur §3" + pget.getName() + " §bà bien était Modifier");
						pget.kickPlayer("§9§l» §bVotre grade a été modifié");
						return true;
					}

					else

					{
						p.sendMessage("§9§l» §bLe Grade n'est pas existants");
						return true;
					}

				} else if (args[0].equalsIgnoreCase("Remove"))

				{
					if (args.length < 2 || args.length > 2) {
						p.sendMessage("§9§l» §bLa Commande est §f: §7/§6grade §7<§eRemove§7> §7<§ePlayer§7>");
						return true;
					}

					else if (pget == null)

					{
						p.sendMessage("§9§l» §bLe Joueur n'est pas connecté");
						return true;
					}

					else if (args.length == 2)

					{

						Main.instance.UpdateGradeProfil(pget.getUniqueId(), "Eclypsien");
						p.sendMessage("§9§l» §bLe grade du joueur §3" + pget.getName() + " §bà bien était retiré");
						pget.kickPlayer("§9§l» §bVotre grade a été modifié");
						return true;
					}

				}

				else

				{
					p.sendMessage("§9§l» §bLa Commande est §f: §7/§6grade §7<§eCreate§7/§eAdd§7/§eRemove§7>");
					return true;
				}

			}

			else

			{
				p.sendMessage("§9§l» §bVous n'avez pas accès à la commande");
			}
		}
		return false;
	}

	private FileConfiguration f = YamlConfiguration.loadConfiguration(Main.instance.getFile("config"));

	private void AddCos(String _Cos) {
		FileConfiguration f = YamlConfiguration.loadConfiguration(Main.instance.getFile("config"));
		String Cos = f.getString("Groups");
		Cos = Cos != null ? Cos : "";

		Cos += _Cos + ",";

		f.set("Groups", Cos);

		try {
			f.save(Main.instance.getFile("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
