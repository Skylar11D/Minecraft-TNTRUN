package org.skylar11d.minecraftp.tntrun.utilities.tasks.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.assets.TitleType;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.GameTask;

import java.util.Map;

public class WaitingTask extends GameTask {

    private int counter = 20;

    public WaitingTask(GameManager manager, RunnerManager runnerManager){
        super(manager, runnerManager);
    }

    @Override
    public void run() {
        Location startingArena = Main.getInstance().getLocations().of("starting");

        int size = Bukkit.getServer().getOnlinePlayers().size();
        Map<Runner, BukkitRunnable> runners = runnerManager.getRunners();

        if(size < 2){
            counter = 20;
            runners.keySet().forEach(
                    r -> r.sendTitle(Main.C("&fthere must be at least &c2 players"),
                            "",
                            TitleType.SUBTITLE)
            );
        } else {
            runners.keySet().forEach(r -> r.sendTitle(Main.C("&c"+size+" &fWaiting players"), "", TitleType.SUBTITLE));
            counter--;

            if(counter % 5 == 0){
                Bukkit.broadcastMessage(Main.C("The game will start in the next &c"+counter));
                runners.keySet().forEach(r -> r.getPlayer().playSound(r.getPlayer().getLocation(), Sound.NOTE_PIANO, 5f, 5f));
            }

            if(counter == 0){
                gameManager.setGameState(GameState.STARTING);
                runners.keySet().forEach(r -> r.getPlayer().teleport(startingArena));
                cancel();

            }
        }

    }
}
