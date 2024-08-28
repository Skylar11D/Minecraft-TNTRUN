package org.skylar11d.minecraftp.tntrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.skylar11d.minecraftp.tntrun.Main;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity().getPlayer();

       Main.getInstance().getPool().execute(() -> {
           Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), () -> p.spigot().respawn(), 4);
       });
    }

}
