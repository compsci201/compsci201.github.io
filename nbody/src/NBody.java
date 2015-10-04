import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

import princeton.StdAudio;
import princeton.StdDraw;

public class NBody {
    public static final double G = 6.67E-11;
    
    /**
     * Displays file chooser for browsing in the project directory. and opens
     * the selected file
     *
     * @return a new Scanner that produces values scanned from the selected
     *         file. null if file could not be opened or was not selected
     */
    public static Scanner openFileFromDialog() {
        Scanner scan = null;
        System.out.println("Opening file dialog.");
        JFileChooser openChooser = new JFileChooser(System.getProperties()
                                                    .getProperty("user.dir"));
        int retval = openChooser.showOpenDialog(null);
        if (retval == JFileChooser.APPROVE_OPTION) {
            File file = openChooser.getSelectedFile();
            try {
                scan = new Scanner(file);
                System.out.println("Opening: " + file.getName() + ".");
            }
            catch (FileNotFoundException e) {
                System.out.println("Could not open selected file.");
                e.printStackTrace();
            }
        }
        else {
            System.out.println("File open canceled.");            
        }
        return scan;
    }

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
    public double distance(double x1, double y1, double x2, double y2) {          //TODO: Complete distance
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
    public double[][] positions(Scanner info, int totalTime,
                                int timeStep) {
        //TODO: Complete positions
        double[][] output = new double[2][0]; //Replace 0 with the number of
                                              //bodies, read from the file

        return output;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner info;
        int time, dt;
        if (args.length == 3){
            info = new Scanner(new File("data/"+args[0]));
            time = Integer.parseInt(args[1]);
            dt = Integer.parseInt(args[2]);
        }
        else{
            info = openFileFromDialog();
            time = 10000000;
            dt = 25000;
        }

        //StdAudio.play("data/2001.mid");
        NBody myNBody = new NBody();
        myNBody.positions(info, time, dt);
        StdDraw.clear();
        StdAudio.close();
    }
}

