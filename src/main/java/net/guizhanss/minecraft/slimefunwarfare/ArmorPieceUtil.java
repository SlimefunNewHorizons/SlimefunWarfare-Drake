package net.guizhanss.minecraft.slimefunwarfare;

import io.github.seggan.slimefunwarfare.items.powersuits.ArmorPiece;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

@UtilityClass
public class ArmorPieceUtil {
    public static @Nonnull String getName(@Nonnull ArmorPiece piece){
        switch (piece) {
            case HEAD:
                return "Helmet";
            case CHEST:
                return "Chestplate";
            case LEGS:
                return "Leggings";
            case FEET:
                return "Boots";
            default:
                return "Unknown";
        }
    }
}
