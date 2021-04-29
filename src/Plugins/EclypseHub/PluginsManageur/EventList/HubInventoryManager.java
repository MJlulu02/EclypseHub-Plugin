package Plugins.EclypseHub.PluginsManageur.EventList;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import Plugins.EclypseHub.PluginsManageur.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class HubInventoryManager implements Listener {

	@EventHandler
	public void onclick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack inv = e.getItem();

		if (inv == null)
			return;

		if (inv.getType() == Material.COMPASS
				&& inv.getItemMeta().getDisplayName().equalsIgnoreCase("§l§bNavigation")) {

			p.openInventory(Main.instance.HubManager.HubInvetoryObject.CreateHubGui(p));
		}

		if (inv.getType() == Material.INK_SACK) {
			if (!Main.instance.PlayerHider.contains(p)) {
				Main.instance.PlayerHider.add(p);

				p.getInventory().setItem(8,
						Main.instance.Utils.CreateItem(Material.INK_SACK, 8, "§bSee Player §7(§4Off§7)"));

				title("§6Vous Ne Voyez Plus les joueurs !", p);

				for (final Player pls : Bukkit.getOnlinePlayers()) {
					p.hidePlayer(pls);
				}

			} else if (Main.instance.PlayerHider.contains(p)) {

				new BukkitRunnable() {
					int time = 12;

					@Override
					public void run() {

						if (time == 12) {
							title("§25", p);
						} else if (time == 10) {
							title("§a4", p);
						} else if (time == 8) {
							title("§63", p);
						} else if (time == 6) {
							title("§e2", p);
						} else if (time == 4) {
							title("§41", p);
						} else if (time == 2) {
							title("§6Vous Voyez les joueurs !", p);

							p.getInventory().setItem(8,
									Main.instance.Utils.CreateItem(Material.INK_SACK, 10, "§bSee Player §7(§2On§7)"));

							for (final Player pls : Bukkit.getOnlinePlayers()) {
								p.showPlayer(pls);
							}

							Main.instance.PlayerHider.remove(p);

							this.cancel();
						}

						time--;
					}
				}.runTaskTimer(Main.instance, 12, 12);
			}
		}

	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		ItemStack currentItem = e.getCurrentItem();

		if (inv.getName().equalsIgnoreCase(Main.instance.HubManager.HubInvetoryObject.GetHubGuiName())) {
			e.setCancelled(true);
			if (currentItem.getType() == Material.GOLDEN_APPLE && currentItem.getItemMeta().getDisplayName()
					.equalsIgnoreCase(Main.instance.HubManager.HubInvetoryObject.GetHubLgUhcName())) {

				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(b);
				try {
					out.writeUTF("Connect");
					out.writeUTF("lguhc");
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				p.sendPluginMessage(Main.instance, "BungeeCord", b.toByteArray());
				p.sendMessage("§9§l» §bConnexion en cours au Serveur Lg UHC...");
				p.closeInventory();
			}
		}

		if (inv.getName().equalsIgnoreCase(Main.instance.HubManager.HubInvetoryObject.GetHubGuiName())) {
			e.setCancelled(true);
			if (currentItem.getType() == Material.FEATHER && currentItem.getItemMeta().getDisplayName()
					.equalsIgnoreCase(Main.instance.HubManager.HubInvetoryObject.GetHubUhcName())) {

				ByteArrayOutputStream b = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(b);
				try {
					out.writeUTF("Connect");
					out.writeUTF("uhc");
					out.flush();
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				p.sendPluginMessage(Main.instance, "BungeeCord", b.toByteArray());
				p.sendMessage("§9§l» §bConnexion en cours au Serveur UHC+...");
				p.closeInventory();
			}
		}

		if (inv.getName().equalsIgnoreCase(Main.instance.HubManager.HubInvetoryObject.GetHubGuiName())) {
			e.setCancelled(true);
			if (currentItem.getType() == Material.DIAMOND_SWORD) {

				p.sendMessage("§9§l» §bLe Practice arrive prochainement...");
				p.closeInventory();
			}
		}

		if (inv.getName().equalsIgnoreCase(Main.instance.HubManager.HubInvetoryObject.GetHubGuiName())) {
			e.setCancelled(true);
			if (currentItem.getType() == Material.GOLD_INGOT) {

				p.sendMessage("§9§l» §bLa Boutique arrive prochainement...");
				p.closeInventory();
			}
		}

	}

	private void title(String name, Player p) {
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\" \"}"),
				20, 40, 20);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(title);

		PacketPlayOutTitle subtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE,
				ChatSerializer.a("{\"text\":\"" + name + "\"}"), 100, 20, 20);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(subtitle);
	}
}