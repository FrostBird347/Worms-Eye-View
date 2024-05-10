package frostbird347.wormseyeview.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.FloatOption;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.IntegerOption;
import net.minecraft.client.option.Option;
import net.minecraft.client.render.shader.PhotoModeRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import frostbird347.wormseyeview.MainMod;
import frostbird347.wormseyeview.ModOptions;

@Mixin(value = {GameSettings.class}, remap = false)
public class GameSettingsMixin implements ModOptions {
	@Shadow
	@Final
	public Minecraft mc;
	
	private final GameSettings mixinInst = (GameSettings)((Object)this);
	private int lastShader = -1;

	@Unique
	public IntegerOption shader = new IntegerOption(this.mixinInst, "frostbird347.wormseyeview.shader", 0);
	
	@Unique
	public FloatOption shaderIntensity = new FloatOption(this.mixinInst, "frostbird347.wormseyeview.shaderIntensity", 1f);
	
	@Inject(method = {"getDisplayString(Lnet/minecraft/client/option/Option;)Ljava/lang/String;"}, at = {@At("HEAD")}, cancellable = true)
	private void displayString(Option<?> option, CallbackInfoReturnable<String> cir) {
		
		if (option == this.shaderIntensity) {
			cir.setReturnValue((int)(((Float)this.shaderIntensity.value).floatValue() * 100f) + "%"); 
		} else if (option == this.shader) {
			if (mixinInst.shaderOverride.value != null) {
				MainMod.LOGGER.info(mixinInst.shaderOverride.value);
			} else {
				MainMod.LOGGER.info("null");
			}

			if (this.shader.value >= PhotoModeRenderer.shaders.length || PhotoModeRenderer.shaders[this.shader.value] == null) {
				this.shader.set(0);
				mixinInst.shaderOverride.set(null);
				cir.setReturnValue("none");
			} else {
				mixinInst.shaderOverride.set("photo/" + PhotoModeRenderer.shaders[this.shader.value] + "/");
				cir.setReturnValue(PhotoModeRenderer.shaders[this.shader.value]);
			}

			if (this.shader.value != lastShader) {
				lastShader = this.shader.value;
				mc.render.reload();
			}
		}
	}

	//@Inject(method = {"optionChanged(Lnet/minecraft/client/option/Option;)V;"}, at = {@At("TAIL")})
	//public void changeSetting(Option<?> option) {
	//	
	//}

	@Override
	public IntegerOption shader() {
		if (PhotoModeRenderer.shaders.length >= this.shader.value) {
			this.shader.set(0);
		}
		return this.shader;
	}

	@Override
	public FloatOption shaderIntensity() {
		if (this.shaderIntensity.value > 1f) {
			this.shaderIntensity.set(1f);
		} else if (this.shaderIntensity.value < 0f) {
			this.shaderIntensity.set(0f);
		}
		return shaderIntensity;
	}
}