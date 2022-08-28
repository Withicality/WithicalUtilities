package withicality.withicalutilities.entity;

import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import withicality.withicalutilities.ScoreboardManager;

import java.util.Arrays;
import java.util.List;

public class WPlayer {
    private final Player player;

    public WPlayer(Player player) {
        this.player = player;
    }

    public void setScoreboard(String title, List<String> lines) {
        FastBoard board = ScoreboardManager.getBoard(player);
        board.updateTitle(title);
        board.updateLines(lines);
    }

    public void resetScoreboard() {
        ScoreboardManager.removeBoard(player);
        ScoreboardManager.addBoard(player);
    }

    public void setScoreboard(String title, String... lines) {
        setScoreboard(title, Arrays.asList(lines));
    }

}
