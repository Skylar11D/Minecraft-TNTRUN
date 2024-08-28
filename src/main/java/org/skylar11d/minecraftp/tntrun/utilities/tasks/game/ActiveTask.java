package org.skylar11d.minecraftp.tntrun.utilities.tasks.game;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.GameTask;

import java.util.Set;

public class ActiveTask extends GameTask {

    private RunnerManager runnerManager;

    public ActiveTask(GameManager gameManager, RunnerManager runnerManager){
        super(gameManager, runnerManager);
    }

    @Override
    public void run() {

        Set<Runner> runners = runnerManager.getRunners().keySet();

        if(runners.size() < 2){

            Bukkit.getServer().getOnlinePlayers().forEach(
                    o -> o.playSound(o.getLocation(), Sound.LEVEL_UP, 5f, 5f)
            );

            announceWinner(runners.stream().findFirst().get());

            gameManager.setGameState(GameState.ENDING);
            cancel();

        }

    }

    private void announceWinner(Runner runner){
        Bukkit.broadcastMessage("§7§l§m=========================");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§e"+runner.getPlayer().getName() + " §a§lWON THE GAME");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("§7§l§m=========================");

        runner.setVictories(runner.getVictories()+1);
    }

}
