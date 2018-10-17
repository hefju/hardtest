package UtilAndroid;

import android.content.Context;
import android.media.MediaPlayer;

import com.azure.hardtest.R;

/**
 * Created by Administrator on 2015/4/27.
 */
public class YLMediaPlayer {

    private MediaPlayer mPlayer;
    private Context context;

    public YLMediaPlayer (){}
    public YLMediaPlayer(Context context) {
        this.context = context;
    }

    public void SuccessOrFail(final boolean trueorfase){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mPlayer = new MediaPlayer();
                    if (trueorfase) {
                        mPlayer = MediaPlayer.create(context, R.raw.msg);
                        if (mPlayer.isPlaying()) {
                            return;
                        }
                    } else {
                        mPlayer.setDataSource("/system/media/audio/notifications/Proxima.ogg");  //选用系统声音文件
                        mPlayer.prepare();
                    }
                    mPlayer.start();
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }

    public void SuccessOrFailMidia(final String mediavoice, final Context YLcontext){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mPlayer = new MediaPlayer();
                    if (mediavoice.equals("success")) {
                        mPlayer = MediaPlayer.create(YLcontext, R.raw.msg);
                        if (mPlayer.isPlaying()) {
                            return;
                        }
                    } else {
                        mPlayer.setDataSource("/system/media/audio/notifications/Proxima.ogg");  //选用系统声音文件
                        mPlayer.prepare();
                    }
                    mPlayer.start();
                    Thread.sleep(300);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        thread.start();
    }
}