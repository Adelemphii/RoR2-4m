package tech.adelemphii.ror24m.directors.interactables;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class Interactable {

    private final int drop;
    private final int cost;
    private final Location location;

    private final InteractableType type;

    private final BoundingBox boundingBox;
    private final Entity armorStand;

    public Interactable(int cost, int drop, Location location, InteractableType type) {
        this.cost = cost;
        this.drop = drop;
        this.location = location;
        this.type = type;

        this.boundingBox = new BoundingBox(location.getX(), location.getY(), location.getZ(),
                location.getX(), location.getY(), location.getZ()).expand(3);

        this.armorStand = location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomNameVisible(true);
        armorStand.customName(Component.text("Press F to open " + type.name()).color(NamedTextColor.GOLD));
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        ((ArmorStand) armorStand).setVisible(false);

    }

    public InteractableType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getDrop() {
        return drop;
    }

    public Location getLocation() {
        return location;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public Entity getArmorStand() {
        return armorStand;
    }
}
