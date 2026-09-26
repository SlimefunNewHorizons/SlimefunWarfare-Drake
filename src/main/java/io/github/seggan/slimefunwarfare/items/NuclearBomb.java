package io.github.seggan.slimefunwarfare.items;

import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import com.github.drakescraft_labs.slimefun4.api.items.ItemGroup;
import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItem;
import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItemStack;
import com.github.drakescraft_labs.slimefun4.api.recipes.RecipeType;
import com.github.drakescraft_labs.slimefun4.core.attributes.Radioactive;
import com.github.drakescraft_labs.slimefun4.core.attributes.Radioactivity;
import com.github.drakescraft_labs.slimefun4.core.handlers.BlockUseHandler;
import com.github.drakescraft_labs.slimefun4.legacy.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Optional;
import javax.annotation.Nonnull;

public class NuclearBomb extends SlimefunItem implements Radioactive {

    public NuclearBomb(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(NuclearBomb.this.getHandler());
    }

    public float getExplosionPower() {
        return 100;
    }

    private BlockUseHandler getHandler() {
        return e -> {
            e.cancel();
            Optional<Block> optionalBlock = e.getClickedBlock();
            if (!optionalBlock.isPresent()) {
                return;
            }
            Block b = optionalBlock.get();
            if (!SlimefunWarfare.inst().getConfig().getBoolean("explosions.nuclear-enabled", false)
                    || !SlimefunWarfare.inst().guard().canModify(e.getPlayer(), b.getLocation())) {
                e.getPlayer().sendMessage("§cThe nuclear bomb is disabled or cannot be used here.");
                return;
            }
            b.setType(Material.AIR);
            TNTPrimed tnt = b.getWorld().spawn(b.getLocation().add(0.5, 0, 0.5), TNTPrimed.class);
            tnt.setFuseTicks(100);
            tnt.setMetadata("isNuke", new FixedMetadataValue(
                SlimefunWarfare.inst(),
                true
            ));
            tnt.setMetadata("rad", new FixedMetadataValue(
                SlimefunWarfare.inst(),
                getExplosionPower()
            ));

            // To prevent ghost blocks
            BlockStorage.clearBlockInfo(b);
        };
    }

    @Nonnull
    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.VERY_DEADLY;
    }
}
