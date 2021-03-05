import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

public class Percolation {
    private int[][] grid;
    private WeightedQuickUnionUF oneDimGrid;
    private int n;

    private void argExcept(int row, int col, int n){
        if (row < 1 || col < 1 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
    }

    private void unionTopBot(int row, int p) {
        if (row == 0){
            oneDimGrid.union(0,p);
        }
        if (row == n-1){
            oneDimGrid.union((n*n)+1, p);
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = -1; // -1 signifies a blocked site.
            }
        }
        this.n = n;
        oneDimGrid = new WeightedQuickUnionUF((n * n) + 2);

    }

    public void open(int row, int col){
        argExcept(row, col, n);

        row--; col--;

        int above = -1;
        int below = -1;
        int right = -1;
        int left = -1;

        if (grid[row][col] == -1){ // If site blocked, continue, otherwise stop.
            try {
                above = grid[row - 1][col]; //Site above checked site
            } catch (ArrayIndexOutOfBoundsException e){}

            try {
                below = grid[row + 1][col]; //Site below checked site
            } catch (ArrayIndexOutOfBoundsException e){}

            try {
                right = grid[row][col + 1]; //Site right of checked site
            } catch (ArrayIndexOutOfBoundsException e){}

            try {
                left = grid[row][col - 1]; // Site left of checked site
            } catch (ArrayIndexOutOfBoundsException e){}

            int p = (row*n) + col+1;

            if (above == -1 && below == -1 && right == -1 && left == -1){
                grid[row][col] = (row*n) + col; // If all 4 sites adjacent to checked site are closed, open the site unconnected
                this.unionTopBot(row, p);
            } else {
                //Connect the site using WeightedQuickUnionUF to above, below, right, and left if not closed
                int q;
                try{
                    if (above != -1){
                        q = ((row-1)*n) + col+1;
                        oneDimGrid.union(p,q);
                    }
                } catch (ArrayIndexOutOfBoundsException e){}

                try {
                    if (below != -1){
                        q = ((row+1)*n) + col+1;
                        oneDimGrid.union(p,q);
                    }
                } catch (ArrayIndexOutOfBoundsException e){}

                try {
                    if (right != -1){
                        q = (row*n) + (col+1)+1;
                        oneDimGrid.union(p,q);
                    }
                } catch (ArrayIndexOutOfBoundsException e){}

                try {
                    if (left != -1){
                        q = (row*n) + (col-1)+1;
                        oneDimGrid.union(p,q);
                    }
                } catch (ArrayIndexOutOfBoundsException e){}

                this.unionTopBot(row, p);

                grid[row][col] = oneDimGrid.find(p);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        argExcept(row, col, n);

        row--; col--;

        if (grid[row][col] != -1) {
            return true;
        } else {
            return false;
        }
    }

    //is the site (row, col) full?
    public boolean isFull(int row, int col) {
        argExcept(row, col, n);

        row--; col--;

        if (this.isOpen(row+1, col+1) == false){
            return false;
        } else {
            if (oneDimGrid.find(0) == oneDimGrid.find((row * this.n) + col + 1)) {
                return true;
            } else {
                return false;
            }
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        int openSites = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != -1){
                    openSites++;
                }
            }
        }
        return openSites;
    }

    public boolean percolates(){

        if (oneDimGrid.find(0) == oneDimGrid.find((n*n)+1)){
            return true;
        } else {
            return false;
        }

    }

    public static void main (String[] args) { }
}