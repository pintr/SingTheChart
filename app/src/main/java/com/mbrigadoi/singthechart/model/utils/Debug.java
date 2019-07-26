package com.mbrigadoi.singthechart.model.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Debug {
    public static void logStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString();
        Log.e("SingTheChart",sStackTrace);
    }
}
