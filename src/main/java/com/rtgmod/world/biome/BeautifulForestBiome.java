package com.rtgmod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

/**
 * Biome "Beautiful Forest": hutan lebat dengan campuran pohon ek besar,
 * birch, dan bunga-bunga berwarna-warni untuk kesan "indah" / dekoratif.
 */
public final class BeautifulForestBiome {

    private BeautifulForestBiome() {}

    public static Biome create() {
        Biome.Builder builder = new Biome.Builder()
                .precipitation(Biome.RainType.RAIN)
                .category(Biome.Category.FOREST)
                .depth(0.15F)
                .scale(0.25F)
                .temperature(0.7F)
                .downfall(0.85F)
                .specialEffects(new BiomeAmbience.Builder()
                        .setWaterColor(4159204)
                        .setWaterFogColor(329011)
                        .setFogColor(12638463)
                        .withSkyColor(7972607)
                        .setGrassColorModifier(BiomeAmbience.GrassColorModifier.NONE)
                        .setMoodSound(BiomeAmbience.MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .mobSpawnSettings(MobSpawnInfo.EMPTY)
                .generationSettings(new GenerationSettings.Builder()
                        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                        .build());

        Biome biome = builder.build();
        GenerationSettings gen = biome.getGenerationSettings();

        DefaultBiomeFeatures.addDefaultCarvers(gen);
        DefaultBiomeFeatures.addDefaultLakes(gen);
        DefaultBiomeFeatures.addDefaultMonsterRoom(gen);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(gen);
        DefaultBiomeFeatures.addDefaultOres(gen);
        DefaultBiomeFeatures.addDefaultSoftDisks(gen);

        // Pohon: campuran ek besar (fancy), birch, dan pohon ek raksasa sesekali,
        // ditumpuk agar hutan terasa lebat & "indah".
        DefaultBiomeFeatures.addForestTrees(gen);
        DefaultBiomeFeatures.addForestFlowers(gen);
        DefaultBiomeFeatures.addDefaultFlowers(gen);
        DefaultBiomeFeatures.addForestGrass(gen);
        DefaultBiomeFeatures.addDefaultMushrooms(gen);
        DefaultBiomeFeatures.addDefaultVegetation(gen);
        DefaultBiomeFeatures.addDefaultSprings(gen);
        DefaultBiomeFeatures.addSweetBerryBushes(gen);
        DefaultBiomeFeatures.addDefaultMonsterRoom(gen);

        return biome;
    }
}
