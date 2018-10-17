//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.za.finger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ZA_finger {
    final String HUB_RST_PATCH = "/sys/zhwpower/zhw_hubrest";
    final String CARD_POWER_PATCH = "/sys/zhwpower/zhw_power_card";
    final String FINGER_POWER_PATCH = "/sys/zhwpower/zhw_power_finger";
    final String DOOR1_POWER_PATCH = "/sys/zhwpower/zhw_power_door1";
    final String DOOR2_POWER_PATCH = "/sys/zhwpower/zhw_power_door2";

    public ZA_finger() {
    }

    public int IO_Switch(String cardpowerPath, int on) {
        try {
            File powerFile = new File(cardpowerPath);
            if (!powerFile.exists()) {
                return 0;
            } else {
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter(powerFile));
                bufWriter.write(Integer.toString(on));
                bufWriter.close();
                return 1;
            }
        } catch (IOException var5) {
            var5.printStackTrace();
            return 0;
        }
    }

    public static String SerialPortnum() {
        String cardpowerPath = "/sys/zhwpower/zhw_id";
        String str = null;

        try {
            File powerFile = new File(cardpowerPath);
            if (!powerFile.exists()) {
                return null;
            } else {
                BufferedReader bufReader = new BufferedReader(new FileReader(powerFile));
                str = bufReader.readLine();
                bufReader.close();
                if (str.equals("0")) {
                    str = "/dev/ttyS0";
                } else if (str.equals("1")) {
                    str = "/dev/ttyS1";
                } else if (str.equals("2")) {
                    str = "/dev/ttyS2";
                } else if (str.equals("3")) {
                    str = "/dev/ttyS3";
                } else if (str.equals("4")) {
                    str = "/dev/ttyS4";
                } else {
                    str = "/dev/ttyS0";
                }

                return str;
            }
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public int card_power_on() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_card", 1);
    }

    public int card_power_off() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_card", 0);
    }

    public int finger_power_on() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_finger", 1);
    }

    public int finger_power_off() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_finger", 0);
    }

    public int hub_rest(int Ms) {
        this.IO_Switch("/sys/zhwpower/zhw_hubrest", 0);

        try {
            Thread.sleep((long)Ms);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

        this.IO_Switch("/sys/zhwpower/zhw_hubrest", 1);
        return 0;
    }

    public int door1_power_on() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_door1", 1);
    }

    public int door1_power_off() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_door1", 0);
    }

    public int door2_power_on() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_door2", 1);
    }

    public int door2_power_off() {
        return this.IO_Switch("/sys/zhwpower/zhw_power_door2", 0);
    }
}
