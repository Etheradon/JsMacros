package xyz.wagyourtail.jsmacros.client.api.classes.math;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3f;

import java.util.Iterator;

/**
 * @author Wagyourtail
 * @since 1.2.6 [citation needed]
 */
public class Vec3D implements Iterable<Double> {
    public double x1;
    public double y1;
    public double z1;
    public double x2;
    public double y2;
    public double z2;

    public Vec3D(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.z1 = z1;
        this.z2 = z2;
    }

    public Vec3D(Pos3D start, Pos3D end) {
        this(start.x, start.y, start.z, end.x, end.y, end.z);
    }

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getZ1() {
        return z1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getZ2() {
        return z2;
    }

    public double getDeltaZ() {
        return z2 - z1;
    }

    public Pos3D getStart() {
        return new Pos3D(x1, y1, z1);
    }

    public Pos3D getEnd() {
        return new Pos3D(x2, y2, z2);
    }

    public double getMagnitude() {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public double getMagnitudeSq() {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        return dx * dx + dy * dy + dz * dz;
    }

    public Vec3D add(Vec3D vec) {
        return new Vec3D(
                this.x1 + vec.x1,
                this.y1 + vec.y1,
                this.z1 + vec.z1,
                this.x2 + vec.x2,
                this.y2 + vec.y2,
                this.z2 + vec.z2
        );
    }

    /**
     * @param pos
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D addStart(Pos3D pos) {
        return new Vec3D(this.x1 + pos.x, this.y1 + pos.y, this.z1 + pos.z, this.x2, this.y2, this.z2);
    }

    /**
     * @param pos
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D addEnd(Pos3D pos) {
        return new Vec3D(this.x1, this.y1, this.z1, this.x2 + pos.x, this.y2 + pos.y, this.z2 + pos.z);
    }

    /**
     * @param x
     * @param y
     * @param z
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D addStart(double x, double y, double z) {
        return new Vec3D(this.x1 + x, this.y1 + y, this.z1 + z, this.x2, this.y2, this.z2);
    }

    /**
     * @param x
     * @param y
     * @param z
     * @return
     *
     * @since 1.6.4
     */
    public Vec3D addEnd(double x, double y, double z) {
        return new Vec3D(this.x1, this.y1, this.z1, this.x2 + x, this.y2 + y, this.z2 + z);
    }

    /**
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @return
     *
     * @since 1.6.3
     */
    public Vec3D add(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Vec3D(this.x1 + x1, this.y1 + y1, this.z1 + z1, this.x2 + x2, this.y2 + y2, this.z2 + z2);
    }

    public Vec3D multiply(Vec3D vec) {
        return new Vec3D(
                this.x1 * vec.x1,
                this.y1 * vec.y1,
                this.z1 * vec.z1,
                this.x2 * vec.x2,
                this.y2 * vec.y2,
                this.z2 * vec.z2
        );
    }

    /**
     * @param x1
     * @param y1
     * @param z1
     * @param x2
     * @param y2
     * @param z2
     * @return
     *
     * @since 1.6.3
     */
    public Vec3D multiply(double x1, double y1, double z1, double x2, double y2, double z2) {
        return new Vec3D(this.x1 * x1, this.y1 * y1, this.z1 * z1, this.x2 * x2, this.y2 * y2, this.z2 * z2);
    }

    /**
     * @param scale
     * @return
     *
     * @since 1.6.3
     */
    public Vec3D scale(double scale) {
        return new Vec3D(x1 * scale, y1 * scale, z1 * scale, x2 * scale, y2 * scale, z2 * scale);
    }

    /**
     * @return
     *
     * @since 1.6.5
     */
    public Vec3D normalize() {
        double mag = getMagnitude();
        return new Vec3D(x1 / mag, y1 / mag, z1 / mag, x2 / mag, y2 / mag, z2 / mag);
    }

    public float getPitch() {
        double dx = x2 - x1;
        double dy = y2 - y1;
        double dz = z2 - z1;
        double xz = Math.sqrt(dx * dx + dz * dz);
        return 90F - (float) MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(xz, -dy)));
    }

    public float getYaw() {
        double dx = x2 - x1;
        double dz = z2 - z1;
        return (float) -MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(dx, dz)));
    }

    public double dotProduct(Vec3D vec) {
        double dx1 = x2 - x1;
        double dx2 = vec.x2 - vec.x1;
        double dy1 = y2 - y1;
        double dy2 = vec.y2 - vec.y1;
        double dz1 = z2 - z1;
        double dz2 = vec.z2 - vec.z1;
        return dx1 * dx2 + dy1 * dy2 + dz1 * dz2;
    }

    public Vec3D crossProduct(Vec3D vec) {
        double dx1 = x2 - x1;
        double dx2 = vec.x2 - vec.x1;
        double dy1 = y2 - y1;
        double dy2 = vec.y2 - vec.y1;
        double dz1 = z2 - z1;
        double dz2 = vec.z2 - vec.z1;
        return new Vec3D(0, 0, 0, dy1 * dz2 - dz1 * dy2, dz1 * dx2 - dx1 * dz2, dx1 * dy2 - dy1 * dx2);
    }

    public Vec3D reverse() {
        return new Vec3D(x2, y2, z2, x1, y1, z1);
    }

    @Override
    public String toString() {
        return String.format("%f, %f, %f -> %f, %f, %f", x1, y1, z1, x2, y2, z2);
    }

    /**
     * @return
     *
     * @since 1.6.5
     */
    public Vec3f toMojangFloatVector() {
        return new Vec3f((float) (x2 - x1), (float) (y2 - y1), (float) (z2 - z1));
    }

    @Override
    public Iterator<Double> iterator() {
        return new DoubleArrayIterator(new double[]{x1, y1, z1, x2, y2, z2});
    }

}