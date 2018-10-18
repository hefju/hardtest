package com.azure.hardtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import UtilAndroid.Util;
import cn.pda.scan.ScanThread;

public class MainActivity extends ActivityScaner {
    private static String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitUI();
        Util.initSoundPool(this);//注册声音
    }



    private EditText txtcontent;

    private void InitUI() {
        txtcontent= findViewById(R.id.txtcontent);
        Button btnhw= findViewById(R.id.btnhw);
        Button btnhf= findViewById(R.id.btnhf);
        Button btnfp= findViewById(R.id.btnfp);
        Button btnfpSet=findViewById(R.id.btnfpSet);
        Button btnfpFind=findViewById(R.id.btnfpFind);
        Button btnfpClose=findViewById(R.id.btnfpClose);
        btnhw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(!IsInfrInit) {
                    boolean success = InfrInit();
                    if(!success){
                        ShowMsg("红外初始化失败.");
                    }
               // }
                Toast.makeText(MainActivity.this,"红外", Toast.LENGTH_SHORT).show();
                txtcontent.setText("红外红外红外红外红外红外红外红外");
            }
        });
        btnhf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // if(!IsHfInit) {
                    boolean success = HfInit();
                    if(!success){
                        ShowMsg("HF初始化失败.");
                    }
               // }
                Toast.makeText(MainActivity.this,"HF", Toast.LENGTH_SHORT).show();
                txtcontent.setText("HFHFHFHFHFHFHFHFHFHFHFHFHFHF");
            }
        });
        //启动指纹
        btnfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!IsFpInit) {
                    boolean success = FpInit();
                    if(!success){
                        ShowMsg("指纹初始化失败.");
                    }
                }
                Toast.makeText(MainActivity.this,"指纹", Toast.LENGTH_SHORT).show();
                txtcontent.setText("指纹指纹指纹指纹指纹指纹指纹指纹");
            }
        });
        //登记指纹
        btnfpSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = System.currentTimeMillis() ;
                endTime = startTime ;
                fpCharBuffer = mFingerHelper.CHAR_BUFFER_A ;
                mHandler.postDelayed(enrollTask, 0);
            }
        });
        //查找指纹
        btnfpFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = System.currentTimeMillis() ;
                endTime = startTime ;
                //run match finger char task
                mHandler.postDelayed(searchTask, 0);
            }
        });
        //关闭指纹
        btnfpClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFingerHelper.close();
            }
        });

        mHandler = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == ScanThread.SCAN) {
                    String data = msg.getData().getString("data");
                    Log.e(TAG, "data = " + data);
//				Toast.makeText(getApplicationContext(), data, 0).show();
                    // sortAndadd(listBarcode, data);
                    //   addListView();
                    txtcontent.setText(data);
                    Util.play(1, 0);
                }else if(msg.what== MSG_HF){
                    String uid = msg.getData().getString("uid");
                    String cardType = msg.getData().getString("cardType");
                    Util.play(1, 0 );
                    txtcontent.setText(uid+"---"+cardType);
                }else if(msg.what== MSG_FP){
                    String fpmsg = msg.getData().getString("fpmsg");
                   // Util.play(1, 0 );
                    txtcontent.setText(fpmsg);
                }

            };
        };
    }

//    //泄露就泄露了, 目前也找不到什么办法
//    private final Handler mHandler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            if (msg.what == ScanThread.SCAN) {
//                String data = msg.getData().getString("data");
//                Log.e(TAG, "data = " + data);
////				Toast.makeText(getApplicationContext(), data, 0).show();
//               // sortAndadd(listBarcode, data);
//             //   addListView();
//              txtcontent.setText(data);
//                Util.play(1, 0);
//            }
//        };
//    };


    @Override
    protected void onDestroy() {
        if (scanThread != null) {
            scanThread.interrupt();
            scanThread.close();
        }
        //注销广播接收者
        unregisterReceiver(keyReceiver);

        hfReader.close(portHf);//关闭hf
        mFingerHelper.close();//关闭指纹
        super.onDestroy();
    }


    private  void  ShowMsg(String content){//整合Toast
        Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
    }


}
