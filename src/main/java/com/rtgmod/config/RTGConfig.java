package com.rtgmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class RTGConfig {

    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue mountainWeight;
    public static final ForgeConfigSpec.IntValue forestWeight;
    public static final ForgeConfigSpec.DoubleValue mountainHeightScale;
    public static final ForgeConfigSpec.DoubleValue treeDensityMultiplier;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.push("world_generation");

        mountainWeight = builder
                .comment("Bobot kemunculan biome Big Mountains di Overworld (semakin besar = semakin sering muncul)")
                .defineInRange("mountainWeight", 8, 1, 50);

        forestWeight = builder
                .comment("Bobot kemunculan biome Beautiful Forest di Overworld")
                .defineInRange("forestWeight", 10, 1, 50);

        mountainHeightScale = builder
                .comment("Pengali tambahan tinggi gunung (1.0 = default mod, lebih tinggi = lebih ekstrem)")
                .defineInRange("mountainHeightScale", 1.5D, 0.5D, 3.0D);

        treeDensityMultiplier = builder
                .comment("Pengali kepadatan pohon di Beautiful Forest")
                .defineInRange("treeDensityMultiplier", 1.5D, 0.5D, 4.0D);

        builder.pop();

        SPEC = builder.build();
    }
}
