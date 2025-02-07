package com.liuliy.ruler.items;

import com.liuliy.ruler.client.ParticleManager;
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

import static com.liuliy.ruler.client.Visualization.spawnParticlesBetween;
import static com.liuliy.ruler.client.Visualization.spawnParticlesBetweenNow;

public class LaserRangefinderItem extends RulerToolPlus  {
    public LaserRangefinderItem() {
        super(new Item.Settings());
    }

    @Override
    protected TypedActionResult<ItemStack> handleMeasurement(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction dir) {
        // 记录第二个点并计算距离
        double distance = calculateDistance(player.getBlockPos(), pos);
        player.sendMessage(Text.literal("§a测量出两点的长度是: " + (int)Math.floor(distance+1) + " 格"), false);
        //并且在两点之间生成一条粒子线
        spawnParticlesBetweenNow(world,player.getBlockPos(), pos,dir);
        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    protected void clearParticle() {

    }
}
