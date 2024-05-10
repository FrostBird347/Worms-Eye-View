package frostbird347.wormseyeview;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class MainMod implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
	public static final String MOD_ID = "wormseyeview";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ModOptions options;
	
	@Override
	public void onInitialize() {
		LOGGER.info("Worm's Eye View initialized.");
	}

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
