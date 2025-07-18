package net.ace.whatmeme;

import org.lwjgl.glfw.GLFW;

import net.ace.whatmeme.sounds.ModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class WhatMemeClient implements ClientModInitializer {
    public static KeyBinding SHOW_GUI_KEY;
    private static boolean showWhat = false;
    private static long startTime;

    @Override
    public void onInitializeClient() {
        SHOW_GUI_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.what-meme.show_what", // Matches your lang file
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.what-meme.general"
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (SHOW_GUI_KEY.wasPressed()) {
                if (client.player != null) {
                    client.player.sendMessage(Text.of("You pressed the WhatMeme key!"), false);
                    System.out.println("Trying to play sound!");
                    client.execute(() -> {
                        if (client.player != null && ModSounds.WHAT_SOUND_EVENT != null) {
                            System.out.println("Sound instance should now play.");
                            showWhat = true;
                            startTime = System.currentTimeMillis();
                            client.getSoundManager().play(
                                PositionedSoundInstance.master(ModSounds.WHAT_SOUND_EVENT, 1.0f)
                            );
                        }
                    });
                }
            }
        });

        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (System.currentTimeMillis() - startTime > 1500) return;

            MinecraftClient client = MinecraftClient.getInstance();
            Identifier image = Identifier.of("what_meme", "textures/gui/what.png");

            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();

            float scale = 2.0f - (float)(System.currentTimeMillis() - startTime) / 1500f;
            int imgWidth = (int)(screenWidth * scale);
            int imgHeight = (int)(screenHeight * scale);
            int x = (screenWidth - imgWidth) / 2;
            int y = (screenHeight - imgHeight) / 2;

            drawContext.drawTexture(
                id -> RenderLayer.getGui(), // layer function (ignore the Identifier param)
                image,
                x, y,
                0f, 0f,
                imgWidth, imgHeight,
                256, 256
            );
        });
    }
}