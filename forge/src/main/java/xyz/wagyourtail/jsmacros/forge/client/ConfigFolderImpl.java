package xyz.wagyourtail.jsmacros.forge.client;

import net.minecraft.client.MinecraftClient;
import xyz.wagyourtail.jsmacros.client.ConfigFolder;

import java.io.File;

public class ConfigFolderImpl implements ConfigFolder {
    @Override
    public File getFolder() {
        File directory = MinecraftClient.getInstance().runDirectory;
        directory = ".".equals(directory.getName()) ? directory.getParentFile() : directory;
        return new File(directory, "config/jsMacros");
    }

}
