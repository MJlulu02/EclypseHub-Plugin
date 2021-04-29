package Plugins.EclypseHub.PluginsManageur;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {

	public ItemStack CreateItemSkull(Material Material, int MaterialShort, String Name, String Lore1, String Lore2,
			String Lore3, String Lore4, String Lore5, Player p) {
		ItemStack item = new ItemStack(Material, 1, (byte) MaterialShort);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setOwner(p.getName());
		meta.setDisplayName(Name);
		meta.setLore(Arrays.asList(Lore1, Lore2, Lore3, Lore4, Lore5));
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack CreateItem(Material Material, int MaterialShort, String Name) {
		ItemStack item = new ItemStack(Material, 1, (byte) MaterialShort);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Name);
		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack CreateItemLore(Material Material, int MaterialShort, String Lore, String Name) {
		ItemStack item = new ItemStack(Material, 1, (byte) MaterialShort);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Name);
		itemMeta.setLore(Arrays.asList(Lore));
		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack CreateItemLore3(Material Material, int MaterialShort, String Lore1, String Lore2, String Lore3,
			String Name) {
		ItemStack item = new ItemStack(Material, 1, (byte) MaterialShort);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Name);
		itemMeta.setLore(Arrays.asList(Lore1, Lore2, Lore3));
		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack CreateItemLore4(Material Material, int MaterialShort, String Lore1, String Lore2, String Lore3,
			String Lore4, String Name) {
		ItemStack item = new ItemStack(Material, 1, (byte) MaterialShort);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Name);
		itemMeta.setLore(Arrays.asList(Lore1, Lore2, Lore3, Lore4));
		item.setItemMeta(itemMeta);
		return item;
	}

	public ItemStack CreateItemLore5(Material Material, int MaterialShort, String Lore1, String Lore2, String Lore3,
			String Lore4, String Lore5, String Name) {
		ItemStack item = new ItemStack(Material, 1, (byte) MaterialShort);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Name);
		itemMeta.setLore(Arrays.asList(Lore1, Lore2, Lore3, Lore4, Lore5));
		item.setItemMeta(itemMeta);
		return item;
	}
}
