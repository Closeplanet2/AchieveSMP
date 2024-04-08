package com.pandapulsestudios.achievesmp;

import com.pandapulsestudios.achievesmp.API.AchieveSMPConfigAPI;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPMenus;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPPermissions;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPSettings;
import com.pandapulsestudios.pulsecore.Java.ClassAPI;
import org.bukkit.plugin.java.JavaPlugin;
import com.pandapulsestudios.pulsecommands.PulseCommands;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPMessages;

public final class AchieveSMP extends JavaPlugin {
    public static AchieveSMP AchieveSMP;
    public static AchieveSMPSettings AchieveSMPSettings;
    public static AchieveSMPPermissions AchieveSMPPermissions;
    public static AchieveSMPMenus AchieveSMPMenus;
    public static AchieveSMPMessages AchieveSMPMessages;

    @Override
    public void onEnable() {
        AchieveSMP = this;
        ClassAPI.RegisterClasses(this);
        PulseCommands.RegisterRaw(this);
        AchieveSMPConfigAPI.ResetConfigs(false);
    }
}
