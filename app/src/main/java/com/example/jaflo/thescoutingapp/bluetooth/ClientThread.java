package com.example.jaflo.thescoutingapp.bluetooth;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jaflo on 1/18/2017.
 */

public class ClientThread extends Thread {
    private final BluetoothSocket socket;
    private final Context context;

    public ClientThread(BluetoothSocket socket, Context context) {
        this.socket = socket;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            Log.i("client", "hello there");
            socket.connect();
            Log.i("client", String.format("connected to %s", socket.getRemoteDevice().getAddress()));
            InputStream input = socket.getInputStream();
            Thread.sleep(200);
            int available = input.available();
            byte[] result = new byte[available];
            int actual = input.read(result);
            Log.i("client", String.format("%d/%d %s", available, actual, new String(result)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                Log.e("client", "We're in deep trouble!");
            }
        }
    }
}
