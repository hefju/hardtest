//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.za.finger;

import android.app.Activity;
import android.content.Context;


public class FingerHelper {
    public final int VendorId0 = 8457;
    public final int ProductId0 = 30264;
    public final int VendorId1 = 8201;
    public final int ProductId1 = 30264;
    public final int PS_OK = 0;
    public final int CONNECT_OK = 1;
    public final int PS_COMM_ERR = 1;
    public final int PS_NO_FINGER = 2;
    public final int PS_GET_IMG_ERR = 3;
    public final int PS_FP_TOO_DRY = 4;
    public final int PS_FP_TOO_WET = 5;
    public final int PS_FP_DISORDER = 6;
    public final int PS_LITTLE_FEATURE = 7;
    public final int PS_NOT_MATCH = 8;
    public final int PS_NOT_SEARCHED = 9;
    public final int PS_MERGE_ERR = 10;
    public final int PS_ADDRESS_OVER = 11;
    public final int PS_READ_ERR = 12;
    public final int PS_UP_TEMP_ERR = 13;
    public final int PS_RECV_ERR = 14;
    public final int PS_UP_IMG_ERR = 15;
    public final int PS_DEL_TEMP_ERR = 16;
    public final int PS_CLEAR_TEMP_ERR = 17;
    public final int PS_SLEEP_ERR = 18;
    public final int PS_INVALID_PASSWORD = 19;
    public final int PS_RESET_ERR = 20;
    public final int PS_INVALID_IMAGE = 21;
    public final int PS_HANGOVER_UNREMOVE = 23;
    public final int CHAR_BUFFER_A = 1;
    public final int CHAR_BUFFER_B = 2;
    public final int MODEL_BUFFER = 3;
    private Context context;
    private Activity activity;
    private SerialPort mSerialPort;
    private ZA_finger za_finger;
    private ZAandroid za;
    private final int GPIO_141 = 141;
    public int ZAVendorId = 8457;
    public int ZAProductId = 30264;
    private int def_iCom = 3;
    private int def_iBaud = 6;
    private int usborcomtype = 0;
    private int defDeviceType = 2;
    private int DEV_ADDR = -1;
    private IUsbConnState connectState;
    private UsbController mUsbController;
    String tag = "FingerHelper";

    public FingerHelper(Activity activity, IUsbConnState connectState) {
        this.activity = activity;
        this.connectState = connectState;
        this.mSerialPort = new SerialPort();
        this.za = new ZAandroid();
        this.za_finger = new ZA_finger();
        this.za.getClass();
        this.ZAVendorId = 8457;
        this.za.getClass();
        this.ZAProductId = 30264;
        this.mUsbController = new UsbController(this.activity, this.connectState, this.ZAVendorId, this.ZAProductId);
    }

    public int init() {
        int statues = 0;//false
        this.mSerialPort.setGPIOlow(141);
        this.mSerialPort.rfidPoweron();

        try {
            Thread.sleep(2000L);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

        this.za_finger.finger_power_on();
        this.mUsbController.init();
        return 0;
    }

    public int connectFingerDev() {
        int fd = 0;//false
        int status = 0;
         fd = this.mUsbController.usbfd;
        if (fd > 0) {
            status = this.za.ZAZOpenDeviceEx(fd, this.defDeviceType, this.def_iCom, this.def_iBaud, 0, 0);
        }

        return status;
    }

    public int setImageSize(int imagesize) {
        return this.za.ZAZSetImageSize(imagesize);
    }

    public int getImage() {
        return this.za.ZAZGetImage(this.DEV_ADDR);
    }

    public int genChar(int iBufferID) {
        return this.za.ZAZGenChar(this.DEV_ADDR, iBufferID);
    }

    public int match(int[] iScore) {
        return this.za.ZAZMatch(this.DEV_ADDR, iScore);
    }

    public int search(int iBufferID, int iStartPage, int iPageNum, int[] iMbAddress) {
        return this.za.ZAZSearch(this.DEV_ADDR, iBufferID, iStartPage, iPageNum, iMbAddress);
    }

    public int regTemplate() {
        return this.za.ZAZRegModule(this.DEV_ADDR);
    }

    public int storeTemplate(int iBufferID, int iPageID) {
        return this.za.ZAZStoreChar(this.DEV_ADDR, iBufferID, iPageID);
    }

    public int loadChar2Buffer(int iBufferID, int iPageID) {
        return this.za.ZAZLoadChar(this.DEV_ADDR, iBufferID, iPageID);
    }

    public int setCharLen(int charLen) {
        return this.za.ZAZSetCharLen(charLen);
    }

    public int upCharFromBufferID(int iBufferID, byte[] pTemplet, int[] iTempletLength) {
        return this.za.ZAZUpChar(this.DEV_ADDR, iBufferID, pTemplet, iTempletLength);
    }

    public int downChar2Buffer(int iBufferID, byte[] pTemplet, int iTempletLength) {
        return this.za.ZAZDownChar(this.DEV_ADDR, iBufferID, pTemplet, iTempletLength);
    }

    public int uploadImage(byte[] pImageData, int[] iTempletLength) {
        return this.za.ZAZUpImage(this.DEV_ADDR, pImageData, iTempletLength);
    }

    public int imageData2BMP(byte[] pImgData, String str) {
        return this.za.ZAZImgData2BMP(pImgData, str);
    }

    public int getImgDataFromBMP(String str, byte[] pImageData, int[] pnImageLen) {
        return this.za.ZAZGetImgDataFromBMP(str, pImageData, pnImageLen);
    }

    public int deleteChar(int iStartPageID, int nDelPageNum) {
        return this.za.ZAZDelChar(this.DEV_ADDR, iStartPageID, nDelPageNum);
    }

    public int getTemplateNum(int[] iMbNum) {
        return this.za.ZAZTemplateNum(this.DEV_ADDR, iMbNum);
    }

    public int emptyChar() {
        return this.za.ZAZEmpty(this.DEV_ADDR);
    }

    public void close() {
        this.za.ZAZCloseDeviceEx();
        this.za_finger.finger_power_off();
        this.mSerialPort.setGPIOhigh(141);
        this.mSerialPort.rfid_poweroff();
    }
}
