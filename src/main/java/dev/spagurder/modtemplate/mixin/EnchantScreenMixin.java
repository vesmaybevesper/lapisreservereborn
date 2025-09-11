package dev.spagurder.modtemplate.mixin;

import dev.spagurder.modtemplate.PlayerInterface;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantScreenMixin extends AbstractContainerMenu {
    @Shadow @Final @Mutable
    private final ContainerLevelAccess access;
    protected EnchantScreenMixin(@Nullable MenuType<?> menuType, int i, ContainerLevelAccess access) {
        super(menuType, i);
        this.access = access;
    }

    @Inject(method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/inventory/ContainerLevelAccess;)V", at = @At("RETURN"))
    private void open(int i, Inventory inventory, ContainerLevelAccess containerLevelAccess, CallbackInfo ci){
        slots.get(1).set(((PlayerInterface)inventory).getLapisReserve());
    }

    @Inject(method = "removed", at = @At("HEAD"))
    private void close(Player player, CallbackInfo ci){
        ((PlayerInterface)player.getInventory()).setLapisReserve(slots.get(1).getItem());
        slots.get(1).set(ItemStack.EMPTY);
    }

}
