package com.liuliy.ruler.util;

import net.minecraft.util.math.BlockPos;
import java.util.ArrayList;
import java.util.List;

public class Bresenham3D {
    public static List<BlockPos> getPoints(BlockPos start, BlockPos end) {
        List<BlockPos> points = new ArrayList<>();
        int x1 = start.getX(), y1 = start.getY(), z1 = start.getZ();
        int x2 = end.getX(), y2 = end.getY(), z2 = end.getZ();

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int dz = Math.abs(z2 - z1);
        int xs = x2 > x1 ? 1 : -1;
        int ys = y2 > y1 ? 1 : -1;
        int zs = z2 > z1 ? 1 : -1;

        // 确定主导方向
        int dr = Math.max(dx, Math.max(dy, dz));
        int xd = dr == dx ? 1 : 0;
        int yd = dr == dy ? 1 : 0;
        int zd = dr == dz ? 1 : 0;

        int p1 = 2 * dr;
        int p2, x = x1, y = y1, z = z1;

        for (int i = 0; i < dr; i++) {
            points.add(new BlockPos(x, y, z));
            p2 = 2 * (xd*dx + yd*dy + zd*dz);
            if (p2 >= p1) {
                y += ys;
                dy -= 1;
                p1 += 2 * dr;
            }
            if (p2 <= p1) {
                x += xs;
                dx -= 1;
                p1 -= 2 * dr;
            }
            if (p2 <= p1) {
                z += zs;
                dz -= 1;
                p1 -= 2 * dr;
            }
        }
        return points;
    }
}