package me.orangemonkey68.thread.config;

import me.orangemonkey68.thread.Thread;
import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name=Thread.MODID)
public class ThreadConfig implements ConfigData {
    public boolean drawArmorHUD = true;
    public boolean drawArmorDurabilityBar = true;
    public boolean drawArmorDurabilityText = true;

    public int armorHUDXOffset = 0;
    public int armorHUDYOffset = 0;
}
