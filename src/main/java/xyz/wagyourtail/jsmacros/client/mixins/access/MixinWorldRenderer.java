package xyz.wagyourtail.jsmacros.client.mixins.access;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {

    // TODO: Was not used before
/*    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    protected abstract void drawBlockOutline(MatrixStack matrices, VertexConsumer vertexConsumer, Entity entity, double cameraX, double cameraY, double cameraZ, BlockPos pos, BlockState state);

    @Shadow
    @Final
    private BufferBuilderStorage bufferBuilders;

    @Shadow
    @Nullable
    private ClientWorld world;

    @Inject(at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=outline"), method = "render")
    public void renderInvisibleOutline(RenderTickCounter tickCounter, boolean renderBlockOutline, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f matrix4f, Matrix4f matrix4f2, CallbackInfo ci) {
        if (world == null) return;
        BlockHitResult target = (BlockHitResult) client.crosshairTarget;
        if (target == null) return;

        BlockPos pos = target.getBlockPos();
        BlockState state = world.getBlockState(pos);
        if (!state.isAir() && !state.getOutlineShape(world, pos).isEmpty()) return;

        Vec3d campos = camera.getPos();
//        drawBlockOutline(
//                matrices,
//                bufferBuilders.getEntityVertexConsumers().getBuffer(RenderLayer.getLines()),
//                camera.getFocusedEntity(),
//                campos.getX(), campos.getY(), campos.getZ(),
//                pos, Blocks.STONE.getDefaultState() // stone won't ever change its shape, right?
//        );
    }*/

}
