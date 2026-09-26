package io.github.seggan.slimefunwarfare.machines;

import io.github.mooy1.infinitylib.common.CoolDowns;
import io.github.mooy1.infinitylib.common.Scheduler;
import io.github.mooy1.infinitylib.core.AddonConfig;
import io.github.seggan.slimefunwarfare.SlimefunWarfare;
import io.github.seggan.slimefunwarfare.lists.Categories;
import io.github.seggan.slimefunwarfare.lists.Items;
import com.github.drakescraft_labs.slimefun4.api.items.SlimefunItemStack;
import com.github.drakescraft_labs.slimefun4.api.recipes.RecipeType;
import com.github.drakescraft_labs.slimefun4.core.handlers.BlockUseHandler;
import com.github.drakescraft_labs.slimefun4.implementation.Slimefun;
import com.github.drakescraft_labs.slimefun4.implementation.items.SimpleSlimefunItem;
import com.github.drakescraft_labs.slimefun4.libraries.dough.protection.Interaction;
import com.github.drakescraft_labs.slimefun4.legacy.api.BlockStorage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;
import javax.annotation.Nonnull;

public class MeteorAttractor extends SimpleSlimefunItem<BlockUseHandler> {

    private static final CoolDowns cooldowns = new CoolDowns(1000L * 60L *
        SlimefunWarfare.inst().getConfig().getInt("space.attractor-cooldown", 1)
    );

    public MeteorAttractor() {
        super(Categories.MACHINES, Items.METEOR_ATTRACTOR, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.NDFEB_ALLOY_BLOCK, null, Items.NDFEB_ALLOY_BLOCK,
            Items.TERFENOL_D_BLOCK, Items.NDFEB_ALLOY_BLOCK, Items.TERFENOL_D_BLOCK
        });
    }

    private void drop(Location l, Player p) {
        Block b = l.getBlock();
        if (!SlimefunWarfare.inst().guard().canModify(p, l)) return;

        AddonConfig config = SlimefunWarfare.inst().getConfig();

        int radius = config.getInt("space.meteor-drop-radius", 100);

        int x = ThreadLocalRandom.current().nextInt(b.getX() - radius, b.getX() + radius + 1);
        int z = ThreadLocalRandom.current().nextInt(b.getZ() - radius, b.getZ() + radius + 1);
        World world = b.getWorld();

        Location impact = new Location(world, x, world.getHighestBlockYAt(x, z), z);
        if (!SlimefunWarfare.inst().guard().canModify(p, impact)) return;
        world.createExplosion(impact, 4F, false, false);

        Scheduler.run(2, () -> {
            SlimefunItemStack stack = Items.OSMIUM_METEOR;
            if (ThreadLocalRandom.current().nextInt(100) < config.getInt("space.segganesson-chance", 0, 100)) {
                stack = Items.SEGGANESSON_METEOR;
            }

            Block landing = world.getHighestBlockAt(x, z);
            if (!landing.isPassable()) {
                landing = landing.getRelative(BlockFace.UP);
            }
            landing.setType(stack.getType());
            BlockStorage.addBlockInfo(landing, "id", stack.getItemId());
        });
    }

    @Nonnull
    @Override
    public BlockUseHandler getItemHandler() {
        return (b) -> {
            if (!SlimefunWarfare.inst().guard().allowUse(b.getPlayer())) return;
            AddonConfig config = SlimefunWarfare.inst().getConfig();
            if (cooldowns.checkAndReset(b.getPlayer().getUniqueId())) {
                int mins = ThreadLocalRandom.current().nextInt(
                    config.getInt("space.meteor-min-time", 10),
                    config.getInt("space.meteor-max-time", 30) + 1
                );

                Location l = b.getClickedBlock().get().getLocation();
                b.getPlayer().sendMessage("Sending meteor in " + mins + " minutes");
                Scheduler.run(mins * 60 * 20, () -> drop(l, b.getPlayer()));
            } else {
                b.getPlayer().sendMessage(ChatColor.RED + "The Meteor Attractor has a "
                    + config.getInt("space.space.attractor-cooldown", 1) +
                    " minute cooldown"
                );
            }
        };
    }
}
