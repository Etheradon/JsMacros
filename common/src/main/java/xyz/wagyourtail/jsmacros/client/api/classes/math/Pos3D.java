package xyz.wagyourtail.jsmacros.client.api.classes.math;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import xyz.wagyourtail.jsmacros.client.api.helpers.world.BlockPosHelper;
import xyz.wagyourtail.jsmacros.client.api.helpers.world.DirectionHelper;
import xyz.wagyourtail.jsmacros.client.api.helpers.world.entity.EntityHelper;

import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;

/**
 * @author Wagyourtail
 * @since 1.2.6 [citation needed]
 */
public class Pos3D implements Iterable<Double> {
    public static final Pos3D ZERO = new Pos3D(0, 0, 0);
    public double x;
    public double y;
    public double z;

    public Pos3D(Vec3d vec) {
        this(vec.getX(), vec.getY(), vec.getZ());
    }

    public Pos3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Pos3D x(double x) {
        return setX(x);
    }

    public double x() {
        return x;
    }

    public Pos3D y(double y) {
        return setY(y);
    }

    public double y() {
        return y;
    }

    public Pos3D z(double z) {
        return setZ(z);
    }

    public double z() {
        return z;
    }

    public Pos3D setX(double x) {
        this.x = x;
        return this;
    }

    public double getX() {
        return x;
    }

    public Pos3D setY(double y) {
        this.y = y;
        return this;
    }

    public double getY() {
        return y;
    }

    public Pos3D setZ(double z) {
        this.z = z;
        return this;
    }

    public double getZ() {
        return z;
    }

    public Pos3D set(Pos3D pos) {
        return set(pos.x, pos.y, pos.z);
    }

    public Pos3D set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    private Direction.Axis getAxis(Object axis) {
        Direction.Axis a;
        if (axis instanceof String stringAxis) {
            a = Direction.Axis.fromName(stringAxis.toLowerCase(Locale.ROOT));
        } else if (axis instanceof DirectionHelper directionHelperAxis) {
            a = directionHelperAxis.getRaw().getAxis();
        } else if (axis instanceof Direction.Axis directionAxis) {
            a = directionAxis;
        } else if (axis.getClass() == Integer.class) {
            a = Direction.Axis.values()[(int) axis];
        } else {
            throw new IllegalArgumentException("Invalid axis: " + axis);
        }
        return a;
    }

    public Pos3D set(Object axis, double value) {
        return switch (getAxis(axis)) {
            case X -> setX(value);
            case Y -> setY(value);
            case Z -> setZ(value);
        };
    }

    public double get(Object axis) {
        return switch (getAxis(axis)) {
            case X -> x;
            case Y -> y;
            case Z -> z;
        };
    }

    public Pos3D offset(double offset) {
        return add(offset, offset, offset);
    }

    public Pos3D offset(Object axis, double offset) {
        return switch (getAxis(axis)) {
            case X -> add(offset, 0, 0);
            case Y -> add(0, offset, 0);
            case Z -> add(0, 0, offset);
        };
    }

    public Pos3D add(Pos3D pos) {
        return add(pos.x, pos.y, pos.z);
    }

