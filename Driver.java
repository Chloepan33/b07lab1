import java.util.Arrays;

public class Driver { 
    public static void main(String [] args) { 
   
     double [] c1 = {-1,1}; 
     int [] e1 = {0,1};
     Polynomial p1 = new Polynomial(c1,e1); 
     double [] c2 = {1,1}; 
     int [] e2 = {0,1};
     Polynomial p2 = new Polynomial(c2,e2); 
     Polynomial s1 = p1.add(p2);
     Polynomial s2 = p1.multiply(p2);
     System.out.println(Arrays.toString(s2.co) + " and " + Arrays.toString(s2.ex));
     System.out.println("s(0.1) = " + s1.evaluate(0.1)); 
     if(s1.hasRoot(1)) 
      System.out.println("1 is a root of s"); 
     else 
      System.out.println("1 is not a root of s"); 
      
      
    } 
   } 
    