package com.pandapulsestudios.achievesmp.Enum;

public enum SMPPermission {
    RELOAD_CONFIGS("AchieveSMP.RELOAD_CONFIGS"),
    RESET_CONFIGS("AchieveSMP.RESET_CONFIGS"),
    ENABLE_SYSTEM("AchieveSMP.ENABLE_SYSTEM");

    public final String permission;
    SMPPermission(String permission){
        this.permission = permission;
    }
}
