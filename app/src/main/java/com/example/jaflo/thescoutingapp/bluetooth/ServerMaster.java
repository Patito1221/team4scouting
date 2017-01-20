package com.example.jaflo.thescoutingapp.bluetooth;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by jaflo on 1/18/2017.
 */

public class ServerMaster extends Thread {
    private BluetoothServerSocket socket;

    public ServerMaster() {

        try {
            if (Resources.ADAPTER != null) {
                socket = Resources.ADAPTER.listenUsingInsecureRfcommWithServiceRecord(Resources.SERVER_NAME, Resources.SERVER_UUID);
                if (socket == null) {
                    throw new RuntimeException();
                }
                Log.i("server", "Made server!");
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void run() {
        while (true) {
            BluetoothSocket client = null;
            try {
                client = socket.accept();
                Log.i("server", "Server started!");
                OutputStream output = client.getOutputStream();
                output.write("hello!".getBytes());
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                    Log.e("server", "We're in big trouble!");

                }
            }
        }
    }
}
