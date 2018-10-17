//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.pda.hf;

public class ISO15693PICC {
    private byte flag;
    private byte infoFlag;
    private byte dsfid;
    private byte[] uid;
    private byte afi;
    private byte blockCount;
    private byte blockLen;
    private byte ICReference;

    public ISO15693PICC() {
    }

    public byte getFlag() {
        return this.flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public byte getInfoFlag() {
        return this.infoFlag;
    }

    public void setInfoFlag(byte infoFlag) {
        this.infoFlag = infoFlag;
    }

    public byte getDsfid() {
        return this.dsfid;
    }

    public void setDsfid(byte dsfid) {
        this.dsfid = dsfid;
    }

    public byte[] getUid() {
        return this.uid;
    }

    public void setUid(byte[] uid) {
        this.uid = uid;
    }

    public byte getAfi() {
        return this.afi;
    }

    public void setAfi(byte afi) {
        this.afi = afi;
    }

    public byte getBlockCount() {
        return this.blockCount;
    }

    public void setBlockCount(byte blockCount) {
        this.blockCount = blockCount;
    }

    public byte getBlockLen() {
        return this.blockLen;
    }

    public void setBlockLen(byte blockLen) {
        this.blockLen = blockLen;
    }

    public byte getICReference() {
        return this.ICReference;
    }

    public void setICReference(byte iCReference) {
        this.ICReference = iCReference;
    }
}
