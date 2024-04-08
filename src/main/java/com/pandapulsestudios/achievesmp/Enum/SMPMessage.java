package com.pandapulsestudios.achievesmp.Enum;

public enum SMPMessage {
    PlayerPermissionCommandFail("#fa3448You do not have the permissions to use this command!"),
    PlayerReloadedConfig("#7fff36You have successfully reload the configs!"),
    PlayerResetConfig("#7fff36You have successfully reset the configs!"),
    PlayerEnabledSystem("#7fff36You have enabled the system!"),
    PlayerDisbaleSystem("#fa3448You have disabled the system!"),
    CompletedAdvancement("#7fff36You have completed a advancement and gained hearts!"),
    PlayerDied("#fa3448Because you have died, you have lost hearts! you wont go below 1 heart!"),
    ExplainHeartSystem("#7fff36This server is using AchieveSMP! You will gain on advancement completion!");

    public final String message;
    SMPMessage(String message){
        this.message = message;
    }
}
