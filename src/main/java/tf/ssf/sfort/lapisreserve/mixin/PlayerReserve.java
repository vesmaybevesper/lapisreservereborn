package tf.ssf.sfort.lapisreserve.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tf.ssf.sfort.lapisreserve.PlayerInterface;

import java.util.Objects;

@Mixin(PlayerInventory.class)
public class PlayerReserve implements PlayerInterface {
	@Dynamic
	public ItemStack lapisreserve = ItemStack.EMPTY;
	@Shadow
	public PlayerEntity player;

	@Inject(method = "writeNbt(Lnet/minecraft/nbt/NbtList;)Lnet/minecraft/nbt/NbtList;",at=@At("HEAD"))
	public void serialize(NbtList tag, CallbackInfoReturnable<NbtList> info) {
		if (lapisreserve.isEmpty()) return;
		NbtCompound compoundTag = new NbtCompound();
		compoundTag.putByte("LapisReserve", (byte)0);
		tag.add(lapisreserve.toNbt(this.player.getRegistryManager(), compoundTag));
	}
	@Inject(method = "readNbt(Lnet/minecraft/nbt/NbtList;)V",at=@At("HEAD"))
	public void deserialize(NbtList tag, CallbackInfo info) {
		for(int i = 0; i < tag.size(); ++i) {
            if (Objects.equals(((NbtElement) tag).toString(), "LapisReserve")){
				lapisreserve = ItemStack.fromNbt(this.player.getRegistryManager(), tag).orElse(ItemStack.EMPTY);
				tag.remove(i);
				break;
			}
		}
	}
	@Override public ItemStack getLapisreserve(){ return lapisreserve; }
	@Override public void setLapisreserve(ItemStack stack) { lapisreserve = stack; }
}
