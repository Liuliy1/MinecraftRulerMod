package com.liuliy.ruler.util;

import net.minecraft.util.math.BlockPos;
import java.util.HashSet;
import java.util.Set;

public class MidpointCircle {
    public static Set<BlockPos> getPoints(BlockPos center, int radius) {
        Set<BlockPos> points = new HashSet<>();
        int x = radius;
        int z = 0;
        int err = 0;

        while (x >= z) {
            addOctantPoints(points, center, x, z);
            if (err <= 0) {
                z += 1;
                err += 2*z + 1;
            } else {
                x -= 1;
                err -= 2*x + 1;
            }
        }
        return points;
    }

    private static void addOctantPoints(Set<BlockPos> points, BlockPos center, int x, int z) {
        int cx = center.getX();
        int cy = center.getY();
        int cz = center.getZ();

        // 八个对称点
        points.add(new BlockPos(cx + x, cy, cz + z));
        points.add(new BlockPos(cx + z, cy, cz + x));
        points.add(new BlockPos(cx - z, cy, cz + x));
        points.add(new BlockPos(cx - x, cy, cz + z));
        points.add(new BlockPos(cx - x, cy, cz - z));
        points.add(new BlockPos(cx - z, cy, cz - x));
        points.add(new BlockPos(cx + z, cy, cz - x));
        points.add(new BlockPos(cx + x, cy, cz - z));
    }
}