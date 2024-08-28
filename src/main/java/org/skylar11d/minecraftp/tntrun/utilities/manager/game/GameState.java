package org.skylar11d.minecraftp.tntrun.utilities.manager.game;

import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.GameTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.EndingTask;

public enum GameState {

    ENDING(new EndingTask(Main.getInstance().getGameManager())),
    WAITING(new EndingTask(Main.getInstance().getGameManager())),
    STARTING(new EndingTask(Main.getInstance().getGameManager())),
    ACTIVE(new EndingTask(Main.getInstance().getGameManager()));

    private final GameTask gameTask;

    GameState(GameTask task) {
        gameTask = task;
    }

    public GameTask getTask() {
        return gameTask;
    }
}
