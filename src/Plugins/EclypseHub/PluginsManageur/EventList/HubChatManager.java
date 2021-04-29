package Plugins.EclypseHub.PluginsManageur.EventList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import Plugins.EclypseHub.PluginsManageur.Main;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("deprecation")
public class HubChatManager implements Listener {

	@SuppressWarnings("static-access")
	@EventHandler
	public void onchat(PlayerChatEvent e) {
		Player p = e.getPlayer();

		String VerifGrade = Main.instance.getGrade(p.getUniqueId());

		if (VerifGrade.equalsIgnoreCase("Admin")) {
			setFormatChat(VerifGrade, "§4", p, e);
		} else if (VerifGrade.equalsIgnoreCase("Modérateur")) {
			setFormatChat(VerifGrade, "§9", p, e);
		} else if (VerifGrade.equalsIgnoreCase("Développeur")) {
			setFormatChat(VerifGrade, "§2", p, e);
		} else if (VerifGrade.equalsIgnoreCase("Support")) {
			setFormatChat(VerifGrade, "§c", p, e);
		} else if (VerifGrade.equalsIgnoreCase("Eclypse")) {
			setFormatChat(VerifGrade, "§3", p, e);
		} else if (VerifGrade.equalsIgnoreCase("UltraVIP")) {
			setFormatChat(VerifGrade, "§2", p, e);
		} else if (VerifGrade.equalsIgnoreCase("VIP")) {
			setFormatChat(VerifGrade, "§a", p, e);
		} else if (VerifGrade.equalsIgnoreCase("Dunkiste")) {
			setFormatChat(VerifGrade, "§6", p, e);
		} else if (VerifGrade.equalsIgnoreCase("Deus")) {
			setFormatChat(VerifGrade, "§5", p, e);
		}

		else

		{

			e.setFormat("§7" + p.getName() + " §8: §7" + e.getMessage());
		}

	}

	private void setFormatChat(String grade, String prefixColor, Player p, PlayerChatEvent e) {
		e.setFormat(ChatColor.translateAlternateColorCodes('&',
				prefixColor + grade + " - " + p.getName() + " §8: §f" + e.getMessage()));
	}
}
