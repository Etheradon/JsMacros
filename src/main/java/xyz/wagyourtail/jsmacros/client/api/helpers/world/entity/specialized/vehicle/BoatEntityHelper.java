package xyz.wagyourtail.jsmacros.client.api.helpers.world.entity.specialized.vehicle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import xyz.wagyourtail.jsmacros.client.api.helpers.inventory.ItemStackHelper;
import xyz.wagyourtail.jsmacros.client.api.helpers.world.entity.EntityHelper;
import xyz.wagyourtail.jsmacros.client.mixins.access.MixinBoatEntity;

/**
 * @author Etheradon
 * @since 1.8.4
 */
@SuppressWarnings("unused")
public class BoatEntityHelper<T extends Entity> extends EntityHelper<T> {

    public BoatEntityHelper(T base) {
        super(base);
    }

    /**
     * @return {@code true} if the boat is a chest boat, {@code false} otherwise.
     * @since 1.8.4
     */
    public boolean isChestBoat() {
        return base instanceof ChestBoatEntity;
    }

    /**
     * @return the boat's plank type.
     * @since 1.8.4
     */
    public ItemStackHelper getBoatBlockType() {
        return new ItemStackHelper(base.getPickBlockStack());
    }

    /**
     * @return {@code true} if the boat is on top of water, {@code false} otherwise.
     * @since 1.8.4
     */
    public boolean isInWater() {
        return getLocation() == BoatEntity.Location.IN_WATER;
    }

    /**
     * @return {@code true} if the boat is on land, {@code false} otherwise.
     * @since 1.8.4
     */
    public boolean isOnLand() {
        return getLocation() == BoatEntity.Location.ON_LAND;
    }

    /**
     * @return {@code true} if the boat is underwater, {@code false} otherwise.
     * @since 1.8.4
     */
    public boolean isUnderwater() {
        return getLocation() == BoatEntity.Location.UNDER_WATER;
    }

    /**
     * @return {@code true} if the boat is in the air, {@code false} otherwise.
     * @since 1.8.4
     */
    public boolean isInAir() {
        return getLocation() == BoatEntity.Location.IN_AIR;
    }

    private BoatEntity.Location getLocation() {
        return ((MixinBoatEntity) base).getLocation();
    }

}
