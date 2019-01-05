package android.sisgo.utils;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.StringTokenizer;

public class Konversi {
    public static String convert(String formatMoney) {
        int reformat = Integer.parseInt(formatMoney);
        String money = NumberFormat.getNumberInstance(Locale.ENGLISH).format(reformat);
        StringTokenizer token = new StringTokenizer(money, ".");
        money = token.nextToken();
        money = money.replace(",",".");
        return "Rp. " + money + ",00";
    }

    public static int deconvert(String formatMoney) {
        char[] arr = formatMoney.toCharArray();
        String backStr = "";
        int i = 0;
        while(i < formatMoney.length()){
            if(arr[i] >= 48 && arr[i] <= 57){
                backStr = backStr+arr[i];
            }
            if(arr[i] == 44){
                i += 2;
            }
            i++;
        }
        int reformat = Integer.parseInt(backStr);
        return reformat;
    }
}
