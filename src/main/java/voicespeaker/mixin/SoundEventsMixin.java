package voicespeaker.mixin;

import net.minecraft.server.command.PlaySoundCommand;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(SoundEvent.class)
public class SoundEventsMixin {
    public void init(CallbackInfo callbackInfo) {

    }
}
