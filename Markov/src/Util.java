import java.util.Random;

public class Util {
	public static int rouletteSelect(double[] weight) {
		double weight_sum = 0;
		for(int i = 0; i < weight.length; i++) {
			weight_sum += weight[i];
		}
		double value = new Random().nextDouble() * weight_sum;	
		for(int i = 0; i < weight.length; i++) {		
			value -= weight[i];		
			if(value <= 0) return i;
		}
		return weight.length - 1;
	}
	public static int rouletteSelect(Double[] weight) {
		double weight_sum = 0;
		for(int i = 0; i < weight.length; i++) {
			weight_sum += weight[i];
		}
		double value = new Random().nextDouble() * weight_sum;	
		for(int i = 0; i < weight.length; i++) {		
			value -= weight[i];		
			if(value <= 0) return i;
		}
		return weight.length - 1;
	}
}
