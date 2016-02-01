package lambdas;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Lambdas {

    public static void main(String[] args) {
	// Fancy
	int factor = 1000;                                                                        
	IntUnaryOperator times1000 =  (int x ) ->  { return  x * factor ; } ;                     
	Arrays.stream(new int[]{1, 2, 3, 4, 5}).map(times1000).forEach(System.out::println);  

	// Simple
	Arrays.stream(new int[]{1, 2, 3, 4, 5}).map(x -> x * 2).forEach(System.out::println);     
    }
} 
