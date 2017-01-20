
package com.example.jaflo.thescoutingapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Handler;

import com.example.jaflo.thescoutingapp.bluetooth.ClientManager;
import com.example.jaflo.thescoutingapp.bluetooth.ClientThread;
import com.example.jaflo.thescoutingapp.bluetooth.ServerMaster;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    private class ConnectThread extends Thread {

        UUID MY_UUID = UUID.fromString("ce1d74ee-0327-4fa7-a78f-af64641337b8");

        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            // Use a temporary object that is later assigned to mmSocket
            // because mmSocket is final.
            BluetoothSocket tmp = null;
            mmDevice = device;

            try {
                // Get a BluetoothSocket to connect with the given BluetoothDevice.
                // MY_UUID is the app's UUID string, also used in the server code.
                tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's create() method failed", e);
            }
            mmSocket = tmp;
        }

        public void attemptConnection() {
            // Cancel discovery because it otherwise slows down the connection.
            mBluetoothAdapter.cancelDiscovery();

            Toast.makeText(getApplicationContext(), "Attempting to connect to " + mmSocket.getRemoteDevice().getName(), Toast.LENGTH_LONG).show();
            try {
                // Connect to the remote device through the socket. This call blocks
                // until it succeeds or throws an exception
                //
                mmSocket.connect();
                Toast.makeText(getApplicationContext(), "Successfully connected to " + mmSocket.getRemoteDevice().getName(), Toast.LENGTH_LONG).show();
                manageMyConnectedSocket(mmSocket);
            } catch (IOException connectException) {
                // Unable to connect; close the socket and return.
//                Toast.makeText(getApplicationContext(), "Failed to connect", Toast.LENGTH_LONG).show();
//                try {
//                    mmSocket.close();
//                } catch (IOException closeException) {
//                    Log.e(TAG, "Could not close the client socket", closeException);
//                }
//                return;

                throw new RuntimeException(connectException);
            }
        }

        // Closes the client socket and causes the thread to finish.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the client socket", e);
            }
        }

        private void manageMyConnectedSocket(BluetoothSocket mmSocket) {
            try {
                Toast.makeText(getApplicationContext(), "yeet", Toast.LENGTH_LONG).show();
                InputStream inputStream = mmSocket.getInputStream();
                OutputStream outputStream = mmSocket.getOutputStream();

                outputStream.write(MY_UUID.toString().getBytes());
                byte[] buffer = new byte[2048];

                int r = inputStream.read(buffer);
                System.out.printf("%d %s\n", r, new String(buffer));

                cancel();

            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }


    private static final String TAG = "MY_APP_DEBUG_TAG";
    private Handler mHandler; // handler that gets info from Bluetooth service

    // Defines several constants used when transmitting messages between the
    // service and the UI.
    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        public static final int MESSAGE_WRITE = 1;
        public static final int MESSAGE_TOAST = 2;

        // ... (Add other message types here as needed.)
    }

    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            mmBuffer = new byte[1024];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer);
                    // Send the obtained bytes to the UI activity.
                    Message readMsg = mHandler.obtainMessage(MessageConstants.MESSAGE_READ, numBytes, -1,
                            mmBuffer);
                    readMsg.sendToTarget();
                } catch (IOException e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        }

        // Call this from the main activity to send data to the remote device.
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);

                // Share the sent message with the UI activity.
                Message writtenMsg = mHandler.obtainMessage(
                        MessageConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
                writtenMsg.sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when sending data", e);

                // Send a failure message back to the activity.
                Message writeErrorMsg =
                        mHandler.obtainMessage(MessageConstants.MESSAGE_TOAST);
                Bundle bundle = new Bundle();
                bundle.putString("toast",
                        "Couldn't send data to the other device");
                writeErrorMsg.setData(bundle);
                mHandler.sendMessage(writeErrorMsg);
            }
        }

    }


    Button reviewButton;
    Button scoutMatchButton;
    Button pitScoutButton;
    Typeface roboto;


    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(1)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Toast.makeText(getApplicationContext(), "Discovered + deviceName", Toast.LENGTH_LONG).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//ggg so it was just the constraint></>
        Button reviewButton = (Button) findViewById(R.id.ReviewBtn);
        Button scoutMatchButton = (Button) findViewById(R.id.ScoutaMatchBtn);
        Button pitScoutButton = (Button) findViewById(R.id.PitScoutBtn);
        Button Bluetooth = (Button) findViewById(R.id.Bluetooth);
        Button Discoverable = (Button) findViewById(R.id.DiscoverableBtn);
        Spinner BluetoothList = (Spinner) findViewById(R.id.BluetoothDevices);

        if (mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "Bluetooth not supported", Toast.LENGTH_LONG).show();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
        final Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        Discoverable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent discoverableIntent =
                        new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                startActivity(discoverableIntent);
            }
        });
        if (pairedDevices.size() > 0) {
            Toast.makeText(getApplicationContext(), "Paired", Toast.LENGTH_LONG).show();
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
            }
        }
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        registerReceiver(mReceiver, filter);
        Bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new ClientManager(getApplicationContext()).createThreads();
                new ServerMaster().start();
            }
        });


        Typeface roboto = Typeface.createFromAsset(getAssets(), "Roboto-Black.ttf");


        reviewButton.setTypeface(roboto);
        scoutMatchButton.setTypeface(roboto);
        pitScoutButton.setTypeface(roboto);


        final Intent startReviewSelection = new Intent(this, TeamReviewSelection.class);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Toast", Toast.LENGTH_LONG).show();
                startActivity(startReviewSelection);
            }
        });
        final Intent startMatchTeamSelection = new Intent(this, MatchTeamSelection.class);
        scoutMatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startMatchTeamSelection);
            }
        });
        final Intent startPitScoutSelection = new Intent(this, PitScoutTeamSelection.class);
        pitScoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(startPitScoutSelection);
            }
        });
    }
}
