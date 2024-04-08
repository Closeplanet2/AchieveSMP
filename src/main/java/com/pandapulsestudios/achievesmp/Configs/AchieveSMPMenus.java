package com.pandapulsestudios.achievesmp.Configs;

import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Enum.SMPMenu;
import com.pandapulsestudios.achievesmp.Enum.SMPMessage;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableArrayList;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class AchieveSMPMenus implements PulseConfig {
    @Override
    public String documentID() { return "AchieveSMPMenus"; }

    @Override
    public JavaPlugin mainClass() { return AchieveSMP.AchieveSMP; }

    @Override
    public boolean useSubFolder() { return false; }

    public SaveableHashmap<SMPMenu, String[]> smpMenus = new SaveableHashmap<>(SaveableType.CONFIG, SMPMenu.class, String[].class);

    @Override
    public void FirstLoad() {
        for(var x : SMPMenu.values()){
            if(!smpMenus.hashMap.containsKey(x)) smpMenus.hashMap.put(x, x.menu);
        }
    }

    @Override
    public void AfterLoad() {
        for(var x : SMPMenu.values()){
            if(!smpMenus.hashMap.containsKey(x)) smpMenus.hashMap.put(x, x.menu);
        }
        ConfigAPI.Save(this, false);
    }

    @Override
    public void BeforeSave() {
        for(var x : SMPMenu.values()){
            if(!smpMenus.hashMap.containsKey(x)) smpMenus.hashMap.put(x, x.menu);
        }
    }

    public AchieveSMPMenus(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }

    public void DisplayMenuTOPlayer(SMPMenu smpMenu, Player player){
        if(player == null) return;
        for(var x : smpMenus.hashMap.getOrDefault(smpMenu, smpMenu.menu)){
            ChatAPI.chatBuilder().messageType(MessageType.Player).playerToo(player).SendMessage(x);
        }
    }

}
