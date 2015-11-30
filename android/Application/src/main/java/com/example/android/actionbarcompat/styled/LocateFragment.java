package com.example.android.actionbarcompat.styled;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class LocateFragment extends Fragment implements BluetoothInterface {

    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;

    private LeDeviceListAdapter leDeviceListAdapter;


    private boolean scanning;
    private Handler handler;

    ListView myBeaconsListView;

    Button faqButton;


    private final long SCAN_PERIOD = 3000;
    private int scanInterval = 3100;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_locate, container, false);


        faqButton = (Button) rootView.findViewById(R.id.locateFaqButton);

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // navigate to faq page
                Fragment frag = new FaqFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_locate, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        handler = new Handler();

        Handler secondsHandler = new Handler();

        myBeaconsListView = (ListView) rootView.findViewById(R.id.locateMyBeaconsListView);
        faqButton = (Button) rootView.findViewById(R.id.locateFaqButton);

        System.out.println(faqButton);


        initializeBluetooth();

        // starts a scan (right away)
        handler.postDelayed(periodicBLEScan, 1);
        secondsHandler.postDelayed(incrementSecondsSinceDetection, 1000);

        return rootView;
    }

    Runnable periodicBLEScan = new Runnable() {
        @Override
        public void run() {
            scanLeDevice(true);
            handler.postDelayed(periodicBLEScan, scanInterval);
        }
    };

    Runnable incrementSecondsSinceDetection = new Runnable() {
        @Override
        public void run() {
            leDeviceListAdapter.incrementSecondsByOne();
            leDeviceListAdapter.notifyDataSetChanged();
            handler.postDelayed(incrementSecondsSinceDetection, 1000);
        }
    };

    @Override
    public void initializeBluetooth() {

        bluetoothManager = (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);

        bluetoothAdapter = bluetoothManager.getAdapter();


        // Ensures Bluetooth is available on the device and it is enabled. If not,
        // displays a dialog requesting user permission to enable Bluetooth.
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        leDeviceListAdapter = new LeDeviceListAdapter();

        Log.d("debug",myBeaconsListView.toString());
        Log.d("debug",leDeviceListAdapter.toString());

        myBeaconsListView.setAdapter(leDeviceListAdapter);

//        leDeviceListAdapter.addExampleBeacons();


    }

    @Override
    public void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    scanning = false;
                    faqButton.setText("not scanning!");
                    bluetoothAdapter.stopLeScan(leScanCallback);
                }
            }, SCAN_PERIOD);

            scanning = true;
            faqButton.setText("scanning!");
            bluetoothAdapter.startLeScan(leScanCallback);
        } else {
            scanning = false;
            faqButton.setText("not scanning!");
            bluetoothAdapter.stopLeScan(leScanCallback);
        }
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback leScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, final int rssi,
                                     final byte[] scanRecord) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

//                            Log.d("ble", "performed scan");

                            CustomBluetoothBeacon beacon =
                                    new CustomBluetoothBeacon(device, rssi, scanRecord);

                            String maj = "";
                            String min = "";

                            String scanData = "";
                            String hex = "";
                            String companyid = "";
                            String devType = "";
                            String uuid = "";

                            for (int i = 0; i < scanRecord.length; i++){

                                scanData += scanRecord[i];
                                hex += String.format("%02x", scanRecord[i]);

                                if(i >= 5 && i <= 6) {
                                    companyid += String.format("%02x", scanRecord[i]);
                                } else if(i == 7) {
                                    devType += String.format("%02x", scanRecord[i]);
                                } else if(i >= 9 && i <= 24) {
                                    uuid += String.format("%02x", scanRecord[i]);
                                } else if(i >= 25 && i <= 26) {
                                    maj += String.format("%02x", scanRecord[i]);
                                } else if(i >= 27 && i <= 28) {
                                    min += String.format("%02x", scanRecord[i]);
                                }
                            }

//                            Log.d("scanrecord", scanData);
                            Log.d("company id", companyid);
                            Log.d("maj", maj);
                            Log.d("min", min);
                            Log.d("uuid", uuid);

                            beacon.majorValue = maj;
                            beacon.minorValue = min;
                            beacon.rssi = rssi;
                            beacon.uuid = uuid;
                            beacon.companyId = companyid;
                            beacon.name = maj + ":" + min; // todo: figure out what we want name to be


                            leDeviceListAdapter.addDevice(beacon);
                            leDeviceListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            };

    @Override
    public void connectToDevice() {

    }
}