package xyz.wagyourtail.jsmacros.client.mixins.access;

import net.minecraft.client.font.TrueTypeFont;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TrueTypeFont.class)
public class MixinTrueTypeFont {

    // cancel canceling spaces by making their calculated width/height not equal to 0 which cancels their existence in the font entirely
    // also I dont actually know which one is width and height, it doesn't matter

    // TODO: Not sure where this is now in 1.21.3
/*    @ModifyVariable(method = "getGlyph", at = @At("STORE"), ordinal = 2)
    public int modifyWidth(int w, int i) {
        if (i == 32) {
            return 1;
        }
        return w;
    }

    @ModifyVariable(method = "getGlyph", at = @At("STORE"), ordinal = 3)
    public int modifyHeight(int h, int i) {
        if (i == 32) {
            return 1;
        }
        return h;
    }*/

}
