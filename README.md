# Samsung Smart TV Remote

Native Android starter app for controlling Samsung TVs from phones with an IR blaster.

## Features

- Full remote layout: power, source, mute, volume, channel, navigation, menu, home, back, play/pause, and number pad.
- Uses Android `ConsumerIrManager` with Samsung-style 38 kHz NEC timing.
- Detects whether the phone has an IR emitter before sending commands.

## Setup

1. Open this folder in Android Studio.
2. Sync Gradle.
3. Run on a physical Android phone with an IR blaster.
4. Point the phone at the Samsung TV and tap a remote button.

## Notes

- Modern phones often do not include IR hardware; the app will still install but cannot transmit without an emitter.
- The command map is a practical Samsung IR starter set and may need adjustment for specific TV models.
- Wi-Fi SmartThings-style control can be added later for phones without IR hardware.
