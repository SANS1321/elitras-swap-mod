package com.example.elitras;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.FireworksItem;
import net.minecraft.world.item.MaceItem;

public class ElitraSwapConfig {
    
    /**
     * Проверяет, является ли предмет булавой
     */
    public static boolean isMaceItem(ItemStack stack) {
        return stack.getItem() instanceof MaceItem;
    }
    
    /**
     * Проверяет, является ли предмет фейерверком
     */
    public static boolean isFireworksItem(ItemStack stack) {
        return stack.getItem() instanceof FireworksItem;
    }
    
    /**
     * Проверяет, являются ли элитры
     */
    public static boolean isElytra(ItemStack stack) {
        return stack.getItem() == Items.ELYTRA;
    }
    
    /**
     * Проверяет, является ли предмет броней (нагрудником)
     */
    public static boolean isArmorChestplate(ItemStack stack) {
        return stack.getItem() == Items.DIAMOND_CHESTPLATE ||
               stack.getItem() == Items.NETHERITE_CHESTPLATE ||
               stack.getItem() == Items.IRON_CHESTPLATE ||
               stack.getItem() == Items.GOLDEN_CHESTPLATE ||
               stack.getItem() == Items.LEATHER_CHESTPLATE;
    }
    
    /**
     * Получает строковое описание типа брони
     */
    public static String getArmorType(ItemStack stack) {
        if (stack.getItem() == Items.DIAMOND_CHESTPLATE) return "Diamond";
        if (stack.getItem() == Items.NETHERITE_CHESTPLATE) return "Netherite";
        if (stack.getItem() == Items.IRON_CHESTPLATE) return "Iron";
        if (stack.getItem() == Items.GOLDEN_CHESTPLATE) return "Golden";
        if (stack.getItem() == Items.LEATHER_CHESTPLATE) return "Leather";
        return "Unknown";
    }
    
    /**
     * Сохраняет данные о переключении для отладки
     */
    public static void logSwap(Player player, String action, String from, String to) {
        if (player.level().isClientSide) {
            return;
        }
        
        ElitraSwapMod.LOGGER.info("Player {} performed action: {} - From: {} To: {}", 
            player.getName().getString(), action, from, to);
    }
    
    /**
     * Проверяет, может ли игрок летать (находится ли в инвентаре элитра)
     */
    public static boolean hasElytraInInventory(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (isElytra(stack)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Находит броню в инвентаре
     */
    public static ItemStack findArmorInInventory(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (isArmorChestplate(stack) && !stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
    
    /**
     * Находит элитры в инвентаре
     */
    public static ItemStack findElytraInInventory(Player player) {
        for (ItemStack stack : player.getInventory().items) {
            if (isElytra(stack) && !stack.isEmpty()) {
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }
}
