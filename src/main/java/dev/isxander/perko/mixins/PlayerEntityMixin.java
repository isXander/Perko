package dev.isxander.perko.mixins;

import dev.isxander.perko.perks.AttackTrigger;
import dev.isxander.perko.perks.Perk;
import dev.isxander.perko.perks.PerkHandler;
import dev.isxander.perko.perks.Trigger;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntityMixin {
    @Inject(
            method = "attack",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z")
    )
    private void onAttack(Entity target, CallbackInfo ci) {
        ItemStack stack = getMainHandStack();
        for (Perk perk : PerkHandler.getPerks(stack.getItem())) {
            for (Trigger trigger : perk.getTriggers()) {
                if (trigger instanceof AttackTrigger attackTrigger && attackTrigger.predicate(stack)) {
                    perk.doAction(trigger, getWorld(), (PlayerEntity) (Object) this, stack);
                }
            }
        }
    }
}
