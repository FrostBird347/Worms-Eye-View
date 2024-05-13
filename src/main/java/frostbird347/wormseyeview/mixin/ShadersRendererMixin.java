package frostbird347.wormseyeview.mixin;

import net.minecraft.client.render.shader.ShadersRenderer;
import net.minecraft.client.render.shader.PhotoModeRenderer;
import net.minecraft.client.render.shader.ShaderProvider;
import net.minecraft.client.render.shader.ShaderProviderInternal;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import frostbird347.wormseyeview.MainMod;

@Mixin(value = {ShadersRenderer.class}, remap = false)
public class ShadersRendererMixin {
	
	//All these shaders use the depth map, which behaves differently outside of the picture mode camera
	//Thus we have to use our own modified versions of them to ensure both look similar
	private static final String[] brokenShaders = {"silhouette", "distantblur", "outline", "outline2"};

	@Inject(method = {"getShader()Lnet/minecraft/client/render/shader/ShaderProvider;"}, at = {@At("HEAD")}, cancellable = true)
	private void getShader(CallbackInfoReturnable<ShaderProvider> cir) {
		if (MainMod.options != null && MainMod.options.shader().value < PhotoModeRenderer.shaders.length && PhotoModeRenderer.shaders[MainMod.options.shader().value] != null) {
			if (ArrayUtils.contains(brokenShaders, PhotoModeRenderer.shaders[MainMod.options.shader().value])) {
				cir.setReturnValue(new ShaderProviderInternal("/shaders/wormseyeview/" + PhotoModeRenderer.shaders[MainMod.options.shader().value] + "/"));
			} else {
				cir.setReturnValue(new ShaderProviderInternal("/shaders/photo/" + PhotoModeRenderer.shaders[MainMod.options.shader().value] + "/"));
			}
		}
	}
}