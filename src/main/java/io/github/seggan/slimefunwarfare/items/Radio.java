package io.github.seggan.slimefunwarfare.items;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import com.github.drakescraft_labs.slimefun4.api.recipes.RecipeType;
import com.github.drakescraft_labs.slimefun4.core.handlers.ItemUseHandler;
import com.github.drakescraft_labs.slimefun4.implementation.SlimefunItems;
import com.github.drakescraft_labs.slimefun4.implementation.items.SimpleSlimefunItem;
import com.github.drakescraft_labs.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import com.github.drakescraft_labs.slimefun4.utils.ChatUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Radio extends SimpleSlimefunItem<ItemUseHandler> {

    public static final NamespacedKey ENCRYPTION_KEY = AbstractAddon.createKey("radio_encryption_key");

    public Radio() {
        super(Categories.GENERAL, Items.RADIO, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            SlimefunItems.GPS_TRANSMITTER_4, new ItemStack(Material.REDSTONE_TORCH), SlimefunItems.GPS_TRANSMITTER_4,
            Items.LASER_DIODE, Items.FIBER_OPTIC_CABLE, Items.LASER_DIODE,
            Items.OSMIUM_INGOT, SlimefunItems.ADVANCED_CIRCUIT_BOARD, Items.OSMIUM_INGOT
        });
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> {
            e.cancel();
            ItemStack stack = e.getItem();
            e.getPlayer().sendMessage(ChatColor.YELLOW + "Enter the new encryption key:");
            ChatUtils.awaitInput(e.getPlayer(), s -> {
                if (stack.hasItemMeta()) {
                    ItemMeta meta = stack.getItemMeta();
                    // to calm IJ down
                    assert meta != null;
                    PersistentDataAPI.setString(meta, ENCRYPTION_KEY, s);
                    stack.setItemMeta(meta);
                }
            });
        };
    }

    @Nullable
    public static String getKey(ItemStack item) {
        return PersistentDataAPI.getString(item.getItemMeta(), ENCRYPTION_KEY);
    }
}
