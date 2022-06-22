package tech.adelemphii.ror24m.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import tech.adelemphii.ror24m.RoR24m;
import tech.adelemphii.ror24m.directors.objects.interactables.Interactable;
import tech.adelemphii.ror24m.directors.objects.interactables.InteractableType;
import tech.adelemphii.ror24m.directors.objects.item.Item;
import tech.adelemphii.ror24m.directors.objects.item.ItemRarity;

@CommandAlias("ror24m|ror2")
public class TestCommand extends BaseCommand {

    private final RoR24m plugin;

    public TestCommand(RoR24m plugin) {
        this.plugin = plugin;
    }

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

        Component[] lore = {
                Component.text("Killing an enemy ignites all enemies within 12m (+4m per stack) for 150% base damage."),
                        Component.text("Additionally, enemies burn for 150% (+75% per stack) base damage.")
        };

        Item item = new Item(Material.FLINT_AND_STEEL, "Gasoline", ItemRarity.EQUIPMENT, lore);

        player.sendMessage("Spawned " + type.name());
        Interactable interactable = new Interactable(0, 0, item, rayTraceResult.getHitBlock().getLocation().add(0.5, 0, 0.5), type);

        plugin.getSceneDirector().addInteractable(interactable);

    }

}
