package com.lzx2005.tools;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Lizhengxian on 2017/4/6.
 */
public class StringTools {
    public final static boolean isEmpty(String str){
        if(str==null||str.equals("")){
            return true;
        }
        return false;
    }

    public final static boolean hasEmpty(String... strings){
        for(String string : strings){
            if(isEmpty(string)){
                return true;
            }
        }
        return false;
    }


    public static String getRandom(int point){
        String result = String.valueOf(Math.random());
        String f = "#####0";
        if(point > 0){
            f = "";
            for(int i=0;i<point-1;i++){
                f+="#";
            }
            f+="0";
        }
        BigDecimal rand = new BigDecimal(result);
        BigDecimal one = new BigDecimal(1);
        double d =  rand.divide(one,point,BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat df = new   DecimalFormat(f);
        String t = df.format(d*Math.pow(10,point));
        return t;
    }
}
