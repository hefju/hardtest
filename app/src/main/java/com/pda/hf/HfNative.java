//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.pda.hf;

public class HfNative {
    static {
        System.loadLibrary("devapi");
        System.loadLibrary("fxjni");
    }

    public HfNative() {
    }

    public native int open(int var1, int var2, int var3);

    public native int close(int var1);

    public native int findM1Card(byte[] var1);

    public native int authM1(int var1, int var2, byte[] var3, int var4, byte[] var5, int var6);

    public native int readM1Block(int var1, byte[] var2);

    public native int writeM1Block(int var1, byte[] var2, int var3);

    public native int find15693(byte[] var1);

    public native int get15693PICC(byte[] var1, int var2, byte[] var3);

    public native int readBlock15693(byte[] var1, int var2, int var3, byte[] var4);

    public native int writeBlock15693(byte[] var1, int var2, int var3, byte[] var4, int var5, byte[] var6);

    public native int getIDCard(byte[] var1);
}
