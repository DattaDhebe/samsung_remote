package com.samsungremote

import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var irManager: ConsumerIrManager
    private lateinit var statusText: TextView

    private val samsungFrequency = 38_000
    private val samsungAddress = 0xE0E0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        irManager = getSystemService(ConsumerIrManager::class.java)
        statusText = findViewById(R.id.status_text)

        updateStatus()
        bindRemoteButtons()
    }

    private fun updateStatus() {
        statusText.text = if (hasIrEmitter()) {
            getString(R.string.status_ready)
        } else {
            getString(R.string.status_no_ir)
        }
    }

    private fun bindRemoteButtons() {
        SamsungCommand.entries.forEach { command ->
            findViewById<View>(command.viewId)?.setOnClickListener {
                sendCommand(command)
            }
        }
    }

    private fun sendCommand(command: SamsungCommand) {
        if (!hasIrEmitter()) {
            Toast.makeText(this, R.string.status_no_ir, Toast.LENGTH_LONG).show()
            return
        }

        irManager.transmit(samsungFrequency, buildSamsungPattern(command.code))
        Toast.makeText(this, getString(R.string.toast_command_sent, command.label), Toast.LENGTH_SHORT).show()
    }

    private fun hasIrEmitter(): Boolean {
        return irManager.hasIrEmitter()
    }

    private fun buildSamsungPattern(commandCode: Int): IntArray {
        val data = (samsungAddress shl 16) or commandCode
        val pattern = mutableListOf(4500, 4500)

        for (bitIndex in 0 until 32) {
            pattern.add(560)
            pattern.add(if (((data shr bitIndex) and 1) == 1) 1690 else 560)
        }

        pattern.add(560)
        return pattern.toIntArray()
    }

    enum class SamsungCommand(
        val viewId: Int,
        val label: String,
        val code: Int
    ) {
        POWER(R.id.button_power, "Power", 0x40BF),
        SOURCE(R.id.button_source, "Source", 0x807F),
        MUTE(R.id.button_mute, "Mute", 0xF00F),
        VOLUME_UP(R.id.button_volume_up, "Volume +", 0xE01F),
        VOLUME_DOWN(R.id.button_volume_down, "Volume -", 0xD02F),
        CHANNEL_UP(R.id.button_channel_up, "Channel +", 0x48B7),
        CHANNEL_DOWN(R.id.button_channel_down, "Channel -", 0x08F7),
        UP(R.id.button_up, "Up", 0x06F9),
        DOWN(R.id.button_down, "Down", 0x8679),
        LEFT(R.id.button_left, "Left", 0xA659),
        RIGHT(R.id.button_right, "Right", 0x46B9),
        OK(R.id.button_ok, "OK", 0x16E9),
        MENU(R.id.button_menu, "Menu", 0x58A7),
        BACK(R.id.button_back, "Back", 0x1AE5),
        HOME(R.id.button_home, "Home", 0x9E61),
        PLAY_PAUSE(R.id.button_play_pause, "Play/Pause", 0xE21D),
        ZERO(R.id.button_0, "0", 0x8877),
        ONE(R.id.button_1, "1", 0x20DF),
        TWO(R.id.button_2, "2", 0xA05F),
        THREE(R.id.button_3, "3", 0x609F),
        FOUR(R.id.button_4, "4", 0x10EF),
        FIVE(R.id.button_5, "5", 0x906F),
        SIX(R.id.button_6, "6", 0x50AF),
        SEVEN(R.id.button_7, "7", 0x30CF),
        EIGHT(R.id.button_8, "8", 0xB04F),
        NINE(R.id.button_9, "9", 0x708F)
    }
}
