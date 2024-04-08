package com.pandapulsestudios.achievesmp.Configs;

import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Enum.SMPPermission;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AchieveSMPPermissions implements PulseConfig {
    @Override
    public String documentID() { return "AchieveSMPPermissions"; }

    @Override
    public JavaPlugin mainClass() { return AchieveSMP.AchieveSMP; }

    @Override
    public boolean useSubFolder() { return false; }

    public SaveableHashmap<SMPPermission, String> smpPermissions = new SaveableHashmap<>(SaveableType.CONFIG, SMPPermission.class, String.class);

    @Override
    public void FirstLoad() {
        for(var x : SMPPermission.values()){
            if(!smpPermissions.hashMap.containsKey(x)) smpPermissions.hashMap.put(x, x.permission);
        }
    }

    @Override
    public void AfterLoad() {
        for(var x : SMPPermission.values()){
            if(!smpPermissions.hashMap.containsKey(x)) smpPermissions.hashMap.put(x, x.permission);
        }
        ConfigAPI.Save(this, false);
    }

    @Override
    public void BeforeSave() {
        for(var x : SMPPermission.values()){
            if(!smpPermissions.hashMap.containsKey(x)) smpPermissions.hashMap.put(x, x.permission);
        }
    }

    public AchieveSMPPermissions(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }

    public boolean DoesPlayerHavePermission(Player player, SMPPermission smpPermission){
        return player == null || player.hasPermission(smpPermissions.hashMap.getOrDefault(smpPermission, smpPermission.permission));
    }
}
