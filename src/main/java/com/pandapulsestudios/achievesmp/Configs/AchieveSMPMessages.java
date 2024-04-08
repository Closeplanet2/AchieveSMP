package com.pandapulsestudios.achievesmp.Configs;

import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Enum.SMPMessage;
import com.pandapulsestudios.achievesmp.Enum.SMPPermission;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import com.pandapulsestudios.pulsecore.Chat.ChatAPI;
import com.pandapulsestudios.pulsecore.Chat.MessageType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class AchieveSMPMessages implements PulseConfig {
    @Override
    public String documentID() { return "AchieveSMPMessages"; }

    @Override
    public JavaPlugin mainClass() { return AchieveSMP.AchieveSMP; }

    @Override
    public boolean useSubFolder() { return false; }

    public SaveableHashmap<SMPMessage, String> smpMessages = new SaveableHashmap<>(SaveableType.CONFIG, SMPMessage.class, String.class);

    @Override
    public void FirstLoad() {
        for(var x : SMPMessage.values()){
            if(!smpMessages.hashMap.containsKey(x)) smpMessages.hashMap.put(x, x.message);
        }
    }

    @Override
    public void AfterLoad() {
        for(var x : SMPMessage.values()){
            if(!smpMessages.hashMap.containsKey(x)) smpMessages.hashMap.put(x, x.message);
        }
        ConfigAPI.Save(this, false);
    }

    @Override
    public void BeforeSave() {
        for(var x : SMPMessage.values()){
            if(!smpMessages.hashMap.containsKey(x)) smpMessages.hashMap.put(x, x.message);
        }
    }

    public AchieveSMPMessages(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }

    public void SendMessageToPlayer(SMPMessage smpMessage, Player player){
        if(player == null) return;
        var storedMessage = smpMessages.hashMap.getOrDefault(smpMessage, smpMessage.message);
        ChatAPI.chatBuilder().messageType(MessageType.Player).playerToo(player).SendMessage(storedMessage);
    }
}
