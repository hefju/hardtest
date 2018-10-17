package com.azure.hardtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.pda.hf.HFReader;
import com.pda.hf.HfConvert;
import com.pda.hf.ISO15693CardInfo;

import java.util.List;

import UtilAndroid.Util;
import cn.pda.scan.ScanThread;

public abstract class ActivityScaner extends AppCompatActivity {
    protected ScanThread scanThread;//红外扫描线程
    protected KeyReceiver keyReceiver; //键盘按键

    protected  Handler mHandler=null ;

    protected  boolean IsInfrInit=false;//红外初始化了没有
    protected  boolean IsHfInit=false; //hf初始化了没有
    protected  boolean IsFpInit=false; //fp初始化了没有
    protected boolean InfrInit() {
        if(IsInfrInit){
            mIsPressed = true;
            scanThread.scan();
            return true;
        }
        try {
            scanThread = new ScanThread(mHandler);
        } catch (Exception e) {
            // 出现异常
            return false;
            // e.printStackTrace();
        }
        scanThread.start();
        //init sound

        //注册按键广播接收者
        keyReceiver = new KeyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.rfid.FUN_KEY");
        filter.addAction("android.intent.action.FUN_KEY");
        registerReceiver(keyReceiver , filter);
        IsInfrInit=true;//已经进行初始化了.
        return  true;
    }
    private HFReader hfReader ;//hf读卡

    private  int portHf = 14 ;
    private int powerHf = HFReader.POWER_PSAM ;
    protected final int MSG_HF = 1101 ;
    protected boolean HfInit() {
        byte[] uid14443 = null;
        List<ISO15693CardInfo> listCard15693 = null;
        byte[] uid15693 = null;
        byte[] uidZhCNid = new byte[16];
        try {
            if (hfReader == null) {
                hfReader = new HFReader(portHf, 115200, powerHf);
            } else {
                uid14443 = hfReader.search14443Acard();
                if (uid14443 != null) {
                    sendMSG_HF(HfConvert.Bytes2HexString(uid14443, uid14443.length), "14443A", MSG_HF);
                } else {
                    //15693
                    listCard15693 = hfReader.search15693Card();
                    if (listCard15693 != null && !listCard15693.isEmpty()) {
                        for (ISO15693CardInfo card : listCard15693) {
                            uid15693 = card.getUid();
                            sendMSG_HF(HfConvert.Bytes2HexString(uid15693, uid15693.length), "15693", MSG_HF);
                        }
                    } else {
                        //zh-CN ID card
                        int ret = hfReader.getidCard(uidZhCNid);
                        if (ret > 0) {
                            sendMSG_HF(HfConvert.Bytes2HexString(uidZhCNid, ret), "zh-CN ID", MSG_HF);
                        }
                    }
                }
            }
            IsHfInit = true;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    //send the result to handler
    private void sendMSG_HF(String cardUid, String cardType, int msgCode) {
        //Log.e("jutest", "cardUid = " + cardUid);
        Bundle bundle = new Bundle();
        bundle.putString("uid", cardUid);
        bundle.putString("cardType", cardType);
        Message msg = new Message() ;
        msg.setData(bundle);
        msg.what = msgCode ;
        mHandler.sendMessage(msg);
    }
    protected boolean FpInit() {
        return  false;
    }

    /**
     * 按键广播接收者 用于接受按键广播 触发扫描
     */
    protected boolean mIsPressed = false;
    protected class KeyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int keyCode = intent.getIntExtra("keyCode", 0);
            // 为兼容早期版本机器
            if (keyCode == 0) {
                keyCode = intent.getIntExtra("keycode", 0);
            }
            boolean keyDown = intent.getBooleanExtra("keydown", false);
            if (keyDown && !mIsPressed) {
                // 根据需要在对应的按键的键值中开启扫描,
                switch (keyCode) {
                    case KeyEvent.KEYCODE_F1:

                    case KeyEvent.KEYCODE_F2:

                    case KeyEvent.KEYCODE_F3:

                    case KeyEvent.KEYCODE_F4:

                    case KeyEvent.KEYCODE_F5:

                    default:
                        //开启扫描
                        mIsPressed = true;
                        scanThread.scan();
                        break;
                }
            }else {
                mIsPressed = false;
            }
        }
    }
}
