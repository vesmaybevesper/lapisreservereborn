package dev.vesper.lapisreservereborn;

import dev.vesper.lapisreservereborn.platform.Platform;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import dev.vesper.lapisreservereborn.platform.fabric.FabricPlatform;
//?} neoforge {
/*import dev.vesper.lapisreservereborn.platform.neoforge.NeoforgePlatform;
 *///?}

@SuppressWarnings("LoggingSimilarMessage")
public class LapisReserveReborn {

	public static final String MOD_ID = /*$ mod_id*/ "lapisreservereborn";
	public static final String MOD_VERSION = /*$ mod_version*/ "1.0.19";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Lapis Reserve Reborn";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, LapisReserveReborn.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	public static void onInitializeClient() {
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}
}
