package dev.isxander.perko.mixins;

import dev.isxander.perko.perks.Perk;
import dev.isxander.perko.perks.PerkHandler;
import dev.isxander.perko.perks.Trigger;
import dev.isxander.perko.perks.UseTrigger;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
            method = "use",
            at = @At("RETURN")
    )
    private void onUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        for (Perk perk : PerkHandler.getPerks(stack.getItem())) {
            for (Trigger trigger : perk.getTriggers()) {
                if (trigger instanceof UseTrigger useTrigger && useTrigger.predicate(stack)) {
                    perk.doAction(trigger, world, user, stack);
                }
            }
        }
    }
}
