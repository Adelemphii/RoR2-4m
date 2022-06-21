package tech.adelemphii.ror24m;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.java.JavaPlugin;
import tech.adelemphii.ror24m.commands.TestCommand;
import tech.adelemphii.ror24m.directors.SceneDirector;

public final class RoR24m extends JavaPlugin {

    private PaperCommandManager commandManager;
    private SceneDirector sceneDirector;

    @Override
    public void onEnable() {
        sceneDirector = new SceneDirector(this);

        /* https://github.com/aikar/commands/wiki/Using-ACF */
        commandManager = new PaperCommandManager(this);

        registerCommands();

    }

    private void registerCommands() {
        commandManager.registerCommand(new TestCommand(this));
    }

    @Override
    public void onDisable() {
    }

    public SceneDirector getSceneDirector() {
        return sceneDirector;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }
}
