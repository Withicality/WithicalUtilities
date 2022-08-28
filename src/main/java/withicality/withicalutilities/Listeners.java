package withicality.withicalutilities;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    private final UtilityPlugin plugin;

    public Listeners(UtilityPlugin plugin) {
        this.plugin = plugin;
        addAll();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        ScoreboardManager.addBoard(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        ScoreboardManager.removeBoard(player);
    }

    public static FastBoard getBoard(Player player) {
        return ScoreboardManager.getBoard(player);
    }

    public static void addAll() {
        Bukkit.getOnlinePlayers().forEach(ScoreboardManager::addBoard);
    }

    private void defaultboard(Player player, FastBoard board) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }
}