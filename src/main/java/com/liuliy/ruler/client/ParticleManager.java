package com.liuliy.ruler.client;



import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;


import java.util.ArrayList;
import java.util.List;


import static com.liuliy.ruler.client.Visualization.*;


// 在客户端工具类或独立类中

public class ParticleManager {
    private static final List<ActiveParticle> activeParticles = new ArrayList<>();


    // 记录活动测量
    public static class ActiveParticle {

        public BlockPos blockPos;
        //当前粒子效果的坐标
        public Vec3d position;
        //生成粒子效果的方块的朝向
        public Direction direction;
        //开始时间,用于后续可能实现的渐变效果
        public long startTime;
        //标志当前粒子效果是否持续存在
        public boolean isParticleActive;


        public ActiveParticle(Vec3d position, Direction dir) {
            this.position=position;
            this.direction = dir;
            this.startTime = System.currentTimeMillis();
            isParticleActive=true;
        }
    }

    // 注册客户端Tick事件
    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null ) {
                // 每Tick更新所有活动测量
                activeParticles.forEach(ActiveParticle ->
                        spawnParticleAt(client.world, ActiveParticle.position)
                );
            }
        });
    }

    // 添加持续测量
    public static void addParticle(BlockPos pos, Direction dir) {
        activeParticles.add(new ActiveParticle(getPosition(pos,dir), dir));

    }
    public static void addParticle(Vec3d pos, Direction dir) {
        activeParticles.add(new ActiveParticle(pos, dir));

    }
    // 移除测量
    public static void removeParticle(Vec3d pos) {
        activeParticles.removeIf(m -> m.position.equals(pos));
    }

    public static void removeParticle() {
        activeParticles.clear();
    }

}