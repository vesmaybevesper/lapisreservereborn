package dev.vesper.lapisreservereborn.platform.fabric;

//? fabric {

import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import dev.vesper.lapisreservereborn.LapisReserveReborn;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		LapisReserveReborn.onInitializeClient();
	}

}
//?}
