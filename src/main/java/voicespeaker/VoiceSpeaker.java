package voicespeaker;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.ArgumentTypeRegistry;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import net.minecraft.command.argument.serialize.ConstantArgumentSerializer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static com.mojang.brigadier.builder.RequiredArgumentBuilder.argument;


public class VoiceSpeaker implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("voice-speaker");
    public static final String MOD_ID = "voice-speaker";

    @Override
    public void onInitialize() {
        ArgumentTypeRegistry.registerArgumentType(new Identifier(MOD_ID, "url"), UrlArgumentType.class, ConstantArgumentSerializer.of(UrlArgumentType::new));
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Register a command

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(CommandManager.literal("playUrlSound")
                .then(argument("RemoteUrl", UrlArgumentType.string()))
                .requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
                .executes(context -> {
                    final String url = UrlArgumentType.getString(context, "RemoteUrl");
                    runCommand(url);
                    return 1;
                })));
        LOGGER.info("Hello Fabric world!");
    }

    public static void runCommand(String url) {
        System.out.println("test");
    }
}