//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.za.finger;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;

public class UsbController {
    private final Context mApplicationContext;
    private final UsbManager mUsbManager;
    private final int VID;
    private final int PID;
    private boolean m_bInit = false;
    private UsbDeviceConnection m_usbConn = null;
    private UsbInterface m_usbIf = null;
    private final IUsbConnState mConnectionHandler;
    protected static final String ACTION_USB_PERMISSION = "ch.serverbox.android.USB";
    public int usbfd;
    private BroadcastReceiver mPermissionReceiver = new UsbController.PermissionReceiver(new UsbController.IPermissionListener() {
        public void onPermissionDenied(UsbDevice d) {
            UsbController.this.l("Permission denied on " + d.getDeviceId());
        }
    });
    public static final String TAG = "USBController";

    public UsbController(Activity parentActivity, IUsbConnState connectionHandler, int vid, int pid) {
        this.mConnectionHandler = connectionHandler;
        this.mApplicationContext = parentActivity.getApplicationContext();
        this.mUsbManager = (UsbManager)this.mApplicationContext.getSystemService("usb");
        this.VID = vid;
        this.PID = pid;
    }

    public void init() {
        this.enumerate(new UsbController.IPermissionListener() {
            public void onPermissionDenied(UsbDevice d) {
                UsbManager usbman = (UsbManager)UsbController.this.mApplicationContext.getSystemService("usb");
                PendingIntent pi = PendingIntent.getBroadcast(UsbController.this.mApplicationContext, 0, new Intent("ch.serverbox.android.USB"), 0);
                UsbController.this.mApplicationContext.registerReceiver(UsbController.this.mPermissionReceiver, new IntentFilter("ch.serverbox.android.USB"));
                usbman.requestPermission(d, pi);
            }
        });
    }

    public void uninit() {
        if (this.m_usbConn != null) {
            this.m_usbConn.releaseInterface(this.m_usbIf);
            this.m_usbConn.close();
            this.m_usbConn = null;
            this.m_bInit = false;
        }

    }

    public void stop() {
        try {
            this.mApplicationContext.unregisterReceiver(this.mPermissionReceiver);
        } catch (IllegalArgumentException var2) {
            ;
        }

    }

    public boolean IsInit() {
        return this.m_bInit;
    }

    private void enumerate(UsbController.IPermissionListener listener) {
        boolean bFound = false;
        this.l("enumerating");
        HashMap<String, UsbDevice> devlist = this.mUsbManager.getDeviceList();
        Iterator deviter = devlist.values().iterator();

        while(deviter.hasNext()) {
            UsbDevice d = (UsbDevice)deviter.next();
            this.l("Found device: " + String.format("%04X:%04X", d.getVendorId(), d.getProductId()));
            if (d.getVendorId() == this.VID && d.getProductId() == this.PID) {
                bFound = true;
                this.l("Device under: " + d.getDeviceName());
                if (this.mUsbManager.hasPermission(d)) {
                    this.usbfd = this.mUsbManager.openDevice(d).getFileDescriptor();
                    Log.e("USBController", "zhw ==555= open fd: " + this.usbfd);
                    this.mConnectionHandler.onUsbConnected();
                    return;
                }

                listener.onPermissionDenied(d);
                break;
            }
        }

        if (!bFound) {
            this.mConnectionHandler.onDeviceNotFound();
        }

    }

    private void l(Object msg) {
        Log.d("USBController", ">==< " + msg.toString() + " >==<");
    }

    private interface IPermissionListener {
        void onPermissionDenied(UsbDevice var1);
    }

    private class PermissionReceiver extends BroadcastReceiver {
        private final UsbController.IPermissionListener mPermissionListener;

        public PermissionReceiver(UsbController.IPermissionListener permissionListener) {
            this.mPermissionListener = permissionListener;
        }

        public void onReceive(Context context, Intent intent) {
            UsbController.this.mApplicationContext.unregisterReceiver(this);
            if (intent.getAction().equals("ch.serverbox.android.USB")) {
                if (!intent.getBooleanExtra("permission", false)) {
                    this.mPermissionListener.onPermissionDenied((UsbDevice)intent.getParcelableExtra("device"));
                    UsbController.this.mConnectionHandler.onUsbPermissionDenied();
                } else {
                    UsbController.this.l("Permission granted");
                    UsbDevice dev = (UsbDevice)intent.getParcelableExtra("device");
                    if (dev != null) {
                        if (dev.getVendorId() == UsbController.this.VID && dev.getProductId() == UsbController.this.PID) {
                            UsbController.this.usbfd = UsbController.this.mUsbManager.openDevice(dev).getFileDescriptor();
                            Log.e("USBController", "zhw ==555= open fd: " + UsbController.this.usbfd);
                            UsbController.this.mConnectionHandler.onUsbConnected();
                        }
                    } else {
                        UsbController.this.mConnectionHandler.onDeviceNotFound();
                    }
                }
            }

        }
    }
}
