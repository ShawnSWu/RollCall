package com.shawn.newrollcall.ScanBLEModel.event;

/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class DeviceListInGroupItem {

    private String deviceName;

    private String deviceAddress;

    public DeviceListInGroupItem(String deviceName, String deviceAddress) {
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }
}
