package tech.adelemphii.ror24m.directors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tech.adelemphii.ror24m.RoR24m;
import tech.adelemphii.ror24m.directors.interactables.Interactable;

import java.util.HashSet;
import java.util.Set;

public class SceneDirector {

    private int interactableCredits;
    private int monsterCredits;

    BukkitRunnable runnable;

    public SceneDirector(RoR24m plugin) {

        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                // this #hideEntity api is experimental but it's easier than using packets, maybe it'll
                // be stable by the time this plugin even releases (if it ever does)
                for(Interactable interactable : interactables) {
                    for(Player player : interactable.getLocation().getWorld().getPlayers()) {
                        if(!interactable.getBoundingBox().contains(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ())) {
                            player.hideEntity(plugin, interactable.getArmorStand());
                        } else {
                            player.showEntity(plugin, interactable.getArmorStand());
                        }
                    }
                }
            }
        };
        runnable.runTaskTimer(plugin, 0, 5);
    }

    private final Set<Interactable> interactables = new HashSet<>();

    public int getInteractableCredits() {
        return interactableCredits;
    }

    public void setInteractableCredits(int interactableCredits) {
        this.interactableCredits = interactableCredits;
    }

    public int getMonsterCredits() {
        return monsterCredits;
    }

    public void setMonsterCredits(int monsterCredits) {
        this.monsterCredits = monsterCredits;
    }

    public Set<Interactable> getInteractables() {
        return interactables;
    }

    public Interactable getInteractableFromLocation(Location location) {
        for(Interactable interactable : interactables) {
            if(interactable.getLocation().equals(location)) {
                return interactable;
            }
        }
        return null;
    }

    public void addInteractable(Interactable interactable) {
        this.interactables.add(interactable);
    }

    public void removeInteractable(Interactable interactable) {
        this.interactables.remove(interactable);
    }
}
