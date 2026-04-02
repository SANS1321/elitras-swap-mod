package com.example.elitras;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElitraSwapMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ElitraSwapHandler {
    private static ItemStack lastMainHand = ItemStack.EMPTY;
    private static ItemStack lastOffHand = ItemStack.EMPTY;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();
        
        // Только на сервере
        if (player.level().isClientSide) {
            return;
        }

        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();
        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        // Проверяем, изменился ли предмет в руках
        if (!ItemStack.isSameItemSameTags(mainHand, lastMainHand) || 
            !ItemStack.isSameItemSameTags(offHand, lastOffHand)) {
            
            handleItemSwap(player, mainHand, offHand, chest);
            
            lastMainHand = mainHand.copy();
            lastOffHand = offHand.copy();
        }
    }

    private static void handleItemSwap(Player player, ItemStack mainHand, ItemStack offHand, ItemStack chest) {
        // Проверяем, есть ли булава в руках
        boolean hasMace = ElitraSwapConfig.isMaceItem(mainHand) || ElitraSwapConfig.isMaceItem(offHand);
        
        // Проверяем, есть ли фейерверки в руках
        boolean hasFireworks = ElitraSwapConfig.isFireworksItem(mainHand) || ElitraSwapConfig.isFireworksItem(offHand);
        
        // Проверяем, одеты ли элитры
        boolean hasElytra = ElitraSwapConfig.isElytra(chest);

        // Булава в руках и на нас элитры
        if (hasMace && hasElytra) {
            swapToArmor(player, chest);
        } 
        // Фейерверки в руках и на нас не элитры
        else if (hasFireworks && !hasElytra) {
            swapToElytra(player, chest);
        }
    }

    private static void swapToArmor(Player player, ItemStack currentChest) {
        // Ищем броню в инвентаре
        ItemStack armor = ElitraSwapConfig.findArmorInInventory(player);
        
        if (!armor.isEmpty()) {
            ItemStack armorCopy = armor.copy();
            armorCopy.setCount(1);
            
            // Кладем элитры в инвентарь
            if (!insertIntoInventory(player, currentChest.copy())) {
                player.drop(currentChest.copy(), false);
            }
            
            // Надеваем броню
            player.setItemSlot(EquipmentSlot.CHEST, armorCopy);
            armor.shrink(1);
            
            // Логируем
            ElitraSwapConfig.logSwap(player, "Mace taken", "Elytra", ElitraSwapConfig.getArmorType(armorCopy));
        }
    }

    private static void swapToElytra(Player player, ItemStack currentChest) {
        // Ищем элитры в инвентаре
        ItemStack elytra = ElitraSwapConfig.findElytraInInventory(player);
        
        if (!elytra.isEmpty()) {
            // Кладем броню в инвентарь
            if (!currentChest.isEmpty() && !insertIntoInventory(player, currentChest.copy())) {
                player.drop(currentChest.copy(), false);
            }
            
            // Надеваем элитры
            ItemStack elytraCopy = elytra.copy();
            elytraCopy.setCount(1);
            player.setItemSlot(EquipmentSlot.CHEST, elytraCopy);
            elytra.shrink(1);
            
            // Логируем
            String armorType = !currentChest.isEmpty() ? ElitraSwapConfig.getArmorType(currentChest) : "None";
            ElitraSwapConfig.logSwap(player, "Fireworks taken", armorType, "Elytra");
        }
    }

    private static boolean insertIntoInventory(Player player, ItemStack stack) {
        // Пытаемся добавить предмет в первый свободный слот инвентаря
        for (int i = 0; i < player.getInventory().items.size(); i++) {
            ItemStack slot = player.getInventory().items.get(i);
            if (slot.isEmpty()) {
                player.getInventory().items.set(i, stack);
                return true;
            }
        }
        return false;
    }
}
