package com.pandapulsestudios.achievesmp.Configs;

import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Enum.SMPMessage;
import com.pandapulsestudios.pulseconfig.APIS.ConfigAPI;
import com.pandapulsestudios.pulseconfig.Enums.SaveableType;
import com.pandapulsestudios.pulseconfig.Interfaces.Config.PulseConfig;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableArrayList;
import com.pandapulsestudios.pulseconfig.Objects.Savable.SaveableHashmap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class AchieveSMPSettings implements PulseConfig, Listener {
    @Override
    public String documentID() { return "AchieveSMPSettings"; }

    @Override
    public JavaPlugin mainClass() { return AchieveSMP.AchieveSMP; }

    @Override
    public boolean useSubFolder() { return false; }

    public SaveableHashmap<UUID, Boolean> listenForPlayers = new SaveableHashmap<>(SaveableType.CONFIG, UUID.class, Boolean.class);
    public SaveableArrayList<String> enabledAdvancements = new SaveableArrayList<String>(SaveableType.CONFIG, String.class);
    public boolean systemEnabled = true;
    public boolean fullHealOnComplete = false;
    public int startingHealth = 20;
    public int maximumHearts = 40;
    public int amountOnComplete = 2;
    public int looseOnDeath = 2;

    public AchieveSMPSettings(boolean debugLoad){
        ConfigAPI.Load(this, debugLoad);
    }


    public void EnableSystem(boolean state, boolean debugLoad){
        systemEnabled = state;
        ConfigAPI.Save(this, debugLoad);
    }

    @EventHandler
    public void PlayerAdvancementDone(PlayerAdvancementDoneEvent event){
        var player = event.getPlayer();
        if(!listenForPlayers.hashMap.getOrDefault(player.getUniqueId(), systemEnabled)) return;
        var advancementKey = event.getAdvancement().getKey().getKey();
        if(!enabledAdvancements.arrayList.contains(advancementKey) && !enabledAdvancements.arrayList.isEmpty()) return;
        ManipulateMaxHealth(player, amountOnComplete);
        AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.CompletedAdvancement, player);
    }


    @EventHandler
    public void PlayerDeath(PlayerDeathEvent event){
        ManipulateMaxHealth(event.getEntity(), -looseOnDeath);
    }

    @EventHandler
    public void PlayerRespawn(PlayerRespawnEvent event){
        AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.PlayerDied, event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if(listenForPlayers.hashMap.containsKey(event.getPlayer().getUniqueId())) return;
        event.getPlayer().setMaxHealth(startingHealth);
        event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
        AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.ExplainHeartSystem, event.getPlayer());
        listenForPlayers.hashMap.put(event.getPlayer().getUniqueId(), systemEnabled);
        ConfigAPI.Save(this, false);
    }

    private void ManipulateMaxHealth(Player player, int modifier){
        var currentMaxHealth = player.getMaxHealth();
        var nextMaxHealth = Math.max(1, Math.min(currentMaxHealth + modifier, maximumHearts));
        player.setMaxHealth(nextMaxHealth);
        if(fullHealOnComplete) player.setHealth(player.getMaxHealth());
    }


}
