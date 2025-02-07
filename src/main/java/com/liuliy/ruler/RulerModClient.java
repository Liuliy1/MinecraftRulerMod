package com.liuliy.ruler;


import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
@Environment(EnvType.CLIENT)
public class RulerModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册按键监听
//        KeyInputHandler.register();

//        // 注册渲染事件
//        WorldRenderEvents.AFTER_TRANSLUCENT.register(context ->
//                Visualization.renderActiveMarks(
//                        context.matrixStack(),
//                        context.consumers(),
//                        context.camera().getPos(),
//                        context.tickDelta()
//                )
//        );
//
//        // 初始化客户端网络
//        NetworkHandler.registerClientPackets();
    }
}