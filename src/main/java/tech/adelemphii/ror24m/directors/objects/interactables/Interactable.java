package tech.adelemphii.ror24m.directors.objects.interactables;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import tech.adelemphii.ror24m.RoR24m;
import tech.adelemphii.ror24m.directors.objects.item.Item;

public class Interactable {

    private final int creditDrop;
    private final Item itemDrop;
    private final int cost;
    private final Location location;

    private boolean opened = false;

    private final InteractableType type;

    private final BoundingBox boundingBox;
    private Entity armorStand;
    private Entity costArmorStand;

    public Interactable(int cost, int creditDrop, Item itemDrop, Location location, InteractableType type) {
        this.cost = cost;
        this.creditDrop = creditDrop;
        this.itemDrop = itemDrop;
        this.location = location;
        this.type = type;

        this.boundingBox = new BoundingBox(location.getX(), location.getY(), location.getZ(),
                location.getX(), location.getY(), location.getZ()).expand(3);

        prepArmorStand();

    }

    private void prepArmorStand() {
        this.armorStand = location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setCustomNameVisible(true);
        armorStand.customName(Component.text("Press ").color(NamedTextColor.GOLD)
                .append(Component.keybind().keybind("key.swapOffhand").color(NamedTextColor.GOLD))
                .append(Component.text(" to open " + type.name()).color(NamedTextColor.GOLD)));
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        ((ArmorStand) armorStand).setVisible(false);

        if(cost > 0) {
            prepCostArmorStand();
        }
    }

    private void prepCostArmorStand() {
        this.costArmorStand = location.getWorld().spawnEntity(location.subtract(0, 0.5, 0), EntityType.ARMOR_STAND);
        costArmorStand.setCustomNameVisible(true);
        costArmorStand.customName(Component.text("$" + cost).color(NamedTextColor.GOLD));
        costArmorStand.setGravity(false);
        costArmorStand.setInvulnerable(true);
        ((ArmorStand) costArmorStand).setVisible(false);
    }

    public Item getItemDrop() {
        return itemDrop;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public InteractableType getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getCreditDrop() {
        return creditDrop;
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

    public Entity getCostArmorStand() {
        return costArmorStand;
    }

    public void dropItemRoR2() {
        Location interactableLocation = this.getLocation().clone().add(0, 2, 0);

        org.bukkit.entity.Item entity = this.getLocation().getWorld().dropItem(interactableLocation,
                this.getItemDrop().getItemStack());

        Vector vector = entity.getLocation().toVector().subtract(interactableLocation.toVector()).setY(1).normalize();
        entity.setVelocity(vector);

        Color color = Color.fromRGB(this.getItemDrop().getRarity().getColor().red(),
                this.getItemDrop().getRarity().getColor().green(),
                this.getItemDrop().getRarity().getColor().blue());
        Particle.DustOptions dustOptions = new Particle.DustOptions(color, 3);

        World world = interactableLocation.getWorld();

        new BukkitRunnable() {
            @Override
            public void run() {
                if(entity.isDead()) {
                    this.cancel();
                }

                world.spawnParticle(Particle.REDSTONE, entity.getLocation(), 1, dustOptions);
            }
        }.runTaskTimer(RoR24m.getPlugin(), 0, 1);

        setOpened(true);
        armorStand.remove();
    }
}
