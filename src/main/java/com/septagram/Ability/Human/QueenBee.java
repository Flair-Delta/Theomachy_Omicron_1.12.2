package com.septagram.Ability.Human;

import java.util.Random;

import com.septagram.Ability.Ability;
import com.septagram.Utility.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class QueenBee extends Ability {

    public final static String[] des = {
            "여왕벌은 벌들의 제왕입니다.",
            ChatColor.YELLOW + "【패시브】 " + ChatColor.WHITE + "독침",
            "자신을 공격해 온 적에게 25%의",
            "확률로 중독되게 합니다.",
            ChatColor.AQUA + "【일반】 " + ChatColor.WHITE + "페로몬",
            "목표로 지정해 둔 가까이 있는 상대를",
            "자신의 위치로 끌어옵니다.",
            "자신이 블록 위에 서 있고",
            "웅크리지 않아야 발동합니다.",
            "목표 지정: /x <대상>"};


    private String abilitytarget;

    public QueenBee(String playerName) {
        super(playerName, "여왕벌", 123, true, true, false, des);

        this.rank = 4;

        this.cool1 = 180;
        this.sta1 = 32;

    }

    public void T_Active(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (PlayerInventory.InHandItemCheck(player, st)) {
            switch (EventFilter.PlayerInteract(event)) {
                case 0:
                case 1:
                    leftAction(player);
                    break;
            }
        }
    }

    private void leftAction(Player player) {
        if (CoolTimeChecker.Check(player, 0) && PlayerInventory.ItemCheck(player, co, sta1)) {
            if (!player.isSneaking() && !player.getLocation().add(0, -1, 0).getBlock().getType().equals(Material.AIR)) {
                if (abilitytarget != null) {

                    Player target = Bukkit.getPlayer(abilitytarget);

                    if (target == null) {
                        player.sendMessage(abilitytarget + ChatColor.RED + " 님은 현재 서버에 없는 것 같습니다..");
                    } else {
                        if (player.getNearbyEntities(10, 10, 10).contains(target)) {
                            Skill.Use(player, co, sta1, 0, cool1);

                            player.sendMessage(ChatColor.YELLOW + "페로몬" + ChatColor.WHITE + "을 이용하여 목표를 유혹했습니다!");
                            target.sendMessage(ChatColor.YELLOW + "페로몬" + ChatColor.WHITE + "에 유혹당했습니다!");

                            target.teleport(player);
                        } else {
                            player.sendMessage("대상이 너무 멀리 있어 실패했습니다.");
                        }
                    }
                } else {
                    player.sendMessage("목표를 설정해주십시오. (목표 설정법: /x <목표>)");
                }
            } else {
                player.sendMessage(ChatColor.RED + "웅크리고 있거나 발 밑의 블록이 없어 능력이 발동되지 않았습니다.");
            }

        }
    }

    public void targetSet(CommandSender sender, String targetName) {
        if (!playerName.equals(targetName)) {
            this.abilitytarget = targetName;
            sender.sendMessage("타겟을 등록했습니다.   " + ChatColor.GREEN + targetName);
        } else
            sender.sendMessage("자기 자신을 목표로 등록 할 수 없습니다.");
    }

    public void T_Passive(EntityDamageByEntityEvent event) {
        Player p = (Player) event.getEntity();
        Player e = (Player) event.getDamager();

        if (p.getName().equals(this.playerName)) {
            Random r = new Random();

            if (r.nextInt(4) == 0) {
                e.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
                e.sendMessage(ChatColor.GOLD + "벌에게 쏘였습니다!");
            }
        }
    }

}
