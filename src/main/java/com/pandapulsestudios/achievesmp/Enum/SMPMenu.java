package com.pandapulsestudios.achievesmp.Enum;

public enum SMPMenu {
    HELP_MENU("/AchieveSMP enabled [TRUE/FALSE]", "/AchieveSMP config reload [TRUE/FALSE]", "/AchieveSMP config reset [TRUE/FALSE]");

    public final String[] menu;
    SMPMenu(String... menu){
        this.menu = menu;
    }

}
