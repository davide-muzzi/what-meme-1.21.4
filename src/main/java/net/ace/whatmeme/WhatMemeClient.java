package net.ace.whatmeme;

import org.lwjgl.glfw.GLFW;

import net.ace.whatmeme.sounds.ModSounds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class WhatMemeClient implements ClientModInitializer {
    public static KeyBinding SHOW_GUI_KEY;

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
                            client.getSoundManager().play(
                                PositionedSoundInstance.master(ModSounds.WHAT_SOUND_EVENT, 1.0f)
                            );
                        }
                    });
                }
            }
        });
    }
}