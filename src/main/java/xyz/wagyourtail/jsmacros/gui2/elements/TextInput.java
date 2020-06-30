package xyz.wagyourtail.jsmacros.gui2.elements;

import java.util.function.Consumer;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

public class TextInput extends Button {
    public Consumer<String> onChange;
    public String content;
    protected int selColor;
    protected int selStart;
    public int selStartIndex;
    protected int selEnd;
    public int selEndIndex;
    protected int arrowCursor;
    protected boolean selecting;

    public TextInput(int x, int y, int width, int height, int color, int borderColor, int hilightColor, int textColor, String message, Consumer<Button> onClick, Consumer<String> onChange) {
        super(x, y, width, height, color, borderColor, color, textColor, new LiteralText(""), onClick);
        this.selColor = hilightColor;
        this.content = message;
        this.onChange = onChange;
        this.updateSelStart(content.length());
        this.updateSelEnd(content.length());
        this.arrowCursor = content.length();
    }

    public void setMessage(String message) {
        content = message;
    }

    public void updateSelStart(int startIndex) {
        selStartIndex = startIndex;
        if (startIndex == 0) selStart = x + 1;
        else selStart = x + 2 + mc.textRenderer.getWidth(content.substring(0, startIndex));
    }

    public void updateSelEnd(int endIndex) {
        selEndIndex = endIndex;
        if (endIndex == 0) selEnd = x + 2;
        else selEnd = x + 3 + mc.textRenderer.getWidth(content.substring(0, endIndex));
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.clicked(mouseX, mouseY);
        if (this.isFocused()) {
            int pos = mc.textRenderer.trimToWidth(content, (int) (mouseX - x - 2)).length();
            updateSelStart(pos);
            updateSelEnd(pos);
            arrowCursor = pos;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.isFocused()) {
            int pos = mc.textRenderer.trimToWidth(content, (int) (mouseX - x - 2)).length();
            updateSelEnd(pos);
            arrowCursor = pos;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    public void swapStartEnd() {
        int temp1 = selStartIndex;
        updateSelStart(selEndIndex);
        updateSelEnd(temp1);

    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        boolean ctrl;
        if (this.isFocused()) {
            if (selEndIndex < selStartIndex) swapStartEnd();
            MinecraftClient mc = MinecraftClient.getInstance();
            if (Screen.isSelectAll(keyCode)) {
                this.updateSelStart(0);
                this.updateSelEnd(content.length());
            } else if (Screen.isCopy(keyCode)) {
                mc.keyboard.setClipboard(this.content.substring(selStartIndex, selEndIndex));
            } else if (Screen.isPaste(keyCode)) {
                content = content.substring(0, selStartIndex) + mc.keyboard.getClipboard() + content.substring(selEndIndex);
                if (onChange != null) onChange.accept(content);
                updateSelEnd(selStartIndex + mc.keyboard.getClipboard().length());
                arrowCursor = selStartIndex + mc.keyboard.getClipboard().length();
            } else if (Screen.isCut(keyCode)) {
                mc.keyboard.setClipboard(this.content.substring(selStartIndex, selEndIndex));
                content = content.substring(0, selStartIndex) + content.substring(selEndIndex);
                if (onChange != null) onChange.accept(content);
                updateSelEnd(selStartIndex);
                arrowCursor = selStartIndex;
            }
            switch (keyCode) {
            case GLFW.GLFW_KEY_BACKSPACE:
                if (selStartIndex == selEndIndex && selStartIndex > 0) updateSelStart(selStartIndex - 1);
                content = content.substring(0, selStartIndex) + content.substring(selEndIndex);
                if (onChange != null) onChange.accept(content);
                updateSelEnd(selStartIndex);
                arrowCursor = selStartIndex;
                break;
            case GLFW.GLFW_KEY_DELETE:
                if (selStartIndex == selEndIndex && selStartIndex < content.length()) updateSelEnd(selEndIndex + 1);
                content = content.substring(0, selStartIndex) + content.substring(selEndIndex);
                if (onChange != null) onChange.accept(content);
                updateSelEnd(selStartIndex);
                arrowCursor = selStartIndex;
                break;
            case GLFW.GLFW_KEY_HOME:
                updateSelStart(0);
                updateSelEnd(0);
                break;
            case GLFW.GLFW_KEY_END:
                this.updateSelStart(content.length());
                this.updateSelEnd(content.length());
                break;
            case GLFW.GLFW_KEY_LEFT:
                ctrl = !Screen.hasControlDown();
                if (arrowCursor > 0) if (arrowCursor < selEndIndex) {
                    updateSelStart(--arrowCursor);
                    if (ctrl) updateSelEnd(selStartIndex);
                } else if (arrowCursor >= selEndIndex) {
                    updateSelEnd(--arrowCursor);
                    if (ctrl) updateSelStart(selEndIndex);
                }
                break;
            case GLFW.GLFW_KEY_RIGHT:
                ctrl = !Screen.hasControlDown();
                if (arrowCursor < content.length()) if (arrowCursor < selEndIndex) {
                    updateSelStart(++arrowCursor);
                    if (ctrl) updateSelEnd(selStartIndex);
                } else if (arrowCursor >= selEndIndex) {
                    updateSelEnd(++arrowCursor);
                    if (ctrl) updateSelStart(selEndIndex);
                }
                break;
            default:
            }
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public boolean charTyped(char chr, int keyCode) {
        if (selEndIndex < selStartIndex) swapStartEnd();
        content = content.substring(0, selStartIndex) + chr + content.substring(selEndIndex);
        if (onChange != null) onChange.accept(content);
        updateSelStart(selStartIndex + 1);
        arrowCursor = selStartIndex;
        updateSelEnd(arrowCursor);
        return false;
    }

    public boolean clicked(double mouseX, double mouseY) {
        boolean bl = super.clicked(mouseX, mouseY);
        if (this.isFocused() ^ bl) this.changeFocus(true);
        return bl;
    }

    protected void renderMessage(MatrixStack matricies) {
        fill(matricies, selStart, height > 9 ? y + 2 : y, Math.min(selEnd, x + width - 2), (height > 9 ? y + 2 : y) + mc.textRenderer.fontHeight, selColor);
        drawStringWithShadow(matricies, mc.textRenderer, mc.textRenderer.trimToWidth(content, width - 4), x + 2, height > 9 ? y + 2 : y, textColor);
    }
}
