package dev.isxander.perko.perks

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.world.World

abstract class Perk(val item: Item) {
    abstract val triggers: List<Trigger>

    abstract fun doAction(trigger: Trigger, world: World, player: PlayerEntity, stack: ItemStack)
}
