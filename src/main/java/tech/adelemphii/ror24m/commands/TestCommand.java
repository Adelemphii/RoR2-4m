package tech.adelemphii.ror24m.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import tech.adelemphii.ror24m.RoR24m;
import tech.adelemphii.ror24m.directors.interactables.Interactable;
import tech.adelemphii.ror24m.directors.interactables.InteractableType;

@CommandAlias("ror24m|ror2")
public class TestCommand extends BaseCommand {

    private final RoR24m plugin;

    public TestCommand(RoR24m plugin) {
        this.plugin = plugin;
    }

    @Default
    @Subcommand("spawn")
    public void spawn(Player player, InteractableType type) {
        RayTraceResult rayTraceResult = player.rayTraceBlocks(10);
        if (rayTraceResult == null) {
            return;
        }
        if (rayTraceResult.getHitBlock() == null) {
            return;
        }

        switch (type) {
            case BARREL -> rayTraceResult.getHitBlock().getLocation().add(0, 1, 0).getBlock().setType(Material.BARREL);
            case CHEST -> rayTraceResult.getHitBlock().getLocation().add(0, 1, 0).getBlock().setType(Material.CHEST);
            default -> {
            }
        }

        player.sendMessage("Spawned " + type.name());
        Interactable interactable = new Interactable(0, 0, rayTraceResult.getHitBlock().getLocation().add(0.5, 0, 0.5), type);

        plugin.getSceneDirector().addInteractable(interactable);

    }

}
