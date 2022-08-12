package withicality.withicalapi;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import withicality.withicalapi.event.PlayerUpdateEvent;

public class WithicalAPI extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                PlayerUpdateEvent event = new PlayerUpdateEvent(player);
                Bukkit.getPluginManager().callEvent(event);
            }
        }, 0L, 1L);
    }
}