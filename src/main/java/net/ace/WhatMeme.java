package net.ace;

import net.ace.whatmeme.Commands.WhatCommands;
import net.ace.whatmeme.sounds.ModSounds;
import net.fabricmc.api.ModInitializer;

public class WhatMeme implements ModInitializer {
    public static final String MOD_ID = "what-meme";

    @Override
    public void onInitialize() {
        ModSounds.registerSounds();
        WhatCommands.register();
    }
}
