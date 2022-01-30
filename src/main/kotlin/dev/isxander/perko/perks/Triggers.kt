package dev.isxander.perko.perks

import net.minecraft.entity.Entity
import net.minecraft.item.ItemStack

fun interface Trigger {
    fun predicate(stack: ItemStack): Boolean
}

open class SimpleTrigger(private val action: (ItemStack) -> Boolean = { true }) : Trigger {
    override fun predicate(stack: ItemStack): Boolean = action(stack)
}

class AttackTrigger(predicate: (ItemStack) -> Boolean = { true }) : SimpleTrigger(predicate)
class UseTrigger(predicate: (ItemStack) -> Boolean = { true }) : SimpleTrigger(predicate)

