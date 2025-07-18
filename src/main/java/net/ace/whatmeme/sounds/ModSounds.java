package net.ace.whatmeme.sounds;

import net.ace.WhatMeme;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier SOUND_ID = Identifier.of(WhatMeme.MOD_ID, "what-sound");
    public static SoundEvent WHAT_SOUND_EVENT;

    public static void registerSounds() {
        WHAT_SOUND_EVENT = Registry.register(
            Registries.SOUND_EVENT,
            SOUND_ID,
            SoundEvent.of(SOUND_ID)
        );
    }
}

