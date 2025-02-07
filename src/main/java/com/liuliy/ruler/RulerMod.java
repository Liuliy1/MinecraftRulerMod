package com.liuliy.ruler;

import com.liuliy.ruler.client.ParticleManager;
import com.liuliy.ruler.client.Visualization;
import com.liuliy.ruler.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.world.World;

public class RulerMod implements ModInitializer {
	public static final MinecraftClient client = MinecraftClient.getInstance();
	@Override
	public void onInitialize() {
		// 注册所有测量工具
		ModItems.register();
		ParticleManager.init();
	}
}