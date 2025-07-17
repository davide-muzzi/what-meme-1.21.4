package net.ace.whatmeme;

import net.fabricmc.api.ModInitializer;
import net.ace.whatmeme.sounds.ModSounds;
import net.ace.whatmeme.Commands.WhatCommands;

public class WhatMeme implements ModInitializer {
    public static final String MOD_ID = "what-meme";

    @Override
    public void onInitialize() {
        ModSounds.registerSounds();
        WhatCommands.register();
    }
}
