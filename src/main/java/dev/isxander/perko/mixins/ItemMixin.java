package dev.isxander.perko.mixins;

import dev.isxander.perko.Perko;
import dev.isxander.perko.perks.*;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(Item.class)
public class ItemMixin implements Perkable {
    private final List<Perk> perks = new ArrayList<>();

    @NotNull
    @Override
    public List<Perk> getPerks() {
        return perks;
    }

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void onInit(Item.Settings settings, CallbackInfo ci) {
        Item item = (Item) (Object) this;
        Class<? extends Item> clazz = item.getClass();
        for (Map.Entry<Class<? extends Item>, List<Function1<?, List<Perk>>>> entry : Perko.INSTANCE.getDefaultPerks().entrySet()) {
            if (!entry.getKey().isAssignableFrom(clazz)) continue;

            for (Function1<?, List<Perk>> perkApplicant : entry.getValue()) {
                List<Perk> perks = ((Function1<Item, List<Perk>>) perkApplicant).invoke(item);
                PerkHandler.getPerks(item).addAll(perks);
            }
        }
    }
}
