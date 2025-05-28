package frostbird347.wormseyeview.modMenu;

import io.github.prospector.modmenu.api.ModMenuApi;
import frostbird347.wormseyeview.gui.ModOptionsPage;
import java.util.function.Function;
import frostbird347.wormseyeview.MainMod;
import net.minecraft.client.gui.Screen;

public class MainEntry implements ModMenuApi {

	@Override
	public String getModId() {
		return MainMod.MOD_ID;
	}
	
	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return ModOptionsPage::guiPage;
	}
	
}
