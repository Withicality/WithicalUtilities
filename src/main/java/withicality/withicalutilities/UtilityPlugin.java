package withicality.withicalutilities;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import withicality.withicalutilities.event.PlayerUpdateEvent;

public class UtilityPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        boolean isPaper = false;
        try {
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            APIManager.paper();
        } catch (ClassNotFoundException ignored) {
        }

        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                PlayerUpdateEvent event = new PlayerUpdateEvent(player);
                Bukkit.getPluginManager().callEvent(event);
            }
        }, 0L, 1L);
    }
}