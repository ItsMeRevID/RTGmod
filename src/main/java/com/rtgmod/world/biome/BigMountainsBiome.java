package com.rtgmod.world.biome;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Features;

/**
 * Biome "Big Mountains": puncak sangat tinggi & curam, salju di puncak,
 * bebatuan terjal, dan pepohonan konifer lebat di lereng bawah.
 */
public final class BigMountainsBiome {

    private BigMountainsBiome() {}

    public static Biome create() {
        Biome.Builder builder = new Biome.Builder()
                .precipitation(Biome.RainType.SNOW)
                .category(Biome.Category.EXTREME_HILLS)
                // depth & scale mengontrol tinggi & "kekasaran" terrain:
                // depth tinggi = dasar terrain lebih tinggi, scale tinggi = variasi ketinggian ekstrem (puncak sangat tajam)
                .depth(1.9F)
                .scale(1.4F)
                .temperature(0.1F)
                .downfall(0.9F)
                .specialEffects(new BiomeAmbience.Builder()
                        .setWaterColor(4020182)
                        .setWaterFogColor(329011)
                        .setFogColor(12638463)
                        // Warna langit dihitung manual (hindari ketergantungan method yang nama
                        // mapping-nya bisa berbeda antar versi mapping). Nilai ini mendekati
                        // hasil vanilla untuk suhu ~0.1 (pegunungan bersalju).
                        .withSkyColor(8103167)
                        .setMoodSound(BiomeAmbience.MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .mobSpawnSettings(MobSpawnInfo.EMPTY)
                .generationSettings(new net.minecraft.world.gen.GenerationSettings.Builder()
                        .surfaceBuilder(ConfiguredSurfaceBuilders.MOUNTAIN)
                        .build());

        Biome biome = builder.build();

        // Tambahkan fitur dekorasi dasar (bebatuan, ore, gua) lalu pohon konifer lebat.
        DefaultBiomeFeatures.addDefaultCarvers(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultLakes(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultCrystalFormations(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultMonsterRoom(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultUndergroundVariety(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultOres(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultSoftDisks(biome.getGenerationSettings());
        DefaultBiomeFeatures.addMountainTrees(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultFlowers(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultGrass(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultMushrooms(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultVegetation(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultSprings(biome.getGenerationSettings());
        DefaultBiomeFeatures.addFrozenTopLayer(biome.getGenerationSettings());
        DefaultBiomeFeatures.addDefaultMonsterRoom(biome.getGenerationSettings());

        return biome;
    }
}
