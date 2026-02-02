package dev.vesper.lapisreservereborn.platform.fabric;

//? fabric {

import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import dev.vesper.lapisreservereborn.LapisReserveReborn;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		LapisReserveReborn.onInitialize();
	}
}
//?}
