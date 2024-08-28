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

    private final FileConfiguration settings = Main.getInstance().getConfigManager().getConfig(ConfigType.SETTINGS);
    private final int settingsNumber = settings.getInt("threads");
    private final Predicate<Integer> integerPredicate = (i) -> i != -1;
    private final int decidedNumber = (integerPredicate.test(settingsNumber)) ? settingsNumber : Runtime.getRuntime().availableProcessors();
    private final ExecutorService pool = Executors.newFixedThreadPool(decidedNumber);

    @EventHandler
    public void onRunning(PlayerRunEvent e){
        RunnerManager runnerManager = Main.getInstance().getRunnerManager();
        Predicate<Runner> runnerPredicate = runnerManager::isRegistered;

        if (!runnerPredicate.test(e.getRunner()))
            return;

        pool.execute(() -> runnerManager.getRunners().get(e.getRunner()).runTaskLater(Main.getPlugin(Main.class), 6));
    }

}
