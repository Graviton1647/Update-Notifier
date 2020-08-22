plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation("org.bukkit:bukkit:1.12.2-R0.1-SNAPSHOT")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.github.Graviton1647:CubeNetworkAPI:6.8")
}

