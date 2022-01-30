package dev.isxander.perko.perks.impl

import dev.isxander.perko.perks.AttackTrigger
import dev.isxander.perko.perks.SwordPerk
import dev.isxander.perko.perks.Trigger
import dev.isxander.perko.perks.UseTrigger
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.world.World

class TestSwordPerk(sword: SwordItem) : SwordPerk(sword) {
    override val triggers = listOf<Trigger>(AttackTrigger(), UseTrigger())

    override fun doAction(trigger: Trigger, world: World, player: PlayerEntity, stack: ItemStack) {
        println("perk activated!")
    }
}
