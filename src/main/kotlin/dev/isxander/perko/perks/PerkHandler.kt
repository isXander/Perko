@file:JvmName("PerkHandler")

package dev.isxander.perko.perks

import net.minecraft.item.Item

val Item.perks: List<Perk>
    get() = (this as Perkable).perks
