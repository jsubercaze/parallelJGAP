# Parallel JGAP

Fork of JGAP, a Java Genetic Algorithm and Genetic Programming library. 
The library is technically a bit outdated, but contains all the bells and whistles to quickly create GA and GP in Java. 

The aim is to fully parallelize the library.


Changelog:

17-06-2015 : Slightly modify the structure to efficiently parallel fitness computation. Preliminary tests show good speedups

16-06-2015 : First version, fully mavenized, all tests ok.
             Removed trove dependency, replaced by Guava
