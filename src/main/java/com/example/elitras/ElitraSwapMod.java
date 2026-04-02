package com.example.elitras;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ElitraSwapMod.MODID)
public class ElitraSwapMod {
    public static final String MODID = "elitras";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    public ElitraSwapMod(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Elitras Swap Mod загружен! v1.0.0");
        LOGGER.info("Для PvP боев на элитрах - булава переключает на броню, фейерверки на элитры");
        NeoForge.EVENT_BUS.register(ElitraSwapHandler.class);
    }
}
