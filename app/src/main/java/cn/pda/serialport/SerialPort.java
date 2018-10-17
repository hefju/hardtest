package cn.pda.serialport;
import android.util.Log;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/*
 * SerialPort类是JNI类，负责程序与硬件的通信
 */
public class SerialPort {

    private static final String TAG = "SerialPort";

    public static int TNCOM_EVENPARITY = 0;
    public static int TNCOM_ODDPARITY = 1;
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;
    private boolean trig_on = false;
    byte[] test;

    static {
        System.loadLibrary("devapi");
        System.loadLibrary("irdaSerialPort");
    }

    public SerialPort() {
    }

    public SerialPort(int port, int baudrate, int flags) throws SecurityException, IOException {
        this.mFd = open(port, baudrate);
        if (this.mFd == null) {
            Log.e("SerialPort", "native open returns null");
            throw new IOException();
        } else {
            this.mFileInputStream = new FileInputStream(this.mFd);
            this.mFileOutputStream = new FileOutputStream(this.mFd);
        }
    }

    public InputStream getInputStream() {
        return this.mFileInputStream;
    }

    public OutputStream getOutputStream() {
        return this.mFileOutputStream;
    }

    public void power_5Von() {
        this.zigbeepoweron();
    }

    public void power_5Voff() {
        this.zigbeepoweroff();
    }

    public void power_3v3on() {
        this.power3v3on();
    }

    public void power_3v3off() {
        this.power3v3off();
    }

    public void rfid_poweron() {
        this.rfidPoweron();
    }

    public void rfid_poweroff() {
        this.rfidPoweroff();
    }

    public void psam_poweron() {
        this.psampoweron();
    }

    public void psam_poweroff() {
        this.psampoweroff();
    }

    public void scaner_poweron() {
        this.scanerpoweron();
        this.scaner_trigoff();
    }

    public void scaner_poweroff() {
        this.scanerpoweroff();
    }

    public void scaner_trigon() {
        this.scanertrigeron();
        this.trig_on = true;
    }

    public void scaner_trigoff() {
        this.scanertrigeroff();
        this.trig_on = false;
    }

    public boolean scaner_trig_stat() {
        return this.trig_on;
    }

    private static native FileDescriptor open(int var0, int var1);

    private static native FileDescriptor open(int var0, int var1, int var2);

    public native void close(int var1);

    public native void zigbeepoweron();

    public native void zigbeepoweroff();

    public native void scanerpoweron();

    public native void scanerpoweroff();

    public native void psampoweron();

    public native void psampoweroff();

    public native void scanertrigeron();

    public native void scanertrigeroff();

    public native void power3v3on();

    public native void power3v3off();

    public native void rfidPoweron();

    public native void rfidPoweroff();

    public native void usbOTGpowerOn();

    public native void usbOTGpowerOff();

    public native void irdapoweron();

    public native void irdapoweroff();

    public native void setGPIOhigh(int var1);

    public native void setGPIOlow(int var1);

    public native void test(byte[] var1);
}