package com.DreamTrip.UI;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreenPreLoader extends Preloader
{

	private Stage preloaderStage;
	private Scene scene;

	public SplashScreenPreLoader() {

	}

	@Override
	public void init() throws Exception
	{
		Parent root1 = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
		scene = new Scene(root1);
	}

	@Override
	public void start(Stage primaryStage)
	{
		this.preloaderStage = primaryStage;
		preloaderStage.setScene(scene);
		preloaderStage.initStyle(StageStyle.UNDECORATED);
		preloaderStage.show();
	}

	@Override
	public void handleApplicationNotification(Preloader.PreloaderNotification info)
	{
		if (info instanceof ProgressNotification)
		{
			SplashScreenController.label.setText("Loading "+((ProgressNotification) info).getProgress()*100 + "%");
			SplashScreenController.statProgressBar.setProgress(((ProgressNotification) info).getProgress());
		}
	}

	@Override
	public void handleStateChangeNotification(Preloader.StateChangeNotification info)
	{
		StateChangeNotification.Type type = info.getType();
		switch (type)
		{
			case BEFORE_LOAD:
				break;
			case BEFORE_INIT:
				break;
			case BEFORE_START:
				System.out.println("BEFORE_START");
				preloaderStage.hide();
				break;
		}


	}
}