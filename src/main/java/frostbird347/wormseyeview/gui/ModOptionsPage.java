package frostbird347.wormseyeview.gui;

import frostbird347.wormseyeview.ModOptions;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.options.GuiOptions;
import net.minecraft.client.gui.options.components.FloatOptionComponent;
import net.minecraft.client.gui.options.components.IntegerOptionComponent;
import net.minecraft.client.gui.options.components.OptionsComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.GameSettings;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class ModOptionsPage implements GameStartEntrypoint {
	public static GameSettings globalSettings = ((Minecraft)FabricLoader.getInstance().getGameInstance()).gameSettings;
	public static ModOptions settings = (ModOptions)((Object)globalSettings);
	public static final OptionsPage optionsPage = OptionsPages.register((new OptionsPage("frostbird347.wormseyeview.options.title", new ItemStack(Block.dirt))).withComponent((OptionsComponent)new IntegerOptionComponent(settings.shader())).withComponent((OptionsComponent)new FloatOptionComponent(settings.shaderIntensity())));

	public static GuiOptions guiPage(GuiScreen parent) {
		return new GuiOptions(parent, optionsPage);
	}
	
	public void beforeGameStart() {}
	
	public void afterGameStart() {}
	
}
