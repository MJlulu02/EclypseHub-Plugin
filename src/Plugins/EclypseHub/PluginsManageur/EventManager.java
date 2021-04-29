package Plugins.EclypseHub.PluginsManageur;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import Plugins.EclypseHub.PluginsManageur.EventList.HubChatManager;
import Plugins.EclypseHub.PluginsManageur.EventList.HubInventoryManager;
import Plugins.EclypseHub.PluginsManageur.EventList.HubInvetoryObject;
import Plugins.EclypseHub.PluginsManageur.EventList.HubJoinPlayer;
import Plugins.EclypseHub.PluginsManageur.EventList.HubProtectSpawn;

public class EventManager {

	public static void registerEvents(Plugin pl) {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new HubInvetoryObject(), pl);
		pm.registerEvents(new HubJoinPlayer(), pl);
		pm.registerEvents(new HubInventoryManager(), pl);
		pm.registerEvents(new HubProtectSpawn(), pl);
		pm.registerEvents(new HubChatManager(), pl);
	}
}
