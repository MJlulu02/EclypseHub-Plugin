package Plugins.EclypseHub.PluginsManageur.EventList;

import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class HubProtectSpawn implements Listener {

	@EventHandler
	public void antiDrop(PlayerDropItemEvent e) {

		e.setCancelled(true);
	}

	@EventHandler
	public void blockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPermission("Eclypse.blockbreak")) {
			player.sendMessage("§9§l» §bVous ne pouvez pas Casser");
			e.setCancelled(true);
		}

		if (player.hasPermission("Eclypse.blockbreak")) {
			e.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onrain(WeatherChangeEvent e)  {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (!player.isOp()) {
			player.sendMessage("§9§l» §bVous ne pouvez pas poser de block");
			e.setCancelled(true);
		}
		if (player.isOp()) {
			e.setCancelled(false);
		}

	}

	@SuppressWarnings("static-access")
	@EventHandler
	public void onAntiDamage(EntityDamageByEntityEvent e) {

		Entity player = e.getEntity();
		Entity pls = e.getDamager();

		if (player instanceof Player) {
			if (e.getCause() == e.getCause().ENTITY_ATTACK) {
				pls.sendMessage("§9§l» §bVous ne pouvez pas pvp");
				e.setCancelled(true);
				return;
			}
		}

	}

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void oncommandUse(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		// String m = e.getMessage();

		List<String> commands = Arrays.asList("minecraft:me", "version", "rl", "minecraft:about", "pl", "plugins",
				"minecraft:version", "minecraft:pl", "minecraft:plugins", "minecraft:rl", "op", "deop", "minecraft:op",
				"minecraft:deop", "me", "about", "help", "minecraft:help", "ban", "minecraft:ban", "kick",
				"minecraft:kick", "minecraft:pardon", "pardon");

		if (commands.contains(e.getMessage().split(" ")[0].replace("/", ""))) {
			if (!p.isOp()) {
				e.setCancelled(true);
				p.sendMessage("§9§l» §bVous n'avez pas accès à la commande");
				return;
			}
		}

	}

	@EventHandler
	public void onmooveitem(InventoryMoveItemEvent e) {
		e.setCancelled(true);
	}
}
