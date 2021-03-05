## Abstract

This program models a percolation system by using an abstract n-by-n grid of sites. The system *percolates* if one site at the top row is continuously connected to a site at the bottom row. The abstract model can be used to describe many real world systems. An example of such a system are composite materials compromised of insulating and metallic materials. Such a composite material would be considered a conductor if and only if the system *percolates,* where the metallic material is an *open* site in the model. Percolation.java uses weighted quick-union find with path compression to create an n-by-n grid, open sites, and determine if a system percolates or not with given open and closed sites. PercolationStats.java determines the approximate *percolation theshold* of a system, the fraction of sites opened when the system percolates if the sites were to be opened at random. No such exact mathematical solution exists, displaying the usefullness of computers to approximate complex problems that would otherwise go unsolved.

## API

### Percolation.Java

**public Percolation(int n)** - Creates an n-by-n grid, with all sites initially blocked.

**public void open (int row, int col)** - Opens the site (row, col) if it is not open already.

**public boolean isOpen (int row, int col)** - Determines if the site (row, col) is open or not.

**public boolean isFull (int row, int col)** - Determines if the site (row, col) is full or not. A full site is an open site continuously connected to an open site at the top row of the grid system.

**public int numberOfOpenSites()** - Returns the number of open sites.

**public boolean percolates()** - Determines if the system percolates or not.

**public static void main (String [] args)** - Reserved for unit testing.

### PercolationStats.Java

**public PercolationStats(int n, int trials)** - Performs independent trials on an n-by-n grid.

**public double mean()** - Returns sample mean of percolation threshold.

**public double stddev()** - Returns sample standard deviation of percolation threshold.

**public double confidenceLow()** - Returns low endpoint of 95% confidence interval.

**public double confidenceHi()** - Returns high endpoint of 95% confidence interval.

**public static voice main (String[] args)** - Test client takes two command line arguments n and T and prints the relevant statistics for T computational experiments on an n-by-n grid.

## Future Improvements

In order to check if the system percolates, it requires the algorithm to check if at least one open site at the bottom row is continuously connected to an open site at the top row. With an n-by-n grid, this takes O(n^2) time. In order to reduce the time complexity, a virtual top and bottom site is connected to each site at the top and bottom row, respectively. This reduces time complexity to O(1) by simply checking if the virtual top site is connected to the virtual bottom site. This results in an issue termed *backwash.** Since the virtual sites are connected to each site at the top and bottom rows, if the system percolates, it can result in a false positive as to which sites are full by indicating that every open site at the bottom and top row are continuously connected due to the virtual sites being connected to them. This issue is acceptable and doesn't effect the results of whether the system percolates or not, but there are clever solutions that can be implemented to resolve the problem.