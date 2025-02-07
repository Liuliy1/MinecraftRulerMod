package com.liuliy.ruler.registry;

import com.liuliy.ruler.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    // 定义物品实例
    public static final Item STRAIGHT_RULER = new StraightRulerItem();

    public static final Item COMPASS = new CompassItem();

    public static final Item LASER_RANGEFINDER = new LaserRangefinderItem();

    // 注册所有物品
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("ruler-mod", "straight_ruler"), STRAIGHT_RULER);
        Registry.register(Registries.ITEM, new Identifier("ruler-mod", "compass"), COMPASS);
        Registry.register(Registries.ITEM, new Identifier("ruler-mod", "laser_rangefinder"), LASER_RANGEFINDER);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToIG);
    }
    //添加到工具栏
    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.add(STRAIGHT_RULER);
        fabricItemGroupEntries.add(COMPASS);
        fabricItemGroupEntries.add(LASER_RANGEFINDER);
    }

}