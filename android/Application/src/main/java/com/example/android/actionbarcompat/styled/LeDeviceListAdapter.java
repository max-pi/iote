package com.example.android.actionbarcompat.styled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// Adapter for holding devices found through scanning.
public class LeDeviceListAdapter extends BaseAdapter {
    private ArrayList<CustomBluetoothBeacon> bleDevices;
    private LayoutInflater mInflator;

    public LeDeviceListAdapter() {
        super();
        bleDevices = new ArrayList<CustomBluetoothBeacon>();
//        mInflator = g.getLayoutInflater();


        //addExampleBeacons();
    }

    public void addDevice(CustomBluetoothBeacon beacon) {

        // only add new beacons (and that matches what we want)
        if(!checkArrayForBeacon(bleDevices, beacon) &&

                beacon.companyId.equals("4c4c")

                ) {
            bleDevices.add(beacon);
        } else {
            // already detected
            // update all detected beacons

            for (CustomBluetoothBeacon alreadyDetectedBeacon : bleDevices) {

                if (alreadyDetectedBeacon.device == null) {
                    // ignores invalid beacons (todo: remove)
                    continue;
                }

                if (alreadyDetectedBeacon.companyId.equals(beacon.companyId) &&
                        alreadyDetectedBeacon.majorValue.equals(beacon.majorValue) &&
                        alreadyDetectedBeacon.minorValue.equals(beacon.minorValue)


                        ) {
                    System.out.println("updating beacon");
                    System.out.println(beacon.rssi);
                    alreadyDetectedBeacon.rssi = beacon.rssi;
                    alreadyDetectedBeacon.secondsSinceDetection = 0;
                }
            }
        }
    }

    private boolean checkArrayForBeacon(ArrayList<CustomBluetoothBeacon> list, CustomBluetoothBeacon beacon) {

        for (CustomBluetoothBeacon b : list) {

//            if (b.getMajorValue().equals(beacon.getMajorValue()) &&
//                    b.getMinorValue().equals(beacon.getMinorValue())) {
//                return true;
//            }
            if (b.device == null) {
                // ignores invalid beacons (todo: remove)
                continue;
            }

            if (b.majorValue.equals(beacon.majorValue) &&
                    b.minorValue.equals(beacon.minorValue)
                    ) {
                return true;
            }
        }
        // if does not find match, return false
        return false;
    }

    public CustomBluetoothBeacon getDevice(int position) {
        return bleDevices.get(position);
    }

    public void clear() {
        bleDevices.clear();
    }

    @Override
    public int getCount() {
        return bleDevices.size();
    }

    @Override
    public Object getItem(int i) {
        return bleDevices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;
        // General ListView optimization code.
        if (view == null) {
            view = inflater.inflate(R.layout.ble_list_item, null);
            viewHolder = new ViewHolder();

            viewHolder.icon = (ImageView) view.findViewById(R.id.locateIconImageView);
            viewHolder.name = (TextView) view.findViewById(R.id.locateNameTextView);
            viewHolder.rssi = (TextView) view.findViewById(R.id.locateRssiTextView);
            viewHolder.secondsSinceDetection = (TextView) view.findViewById(R.id.locateTimerTextView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        CustomBluetoothBeacon beacon = bleDevices.get(i);

        // todo: match ble device id to internal name storage (do not display if no match)
        // also set correct name and correct icon
        // display last seen, etc
        // color background

        if (beacon.name != null) {
            viewHolder.name.setText(beacon.name);
        } else {
            viewHolder.name.setText("unknown beacon");
        }

        viewHolder.rssi.setText(String.valueOf(beacon.rssi));
        viewHolder.icon.setImageResource(R.drawable.location_icon);
        viewHolder.secondsSinceDetection.setText(String.valueOf(beacon.secondsSinceDetection));
        viewHolder.name.setText(beacon.name);

        if( view == null ){
            //We must create a View:
            view = inflater.inflate(R.layout.ble_list_item, parent, false);
        }
        //Here we can do changes to the convertView, such as set a text on a TextView
        //or an image on an ImageView.
        return view;
    }

    public void addExampleBeacons() {
        for (int i = 0; i<3 ; i++){
            CustomBluetoothBeacon fakeBeacon = CustomBluetoothBeacon.createExampleBeacon();
            bleDevices.add(fakeBeacon);
        }
    }

    public void incrementSecondsByOne(){

        for (CustomBluetoothBeacon b : bleDevices) {
            b.secondsSinceDetection += 1;
        }
    }


}

class ViewHolder {
    ImageView icon;
    TextView name;
    TextView rssi;
    TextView secondsSinceDetection;
}