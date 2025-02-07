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

            double offsetX = 0.5, offsetY = 1.0, offsetZ = 0.5; // 默认位置在方块上方0.5格
            switch (dir) {
                case NORTH:
                    offsetZ = -0.5; // 朝北方向，调整Z轴
                    break;
                case SOUTH:
                    offsetZ = 0.5;  // 朝南方向，调整Z轴
                    break;
                case WEST:
                    offsetX = -0.5; // 朝西方向，调整X轴
                    break;
                case EAST:
                    offsetX = 0.5;  // 朝东方向，调整X轴
                    break;
                case UP:
                    offsetY = 1.5;  // 朝上方向，粒子更高一点
                    break;
                case DOWN:
                    offsetY = 0.5;  // 朝下方向，粒子更低一点
                    break;
            }

            // 在两点之间生成粒子
            for (int i = 0; i <= particleCount; i++) {
                double fraction = i / (double) particleCount;
                double x = point1.getX() + dx * fraction + offsetX;
                double y = point1.getY() + dy * fraction + offsetY;
                double z = point1.getZ() + dz * fraction + offsetZ;
                ParticleManager.addParticle(new Vec3d(x,y,z),dir);
            }
        }
    }
   //获取生成粒子的坐标
    @Environment(EnvType.CLIENT)
    public static Vec3d getPosition(BlockPos pos,Direction dir){
            // 计算粒子效果的位置：离方块面向玩家的面0.5格的距离
//            double offsetX = 0.5, offsetY = 1.0, offsetZ = 0.5; // 默认位置在方块上方0.5格
//            switch (dir) {
//                case NORTH:
//                    offsetZ = -0.5; // 朝北方向，调整Z轴
//                    break;
//                case SOUTH:
//                    offsetZ = 0.5;  // 朝南方向，调整Z轴
//                    break;
//                case WEST:
//                    offsetX = -0.5; // 朝西方向，调整X轴
//                    break;
//                case EAST:
//                    offsetX = 0.5;  // 朝东方向，调整X轴
//                    break;
//                case UP:
//                    offsetY = 1.5;  // 朝上方向，粒子更高一点
//                    break;
//                case DOWN:
//                    offsetY = 0.5;  // 朝下方向，粒子更低一点
//                    break;
//            }
        Vec3d offset = switch (dir) {
            case NORTH -> new Vec3d(0.5, 1.0, -0.5);
            case SOUTH -> new Vec3d(0.5, 1.0, 0.5);
            case WEST  -> new Vec3d(-0.5, 1.0, 0.5);
            case EAST  -> new Vec3d(0.5, 1.0, 0.5);
            case UP    -> new Vec3d(0.5, 1.5, 0.5);
            case DOWN  -> new Vec3d(0.5, 0.5, 0.5);
            default    -> new Vec3d(0.5, 1.0, 0.5);
        };
            Vec3d position = new Vec3d(pos.getX(), pos.getY() , pos.getZ()).add(offset);
        return position ;
        }
}
