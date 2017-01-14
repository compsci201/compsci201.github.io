
/**
 *	Launches the user interface for the Huffman assignment.
 *
 *	@author Brian Lavallee
 *	@since 6 November 2015
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HuffMain extends Application {

	public static final String PROCESSOR = "HuffProcessor";

	public static final double WIDTH = 600;
	public static final double HEIGHT = 600;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage window) {
		window.setWidth(WIDTH);
		window.setHeight(HEIGHT);
		window.setResizable(false);
		window.setTitle("Huffman");

		HuffViewer viewer = new HuffViewer(PROCESSOR);
		window.setScene(new Scene(viewer.createLayout(WIDTH, HEIGHT), WIDTH, HEIGHT));

		window.show();
	}
}