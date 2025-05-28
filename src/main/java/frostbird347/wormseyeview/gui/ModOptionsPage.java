package frostbird347.wormseyeview.gui;

import frostbird347.wormseyeview.ModOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.options.ScreenOptions;
import net.minecraft.client.gui.options.components.FloatOptionComponent;
import net.minecraft.client.gui.options.components.IntegerOptionComponent;
import net.minecraft.client.gui.options.components.OptionsComponent;
import net.minecraft.client.gui.options.data.OptionsPage;
import net.minecraft.client.gui.options.data.OptionsPages;
import net.minecraft.client.option.GameSettings;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.GameStartEntrypoint;

public class ModOptionsPage implements GameStartEntrypoint {
	public static GameSettings globalSettings = Minecraft.getMinecraft().gameSettings;
	public static ModOptions settings = (ModOptions)((Object)globalSettings);
	public static final OptionsPage optionsPage = OptionsPages.register((new OptionsPage("frostbird347.wormseyeview.options.title", new ItemStack(Blocks.DIRT))).withComponent((OptionsComponent)new IntegerOptionComponent(settings.shader())).withComponent((OptionsComponent)new FloatOptionComponent(settings.shaderIntensity())));

	public static ScreenOptions guiPage(Screen parent) {
		return new ScreenOptions(parent, optionsPage);
	}

	@Override
	public void beforeGameStart() {}
	
	@Override
	public void afterGameStart() {}
	
}
