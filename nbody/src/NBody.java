import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import princeton.StdAudio;
import princeton.StdDraw;

public class NBody extends Application {
	
    public static final double G = 6.67E-11;

    /**
     * returns Euclidean distance between (x1, y1) and (x2, y2)
     *
     * @param x1
     *            x-coordinate of point 1
     * @param y1
     *            y-coordinate of point 1
     * @param x2
     *            x-coordinate of point 2
     * @param y2
     *            y-coordinate of point 2
     * @return Euclidean distance between (x1, y1) and (x2, y2)
     */
    public double distance(double x1, double y1, double x2, double y2) {
    	//TODO: Complete distance
        return 0;
    }
    
    
    /**
     * return the magnitude of the gravitational force between two bodies of
     * mass m1 and m2 that are distance r apart
     *
     * @param m1
     *            mass of body 1 in kg
     * @param m2
     *            mass of body 2 in kg
     * @param r
     *            distance in m
     * @return force between m1 and m2 that are distance r apart
     */
    public double force(double m1, double m2, double r) {
        //TODO: Complete force
        return 0;
    }


    /**
     * Returns the x positions and y positions of bodies
     * @param totalTime The total amount of universe time to run for
     * @param timeStep The value of delta t in the equations to calculate position
     * @param info The scanner with info about the initial conditions of the
     * bodies
     * @return an array whose first element is the x positions of the bodies,
     * and whose second element is the y positions of the bodies at time
     * t = totalTime
     */
    public double[][] positions(Scanner info, int totalTime, int timeStep) {
        //TODO: Complete positions
        double[][] output = new double[0][2]; //Replace 0 with the number of
                                              //bodies, read from the file

        return output;
    }

    public static void main(String[] args) {
    	launch(args);
    }
    
    public void start(Stage stage) {
    	Scanner info = openFile();
    	int time = 10000000;
    	int dt = 25000;
    	
    	if (info != null) {
    		//StdAudio.play("data/2001.mid");
	        NBody myNBody = new NBody();
	        double[][] results = myNBody.positions(info, time, dt);
	        for(int i = 0; i < results.length; i++) {
	            for(int j = 0; j < results[i].length; j++) {
	                System.out.print(results[i][j]+" ");
	            }
	            System.out.println();
	        }
	        StdDraw.clear();
	        StdAudio.close();
    	}
    }
    
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     *
     * @return a new Scanner that produces values scanned from the selected
     *         file. null if file could not be opened or was not selected
     */
    public static Scanner openFile() {
        System.out.println("Opening file chooser");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Configuration File");
        chooser.setInitialDirectory(new File("data"));
        File file = chooser.showOpenDialog(new Stage());
        
        if (file != null) {
            try {
                System.out.println("Opening: " + file.getName() + "");
                return new Scanner(file);
            }
            catch (FileNotFoundException fnf) {
                throw new RuntimeException(fnf);
            }
        }
        else {
            System.out.println("File open canceled");    
            return null;
        }
    }
}