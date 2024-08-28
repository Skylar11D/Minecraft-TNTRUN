package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.events.PlayerRunEvent;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigType;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;

import java.util.concurrent.*;
import java.util.function.Predicate;

public class PlayerRunningListener implements Listener {

    @EventHandler
    public void onRunning(PlayerRunEvent e){
        Main main = Main.getInstance();
        RunnerManager runnerManager = main.getRunnerManager();
        Predicate<Runner> runnerPredicate = runnerManager::isRegistered;

        if (!runnerPredicate.test(e.getRunner()))
            return;

        main.getPool().execute(
                () -> runnerManager.getRunners().get(e.getRunner()).runTaskLater(Main.getPlugin(Main.class), 6)
        );
    }

}
