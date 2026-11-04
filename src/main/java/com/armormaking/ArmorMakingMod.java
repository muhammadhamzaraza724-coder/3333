package com.armormaking;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ArmorMakingMod implements ClientModInitializer {
    private static boolean enabled = false;

    @Override
    public void onInitializeClient() {
        KeyBinding toggle = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.armormaking.toggle", GLFW.GLFW_KEY_K, "key.category.armormaking.main"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.wasPressed()) {
                enabled = !enabled;
                if (client.player != null) {
                    client.player.sendMessage(net.minecraft.text.Text.literal(
                        "Armor Making Mod: " + (enabled ? "ON" : "OFF")), true);
                }
            }
        });
    }
}
