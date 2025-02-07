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
//        MeasurementData data = MEASUREMENTS.getOrDefault(player, new MeasurementData(2));
//
//        switch (data.step) {
//            case 0 -> { // 第一步：记录圆心
//                data.points[0] = pos;
//                data.step++;
//                player.sendMessage(Text.literal("§a圆心已记录！"), true);
//                Visualization.showMarker(world, pos);
//                MEASUREMENTS.put(player, data);
//            }
//            case 1 -> { // 第二步：记录半径并计算
//                data.points[1] = pos;
//                double radius = calculateHorizontalDistance(data.points[0], data.points[1]);
//                int circumference = (int) (2 * Math.PI * radius);
//                int area = (int) (Math.PI * radius * radius);
//
//                player.sendMessage(Text.literal(String.format(
//                        "§6半径：§e%.1f 方块\n§6周长：§e%d 方块\n§6面积：§e%d 方块",
//                        radius, circumference, area
//                )), false);
//
//                Visualization.showCircle(world, data.points[0], (int) Math.round(radius));
//                MEASUREMENTS.remove(player);
//            }
//        }

        return TypedActionResult.success(player.getStackInHand(hand));
    }

    @Override
    protected void clearParticle() {

    }

}