package Plugins.EclypseHub.PluginsManageur;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import Plugins.EclypseHub.PluginsManageur.Commands.CommandBan;
import Plugins.EclypseHub.PluginsManageur.Commands.CommandGrade;
import Plugins.EclypseHub.PluginsManageur.Commands.CommandStaffHelp;
import Plugins.EclypseHub.PluginsManageur.Commands.CommandUnBan;
import Plugins.EclypseHub.PluginsManageur.EventList.HubManager;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

public class Main extends JavaPlugin {

	public static Main instance;

	public Utils Utils;
	public HubManager HubManager;

	public int AllPlayers = 0;

	public List<Player> PlayerHider = new ArrayList<Player>();

	private static Connection cnx;
	private static Statement st;
	private static ResultSet rst;

	private String gradeList[] = { "Admin", "Modérateur"};
	private String gradeList1[] = { "Admin", "Modérateur", "Support" };
	public List<String> PlayerGradeNoSupport = Arrays.asList(gradeList);
	public List<String> PlayerGradeSupport = Arrays.asList(gradeList1);
	
	public HashMap<UUID, PermissionAttachment> playerpermissions = new HashMap<>();

	public void onEnable() {
		System.out.println("[Ecplypse-Hub] Enabled");

		EventManager.registerEvents(this);
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

		Main.instance = this;

		this.Utils = new Utils();
		this.HubManager = new HubManager();
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();

		getCommand("grade").setExecutor(new CommandGrade());
		getCommand("staffhelp").setExecutor(new CommandStaffHelp());
		getCommand("ban").setExecutor(new CommandBan());
		getCommand("unban").setExecutor(new CommandUnBan());

		PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
		new BukkitRunnable() {

			@Override
			public void run() {

				try {
					Field a = packet.getClass().getDeclaredField("a");
					a.setAccessible(true);
					Field b = packet.getClass().getDeclaredField("b");
					b.setAccessible(true);

					Object header = new ChatComponentText("§bᐒ §3Eclypse §bᐘ\n§feclypsemc.fr");
					Object footer = new ChatComponentText("§9/staffhelp §f» contacter un §9staff §f!");

					a.set(packet, header);
					b.set(packet, footer);

					if (Bukkit.getOnlinePlayers().size() == 0)
						return;
					for (Player p : Bukkit.getOnlinePlayers()) {
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
					}

				} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}

			}
		}.runTaskTimer(this, 0, 20);
	}

	public void onDisable() {
		System.out.println("[Ecplypse-Hub] Disable");
		playerpermissions.clear();
	}
	
	/*
	 * 
	 * Permission File
	 * 
	 */
	
	public File getFile(String FileName) {
		return new File(getDataFolder(), FileName + ".yml");
	}
	
	public void setupPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(this);
        this.playerpermissions.put(player.getUniqueId(), attachment);
        permissionsSetter(player.getUniqueId());
    }

    private void permissionsSetter(UUID uuid) {
        PermissionAttachment attachment = this.playerpermissions.get(uuid);
        for (String groups : this.getConfig().getConfigurationSection("Groups").getKeys(false)) {
            for (String permissions : this.getConfig().getStringList("Groups." + groups + ".permissions")) {
                System.out.print(permissions);
                attachment.setPermission(permissions, true);
            }
        }
    }
	
	
	/*
	 * 
	 * Database Dependency
	 * 
	 */

	public static void UpdateGradeProfil(UUID uuid, String grade) {
		try {
			String query = "UPDATE tt_users SET grade = '" + grade + "' WHERE uuid = '" + uuid.toString()
					+ "'";
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void UpdateBanPlayer(UUID uuid, int banInt) {
		try {
			String query = "UPDATE tt_users SET ban = '" + banInt + "' WHERE uuid = '" + uuid.toString()
					+ "'";
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void AddProfil(String pseudo, UUID uuid) {
		try {
			String query = "INSERT INTO tt_users (`pseudo`,`uuid`) VALUES ('" + pseudo.toString() + "','"
					+ uuid.toString() + "')";
			cnx = connecterDB();
			st = cnx.createStatement();
			st.executeUpdate(query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean VerifProfilExist(UUID uuid) {
		String query = "SELECT * FROM tt_users WHERE uuid = '" + uuid.toString() + "'";
		cnx = connecterDB();
		try {
			st = cnx.createStatement();
			rst = st.executeQuery(query);
			rst.last();
			int nbrRow = rst.getRow();
			if (nbrRow != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}
	
	public static int VerifBannedPlayer(UUID uuid) {
		int banRow = 0;

		try {
			String query = "SELECT * FROM `tt_users` WHERE uuid = '" + uuid.toString() + "'";
			cnx = connecterDB();
			st = cnx.createStatement();
			rst = st.executeQuery(query);
			rst.last();
			banRow = rst.getInt("ban");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return banRow;
	}

	public static String getGrade(UUID uuid) {
		String grade = "";

		try {
			String query = "SELECT * FROM `tt_users` WHERE uuid = '" + uuid.toString() + "'";
			cnx = connecterDB();
			st = cnx.createStatement();
			rst = st.executeQuery(query);
			rst.last();
			grade = rst.getString("grade");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return grade;
	}

	private static Connection connecterDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/#db";
			String user = "#user";
			String pass = "#password";

			Connection cnx = DriverManager.getConnection(url, user, pass);

			return cnx;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
