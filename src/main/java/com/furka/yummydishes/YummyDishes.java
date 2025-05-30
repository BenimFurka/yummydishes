package com.furka.yummydishes;

import com.furka.yummydishes.init.ModBlocks;
import com.furka.yummydishes.init.ModItems;
import com.furka.yummydishes.init.ModTab;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(YummyDishes.MODID)
public class YummyDishes {
    public static final String MODID = "yummydishes";
    private static final org.slf4j.Logger LOGGER = LogUtils.getLogger();

    public YummyDishes(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModTab.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing YummyDishes...");
    }
}