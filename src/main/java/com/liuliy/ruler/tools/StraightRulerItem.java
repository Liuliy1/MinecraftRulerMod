package com.liuliy.ruler.tools;

import com.liuliy.ruler.client.ParticleManager;
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

import java.util.ArrayList;
import java.util.List;

import static com.liuliy.ruler.client.Visualization.spawnParticlesBetween;

public class StraightRulerItem extends RulerTool {

    public StraightRulerItem() {
        super(new Item.Settings());
    }

    public static List<BlockPos> activePos = new ArrayList<>();
    @Override
    protected TypedActionResult<ItemStack> handleMeasurement(  PlayerEntity player, World world, Hand hand, BlockPos pos, Direction dir) {
        MeasurementData data = MEASUREMENTS.computeIfAbsent(player, p -> new MeasurementData());

        if (data.step == 0) {
            // 第一次点击，记录第一个点
            data.points[0] = pos;
            data.step = 1;  // 标记为已记录第一个点
            player.sendMessage(Text.literal("§a已记录第一个点的位置!"), false);
            //显示第一个点的粒子效果
            ParticleManager.addParticle(data.points[0],dir);
            //记录该坐标已生成粒子
            activePos.add(data.points[0]);

        } else if (data.step == 1) {
            // 第二次点击，检查点击的方块是否与第一个相同
            if (pos.equals(data.points[0])) {
                player.sendMessage(Text.literal("§c第二个点不能与第一个点相同，请选择不同的方块！"), false);
                return TypedActionResult.fail(player.getStackInHand(hand));  // 退出并提示错误
            }

            // 记录第二个点并计算距离
            data.points[1] = pos;
            double distance = calculateDistance(data.points[0], data.points[1]);
            player.sendMessage(Text.literal("§a测量出两点的长度是: " + (int)Math.floor(distance+1) + " 格"), false);
            data.step = 2;  // 测量完成
            // 在第二个点上显示粒子效果
            ParticleManager.addParticle(data.points[1],dir);
            //记录该坐标已生成粒子
            activePos.add(data.points[1]);
            //并且在两点之间生成一条粒子线
            spawnParticlesBetween(world, data.points[0], data.points[1],dir);

        }else if (data.step == 2) {
            // 第三次点击
            double distance = calculateDistance(data.points[0], data.points[1]);
            player.sendMessage(Text.literal("§a测量出两点的长度是: " + (int)Math.floor(distance+1) + " 格"), false);
        }
        return TypedActionResult.success(player.getStackInHand(hand));


    }

    @Override
    protected void clearParticle() {
//        for (BlockPos pos : StraightRulerItem.activePos){
//            ParticleManager.removeParticle(pos);
//        }
        ParticleManager.removeParticle();
        StraightRulerItem.activePos.clear();
    }

}
