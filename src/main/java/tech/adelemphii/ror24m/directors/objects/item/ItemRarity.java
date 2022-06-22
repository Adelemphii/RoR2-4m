package tech.adelemphii.ror24m.directors.objects.item;

import net.kyori.adventure.text.format.NamedTextColor;

public enum ItemRarity {

    COMMON(NamedTextColor.WHITE),
    UNCOMMON(NamedTextColor.GREEN),
    LEGENDARY(NamedTextColor.RED),
    BOSS(NamedTextColor.YELLOW),
    LUNAR(NamedTextColor.BLUE),
    VOID(NamedTextColor.DARK_PURPLE),
    EQUIPMENT(NamedTextColor.GOLD);

    private final NamedTextColor color;

    ItemRarity(NamedTextColor color) {
        this.color = color;
    }

    public NamedTextColor getColor() {
        return color;
    }
}
