package com.example.jaflo.thescoutingapp.bluetooth;

import android.bluetooth.BluetoothAdapter;

import java.util.UUID;

/**
 * Created by jaflo on 1/18/2017.
 */

public class Resources {
    public static final UUID SERVER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    public static final String SERVER_NAME = "serverrrr";
    public static final BluetoothAdapter ADAPTER = BluetoothAdapter.getDefaultAdapter();
}
