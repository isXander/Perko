package dev.isxander.perko

import dev.isxander.perko.perks.Perk
import dev.isxander.perko.perks.impl.TestSwordPerk
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.item.Item
import net.minecraft.item.SwordItem

object Perko : ModInitializer, PreLaunchEntrypoint {
    val defaultPerks = mutableMapOf<Class<out Item>, MutableList<Function1<*, List<Perk>>>>()

    override fun onInitialize() {
        println("init")
    }

    override fun onPreLaunch() {
        addDefaultPerks<SwordItem> { listOf(TestSwordPerk(it)) }
    }

    inline fun <reified T : Item> addDefaultPerks(noinline action: (T) -> List<Perk>) {
        defaultPerks.computeIfAbsent(T::class.java) { mutableListOf() }.add(action)
    }
}
