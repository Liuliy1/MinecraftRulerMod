package com.liuliy.ruler.tools;

import com.liuliy.ruler.client.Visualization;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
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
    protected ActionResult handleMeasurement(PlayerEntity player, World world, BlockPos pos, Direction dir) {
        return null;
    }

    @Override
    protected void clearParticle() {

    }

}