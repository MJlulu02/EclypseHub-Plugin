package Plugins.EclypseHub.PluginsManageur.EventList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import Plugins.EclypseHub.PluginsManageur.Main;

public class HubJoinPlayer implements Listener {

	@SuppressWarnings({ "static-access" })
	@EventHandler
	public void playerjoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String getGrade = Main.instance.getGrade(p.getUniqueId());

		try {

			if (Main.instance.VerifBannedPlayer(p.getUniqueId()) == 1) {
				p.kickPlayer("§4Your are Banned by a operator");
				return;

			}

			if (Main.instance.VerifProfilExist(p.getUniqueId()) == false) {
				Main.instance.AddProfil(p.getName(), p.getUniqueId());
			}

			if (getGrade.equalsIgnoreCase("Admin")) {
				setJoinMessage(getGrade, "§4", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_BASS, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("Modérateur")) {
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_BASS_DRUM, 1f, 1f);
				}
				setJoinMessage(getGrade, "§9", p, e);
			} else if (getGrade.equalsIgnoreCase("Développeur")) {
				setJoinMessage(getGrade, "§2", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_BASS_GUITAR, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("Support")) {
				setJoinMessage(getGrade, "§c", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_PIANO, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("Eclypse")) {
				setJoinMessage(getGrade, "§3", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_SNARE_DRUM, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("UltraVIP")) {
				setJoinMessage(getGrade, "§2", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_SNARE_DRUM, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("VIP")) {
				setJoinMessage(getGrade, "§a", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_SNARE_DRUM, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("Dunkiste")) {
				setJoinMessage(getGrade, "§6", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_SNARE_DRUM, 1f, 1f);
				}
			} else if (getGrade.equalsIgnoreCase("Deus")) {
				setJoinMessage(getGrade, "§5", p, e);
				for (Player pls : Bukkit.getOnlinePlayers()) {
					pls.playSound(pls.getLocation(), Sound.NOTE_SNARE_DRUM, 1f, 1f);
				}
			}

			else

			{
				e.setJoinMessage(null);
				p.setPlayerListName("§7" + p.getName());
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		p.getInventory().clear();
		p.getInventory().setItem(4, Main.instance.Utils.CreateItem(Material.COMPASS, 0, "§l§bNavigation"));
		p.getInventory().setItem(8, Main.instance.Utils.CreateItem(Material.INK_SACK, 10, "§bSee Player §7(§2On§7)"));
		p.teleport(new Location(Bukkit.getServer().getWorld("world"), 735.415, 23, -1012.574));
		Main.instance.AllPlayers++;
		
		Main.instance.setupPermissions(p);

		ScoreboardManager m = Bukkit.getScoreboardManager();
		p.setScoreboard(getScoreboard(m, p, getGrade));

		new BukkitRunnable() {

			@Override
			public void run() {

				p.setScoreboard(getScoreboard(m, p, getGrade));

			}
		}.runTaskTimer(Main.instance, 150, 150);

	}

	private void setJoinMessage(String grade, String prefixColor, Player p, PlayerJoinEvent e) {
		e.setJoinMessage(prefixColor + grade + " - " + p.getName() + " §ba rejoint le §3Lobby §b!");
		p.setPlayerListName(prefixColor + grade + " - " + p.getName());
	}

	private Scoreboard getScoreboard(ScoreboardManager m, Player p, String Grade) {
		Scoreboard b = m.getNewScoreboard();

		Objective o = b.registerNewObjective("Gold", "");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName("§f• Eclypse •");

		getScores("§r", o).setScore(11);
		getScores("§8│ §7Pseudo §3» §f" + p.getName(), o).setScore(10);
		getScores("§8│ §7Grade §3» §f" + Grade, o).setScore(9);
		getScores("§8", o).setScore(8);
		getScores("§8│ §7ECCoins §3» §f0.00 ⛁", o).setScore(7);
		getScores("§8│ §7Eclypsia §3» §f0.00 ⛁", o).setScore(6);
		getScores("§1", o).setScore(5);
		getScores("§8│ §7Serveur §3» §bLobby", o).setScore(4);
		getScores("§8│ §7Joueurs §3» §b" + Main.instance.AllPlayers, o).setScore(3);
		getScores("§2", o).setScore(2);
		getScores("§8│ §3eclypsemc.fr", o).setScore(1);
		return b;
	}

	private Score getScores(String Name, Objective o) {
		Score score = o.getScore(Name);

		return score;
	}

	@EventHandler
	public void onquit(PlayerQuitEvent e) {
		Main.instance.AllPlayers--;
		e.setQuitMessage(null);
		
		Main.instance.playerpermissions.remove(e.getPlayer().getUniqueId());
	}

}
