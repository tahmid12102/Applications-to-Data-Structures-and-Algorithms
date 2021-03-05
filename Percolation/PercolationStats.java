import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;
import java.lang.Integer;

public class PercolationStats {
    private double [] probabilities;
    private double n;
    private int trials;
    private Percolation perc;
    //perform independent trials on an n=by=b grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("Illegal Argument: Dimension or # of trials <= 0");
        }

        this.n = n;
        this.trials = trials;
        probabilities = new double [trials];
        for (int i = 0; i < trials; i++){
            perc = new Percolation(n);

            while (perc.percolates() == false) {
                int p = StdRandom.uniform(n) + 1;
                int q = StdRandom.uniform(n) + 1;
                perc.open(p, q);
            }
            probabilities[i] = perc.numberOfOpenSites()/(this.n*this.n);
        }
    }

    public double mean(){
        //return StdStats.mean(probabilities);
        return StdStats.mean(probabilities);
    }

    public double stddev(){
        return StdStats.stddev(probabilities);
    }

    public double confidenceLo(){ return this.mean() - ((1.96*this.stddev())/Math.sqrt(trials)); }

    public double confidenceHi(){
        return this.mean() + ((1.96*this.stddev())/Math.sqrt(trials));
    }

    public static void main (String[] args){
        PercolationStats perc = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
        System.out.println("mean                    = " + perc.mean());
        System.out.println("stddev                  = " + perc.stddev());
        System.out.println("95% confidence interval = [" + perc.confidenceLo() + ", " + perc.confidenceHi() + "]");
    }
}
