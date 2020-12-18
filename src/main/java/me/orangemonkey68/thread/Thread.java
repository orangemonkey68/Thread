package me.orangemonkey68.thread;

import me.orangemonkey68.thread.config.ThreadConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Thread implements ModInitializer {
    public static final String MODID = "string";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void onInitialize() {
        AutoConfig.register(ThreadConfig.class, GsonConfigSerializer::new);



        /*
        * IDEAS:
        *
        * Wrench (rotates blocks)
        * Pokeball-ish (needs a better name) to carry mobs
        * More path options
        * Option to hide armor
        * Obsidian boat for lava (sinks in water)
        *
        * HUD:
        *   Armor durability
        *   Tool Durability
        *   Count of whatever's in your hand + offhand
        *
        * Lock certain inventory slots
        *
        * Make lists of items in JEI and then calculate all the ingredients
        *
        * Stop textures from animating in singleplayer worlds when the game is paused
        *
        */
    }
}
