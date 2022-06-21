package tech.adelemphii.ror24m.directors.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import tech.adelemphii.ror24m.directors.KeyDirector;

import java.util.Arrays;

public class Item {

    private final Material material;

    private final String name;
    private final ItemRarity rarity;
    private final Component[] lore;

    public Item(Material material, String name, ItemRarity rarity, Component[] lore) {
        this.material = material;
        this.name = name;
        this.rarity = rarity;
        this.lore = lore;
    }

    public Component[] getLore() {
        return lore;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public ItemRarity getRarity() {
        return rarity;
    }

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(this.getMaterial());
        ItemMeta meta = itemStack.getItemMeta();

        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(KeyDirector.getItemKey(), PersistentDataType.STRING, rarity.name());

        meta.displayName(Component.text(this.getName()).color(this.getRarity().getColor()));
        meta.lore(Arrays.asList(this.getLore()));

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
