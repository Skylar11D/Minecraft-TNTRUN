package org.skylar11d.minecraftp.tntrun.utilities.tasks.game;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.GameTask;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class EndingTask extends GameTask {

    private int counter = 7;

    public EndingTask(GameManager gameManager){
        super(gameManager, null);
    }
    public EndingTask(GameManager gameManager, RunnerManager runnerManager){
        super(gameManager, runnerManager);
    }

    @Override
    public void run() {

        counter--;
        if(counter <= 0)Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"restart");

    }
}
