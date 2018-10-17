//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.za.finger;

public class ZAandroid {
    public final int VendorId0 = 8457;
    public final int ProductId0 = 30264;
    public final int VendorId1 = 8201;
    public final int ProductId1 = 30264;
    public final int PS_OK = 0;
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

    static {
        System.loadLibrary("ZAandroid");
    }

    public ZAandroid() {
    }

    public native String init();

    public native int card_power_on();

    public native int card_power_off(int var1, int var2);

    public native int finger_power_on();

    public native int finger_power_off();

    public native int ZAZOpenDeviceEx(int var1, int var2, int var3, int var4, int var5, int var6);

    public native int ZAZSetImageSize(int var1);

    public native int ZAZCloseDeviceEx();

    public native int ZAZGetImage(int var1);

    public native int ZAZGenChar(int var1, int var2);

    public native int ZAZMatch(int var1, int[] var2);

    public native int ZAZSearch(int var1, int var2, int var3, int var4, int[] var5);

    public native int ZAZRegModule(int var1);

    public native int ZAZStoreChar(int var1, int var2, int var3);

    public native int ZAZLoadChar(int var1, int var2, int var3);

    public native int ZAZSetCharLen(int var1);

    public native int ZAZUpChar(int var1, int var2, byte[] var3, int[] var4);

    public native int ZAZDownChar(int var1, int var2, byte[] var3, int var4);

    public native int ZAZUpImage(int var1, byte[] var2, int[] var3);

    public native int ZAZDownImage(int var1, byte[] var2, int var3);

    public native int ZAZImgData2BMP(byte[] var1, String var2);

    public native int ZAZGetImgDataFromBMP(String var1, byte[] var2, int[] var3);

    public native int ZAZDelChar(int var1, int var2, int var3);

    public native int ZAZEmpty(int var1);

    public native int ZAZReadParTable(int var1, byte[] var2);

    public native int ZAZHighSpeedSearch(int var1, int var2, int var3, int var4, int[] var5);

    public native int ZAZTemplateNum(int var1, int[] var2);

    public native int ZAZSetPwd(int var1, byte[] var2);

    public native int ZAZVfyPwd(int var1, byte[] var2);

    public native int ZAZReadInfo(int var1, int var2, byte[] var3);

    public native int ZAZWriteInfo(int var1, int var2, byte[] var3);

    public native int ZAZSetBaud(int var1, int var2);

    public native int ZAZSetSecurLevel(int var1, int var2);

    public native int ZAZSetPacketSize(int var1, int var2);

    public native int ZAZUpChar2File(int var1, int var2, byte[] var3);

    public native int ZAZDownCharFromFile(int var1, int var2, byte[] var3);

    public native int ZAZGetRandomData(int var1, byte[] var2);

    public native int ZAZSetChipAddr(int var1, byte[] var2);

    public native int ZAZBT_rev(byte[] var1, int var2);
}
