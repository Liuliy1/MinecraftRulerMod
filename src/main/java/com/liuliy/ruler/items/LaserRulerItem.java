package com.liuliy.ruler.items;

import com.liuliy.ruler.client.ParticleManager;
import com.liuliy.ruler.client.Visualization;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

import static com.liuliy.ruler.client.Visualization.spawnParticlesBetween;

public class LaserRulerItem extends RulerToolPlus {
    public static List<Vec3d> activePos = new ArrayList<>();

    public LaserRulerItem( ) {
        super(new Item.Settings());
    }
    PlayerEntity player;
    @Environment(EnvType.CLIENT)
    @Override
    protected TypedActionResult<ItemStack> handleMeasurement(  PlayerEntity player, World world, Hand hand, BlockPos pos, Direction dir) {
        MeasurementData data = MEASUREMENTS.computeIfAbsent(player, p -> new MeasurementData());
        this.player=player;
        if (data.step == 0) {
            // 第一次点击，记录第一个点
            data.points[0] = pos;
            data.step = 1;  // 标记为已记录第一个点
            //记录世界
            data.worlds[0]=world;
            player.sendMessage(Text.translatable("message.ruler-mod.measure_start"), false);
            //显示第一个点的粒子效果
            ParticleManager.addParticle(data.points[0],dir);
            //记录该坐标已生成粒子
            activePos.add(Visualization.getPosition(pos, dir));

        } else if (data.step == 1) {

            // 记录第二个点并计算距离
            data.points[1] = pos;
            //记录世界
            data.worlds[1]=world;
            if (data.worlds[0]!=world){
                player.sendMessage(Text.translatable("message.ruler-mod.cross_dimension_error"), false);
                return TypedActionResult.fail(player.getStackInHand(hand));
            }
            double distance = calculateDistance(data.points[0], data.points[1]);
            player.sendMessage(Text.translatable("message.ruler-mod.measure_distance" ,(int)Math.floor(distance+1)), false);
            data.step = 2;  // 测量完成
            // 在第二个点上显示粒子效果
            ParticleManager.addParticle(data.points[1],dir);
            //记录该坐标已生成粒子
            activePos.add(Visualization.getPosition(pos, dir));
            //并且在两点之间生成一条粒子线
            spawnParticlesBetween(world, data.points[0], data.points[1],dir,"laserRuler");

        }else if (data.step == 2) {
            // 第三次点击
            double distance = calculateDistance(data.points[0], data.points[1]);
            player.sendMessage(Text.translatable("message.ruler-mod.measure_distance" ,(int)Math.floor(distance+1)), false);
        }
        return TypedActionResult.success(player.getStackInHand(hand));


    }
    @Environment(EnvType.CLIENT)
    @Override
    protected void clearParticle() {
        player.sendMessage(Text.translatable("message.ruler-mod.measure_clear"), false);
        for (Vec3d pos : LaserRulerItem.activePos){
            ParticleManager.removeParticle(pos);
        }
//        ParticleManager.removeParticle();
        LaserRulerItem.activePos.clear();
    }
}
