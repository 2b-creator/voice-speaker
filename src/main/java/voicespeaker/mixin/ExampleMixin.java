package voicespeaker.mixin;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "loadWorld")
	private void init(CallbackInfo info) {
		System.out.println("This line is printed by an example mod mixin!");
		// This code is injected into the start of MinecraftServer.loadWorld()V
	}
}