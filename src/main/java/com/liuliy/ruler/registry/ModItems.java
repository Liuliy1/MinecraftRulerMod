package com.liuliy.ruler.registry;

import com.liuliy.ruler.tools.*;
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
//    public static final Item TAPE_MEASURE = new TapeMeasureItem();
    public static final Item COMPASS = new CompassItem();

    // 注册所有物品
    public static void register() {
        Registry.register(Registries.ITEM, new Identifier("ruler-mod", "straight_ruler"), STRAIGHT_RULER);
//        Registry.register(Registries.ITEM, new Identifier("ruler-mod", "tape_measure"), TAPE_MEASURE);
        Registry.register(Registries.ITEM, new Identifier("ruler-mod", "compass"), COMPASS);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToIG);
    }
    private static void addItemsToIG(FabricItemGroupEntries fabricItemGroupEntries) {
        fabricItemGroupEntries.add(STRAIGHT_RULER);
//        fabricItemGroupEntries.add(TAPE_MEASURE);
        fabricItemGroupEntries.add(COMPASS);
    }

}