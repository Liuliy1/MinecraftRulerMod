package com.liuliy.ruler;

import com.liuliy.ruler.registry.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static void registerModItemGroup(){

    }
    public static final RegistryKey<ItemGroup> RULER_ITEM_GROUP_KEY
            = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(RulerMod.MOD_ID, "ruler_group"));
    public static final ItemGroup Ruler_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.STRAIGHT_RULER))
            .displayName(Text.translatable( "itemGroup.ruler-mod.group_name"))
            .build();
}
