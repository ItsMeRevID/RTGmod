package com.rtgmod.init;

import com.rtgmod.RTGMod;
import com.rtgmod.world.biome.BeautifulForestBiome;
import com.rtgmod.world.biome.BigMountainsBiome;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, RTGMod.MODID);

    public static final RegistryObject<Biome> BIG_MOUNTAINS =
            BIOMES.register("big_mountains", BigMountainsBiome::create);

    public static final RegistryObject<Biome> BEAUTIFUL_FOREST =
            BIOMES.register("beautiful_forest", BeautifulForestBiome::create);

    // RegistryKey dipakai saat mendaftarkan biome ke BiomeManager (world generation)
    public static final RegistryKey<Biome> BIG_MOUNTAINS_KEY =
            RegistryKey.create(net.minecraft.util.registry.Registry.BIOME_KEY,
                    new ResourceLocation(RTGMod.MODID, "big_mountains"));

    public static final RegistryKey<Biome> BEAUTIFUL_FOREST_KEY =
            RegistryKey.create(net.minecraft.util.registry.Registry.BIOME_KEY,
                    new ResourceLocation(RTGMod.MODID, "beautiful_forest"));

    private ModBiomes() {}
}
