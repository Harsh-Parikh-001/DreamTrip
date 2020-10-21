package com.DreamTrip.UI;

public class Hotel {
    public static int no = 9;
    public static String[] hotel_name = new String[no];
    public static String[] hotel_image = new String[no];

    Hotel() {
        for(int i=0;i<no;i++)
            hotel_name[i]="OG Hotel";
    }
}
