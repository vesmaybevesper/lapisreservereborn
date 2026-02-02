package dev.vesper.lapisreservereborn.mixin;

import com.mojang.authlib.GameProfile;
import dev.vesper.lapisreservereborn.common.PlayerInterface;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerReserve extends Player {

	public ServerPlayerReserve(Level level, GameProfile gameProfile) {
		super(level, gameProfile);
	}

	@Inject(method = "restoreFrom", at = @At("TAIL"))
	private void afterLoadLevel(ServerPlayer serverPlayer, boolean bl, CallbackInfo ci) {
		((PlayerInterface)this.getInventory()).setLapisReserve(((PlayerInterface)serverPlayer.getInventory()).getLapisReserve());
	}

}
