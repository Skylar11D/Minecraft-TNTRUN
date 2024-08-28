package org.skylar11d.minecraftp.tntrun.utilities.manager.game;

import org.skylar11d.minecraftp.tntrun.Main;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.GameTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.ActiveTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.EndingTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.StartingTask;
import org.skylar11d.minecraftp.tntrun.utilities.tasks.game.WaitingTask;

public enum GameState {

    WAITING(new WaitingTask(Main.getInstance().getGameManager(), Main.getInstance().getRunnerManager())),
    STARTING(new StartingTask(Main.getInstance().getGameManager(), Main.getInstance().getRunnerManager())),
    ACTIVE(new ActiveTask(Main.getInstance().getGameManager(), Main.getInstance().getRunnerManager())),
    ENDING(new EndingTask(Main.getInstance().getGameManager()));

    private final GameTask gameTask;

    GameState(GameTask task) {
        gameTask = task;
    }

    public GameTask getTask() {
        return gameTask;
    }
}
