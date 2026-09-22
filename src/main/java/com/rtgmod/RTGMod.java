package com.rtgmod;

import com.rtgmod.config.RTGConfig;
import com.rtgmod.init.ModBiomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("rtgmod")
public class RTGMod {

    public static final String MODID = "rtgmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public RTGMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Daftarkan semua DeferredRegister (biome, fitur, dll.)
        ModBiomes.BIOMES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, RTGConfig.SPEC);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // Masukkan biome kustom ke daftar generasi Overworld.
            // Bobot (weight) lebih tinggi = lebih sering muncul.
            BiomeManager.addBiome(BiomeManager.BiomeType.COOL,
                    new BiomeManager.BiomeEntry(ModBiomes.BIG_MOUNTAINS_KEY, RTGConfig.mountainWeight.get()));

            BiomeManager.addBiome(BiomeManager.BiomeType.WARM,
                    new BiomeManager.BiomeEntry(ModBiomes.BEAUTIFUL_FOREST_KEY, RTGConfig.forestWeight.get()));

            LOGGER.info("[RTGMod] Biome kustom (Big Mountains, Beautiful Forest) terdaftar ke Overworld.");
        });
    }
}
