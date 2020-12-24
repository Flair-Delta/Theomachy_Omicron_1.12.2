package com.septagram.Ability.Human;

import com.septagram.Ability.Ability;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class Gardener extends Ability {

    private final static String[] des= {
            "돌과 꽃으로 기지 앞마당을 꾸며보세요.",
            ChatColor.YELLOW+"【패시브】 "+ChatColor.WHITE+"소재 획득",
            "나무를 캐면 돌 1개, 꽃 한 송이를 얻습니다.",
            "얻은 돌과 꽃으로 기지의 앞마당을",
            "아름답게 꾸밉시다! 우리 강산 푸르게 푸르게"};

    public Gardener(String playerName) {
        super(playerName, "정원사", 135, false, true, false, des);

        this.rank=2;

    }

    public void T_Passive(BlockBreakEvent event)
    {
        Material m=event.getBlock().getType();
        if(m.equals(Material.LOG)){
            event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.RED_ROSE, 1));
            event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COBBLESTONE, 1));
        }
    }

    public void conditionSet(){
        Bukkit.getPlayer(playerName).getInventory().addItem(new ItemStack(Material.SAPLING, 5));
        Bukkit.getPlayer(playerName).getInventory().addItem(new ItemStack(Material.BONE, 3));
    }

}
