package com.DreamTrip.UI;

public class Hotel {
	public static int no = 9;
	public static String[] hotel_name = new String[no];
	public static String[] hotel_image = new String[no];
	public static float[] hotel_rating = new float[no];
	public static String[] no_of_re = new String[no];
	public static String[] dest = new  String[no];
	public static String[] descrip = new String[no];

	Hotel()
	{
		for(int i=0; i<no; i++)
		{
			hotel_name[i] = "OG Hotel";
			hotel_image[i] = "../DreamTrip/src/com/DreamTrip/UI/Hotel1.jpeg";
			hotel_rating[i] = 4.7f;
			no_of_re[i] = "(20,000)";
			dest[i] = "Dubai";
			descrip[i] = "Spa, Gym";
		}
	}
}
