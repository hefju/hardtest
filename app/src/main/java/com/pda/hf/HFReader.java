//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.pda.hf;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HFReader {
    public static int POWER_3_3V = 0;
    public static int POWER_PSAM = 1;
    public static int POWER_RFID = 2;
    public static int POWER_5V = 3;
    public static int POWER_SCAN = 4;
    public static int AUTH_A = 0;
    public static int AUTH_B = 1;
    private HfNative hf = new HfNative();

    public HFReader(int port, int baudrate, int powerCode) {
        this.hf.open(port, baudrate, powerCode);
    }

    public void close(int port) {
        if (this.hf != null) {
            this.hf.close(port);
        }

    }

    public byte[] search14443Acard() {
        byte[] uid = null;
        byte[] buffer = new byte[32];
        int bufferLen = 0;
        byte Null = 1;
        if (this.hf != null) {
             bufferLen = this.hf.findM1Card(buffer);
            if (bufferLen > 0) {
                uid = new byte[bufferLen];
                System.arraycopy(buffer, 0, uid, 0, bufferLen);
            }
        }

        return uid;
    }

    public boolean authM1(int keyType, int sector, byte[] keys, byte[] uid) {
        if (uid != null && keys != null) {
            boolean flag = false;
            int st = 0;
            if (this.hf != null) {
                 st = this.hf.authM1(keyType, sector, keys, keys.length, uid, uid.length);
                if (st >= 0) {
                    flag = true;
                }
            }

            return flag;
        } else {
            return false;
        }
    }

    public byte[] readM1Block(int block) {
        byte[] data = null;
        byte[] blockData = new byte[64];
        int dataLen = 0;
        if (this.hf != null) {
             dataLen = this.hf.readM1Block(block, blockData);
            if (dataLen > 0) {
                data = new byte[dataLen];
                System.arraycopy(blockData, 0, data, 0, dataLen);
            }
        }

        return data;
    }

    public boolean writeM1Block(int block, byte[] writeData) {
        if (writeData == null) {
            return false;
        } else {
            boolean flag = false;
            int st = 0;
            if (this.hf != null) {
                 st = this.hf.writeM1Block(block, writeData, writeData.length);
                if (st >= 0) {
                    flag = true;
                }
            }

            return flag;
        }
    }

    public List<ISO15693CardInfo> search15693Card() {
        List<ISO15693CardInfo> list = new ArrayList();
        byte[] buffer = new byte[256];
        int st = 0;
        int cardCount = 0;
        //int index = true;
        if (this.hf != null) {
             st = this.hf.find15693(buffer);
            if (st > 0) {
                 cardCount = buffer[0] & 255;
                ISO15693CardInfo info = new ISO15693CardInfo();
                byte[] uid = new byte[8];
                info.setFlags(buffer[0]);
                info.setDsfid(buffer[1]);
                System.arraycopy(buffer, 2, uid, 0, 8);
                info.setUid(uid);
                list.add(info);
            }
        }

        return list;
    }

    public ISO15693PICC get15693PICC(byte[] uid, int flag) {
        if (uid == null) {
            return null;
        } else {
            ISO15693PICC picc = null;
            byte[] buffer = new byte[64];
            int st = 0;
            if (this.hf != null) {
                 st = this.hf.get15693PICC(uid, flag, buffer);
                if (st > 0) {
                    picc = new ISO15693PICC();
                    picc.setFlag(buffer[10]);
                    picc.setInfoFlag((byte)0);
                    picc.setUid(uid);
                    picc.setDsfid(buffer[11]);
                    picc.setAfi(buffer[0]);
                    picc.setBlockCount(buffer[12]);
                    picc.setBlockLen((byte)(buffer[13] + 1));
                    picc.setICReference(buffer[14]);
                }
            }

            return picc;
        }
    }

    public byte[] read15693Block(byte[] uid, int flag, int block) {
        if (uid == null) {
            return null;
        } else {
            byte[] data = null;
            byte[] buffer = new byte[64];
            int st = 0;
            if (this.hf != null) {
                 st = this.hf.readBlock15693(uid, flag, block, buffer);
                if (st > 1) {
                    Log.e("buffer", HfConvert.Bytes2HexString(buffer, st));
                    data = new byte[st - 1];
                    System.arraycopy(buffer, 1, data, 0, st - 1);
                }
            }

            return data;
        }
    }

    public boolean write15693Block(byte[] uid, int flag, int block, byte[] wData) {
        if (uid != null && wData != null) {
            boolean wFlag = false;
            int st = 0;
            byte[] buffer = new byte[64];
            if (this.hf != null) {
                 st = this.hf.writeBlock15693(uid, flag, block, wData, wData.length, buffer);
                Log.e("", "write st = " + st);
                if (st > 0) {
                    wFlag = true;
                }
            }

            return wFlag;
        } else {
            return false;
        }
    }

    public int getidCard(byte[] uid) {
        return this.hf.getIDCard(uid);
    }
}
