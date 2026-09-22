# RTG Mod — Realistic Terrain Generation (Forge 1.16.5, JRE 8)

Mod ini menambahkan dua biome kustom ke Overworld:

- **Big Mountains** — puncak tinggi & curam, salju di puncak, pohon konifer di lereng.
- **Beautiful Forest** — hutan lebat dengan campuran pohon ek besar, birch, dan bunga warna-warni.

Ditambah **datapack opsional** (`datapack-longrivers/`) yang memperbesar ukuran
biome & sungai di Overworld agar sungai terasa lebih panjang dan wilayah
pegunungan lebih luas.

## Struktur proyek

```
RTGMod/
├── build.gradle              # dikonfigurasi untuk Java 8 (sourceCompatibility 1.8)
├── gradle.properties
├── settings.gradle
├── src/main/java/com/rtgmod/
│   ├── RTGMod.java            # entry point, mendaftarkan biome ke Overworld
│   ├── config/RTGConfig.java  # config bobot biome & skala terrain
│   ├── init/ModBiomes.java    # DeferredRegister biome
│   └── world/biome/
│       ├── BigMountainsBiome.java
│       └── BeautifulForestBiome.java
├── src/main/resources/META-INF/mods.toml
└── datapack-longrivers/       # datapack terpisah, tidak wajib
    └── data/minecraft/dimension/overworld.json
```

## Cara build (butuh JDK 8 & koneksi internet untuk ForgeGradle)

```bash
cd RTGMod
./gradlew build
```

Hasil `.jar` akan ada di `build/libs/rtgmod-1.0.0.jar`. Pasang seperti mod
Forge 1.16.5 biasa: masukkan ke folder `mods/` pada instalasi Forge 1.16.5
yang berjalan di atas **JRE 8** (Forge 1.16.5 memang mensyaratkan Java 8,
jadi `sourceCompatibility` di `build.gradle` sudah dikunci ke `1.8`).

## Cara pasang datapack "Long Rivers"

Ini bukan bagian dari file `.jar` mod (karena mod tidak bisa dengan andal
menimpa `data/minecraft/dimension/overworld.json` bawaan vanilla dari dalam
jar-nya sendiri). Cara pakainya:

1. Buat dunia baru.
2. Salin folder `datapack-longrivers` ke `saves/<NamaDuniaAnda>/datapacks/`.
3. Jalankan `/reload` di dalam game, atau muat ulang dunia.

`biome_size` dan `river_size` yang diperbesar (dari default 4 menjadi 6)
membuat setiap "sel" biome — termasuk sungai — di-zoom lebih sedikit,
sehingga bentuknya jadi lebih besar/panjang secara alami. Anda bisa
menaikkan nilainya lebih jauh (mis. 8) untuk efek lebih ekstrem, tapi ini
memperbesar jarak antar-biome secara keseluruhan.

## Cara kerja "Big Mountains" & "Beautiful Trees"

- Ketinggian & kecuraman gunung dikontrol lewat `depth`/`scale` pada
  `Biome.Builder` di `BigMountainsBiome.java`. Nilai ini sudah dinaikkan
  dari default vanilla agar puncak lebih ekstrem.
- Kepadatan & variasi pohon di `BeautifulForestBiome.java` memakai
  kombinasi method bawaan `DefaultBiomeFeatures` (pohon ek, birch, semak
  buah) yang ditumpuk agar hutan terasa lebih lebat daripada hutan vanilla.
- Bobot kemunculan kedua biome bisa diatur lewat file config
  `rtgmod-common.toml` yang muncul otomatis setelah mod dijalankan sekali
  (opsi `mountainWeight`, `forestWeight`, dll.).

## ⚠️ Catatan penting / batasan

1. **Saya menulis kode ini tanpa toolchain Forge di sini** (tidak ada akses
   internet untuk mengunduh dependensi ForgeGradle/mapping resmi), jadi kode
   ini **belum saya compile**. Nama method seperti yang ada di
   `DefaultBiomeFeatures` (mis. `addForestTrees`, `addSweetBerryBushes`)
   sudah saya tulis berdasarkan API Forge 1.16.5 yang umum dipakai, tapi
   nama persis bisa sedikit berbeda tergantung versi mapping. Jalankan
   `./gradlew build` — kalau ada error "method not found", cek nama method
   yang benar lewat auto-complete IDE (IntelliJ/Eclipse) setelah proyek
   ter-sync, biasanya tinggal ganti nama method yang mirip.
2. Kontrol *bentuk* sungai (benar-benar membuat jalurnya lebih panjang &
   berkelok, bukan cuma lebih besar) di Forge 1.16.5 memerlukan penulisan
   `BiomeProvider`/`Layer` kustom sendiri — jauh lebih kompleks daripada
   registrasi biome biasa. Pendekatan datapack `biome_size`/`river_size` di
   atas adalah cara paling realistis untuk mendapatkan efek "sungai lebih
   panjang" tanpa menulis ulang seluruh sistem noise-layer vanilla.
3. Untuk hasil visual terbaik, tambahkan tekstur/model block sendiri (mis.
   varian batu gunung) di `src/main/resources/assets/rtgmod/` — belum saya
   sertakan karena di luar cakupan kode generasi terrain.

Kalau nanti mau saya lanjutkan — misalnya menambah block/tekstur kustom,
struktur (reruntuhan gunung), atau benar-benar menulis `BiomeProvider`
kustom untuk kontrol sungai yang lebih presisi — tinggal bilang saja.
