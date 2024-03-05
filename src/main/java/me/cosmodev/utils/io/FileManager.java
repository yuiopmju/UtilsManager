package me.cosmodev.utils.io;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private File file;
    private String name;
    private boolean isYmlFormat = false;
    private boolean isNew = false;

    public FileManager(File folder, String name){
        if(!folder.exists()){
            folder.mkdirs();
        }

        this.name = name;

        if(name.endsWith(".yml")){
            isYmlFormat = true;
        }

        try {
            File file = new File(folder, name);
            this.file = file;
            if(!file.exists()){
                file.createNewFile();
                isNew = true;
            }
        } catch (IOException e){
            Bukkit.getLogger().warning(e.getMessage());
            e.printStackTrace();
        }
    }

    public FileManager(JavaPlugin plugin, String name){
        File folder = plugin.getDataFolder();

        if(!folder.exists()){
            folder.mkdirs();
        }

        this.name = name;

        if(name.endsWith(".yml")){
            isYmlFormat = true;
        }

        try {
            File file = new File(folder, name);
            this.file = file;
            if(!file.exists()){
                file.createNewFile();
                isNew = true;
            }
        } catch (IOException e){
            Bukkit.getLogger().warning(e.getMessage());
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isYmlFormat() {
        return isYmlFormat;
    }

    public void save(FileConfiguration data){
        try {
            data.save(getFile());
        } catch (IOException e){
            Bukkit.getLogger().warning(e.getMessage());
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfiguration(){
        return YamlConfiguration.loadConfiguration(getFile());
    }
// Example:
//    public static ConfigManager config;
//
//    config = new ConfigManager(this, "config.yml");
//        if(config.isNew()){
//        FileConfiguration data = config.getConfiguration();
//        data.set("example.section.in.example.config.with.example.value", "ExampleValue");
//        data.getString("example.section.in.example.config.with.example.value");
//        config.save(data);
//    }
}
