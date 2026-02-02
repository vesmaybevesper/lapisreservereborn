package dev.vesper.lapisreservereborn.mixin;

import dev.vesper.lapisreservereborn.common.PlayerInterface;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//? >= 1.21.6{
/*import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.ItemStackWithSlot;
*///?}

@Mixin(Inventory.class)
public abstract class PlayerReserve implements PlayerInterface {
	@Unique
	ItemStack lapisReserve;

	@Shadow
	@Final
	private NonNullList<ItemStack> items;

	@Shadow
	@Final
	public Player player;

	public PlayerReserve() {
		this.lapisReserve = ItemStack.EMPTY;
	}
//? >= 1.21.6{
	/*@Inject(method = "save", at = @At("HEAD"))
	private void serialize(ValueOutput.TypedOutputList<ItemStackWithSlot> typedOutputList, CallbackInfo ci){
		if (!this.lapisReserve.isEmpty()) {
			CompoundTag tag = new CompoundTag();
			tag.putString("CustomData", "LapisReserve");
			typedOutputList.add(new ItemStackWithSlot(200, this.lapisReserve));
		}
	}

	@Inject(method = "load", at = @At("HEAD"))
	private void deserialize(ValueInput.TypedInputList<ItemStackWithSlot> typedInputList, CallbackInfo ci){
		List<ItemStackWithSlot> customItems = new ArrayList<>();
		Iterator iterator = typedInputList.iterator();

		while(iterator.hasNext()) {
			ItemStackWithSlot stack = (ItemStackWithSlot) iterator.next();
			if (stack.slot() == 200) {
				customItems.add(stack);
			}
		}

		if(!customItems.isEmpty()) {
			ItemStackWithSlot customItem = customItems.getFirst();
			this.lapisReserve = customItem.stack();
		}
	}
	*///? } 1.21.1 && < 1.21.6{
	@Inject(method = "save", at = @At("HEAD"))
	private void serialize(ListTag listTag, CallbackInfoReturnable<ListTag> cir){
		if (!this.lapisReserve.isEmpty()) {
			CompoundTag tag = new CompoundTag();
			tag.putByte("LapisReserve", (byte) 0);
			listTag.add(this.lapisReserve.save(this.player.registryAccess(), tag));
		}
	}

	@Inject(method = "load", at = @At("HEAD"))
	private void deserialize(ListTag listTag, CallbackInfo ci){
		for(int i = 0; i < listTag.size(); ++i) {
			CompoundTag compoundTag = listTag.getCompound(i);
			if (compoundTag.contains("LapisReserve")) {
				this.lapisReserve = (ItemStack)ItemStack.parse(this.player.registryAccess(), compoundTag).orElse(ItemStack.EMPTY);
				listTag.remove(i);
				break;
			}
		}
	}
	//?}

	@Override
	public ItemStack getLapisReserve() {
		return this.lapisReserve;
	}

	@Override
	public void setLapisReserve(ItemStack stack) {
		this.lapisReserve = stack;
	}
}
