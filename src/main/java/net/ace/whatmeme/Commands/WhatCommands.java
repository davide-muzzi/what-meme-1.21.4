package net.ace.whatmeme.Commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class WhatCommands {
    public static void register() {
        CommandManager.literal("whatmeme")
            .executes(context -> {
                ServerCommandSource source = context.getSource();
                source.sendFeedback(() -> Text.literal("What Meme mod is active."), false);
                return 1;
            });
    }
}
