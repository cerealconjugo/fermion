package com.rosemods.fermion.core;

import com.rosemods.fermion.core.data.client.FermionLanguageProvider;
import com.rosemods.fermion.core.other.FermionModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Fermion.MODID)
public class Fermion {
    public static final String MODID = "fermion";
    public static final Logger LOGGER = LogManager.getLogger(MODID);


    public Fermion() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        final ModLoadingContext context = ModLoadingContext.get();

        bus.addListener(EventPriority.LOWEST, this::commonSetup);
        bus.addListener(this::dataSetup);

        MinecraftForge.EVENT_BUS.register(this);
        context.registerConfig(ModConfig.Type.COMMON, FermionConfig.COMMON_SPEC);
        context.registerConfig(ModConfig.Type.CLIENT, FermionConfig.CLIENT_SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            FermionModifiers.removeItems();
            FermionModifiers.hideModdedTabs();
            FermionModifiers.modifyGroups();
        });
    }

    private void dataSetup(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        boolean client = event.includeClient();

        gen.addProvider(client, new FermionLanguageProvider(event));
    }


}