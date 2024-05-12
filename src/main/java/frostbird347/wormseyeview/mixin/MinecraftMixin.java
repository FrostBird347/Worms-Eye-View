package frostbird347.wormseyeview.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import frostbird347.wormseyeview.MainMod;
import frostbird347.wormseyeview.ModOptions;

@Mixin(value = {Minecraft.class}, remap = false)
public class MinecraftMixin {
	@Shadow
	public GameSettings gameSettings;

	Minecraft mixinInst = (Minecraft)((Object)this);
	
	@Inject(method = {"startGame"}, at = {@At("TAIL")})
	private void startOfGameInit(CallbackInfo ci) {
		if (MainMod.options == null) {
			MainMod.options = (ModOptions)((Object)this.gameSettings);
			mixinInst.render.reload();
		}
	}
}