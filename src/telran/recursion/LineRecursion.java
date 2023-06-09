package telran.recursion;

public class LineRecursion {
	public static long factorial(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if(n > 1) {
			res = n*factorial(n - 1);
		}
		return res;
	}
	public static long pow(int a, int b) {

		if (b < 0) {
				throw new IllegalArgumentException("Pow cannot be negative value");
		}
		
		if (b == 0) {
			return 1;
		}
		return multiplay(a,pow(a, b-1));	
	}
	public static long multiplay(int x, long y) {
		if (y==0) {
			   return 0;
		  }
		  if (y < 0) { 
			  return multiplay( -x, -y); 
		  }
		  return x+ multiplay(x, y - 1);
	  }
	
	
	public static long sum(int[] array) {
		return sum(0, array);
	}
	private static long sum(int firstIndex, int[] array) {
		long sum = 0;
		if (firstIndex < array.length) {
			sum = array[firstIndex] + sum(firstIndex + 1, array);
		}
		return sum;
	}
	public static int[] reverse(int[] array) {
		return reverse(array, 0, array.length - 1);
	}
	private static int[] reverse(int[] array, int left, int right) {
		
		if(left < right) {
			array[left] = array[left] + array[right];
			array[right] = array[left] - array[right];
			array[left] = array[left] - array[right];
			reverse(array, left + 1, right - 1);
		}
		return array;
	}
	public static long square(int x) { 
		 if (x==1) {
			 return 1;
		 }
		 if (x<0) {
			  return square (-x);
			  
		 }
		return x + x-1 + square(x-1);
	}
	public static boolean isSubstring( String string, String substr) {
	
		    if (substr.length() == 0) {
		        return true; 
		    }
		    
		    if (string.length()==0 || string.length() < substr.length()) {
		        return false; 
		    }
		    if (string.charAt(0) == substr.charAt(0)) {
		        if (string.length() >= substr.length()) {
		            if (string.substring(0, substr.length()).equals(substr)) {
		                return true;
		            } else {
		                
		               
		return isSubstring(string.substring(1), substr);
		            }
		      
		        }
		    }     return false;
	}
}

		        

