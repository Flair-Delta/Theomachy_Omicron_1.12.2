package com.septagram.Manager.CommandModule;

import com.septagram.Theomachy.Theomachy;
import com.septagram.Utility.PermissionChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class GUISetting {

    public static void Module(CommandSender sender) {

        Player p=(Player)sender;

        if(PermissionChecker.Sender(sender)) {

            p.openInventory(gui());

        }
    }

    private static Inventory gui() {

        Inventory gui=Bukkit.createInventory(null, 27, ChatColor.BLACK+":::::: 설정 ::::::");

        ArrayList<ItemStack> wool=new ArrayList<>();
        ArrayList<ItemMeta> meta=new ArrayList<>();
        ArrayList<Integer> dura=new ArrayList<>();

        dura.add(Theomachy.INVENTORY_CLEAR ? 5:14);
        dura.add(Theomachy.GIVE_ITEM ? 5:14);
        dura.add(Theomachy.ENTITIES_REMOVE ? 5:14);
        dura.add(Theomachy.IGNORE_BED ? 5:14);
        dura.add(Theomachy.FAST_START ? 5:14);
        dura.add(Theomachy.AUTO_SAVE ? 5:14);
        dura.add(Theomachy.ANIMAL  ? 5:14);
        dura.add(Theomachy.MONSTER  ? 5:14);
        dura.add(Theomachy.GAMB ? 5:14);
        dura.add(Theomachy.PROTECT ? 5:14);
        dura.add(Theomachy.FORBID ? 5:14);
        dura.add(Theomachy.SCOREBOARD ? 5:14);

        for(int i=0;i<dura.size();i++) {
            ItemStack is=new ItemStack(Material.WOOL);
            is.setDurability(dura.get(i).shortValue());
            wool.add(is);
            meta.add(wool.get(i).getItemMeta());
        }

        meta.get(0).setDisplayName(ChatColor.WHITE+"게임 시작 시 인벤토리 클리어");
        meta.get(1).setDisplayName(ChatColor.WHITE+"게임 시작 시 스카이블럭 아이템 지급");
        meta.get(2).setDisplayName(ChatColor.WHITE+"게임 시작 시 엔티티 제거");
        meta.get(3).setDisplayName(ChatColor.WHITE+"침대 무시");
        meta.get(4).setDisplayName(ChatColor.WHITE+"빠른 시작");
        meta.get(5).setDisplayName(ChatColor.WHITE+"서버 자동 저장");
        meta.get(6).setDisplayName(ChatColor.WHITE+"동물 스폰");
        meta.get(7).setDisplayName(ChatColor.WHITE+"몬스터 스폰");
        meta.get(8).setDisplayName(ChatColor.WHITE+"도박 허용");
        meta.get(9).setDisplayName(ChatColor.WHITE+"다이아몬드 블록 방어");
        meta.get(10).setDisplayName(ChatColor.WHITE+"다이아 곡괭이 금지");
        meta.get(11).setDisplayName(ChatColor.WHITE+"스코어보드 안내 사용");

        for(int i=0;i<wool.size();i++) {
            wool.get(i).setItemMeta(meta.get(i));
        }

        for(int i=0;i<5;i++){
            gui.setItem(i*2, wool.get(i));
        }

        for(int i=0;i<5;i++){
            gui.setItem((i*2)+9, wool.get(i+5));
        }

        gui.setItem(20, wool.get(10));
        gui.setItem(23, wool.get(11));

        return gui;
    }

    public static void guiListener(ItemStack wool){
        int code=wool.getDurability();
        switch(ChatColor.stripColor(wool.getItemMeta().getDisplayName())) {
            case "게임 시작 시 인벤토리 클리어":
                if(code==5) { Theomachy.INVENTORY_CLEAR=false; wool.setDurability((short)14);}
                else { Theomachy.INVENTORY_CLEAR=true; wool.setDurability((short)5); }
                break;
            case "게임 시작 시 스카이블럭 아이템 지급":
                if(code==5) { Theomachy.GIVE_ITEM=false; wool.setDurability((short)14);}
                else { Theomachy.GIVE_ITEM=true; wool.setDurability((short)5); }
                break;
            case "게임 시작 시 엔티티 제거":
                if(code==5) { Theomachy.ENTITIES_REMOVE=false; wool.setDurability((short)14);}
                else { Theomachy.ENTITIES_REMOVE=true; wool.setDurability((short)5); }
                break;
            case "침대 무시":
                if(code==5) { Theomachy.IGNORE_BED=false; wool.setDurability((short)14);}
                else { Theomachy.IGNORE_BED=true; wool.setDurability((short)5); }
                break;
            case "빠른 시작":
                if(code==5) { Theomachy.FAST_START=false; wool.setDurability((short)14);}
                else { Theomachy.FAST_START=true; wool.setDurability((short)5); }
                break;
            case "서버 자동 저장":
                if(code==5) { Theomachy.AUTO_SAVE=false; wool.setDurability((short)14);}
                else { Theomachy.AUTO_SAVE=true; wool.setDurability((short)5); }
                break;
            case "동물 스폰":
                if(code==5) { Theomachy.ANIMAL=false; wool.setDurability((short)14);}
                else { Theomachy.ANIMAL=true; wool.setDurability((short)5); }
                break;
            case "몬스터 스폰":
                if(code==5) { Theomachy.MONSTER=false; wool.setDurability((short)14);}
                else { Theomachy.MONSTER=true; wool.setDurability((short)5); }
                break;
            case "도박 허용":
                if(code==5) { Theomachy.GAMB=false; wool.setDurability((short)14);}
                else { Theomachy.GAMB=true; wool.setDurability((short)5); }
                break;
            case "다이아몬드 블록 방어":
                if(code==5) { Theomachy.PROTECT=false; wool.setDurability((short)14);}
                else { Theomachy.PROTECT=true; wool.setDurability((short)5); }
                break;
            case "다이아 곡괭이 금지":
                if(code==5) { Theomachy.FORBID=false; wool.setDurability((short)14);}
                else { Theomachy.FORBID=true; wool.setDurability((short)5); }
                break;
            case "스코어보드 안내 사용":
                if(code==5) { Theomachy.SCOREBOARD=false; wool.setDurability((short)14);}
                else { Theomachy.SCOREBOARD=true; wool.setDurability((short)5); }
                break;
        }
    }

}
