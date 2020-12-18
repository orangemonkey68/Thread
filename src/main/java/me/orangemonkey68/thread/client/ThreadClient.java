package me.orangemonkey68.thread.client;

import me.orangemonkey68.thread.client.gui.hud.ThreadHud;
import me.orangemonkey68.thread.client.gui.hud.widgets.ArmorWidget;
import me.orangemonkey68.thread.config.ThreadConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ThreadClient implements ClientModInitializer {

    ThreadHud hud = new ThreadHud();

    @Override
    public void onInitializeClient() {
        ArmorWidget armorWidget = new ArmorWidget(MinecraftClient.getInstance().player);
        armorWidget.setEnabled(true);
        hud.addWidget(armorWidget);

        KeyBinding listsBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.thread.lists",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                "category.thread.lists"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (listsBinding.wasPressed()){
                ClientPlayerEntity player = MinecraftClient.getInstance().player;
                if (player != null) {
                    player.openHandledScreen()
                }
            }
        });

//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            while (binding.wasPressed()){
//                client.player.openHandledScreen()
//            }
//        });

        HudRenderCallback.EVENT.register(((matrixStack, v) -> {
            MinecraftClient instance = MinecraftClient.getInstance();

            ThreadConfig config = AutoConfig.getConfigHolder(ThreadConfig.class).get();

            hud.render(matrixStack);
        }));
    }
}
