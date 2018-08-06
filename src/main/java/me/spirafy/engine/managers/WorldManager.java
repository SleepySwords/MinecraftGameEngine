package me.spirafy.engine.managers;

/*
 * This code was originally developed by Ibrahim.
 * You may contact by his email: Nintendodeveloper8@gmail.com
 * You can also contact him by his Discord: sword1234#6398
 */


import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class WorldManager {


    public World copyFolder(World world, String destWorldName) {
        try {
            File dest = new File(destWorldName);
            if(!dest.exists()){

                dest.mkdir();
            }

            FileUtils.copyDirectory(world.getWorldFolder(), dest);
            File uid = new File(destWorldName + "/uid.dat");
            if(uid.isFile()){

                System.out.println("This works");
            }
            uid.delete();

            return WorldCreator.name(destWorldName).createWorld();
            //FileUtils.deleteDirectory(world.getWorldFolder());
        } catch (IOException e) {

            e.printStackTrace();

        }
        return null;
    }

    public void dirDelete(File file) throws IOException
    {
        if (file.isDirectory())
        {
            if (file.list().length == 0)
                file.delete();
            else
            {
                String files[] = file.list();

                for (String temp : files)
                    dirDelete(new File(file, temp));

                if (file.list().length == 0)
                    file.delete();
            }
        }
        else
            file.delete();
    }

    public void deleteWorld(World world){
        if (world != null) {
            for(Player o : world.getPlayers()){
                o.teleport(new Location(Bukkit.getWorld("world"), 10, 40, 10));
            }

            Bukkit.getServer().unloadWorld(world, true);
            try {
                FileUtils.deleteDirectory(world.getWorldFolder());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
