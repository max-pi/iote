package com.example.android.actionbarcompat.styled;

import android.bluetooth.BluetoothDevice;

import java.util.Date;

/**
 * Created by maxp on 15-11-22.
 */
public class CustomBluetoothBeacon {

    final BluetoothDevice device;
    int rssi;
    byte[] scanRecord;

    String uuid;

    String majorValue;
    String minorValue;

    String companyId;

    // user facing variables:

    String tagId;       // globally unique
    String userId;
    String name;        // displayed name
    Date lastSeen;

    // just for testing
    int secondsSinceDetection = 0;



    public CustomBluetoothBeacon(BluetoothDevice device, int rssi, byte[] scanRecord) {
        this.device = device;
        this.rssi = rssi;
        this.scanRecord = scanRecord;

        // todo: set the beacon attrbutes here rather than in the fragment

    }

    public static CustomBluetoothBeacon createExampleBeacon() {
        // creates fake beacon for testing
        BluetoothDevice fakeDevice = null;
        int fakeRssi = -32;

        CustomBluetoothBeacon fakeBeacon = new CustomBluetoothBeacon(fakeDevice, fakeRssi, new byte[32]);
        fakeBeacon.uuid = "fake uuid";

        // ui stuff:
        fakeBeacon.name = "name of beacon";
        fakeBeacon.lastSeen = new Date();


        return fakeBeacon;
    }


}
