package com.liuliy.ruler.items;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import java.util.HashMap;
import java.util.Map;

public abstract class RulerTool extends Item {
    protected static final Map<PlayerEntity, MeasurementData> MEASUREMENTS = new HashMap<>();

    public RulerTool(Settings settings) {
        super(settings.maxCount(1));  // 限制物品堆叠数量为1
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
       //获取初始化数据
        PlayerEntity player = context.getPlayer();
        BlockPos pos = context.getBlockPos();
        Direction direction = context.getSide();
        World world = context.getWorld();

        if (!context.getWorld().isClient) {
            return ActionResult.PASS;
        }

        // Shift+右键清除测量
        if (player.isSneaking()) {
            MEASUREMENTS.remove(player);
            player.sendMessage(Text.literal("§a已清除测量标记！"), false);
            // 停止粒子效果
            clearParticle();
            return ActionResult.SUCCESS;
        }
        //进行测量
        handleMeasurement(player, world, pos,direction);
        return ActionResult.SUCCESS;
    }

    protected abstract ActionResult handleMeasurement(
            PlayerEntity player, World world, BlockPos pos ,Direction dir
    );
    protected  abstract void clearParticle();
    protected static class MeasurementData {
        public BlockPos[] points = new BlockPos[2];  // 记录两个点的数组
        public World[] worlds = new World[2];  // 记录两个维度的数组
        public int step = 0;  // 当前步骤
        public MeasurementData() {
            // 初始化为空点
            points[0] = null;
            points[1] = null;
        }
    }

    protected static double calculateDistance(BlockPos a, BlockPos b) {
        int dx = b.getX() - a.getX();
        int dy = b.getY() - a.getY();
        int dz = b.getZ() - a.getZ();

        return Math.max(Math.abs(dx),Math.max(Math.abs(dy),Math.abs(dz)));
    }

}
