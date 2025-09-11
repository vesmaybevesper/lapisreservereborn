package dev.spagurder.modtemplate.mixin;

import dev.spagurder.modtemplate.PlayerInterface;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ItemStackWithSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Inventory.class)
public abstract class PlayerReserve implements PlayerInterface {

    @Shadow @Final private NonNullList<ItemStack> items;
    @Shadow public abstract void setItem(int i, ItemStack itemStack);
    @Dynamic ItemStack lapisReserve = ItemStack.EMPTY;

    @Inject(method = "save(Lnet/minecraft/world/level/storage/ValueOutput$TypedOutputList;)V", at = @At("HEAD"))
    private void serialize(ValueOutput.TypedOutputList<ItemStackWithSlot> typedOutputList, CallbackInfo ci){
        if (lapisReserve.isEmpty()) return;
        CompoundTag tag = new CompoundTag();
        tag.putByte("Lapis Reserve", (byte) 0);
        typedOutputList.add(new ItemStackWithSlot((byte) 0, lapisReserve.copy()));
    }

    @Inject(method = "load(Lnet/minecraft/world/level/storage/ValueInput$TypedInputList;)V", at = @At("HEAD"))
    private void deserialize(ValueInput.TypedInputList<ItemStackWithSlot> typedInputList, CallbackInfo ci){
        this.items.clear();
        for (ItemStackWithSlot stack : typedInputList){
            ItemStack stack1 = stack.stack();

            if ("Lapis Reserve".equals(stack1.getHoverName().getString())){
                this.lapisReserve = stack1;
                continue;
            }
            if (stack.isValidInContainer(this.items.size())){
                this.setItem(stack.slot(), stack1);
            }
        }
    }


    @Override
    public ItemStack getLapisReserve() {
        return lapisReserve;
    }

    @Override
    public void setLapisReserve(ItemStack lapisReserve) {
        this.lapisReserve = lapisReserve;
    }
}
