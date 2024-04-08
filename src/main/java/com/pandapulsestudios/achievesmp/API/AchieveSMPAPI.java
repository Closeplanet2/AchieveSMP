package com.pandapulsestudios.achievesmp.API;

import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Enum.SMPMessage;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AchieveSMPAPI {
    public static void EnableSystem(Player player, Boolean state, Boolean debug){
        AchieveSMP.AchieveSMPSettings.EnableSystem(state, debug);
        AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(state ? SMPMessage.PlayerEnabledSystem : SMPMessage.PlayerDisbaleSystem, player);
    }

    public static void AddAdvancements(String advancement, boolean debug){
        if(ListenForAdvancement(advancement)) return;
        AchieveSMP.AchieveSMPSettings.enabledAdvancements.arrayList.add(advancement);
        ConfigAPI.Save(AchieveSMP.AchieveSMPSettings, debug);
    }

    public static void RemoveAdvancements(String advancement, boolean debug){
        if(!ListenForAdvancement(advancement)) return;
        AchieveSMP.AchieveSMPSettings.enabledAdvancements.arrayList.remove(advancement);
        ConfigAPI.Save(AchieveSMP.AchieveSMPSettings, debug);
    }

    public static void EnablePlayerListen(UUID uuid, boolean state, boolean debug){
        if(!AchieveSMP.AchieveSMPSettings.listenForPlayers.hashMap.containsKey(uuid)) return;
        AchieveSMP.AchieveSMPSettings.listenForPlayers.hashMap.put(uuid, state);
        ConfigAPI.Save(AchieveSMP.AchieveSMPSettings, debug);
    }

    public static boolean CurrentPlayerListenState(UUID uuid){
        return AchieveSMP.AchieveSMPSettings.listenForPlayers.hashMap.getOrDefault(uuid, IsSystemEnabled());
    }

    public static boolean ListenForAdvancement(String advancement){
        return AchieveSMP.AchieveSMPSettings.enabledAdvancements.arrayList.contains(advancement);
    }

    public static boolean IsSystemEnabled(){
        return AchieveSMP.AchieveSMPSettings.systemEnabled;
    }


}
