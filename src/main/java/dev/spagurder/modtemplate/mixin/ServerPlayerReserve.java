package dev.spagurder.modtemplate.mixin;

import com.mojang.authlib.GameProfile;
import dev.spagurder.modtemplate.LapisReserveReborn;
import dev.spagurder.modtemplate.PlayerInterface;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
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
    private void copyFrom(ServerPlayer serverPlayer, boolean bl, CallbackInfo ci) {
        ((PlayerInterface)getInventory()).setLapisReserve(((PlayerInterface)serverPlayer.getInventory()).getLapisReserve());
    }


}
