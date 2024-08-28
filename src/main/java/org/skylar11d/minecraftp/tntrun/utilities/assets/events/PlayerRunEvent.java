package org.skylar11d.minecraftp.tntrun.utilities.assets.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.Runner;

/**
 *  @Author Skylar11D
 */

public class PlayerRunEvent extends Event implements Cancellable {

    private boolean cancelled = false;
    Runner runner;

    public PlayerRunEvent(Runner runner){

        this.runner = runner;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public void setCancelled(boolean b) {
        cancelled = b;
        Bukkit.getServer().getScheduler().cancelTask(Main.getInstance().getRunnerManager().getRunners().get(runner).getTaskId());
    }

    public Runner getRunner() {
        return runner;
    }



    private static final HandlerList handlerList = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
    public static HandlerList getHandlerList(){
        return handlerList;
    }

}
