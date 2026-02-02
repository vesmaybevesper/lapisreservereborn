package dev.vesper.lapisreservereborn.platform.neoforge;

//? neoforge {

import dev.vesper.lapisreservereborn.LapisReserveReborn;
import net.neoforged.fml.common.Mod;

@Mod(LapisReserveReborn.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint() {
		LapisReserveReborn.onInitialize();
	}
}
//?}
