package net.kaupenjoe.tutorialmod;

import glitchcore.event.EventManager;
import glitchcore.util.Environment;
import glitchcore.util.RegistryHelper;
import net.kaupenjoe.tutorialmod.api.BOPAPI;
import net.kaupenjoe.tutorialmod.init.*;
import net.kaupenjoe.tutorialmod.worldgen.carver.BOPWorldCarvers;
import net.kaupenjoe.tutorialmod.worldgen.feature.BOPBaseFeatures;
import net.minecraft.core.registries.Registries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TutorialMod
{
    public static final String MOD_ID = BOPAPI.MOD_ID;
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init()
    {
        // Initialize the config file first so other things can rely on it
        ModConfig.setup();

        ModBiomes.setup();
        ModTags.setup();
        addRegistrars();
        addHandlers();
    }

    private static void addRegistrars()
    {
        //TODO: build up over time.
        var regHelper = RegistryHelper.create();
        regHelper.addRegistrar(Registries.BLOCK, ModBlocks::setup);
//        regHelper.addRegistrar(Registries.BLOCK_ENTITY_TYPE, ModBlockEntities::registerBlockEntities);
//        regHelper.addRegistrar(Registries.FLUID, ModFluids::registerFluids);
        regHelper.addRegistrar(Registries.ITEM, ModItems::setup);
//        regHelper.addRegistrar(Registries.FEATURE, BOPBaseFeatures::registerFeatures);
        regHelper.addRegistrar(Registries.CARVER, BOPWorldCarvers::registerCarvers);
//        regHelper.addRegistrar(Registries.ENTITY_TYPE, ModEntities::registerEntities);
        regHelper.addRegistrar(Registries.CREATIVE_MODE_TAB, ModCreativeTab::registerCreativeTabs);
//        regHelper.addRegistrar(Registries.PARTICLE_TYPE, ModParticles::registerParticles);
//        regHelper.addRegistrar(Registries.SOUND_EVENT, ModSounds::registerSounds);
    }

    private static void addHandlers()
    {
        // Misc handlers
//        if (ModConfig.gameplay.wanderingTraderTrades) { EventManager.addListener(ModVillagerTrades::addWanderingVillagerTrades); }

        if (Environment.isClient())
        {
            ModClient.addClientHandlers();
        }
    }

    public static void setupTerraBlender()
    {
        ModBiomes.setupTerraBlender();
    }

}