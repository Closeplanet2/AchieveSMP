package com.pandapulsestudios.achievesmp.API;

import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPMenus;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPMessages;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPPermissions;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPSettings;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulsecore.Java.ClassAPI;

public class AchieveSMPConfigAPI {
    public static void ReloadConfigs(boolean debugLoad){
        ConfigAPI.Load(AchieveSMP.AchieveSMPSettings, debugLoad);
        ConfigAPI.Load(AchieveSMP.AchieveSMPMenus, debugLoad);
        ConfigAPI.Load(AchieveSMP.AchieveSMPPermissions, debugLoad);
        ConfigAPI.Load(AchieveSMP.AchieveSMPMessages, debugLoad);
    }

    public static void ResetConfigs(boolean debugLoad){
        if(AchieveSMP.AchieveSMPSettings != null) ConfigAPI.Delete(AchieveSMP.AchieveSMPSettings);
        if(AchieveSMP.AchieveSMPMenus != null) ConfigAPI.Delete(AchieveSMP.AchieveSMPMenus);
        if(AchieveSMP.AchieveSMPPermissions != null) ConfigAPI.Delete(AchieveSMP.AchieveSMPPermissions);
        if(AchieveSMP.AchieveSMPMessages != null) ConfigAPI.Delete(AchieveSMP.AchieveSMPMessages);
        AchieveSMP.AchieveSMPSettings = new AchieveSMPSettings(debugLoad);
        AchieveSMP.AchieveSMPMenus = new AchieveSMPMenus(debugLoad);
        AchieveSMP.AchieveSMPPermissions = new AchieveSMPPermissions(debugLoad);
        AchieveSMP.AchieveSMPMessages = new AchieveSMPMessages(debugLoad);
        ClassAPI.RegisterListener(AchieveSMP.AchieveSMP, AchieveSMP.AchieveSMPSettings);
    }
}
