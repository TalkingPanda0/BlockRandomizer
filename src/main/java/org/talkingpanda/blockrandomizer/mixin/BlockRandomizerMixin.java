package org.talkingpanda.blockrandomizer.mixin;

import org.talkingpanda.blockrandomizer.BlockRandomizer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult.Type;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class BlockRandomizerMixin {
	
    @Inject(method="interactBlock", at=@At(value="RETURN",
            target="Lnet/minecraft/client/network/ClientPlayerInteractionManager;sendSequencedPacket(Lnet/minecraft/client/world/ClientWorld;Lnet/minecraft/client/network/SequencedPacketCreator;)V"))
    public void onPlayerInteractBlockSuccessfully(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult, CallbackInfoReturnable cir) {
	    // TODO run only when a block placed
	    if(BlockRandomizer.isActive && hitResult.getType().equals(Type.BLOCK))
		BlockRandomizer.RandomizeBlock(player);
    }
}
