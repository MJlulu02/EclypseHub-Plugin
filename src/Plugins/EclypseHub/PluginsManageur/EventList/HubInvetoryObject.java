package Plugins.EclypseHub.PluginsManageur.EventList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import Plugins.EclypseHub.PluginsManageur.Main;

public class HubInvetoryObject implements Listener {

	@SuppressWarnings("static-access")
	public Inventory CreateHubGui(Player p) {
		Inventory HubGui = Bukkit.createInventory(null, 54, "§l§bNavigation Menu");
		ItemStack glass = Main.instance.Utils.CreateItem(Material.STAINED_GLASS_PANE, 9, " ");
		String grade = Main.instance.getGrade(p.getUniqueId());

		// Set Item Glass Pane
		HubGui.setItem(0, glass);
		HubGui.setItem(1, glass);
		HubGui.setItem(7, glass);
		HubGui.setItem(8, glass);
		HubGui.setItem(9, glass);
		HubGui.setItem(17, glass);
		HubGui.setItem(36, glass);
		HubGui.setItem(44, glass);
		HubGui.setItem(45, glass);
		HubGui.setItem(46, glass);
		HubGui.setItem(52, glass);
		HubGui.setItem(53, glass);

		HubGui.setItem(12,
				Main.instance.Utils.CreateItemLore5(Material.GOLDEN_APPLE, 0, " ", "§8§l→ §fFabriquez vous un Stuff",
						"§8§l→ §fRecevez un Rôle", "§8§l→ §fAffrontez vos Ennemis",
						"§8§l→ §fPour Arriver jusqu'à la Victoire", "§9§l» §1§k!!§3LG UHC§1§k!!"));
		HubGui.setItem(14,
				Main.instance.Utils.CreateItemLore4(Material.FEATHER, 0, " ", "§8§l→ §fFabriquez vous un Stuff",
						"§8§l→ §fAffrontez vos Ennemis dans Différents Modes de Jeux",
						"§8§l→ §fPour Arriver jusqu'à la Victoire", "§9§l» §6§k!!§eUHC+§6§k!!"));
		HubGui.setItem(20,
				Main.instance.Utils.CreateItemLore4(Material.DIAMOND_SWORD, 0, " ",
						"§8§l→ §fMontrez que vous êtes le plus fort", "§8§l→ §fEliminer 1 par 1 vos Ennemis",
						"§8§l→ §fDans l'arène d'Eclypse", "§9§l» §4§k!!§cPractice§4§k!!"));
		HubGui.setItem(24, Main.instance.Utils.CreateItem(Material.SLIME_BALL, 0, "§4Prochainement..."));
		HubGui.setItem(31,
				Main.instance.Utils.CreateItemSkull(Material.SKULL_ITEM, SkullType.PLAYER.ordinal(),
						"§5§k!!§dProfil§5§k!!", " ", "§8§l→ §7Grade : §f" + grade ,
						"§8§l→ §7ECCoins : §f0.00 ⛁", "§8§l→ §7Eclypsia : §f0.00 ⛁", "§8§l→ §7Temps : §f0,00 H", p));
		HubGui.setItem(38, Main.instance.Utils.CreateItem(Material.GOLD_INGOT, 0, "§f§k!!§7Boutique§f§k!!"));
		HubGui.setItem(42, Main.instance.Utils.CreateItemLore(Material.BOOK_AND_QUILL, 0, "§feclypsemc.fr",
				"§fMerci de jouer sur §3§nEclypse§r §f!"));

		return HubGui;
	}

	public String GetHubGuiName() {
		return "§l§bNavigation Menu";
	}

	public String GetHubLgUhcName() {
		return "§9§l» §1§k!!§3LG UHC§1§k!!";
	}

	public String GetHubUhcName() {
		return "§9§l» §6§k!!§eUHC+§6§k!!";
	}

	public String GetHubPracticeName() {
		return "§9§l» §4§k!!§cPractice§4§k!!";
	}

}