    /**
     * @param x
     * @param y
     * @param z
     * @return
     *
     * @since 1.6.3
     */
    public Pos3D add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Pos3D add(BlockPosHelper pos) {
        return add(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * @param pos the position to subtract
     * @return the new position.
     *
     * @since 1.8.4
     */
    public Pos3D sub(Pos3D pos) {
        this.x -= pos.x;
        this.y -= pos.y;
        this.z -= pos.z;
        return this;
    }

    /**
     * @param x the x coordinate to subtract
     * @param y the y coordinate to subtract
     * @param z the z coordinate to subtract
     * @return the new position.
     *
     * @since 1.8.4
     */
    public Pos3D sub(double x, double y, double z) {
        return sub(this.x - x, this.y - y, this.z - z);
    }

    public Pos3D sub(BlockPosHelper pos) {
        return sub(pos.getX(), pos.getY(), pos.getZ());
    }

    public Pos3D multiply(Pos3D pos) {
        return multiply(pos.x, pos.y, pos.z);
    }

    /**
     * @param x
     * @param y
     * @param z
     * @return
     *
     * @since 1.6.3
     */
    public Pos3D multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    /**
     * @param pos the position to divide by
     * @return the new position.
     *
     * @since 1.8.4
     */
    public Pos3D divide(Pos3D pos) {
        return divide(pos.x, pos.y, pos.z);
    }

    /**
     * @param x the x coordinate to divide by
     * @param y the y coordinate to divide by
     * @param z the z coordinate to divide by
     * @return the new position.
     *
     * @since 1.8.4
     */
    public Pos3D divide(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Pos3D mod(Pos3D pos) {
        this.x %= pos.x;
        this.y %= pos.y;
        this.z %= pos.z;
        return this;
    }

    public Pos3D mod(double x, double y, double z) {
        this.x %= x;
        this.y %= y;
        this.z %= z;
        return this;
    }

    /**
     * @param scale
     * @return
     *
     * @since 1.6.3
     */
    public Pos3D scale(double scale) {
        this.x *= scale;
        this.y *= scale;
        this.z *= scale;
        return this;
    }

    public String toString() {
        return String.format("%f, %f, %f", x, y, z);
    }

    /**
     * @return
     *
     * @since 1.8.0
     */
    public BlockPosHelper toBlockPos() {
        return new BlockPosHelper(new BlockPos(Math.floor(x), Math.floor(y), Math.floor(z)));
    }

    public Pos3D copy() {
        return new Pos3D(x, y, z);
    }

    public Pos3D abs() {
        return set(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Pos3D floor() {
        return set(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    public Pos3D ceil() {
        return set(Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    public Pos3D round() {
        return set(Math.round(x), Math.round(y), Math.round(z));
    }

    public Pos3D min(Pos3D pos) {
        return min(pos.x, pos.y, pos.z);
    }

    public Pos3D min(double x, double y, double z) {
        this.x = Math.min(this.x, x);
        this.y = Math.min(this.y, y);
        this.z = Math.min(this.z, z);
        return this;
    }

    public Pos3D max(Pos3D pos) {
        return max(pos.x, pos.y, pos.z);
    }

    public Pos3D max(double x, double y, double z) {
        this.x = Math.max(this.x, x);
        this.y = Math.max(this.y, y);
        this.z = Math.max(this.z, z);
        return this;
    }

    public Pos3D clamp(Pos3D min, Pos3D max) {
        return max(min).min(max);
    }

    public double dist(EntityHelper<?> entity) {
        return Math.sqrt(entity.distanceTo(this));
    }

    public double dist(BlockPosHelper pos) {
        return Math.sqrt(pos.distanceTo(this));
    }

    public double dist(Pos3D pos) {
        return dist(pos.x, pos.y, pos.z);
    }

    public double dist(double x, double y, double z) {
        return Math.sqrt(distSq(x, y, z));
    }

    public double distSq(double x, double y, double z) {
        double dx = this.x - x;
        double dy = this.y - y;
        double dz = this.z - z;
        return dx * dx + dy * dy + dz * dz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pos3D pos)) {
            return false;
        }
        return pos.x == x && pos.y == y && pos.z == z;
    }

    public boolean equals(double x, double y, double z) {
        return this.x == x && this.y == y && this.z == z;
    }

    public Vec3D toVector() {
        return new Vec3D(ZERO, this);
    }

    /**
     * @param start_pos
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D toVector(Pos2D start_pos) {
        return toVector(start_pos.to3D());
    }

    /**
     * @param start_pos
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D toVector(Pos3D start_pos) {
        return new Vec3D(start_pos, this);
    }

    /**
     * @param start_x
     * @param start_y
     * @param start_z
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D toVector(double start_x, double start_y, double start_z) {
        return new Vec3D(start_x, start_y, start_z, this.x, this.y, this.z);
    }

    /**
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D toReverseVector() {
        return new Vec3D(this, ZERO);
    }

    public Vec3D toReverseVector(Pos2D end_pos) {
        return toReverseVector(end_pos.to3D());
    }

    /**
     * @param end_pos
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D toReverseVector(Pos3D end_pos) {
        return new Vec3D(this, end_pos);
    }

    /**
     * @param end_x
     * @param end_y
     * @param end_z
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D toReverseVector(double end_x, double end_y, double end_z) {
        return new Vec3D(this, new Pos3D(end_x, end_y, end_z));
    }

    /**
     * @return
     *
     * @since 1.8.0
     */
    public BlockPos toRawBlockPos() {
        return new BlockPos(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    /**
     * @return the raw minecraft double vector with the same coordinates as this position.
     *
     * @since 1.8.4
     */
    public Vec3d toMojangDoubleVector() {
        return new Vec3d(x, y, z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public Iterator<Double> iterator() {
        return new DoubleArrayIterator(new double[]{x, y, z});
    }

}