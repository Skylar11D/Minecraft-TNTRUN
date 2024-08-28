package org.skylar11d.minecraftp.tntrun.utilities.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

public abstract class GameTask extends BukkitRunnable {

    protected GameManager gameManager;
    protected RunnerManager runnerManager;

    public GameTask(GameManager gameManager, RunnerManager runnerManager){
        this.gameManager = gameManager;
        this.runnerManager = runnerManager;
    }

}
