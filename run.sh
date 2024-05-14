#!/bin/bash
# I have to use this script to test the mod because I can't get BTA to launch outside of prism
cd "$(dirname "$0")" &&
ModName="$(cat gradle.properties | grep "mod_name" | cut -d "=" -f2)" &&
ModVersion="$(cat gradle.properties | grep "mod_version" | cut -d "=" -f2)" &&
./gradlew build &&
cp "build/libs/${ModName}-${ModVersion}.jar" "/Users/frostbird/Library/Application Support/PrismLauncher/instances/bta_babric_instance_7.1_mod_debug/.minecraft/mods/${ModName}.jar" &&
/Applications/Prism\ Launcher.app/Contents/MacOS/prismlauncher -l "bta_babric_instance_7.1_mod_debug"