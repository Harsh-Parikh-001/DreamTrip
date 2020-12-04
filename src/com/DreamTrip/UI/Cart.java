package com.DreamTrip.UI;

public class Cart
{
    public static int no = 9;
    public static String[] item_name = new String[no];
    public static String[] item_image = new String[no];
    public static String[] dest = new  String[no];
    public static String[] item_details = new String[no];
    Cart()
    {
        for(int i=0; i<no; i++)
        {
            item_name[i] = "OG Trains";
            item_image[i] = "../DreamTrip/src/com/DreamTrip/UI/Hotel1.jpeg";
            dest[i] = "Dubai";
            item_details[i] = "Spa, Gym";
        }
    }
}
