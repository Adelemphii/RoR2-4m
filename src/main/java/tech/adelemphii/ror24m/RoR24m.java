package tech.adelemphii.ror24m;

import co.aikar.commands.PaperCommandManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tech.adelemphii.ror24m.commands.TestCommand;
import tech.adelemphii.ror24m.directors.SceneDirector;
import tech.adelemphii.ror24m.listeners.InteractableListeners;

public final class RoR24m extends JavaPlugin {

    private PaperCommandManager commandManager;
    private SceneDirector sceneDirector;
    private static RoR24m PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        sceneDirector = new SceneDirector(this);

        /* https://github.com/aikar/commands/wiki/Using-ACF */
        commandManager = new PaperCommandManager(this);

        registerCommands();
        registerEvents();

    }

    private void registerCommands() {
        commandManager.registerCommand(new TestCommand(this));
    }

    private void registerEvents() {
        PluginManager manager = getServer().getPluginManager();

        manager.registerEvents(new InteractableListeners(this), this);
    }

    @Override
    public void onDisable() {
    }

    public SceneDirector getSceneDirector() {
        return sceneDirector;
    }

    public static RoR24m getPlugin() {
        return PLUGIN;
    }
}
