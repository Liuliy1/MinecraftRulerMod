package com.liuliy.ruler.tools;

import com.liuliy.ruler.client.Visualization;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CompassItem extends RulerTool {
    public CompassItem() {
        super(new Item.Settings());
    }

    @Override
    protected TypedActionResult<ItemStack> handleMeasurement(
            PlayerEntity player, World world, Hand hand, BlockPos pos, Direction dir
            ) {
        player.sendMessage(Text.literal("这是圆规"), true);
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    protected void clearParticle() {

    }

}