package frostbird347.wormseyeview.mixin;

import net.minecraft.client.render.shader.Shaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPhotoMode;
import net.minecraft.client.render.shader.Shader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import frostbird347.wormseyeview.MainMod;

@Mixin(value = {Shaders.class}, remap = false)
public class ShadersMixin {
	
	@Inject(method = {"setUniforms(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/render/shader/Shader;F)V"}, at = {@At("TAIL")})
	private static void setIntensity(Minecraft mc, Shader shader, float partialTicks, CallbackInfo ci) {
		//Don't override the intensity value when photo mode is active
		if (mc.currentScreen == null || !(mc.currentScreen instanceof GuiPhotoMode)) {
			if (MainMod.options != null) {
				shader.uniformFloat("intensity", MainMod.options.shaderIntensity().value);
			}
		}
	}
}