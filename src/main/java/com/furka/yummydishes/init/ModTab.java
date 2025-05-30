package com.furka.yummydishes.init;

import com.furka.yummydishes.YummyDishes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Collectors;

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, YummyDishes.MODID);

    public static final RegistryObject<CreativeModeTab> YUMMY_DISHES_TAB = CREATIVE_MODE_TABS.register("yummy_dishes_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.yummy_dishes_tab"))
                    .icon(() -> new ItemStack(ModItems.TOMATO.get()))
                    .displayItems((params, output) -> {
                        output.acceptAll(ModItems.ITEMS.getEntries().stream()
                                .map(RegistryObject::get)
                                .map(ItemStack::new)
                                .collect(Collectors.toList()));
                        
                        output.acceptAll(ModBlocks.BLOCKS.getEntries().stream()
                                .map(RegistryObject::get)
                                .map(block -> new ItemStack(block.asItem()))
                                .collect(Collectors.toList()));
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
