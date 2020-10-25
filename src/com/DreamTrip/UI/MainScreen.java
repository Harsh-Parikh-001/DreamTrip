package com.DreamTrip.UI;
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.stage.Stage;

public class MainScreen extends Application
{
	private static final int COUNT_LIMIT = 10;
	@Override
	public void start(Stage primaryStage) throws Exception
	{
	}
	@Override
	public void init() throws Exception
	{
		for (int i = 1; i <= COUNT_LIMIT; i++)
		{
			double progress =(double) i/10;
			LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
			Thread.sleep(400);
		}
	}
	public static void main(String[] args)
	{
		LauncherImpl.launchApplication(MainScreen.class, SplashScreenPreLoader.class, args);
	}
}