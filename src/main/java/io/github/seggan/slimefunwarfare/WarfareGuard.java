package io.github.seggan.slimefunwarfare;

import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/** Centraliza los límites de mundo y protección de todo el contenido bélico. */
public final class WarfareGuard {

    private static final Set<String> DEFAULT_WORLDS = Set.of("world", "world_nether", "world_the_end");
    private final SlimefunWarfare plugin;
    private Set<String> allowedWorlds = DEFAULT_WORLDS;

    public WarfareGuard(SlimefunWarfare plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        Set<String> configured = new HashSet<>();
        for (String world : plugin.getConfig().getStringList("safety.allowed-worlds")) {
            if (world != null && !world.isBlank()) configured.add(normalize(world));
        }
        allowedWorlds = configured.isEmpty() ? DEFAULT_WORLDS : Set.copyOf(configured);
    }

    public boolean isAllowed(Location location) {
        return location != null && isAllowedWorld(allowedWorlds, location.getWorld().getName());
    }

    public boolean allowUse(Player player) {
        if (isAllowed(player.getLocation())) return true;
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(
                "safety.denied-message",
                "&cWarfare is only available in Slimefun worlds.")));
        return false;
    }

    public boolean canAttack(Player player, Entity target) {
        if (!isAllowed(player.getLocation()) || !isAllowed(target.getLocation())) return false;
        Interaction interaction = target instanceof Player ? Interaction.ATTACK_PLAYER : Interaction.ATTACK_ENTITY;
        return Slimefun.getProtectionManager().hasPermission(player, target.getLocation(), interaction);
    }

    public boolean canModify(Player player, Location location) {
        return isAllowed(location)
                && Slimefun.getProtectionManager().hasPermission(player, location, Interaction.BREAK_BLOCK);
    }

    static String normalize(String world) {
        return world.trim().toLowerCase(Locale.ROOT);
    }

    static boolean isAllowedWorld(Set<String> allowed, String world) {
        return world != null && allowed.contains(normalize(world));
    }
}
