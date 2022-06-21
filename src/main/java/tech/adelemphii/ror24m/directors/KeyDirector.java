package tech.adelemphii.ror24m.directors;

import org.bukkit.NamespacedKey;
import tech.adelemphii.ror24m.RoR24m;

public class KeyDirector {

    private static final NamespacedKey ITEM_KEY = new NamespacedKey(RoR24m.getPlugin(), "ror2-4m_item");

    public static NamespacedKey getItemKey() {
        return ITEM_KEY;
    }

}
