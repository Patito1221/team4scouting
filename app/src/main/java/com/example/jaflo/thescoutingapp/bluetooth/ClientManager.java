package com.example.jaflo.thescoutingapp.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaflo on 1/18/2017.
 */

public class ClientManager {
    private List<ClientThread> threads;
    private final Context context;

    public ClientManager(Context context) {
        threads = new ArrayList<>();
        this.context = context;
    }

    public void createThreads() {
        for (BluetoothDevice device : Resources.ADAPTER.getBondedDevices()) {
            try {
                Log.i("client-m", String.format("Found device %s with address %s", device.getName(), device.getAddress()));
                BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(Resources.SERVER_UUID);
                ClientThread thread = new ClientThread(socket, context);
                thread.start();
                threads.add(thread);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
