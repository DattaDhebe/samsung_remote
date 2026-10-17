# Samsung Smart TV Remote

**A native Android remote for phones with an IR emitter.**

Kotlin · Android · ConsumerIrManager

[Features](#features) · [Setup](#setup) · [Code tour](#code-tour)

A starter application that maps an on-screen remote to Samsung-style infrared commands. It checks the phone's IR hardware before transmitting and displays a readiness message.

## Features

- Power, source, mute, volume and channel controls.
- Navigation, menu, home, back, play/pause and number keys.
- A 38 kHz command transmitter using Android's `ConsumerIrManager`.
- A centralized command map for adapting button codes.

## Setup

1. Open the repository in Android Studio and sync the Gradle project.
2. Connect a physical Android phone with an IR emitter.
3. Build and run the application.
4. Check the hardware status, point the phone at the TV and try a command.

An emulator or phone without an IR emitter cannot transmit these commands. TV-model compatibility depends on the command set and has not been established for every Samsung model.

## Code tour

| File | Responsibility |
| --- | --- |
| [MainActivity.kt](app/src/main/java/com/samsungremote/MainActivity.kt) | Hardware checks, button bindings, command codes and IR timing |
| [activity_main.xml](app/src/main/res/layout/activity_main.xml) | Remote-control interface |
| [AndroidManifest.xml](app/src/main/AndroidManifest.xml) | Android application configuration |
| [build.gradle](build.gradle) | Root Gradle configuration |

## Project scope

This is an IR-control starter. Network control through SmartThings or Wi-Fi is a separate extension to the current implementation.

Built by [Datta Dhebe](https://github.com/DattaDhebe).
