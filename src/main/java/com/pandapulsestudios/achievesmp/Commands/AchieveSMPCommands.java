package com.pandapulsestudios.achievesmp.Commands;

import com.pandapulsestudios.achievesmp.API.AchieveSMPAPI;
import com.pandapulsestudios.achievesmp.API.AchieveSMPConfigAPI;
import com.pandapulsestudios.achievesmp.AchieveSMP;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPMessages;
import com.pandapulsestudios.achievesmp.Configs.AchieveSMPSettings;
import com.pandapulsestudios.achievesmp.Enum.SMPMenu;
import com.pandapulsestudios.achievesmp.Enum.SMPMessage;
import com.pandapulsestudios.achievesmp.Enum.SMPPermission;
import com.pandapulsestudios.pulsecommands.Enums.TabType;
import com.pandapulsestudios.pulsecommands.Interface.PCMethod;
import com.pandapulsestudios.pulsecommands.Interface.PCPerm;
import com.pandapulsestudios.pulsecommands.Interface.PCSignature;
import com.pandapulsestudios.pulsecommands.Interface.PCTab;
import com.pandapulsestudios.pulsecommands.PlayerCommand;
import com.pandapulsestudios.pulsecore.Java.PulseAutoRegister;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

@PulseAutoRegister
public class AchieveSMPCommands extends PlayerCommand{
    public AchieveSMPCommands() { super("achievesmp", false, "asmp"); }

    @Override
    public void NoMethodFound(CommandSender commandSender, String s, String[] strings) {
        AchieveSMP.AchieveSMPMenus.DisplayMenuTOPlayer(SMPMenu.HELP_MENU, (Player) commandSender);
    }

    @PCMethod
    @PCSignature({"enable"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[ENABLE SYSTEM]")
    @PCTab(pos = 2, type = TabType.Pure_Data, data = "[DEBUG STATE]")
    public void EnableSystem(CraftPlayer admin, boolean state, boolean debug){
        if(!AchieveSMP.AchieveSMPPermissions.DoesPlayerHavePermission(admin, SMPPermission.ENABLE_SYSTEM)){
            AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.PlayerPermissionCommandFail, admin);
            return;
        }
        AchieveSMPAPI.EnableSystem(admin, state, debug);
    }

    @PCMethod
    @PCSignature({"configs", "reload"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[DEBUG STATE]")
    public void ReloadConfigs(CraftPlayer admin, boolean state){
        if(!AchieveSMP.AchieveSMPPermissions.DoesPlayerHavePermission(admin, SMPPermission.RELOAD_CONFIGS)){
            AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.PlayerPermissionCommandFail, admin);
            return;
        }
        AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.PlayerReloadedConfig, admin);
        AchieveSMPConfigAPI.ReloadConfigs(state);
    }

    @PCMethod
    @PCSignature({"configs", "restart"})
    @PCTab(pos = 1, type = TabType.Pure_Data, data = "[DEBUG STATE]")
    public void RestartConfigs(CraftPlayer admin, boolean state){
        if(!AchieveSMP.AchieveSMPPermissions.DoesPlayerHavePermission(admin, SMPPermission.RESET_CONFIGS)){
            AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.PlayerPermissionCommandFail, admin);
            return;
        }
        AchieveSMP.AchieveSMPMessages.SendMessageToPlayer(SMPMessage.PlayerResetConfig, admin);
        AchieveSMPConfigAPI.ResetConfigs(state);
    }
}
