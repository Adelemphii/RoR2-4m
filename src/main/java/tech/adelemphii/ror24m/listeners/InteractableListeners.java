package tech.adelemphii.ror24m.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import tech.adelemphii.ror24m.RoR24m;
import tech.adelemphii.ror24m.directors.interactables.Interactable;

public class InteractableListeners implements Listener {

    private final RoR24m plugin;

    public InteractableListeners(RoR24m plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSwapHandsEvent(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        for(Interactable interactable : plugin.getSceneDirector().getInteractables()) {
            if(interactable.getBoundingBox().contains(player.getLocation().toVector())) {
                if(interactable.isOpened()) {
                    return;
                }

                interactable.dropItemRoR2();
            }
        }
    }
}
