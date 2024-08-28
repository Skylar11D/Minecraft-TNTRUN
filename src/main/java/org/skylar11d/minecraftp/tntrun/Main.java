package org.skylar11d.minecraftp.tntrun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigManager;
import org.skylar11d.minecraftp.tntrun.utilities.config.ConfigType;
import org.skylar11d.minecraftp.tntrun.utilities.manager.locations.ArenaLocation;
import org.skylar11d.minecraftp.tntrun.utilities.manager.runner.RunnerManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameManager;
import org.skylar11d.minecraftp.tntrun.utilities.manager.game.GameState;
import org.skylar11d.minecraftp.tntrun.utilities.plugin.Provider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Predicate;

/**
 *  @Author Skylar11D
 */

public class Main extends JavaPlugin {

    private Provider provider;
    private static volatile Main instance;
    private GameManager gameManager;
    private RunnerManager runnerManager;
    private ArenaLocation locationManager;
    private ConfigManager configManager;

    private ExecutorService pool;

    @Override
    public void onEnable() {
        long timer = System.currentTimeMillis();
        Bukkit.getLogger().info("initializing plugin's declared variables..");
        setPool(pool);
        instance = this;
        provider = new MainProvider(this);
        gameManager = new GameManager(this, GameState.WAITING);
        runnerManager = new RunnerManager();
        configManager = new ConfigManager();
        locationManager = new ArenaLocation();

        provider.onEnable();

        Bukkit.getLogger().info("setting plugin's properties up..");
        Bukkit.getOnlinePlayers().forEach(runnerManager::registerRunner);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
        Bukkit.getServer().getWorld("world").setAutoSave(false);

        Bukkit.getLogger().info("setting up plugin's files..");
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        long time = (System.currentTimeMillis() - timer);
        Bukkit.getLogger().info("This plugin has been loaded within "+time+"ms");
    }

    public static Main getInstance() {
        return instance;
    }

    public Provider getProvider() {
        return provider;
    }

    public static String C(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public RunnerManager getRunnerManager() {
        return runnerManager;
    }

    public ArenaLocation getLocations() {
        return locationManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public ExecutorService getPool() {
        return pool;
    }
    private void setPool(ExecutorService pool){
        final FileConfiguration settings = configManager.getConfig(ConfigType.SETTINGS);
        final int settingsNumber = settings.getInt("threads");
        final Predicate<Integer> integerPredicate = (i) -> i != -1;
        final int decidedNumber = (integerPredicate.test(settingsNumber)) ? settingsNumber : Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(decidedNumber);
    }
}
