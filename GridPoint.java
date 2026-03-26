/**
 * A point on the grid. 
 * 
 * @author Joanna Klukowska
 */
public class GridPoint {
    private int row;
    private int col;

    /**
     * Constructs a grid point with the specified row and column.
     * 
     * @param row the row
     * @param col the column 
     */
    public GridPoint(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the row of this grid point.
     * 
     * @return the row of this grid point
     */
    public int row() {
        return row;
    }

    /**
     * Returns the column of this grid point.
     * 
     * @return the column of this grid point
     */
    public int col() {
        return col;
    }
    /**
     * Determines whether this grid point is equal to another object.
     * Returns true if and only if the other object is a GridPoint 
     * with the same row and column as this grid point. 
     * @param obj the other object to be compared to this grid point
     * @return true if the other object is a GridPoint with the same row and column
     * as this grid point, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GridPoint))
            return false;

        GridPoint other = (GridPoint) obj;
        return row == other.row && col == other.col;
    }
    /**
     * Returns a string representation of this grid point.
     * The string has the form { row, col }.
     */
    @Override
    public String toString() {
        return "{ " + row + ", " + col + " }";
    }
    /**
     * Returns a new grid point that is one row above this grid point.
     * 
     * @return a new grid point that is one row above this grid point 
      */
    public GridPoint up() {
        return new GridPoint(row - 1, col);
    }

    /**
     * Returns a new grid point that is one row below this grid point.
     * 
     * @return a new grid point that is one row below this grid point
     */
    public GridPoint down() {
        return new GridPoint(row + 1, col);
    }

    /**
     * Returns a new grid point that is one column to the left of this grid point.
     * 
     * @return a new grid point that is one column to the left of this grid point
     */
    public GridPoint left() {
        return new GridPoint(row, col - 1);
    }
    
    /**
     * Returns a new grid point that is one column to the right of this grid point.
     * 
     * @return a new grid point that is one column to the right of this grid point
     */
    public GridPoint right() {
        return new GridPoint(row, col + 1);
    }
    
}
