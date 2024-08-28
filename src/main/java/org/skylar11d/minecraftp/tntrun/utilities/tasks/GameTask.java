package org.skylar11d.minecraftp.tntrun.utilities.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;

public abstract class GameTask extends BukkitRunnable {

    protected GameManager gameManager;

    public GameTask(GameManager gameManager){
        this.gameManager = gameManager;
    }

}
