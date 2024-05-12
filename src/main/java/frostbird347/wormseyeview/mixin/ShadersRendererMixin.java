package frostbird347.wormseyeview.mixin;

import net.minecraft.client.render.shader.ShadersRenderer;
import net.minecraft.core.Global;
import net.minecraft.client.render.shader.PhotoModeRenderer;
import net.minecraft.client.render.shader.ShaderProvider;
import net.minecraft.client.render.shader.ShaderProviderExternal;
import net.minecraft.client.render.shader.ShaderProviderInternal;
import java.io.File;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import frostbird347.wormseyeview.MainMod;

@Mixin(value = {ShadersRenderer.class}, remap = false)
public class ShadersRendererMixin {
	
	@Inject(method = {"getShader()Lnet/minecraft/client/render/shader/ShaderProvider;"}, at = {@At("HEAD")}, cancellable = true)
	private void getShader(CallbackInfoReturnable<ShaderProvider> cir) {
		if (MainMod.options != null && MainMod.options.shader().value < PhotoModeRenderer.shaders.length && PhotoModeRenderer.shaders[MainMod.options.shader().value] != null) {
			if (PhotoModeRenderer.shaders[MainMod.options.shader().value].equals("silhouette")) {
				cir.setReturnValue(new ShaderProviderInternal("/shaders/wormseyeviewdepth/"));
			} else {
				cir.setReturnValue(new ShaderProviderInternal("/shaders/photo/" + PhotoModeRenderer.shaders[MainMod.options.shader().value] + "/"));
			}
		}
	}
}