package com.liuliy.ruler.client;


import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;




public class Visualization {

    // 在指定位置显示粒子效果
    public static void spawnParticleAt(World world, Vec3d position) {
        if (world.isClient) {
            // 生成粒子效果
            world.addParticle(ParticleTypes.END_ROD,
                    position.x,  position.y,  position.z,
                    0, 0, 0);
        }

    }

    // 在两点之间生成粒子效果的直线
    @Environment(EnvType.CLIENT)
    public static void spawnParticlesBetween(World world, BlockPos point1, BlockPos point2,Direction dir) {
        if (world.isClient) {
            // 计算两点之间的差值
            double dx = point2.getX() - point1.getX();
            double dy = point2.getY() - point1.getY();
            double dz = point2.getZ() - point1.getZ();
            int particleCount = (int) Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));

            Vec3d offset = getOffset(dir);

            // 在两点之间生成粒子
            for (int i = 0; i <= particleCount; i++) {
                double fraction = i / (double) particleCount;
                double x = point1.getX() + dx * fraction;
                double y = point1.getY() + dy * fraction;
                double z = point1.getZ() + dz * fraction;
                ParticleManager.addParticle(new Vec3d(x,y,z).add(offset),dir);
            }
        }
    }
    // 在两点之间生成粒子效果的直线,不持续
    @Environment(EnvType.CLIENT)
    public static void spawnParticlesBetweenNow(World world, BlockPos point1, BlockPos point2,Direction dir) {
        if (world.isClient) {
            // 计算两点之间的差值
            double dx = point2.getX() - point1.getX();
            double dy = point2.getY() - point1.getY();
            double dz = point2.getZ() - point1.getZ();
            int particleCount = (int) Math.max(Math.abs(dx), Math.max(Math.abs(dy), Math.abs(dz)));

            Vec3d offset = getOffset(dir);

            // 在两点之间生成粒子
            for (int i = 0; i <= particleCount; i++) {
                double fraction = i / (double) particleCount;
                double x = point1.getX() + dx * fraction;
                double y = point1.getY() + dy * fraction;
                double z = point1.getZ() + dz * fraction;
                spawnParticleAt(world,new Vec3d(x,y,z).add(offset));
            }
        }
    }
   //获取生成粒子的坐标

    public static Vec3d getPosition(BlockPos pos,Direction dir){
            Vec3d position = new Vec3d(pos.getX(), pos.getY() , pos.getZ()).add(getOffset(dir));
        return position ;
    }

    //获取偏移量
    public static Vec3d getOffset(Direction dir){
        // 计算粒子效果的位置：离方块面向玩家的面0.5格的距离
        // 基础偏移量：方块中心点 (0.5, 0.5, 0.5)
        Vec3d baseOffset = new Vec3d(0.5, 0.5, 0.5);
        double Value=0.7;
        // 根据不同的面添加微调偏移
        Vec3d faceOffset = switch (dir) {
            // ----------- 侧面 -----------
            case NORTH -> new Vec3d(0, 0, -Value);  // 北面：Z轴-0.5方向，略微突出
            case SOUTH -> new Vec3d(0, 0, Value);   // 南面：Z轴+0.5方向
            case WEST  -> new Vec3d(-Value, 0, 0);  // 西面：X轴-0.5方向
            case EAST  -> new Vec3d(Value, 0, 0);   // 东面：X轴+0.5方向

            // ----------- 顶部/底部 -----------
            case UP    -> new Vec3d(0, Value, 0);   // 顶面：Y轴+0.5方向
            case DOWN  -> new Vec3d(0, -Value, 0);  // 底面：Y轴-0.5方向

            default -> Vec3d.ZERO; // 理论上不会触发
        };
        return baseOffset.add(faceOffset);
    }
}
