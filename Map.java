import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A map of elevations for the flood modeling. It includes 
 * the water sources and the water level to be simulated. 
 * 
 * The map is represented as a 2D array of elevations. The upper-left corner 
 * of the map is at row 0, column 0 (or GridPoint { 0, 0 }. 
 * The water sources are represented as an array of GridPoints. Any point on the
 * map can be used as a water source. 
 * 
 * The water level is a double value that represents the height of the water 
 * to be used in the simulation. 
 * 
 * @author Joanna Klukowska
 * @author Robert Fang 
 */
public class Map {
    private double[][] elevations;
    private double maxElevation;
    private double minElevation;
    private boolean maxMinComputed = false; 
    private GridPoint[] waterSources;
    private double waterHeight;

    /**
     * Creates a map with the specified elevations, water sources and water level.
     * 
     * @param elevations   the elevations of the map
     * @param waterSources the water sources of the map
     * @param waterHeight  the water level 
     * @throws IllegalArgumentException if elevations or waterSources is null
     */
    public Map(double[][] elevations, GridPoint[] waterSources, double waterHeight) 
            throws IllegalArgumentException {
        if(elevations == null || waterSources == null){
            throw new IllegalArgumentException("Elevation and/or water sources cannot be null");
        }
        //set the values of the instance variables
        this.elevations = elevations;
        this.waterSources = waterSources;
        this.waterHeight = waterHeight;
    }

    /**
     * Creates a map from the specified file.
     * 
     * @param mapFile the file from which to create the map
     * @throws FileNotFoundException if mapFile does not exist 
     */
    public Map(File mapFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(mapFile);
        double height = scanner.nextDouble();
        this.waterHeight = height; 
        int numOfSources = scanner.nextInt();
        this.waterSources = new GridPoint[numOfSources]; 
        for (int source = 0; source < numOfSources; source++) {
            int sr = scanner.nextInt();
            int sc = scanner.nextInt();
            waterSources[source] = new GridPoint(sr, sc);
        }
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        elevations = new double[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                elevations[r][c] = scanner.nextDouble();
            }
        }
    }
    
    /**
     * Returns the elevation at a specified grid point on the map.
     * @param p the grid point
     * @return    the elevation at the specified grid point
     * @throws NotOnMapException if the grid point is not on the map
     */
    public double elevation(GridPoint p) throws NotOnMapException {
        int row = p.row();
        int col = p.col();
        if(row >= elevations.length || row < 0 || col >= elevations[0].length || col < 0){
            throw new NotOnMapException("No such point on map");
        }
        //return the element and the specified indices
        return elevations[row][col];
    }

    /**
     * Returns the maximum elevation on the map.
     * 
     * @return the maximum elevation on the map
     */
    public double maxElevation() {
        //if max and min are already found, return max
        if(maxMinComputed){
            return maxElevation;
        }

        double max = elevations[0][0];
        double min = elevations[0][0];

        //use a nested for loop to check which elevation is the highest and lowest
        for(double[] d : elevations){
            for(double i : d){
                if(i < min){
                    min = i;
                }
                if (i > max){
                    max = i;
                }
            }
        }

        //set max and min and declare that they are found
        maxElevation = max;
        minElevation = min;
        maxMinComputed = true;
        return max;
    }

    /**
     * Returns the minimum elevation on the map.
     * 
     * @return the minimum elevation on the map
     */
    public double minElevation() {
        //if max and min are already found, return min
        if(maxMinComputed){
            return minElevation;
        }

        double min = elevations[0][0];
        double max = elevations[0][0];

        //use a nested for loop to check which elevation is the lowest and highest
        for(double[] d : elevations){
            for(double i : d){
                if (i < min){
                    min = i;
                }
                if(i > max){
                    max = i;
                }
            }
        }

        //set max and min and declare that they are found
        minElevation = min;
        maxElevation = max;
        maxMinComputed = true;
        return min;
    }
    
    /**
     * Returns the number of rows in this map.
     * @return the number of rows in this map
     */
    public int numOfRows() {
        return elevations.length;
    }

    /**
     * Returns the number of columns in this map.
     * 
     * @return the number of columns in this map
     */
    public int numOfCols() {
        return elevations[0].length;
    }
    
    /**
     * Returns the water sources on this map.
     * 
     * @return the water sources on this map
     */
    public GridPoint[] waterSources() {
        return waterSources;
    }
    
    /**
     * Returns the water height on this map.
     * 
     * @return the water height on this map
     */
    public double waterHeight() {
        return waterHeight;
    }
    
    /**
     * Determines if a grid point is below a specified height.
     * @param p the grid point
     * @return true if the grid point is below or at the specified height, false otherwise
     * @throws NotOnMapException if the grid point is not on this map
     */
    public boolean belowOrAtHeight(GridPoint p) throws NotOnMapException {
        return elevation(p) <= waterHeight;
    }

    /**
     * Determines if a grid point is above a specified height.
     * @param p the grid point
     * @return true if the grid point is above the specified height, false otherwise
     * @throws NotOnMapException if the grid point is not on this map
     */
    public boolean aboveHeight(GridPoint p ) throws NotOnMapException {
        return elevation(p) > waterHeight;
    }
    
    /**
     * Returns a string representation of this map. The string contains
     * the elevations of the map one row per line in the string. The values
     * in each row are space separated. 
     * 
     * The returned string is aligned by columns as long as the elevation 
     * values contain no more than three digits (including the potential 
     * negative sign) before the decimal point. 
     * 
     * @return a string representation of this map
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elevations.length; i++) {
            for (int j = 0; j < elevations[0].length; j++) {
                sb.append(String.format("%5.1f ", elevations[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
