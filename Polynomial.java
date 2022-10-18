import java.lang.Math;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;

public class Polynomial {
    private double [] co; //change it to private!!!!
    private int [] ex;

    public Polynomial(){
        co = new double[100];
        co[0] = 0;
        ex[0] = 0;
    }

    public Polynomial(double[] co, int[] ex){
        this.co = co;
        this.ex = ex;
    }

    public int Findnumber (int target, int[] nums){
        for(int i = 0; i < nums.length; i++){
            if (target == nums[i]){
                return i;
            }
        }
        return -1;
    }


    public Polynomial add (Polynomial p){
        int length = this.ex.length + p.ex.length;
        for (int i = 0;i < this.ex.length; i++ ){
            if (Findnumber(this.ex[i], p.ex) != -1){
                length--;
                if(this.co[i] + p.co[Findnumber(this.ex[i], p.ex)] == 0){
                    length--;
                }
            }
        }
        int k = 0;
        double[] reCo = new double[length];
        int[] reEx = new int[length];
        for (int i = 0;i < this.ex.length; i++){
            if (Findnumber(this.ex[i], p.ex) == -1){
                reEx[k] = this.ex[i];
                reCo[k] = this.co[i];
                k++;
            }
            else{
                if(this.co[i] + p.co[Findnumber(this.ex[i], p.ex)] != 0){
                    reEx[k] = this.ex[i];
                    reCo[k] = this.co[i] + p.co[Findnumber(this.ex[i], p.ex)];
                    k++;
                }
            
            }
        }
        for (int i = 0;i < p.ex.length; i++){
            if (Findnumber(p.ex[i], this.ex) == -1){
                reEx[k] = p.ex[i];
                reCo[k] = p.co[i];
                k++;
            }
        }

        return new Polynomial(reCo,reEx);
    }

    public Polynomial multiply (Polynomial p){
        int[] temExs = new int[100];
        double[] temCos = new double[100];
        int k = 0;
        int newEx = 0;
        double newCo = 0;
        for (int i = 0; i < this.ex.length; i++){
            for (int m = 0; m < p.ex.length; m++){
                newEx = this.ex[i] + p.ex[m];
                newCo = this.co[i] * p.co[m];
                if (Findnumber(newEx + 1, temExs) == -1){
                    temExs[k] = newEx + 1;
                    temCos[k] = newCo;
                    k++;
                }
                else{
                    temCos[Findnumber(newEx + 1, temExs)] += newCo;
                }
            }
        }
        for (int i = 0; temExs[i] != 0; i++){
            if (temCos[i] == 0){
                k--;
            }
        }
        int m = 0;
        int[] reExs = new int[k];
        double[] reCos = new double[k];
        for(int i = 0; temExs[i] != 0; i++){
            if(temCos[i] != 0){
                reExs[m] = temExs[i] - 1;
                reCos[m] = temCos[i];
                m++;
            }
        }
        return new Polynomial(reCos,reExs);
    }

    public double evaluate (double m){
        double n = 0;
        for (int i = 0; i < co.length; i++){
            n = n + co[i] * Math.pow(m, ex[i]);
        }
        return n;

    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public Polynomial(File f) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(f);
        BufferedReader input = new BufferedReader(fr);
        String line = input.readLine();

        String [] terms = new String[100];
		int k = 0;

        String[] po = line.split("\\+");
        for (int i = 0; i < po.length; i++){
            String [] ne = po[i].split("\\-");
            terms[k] = ne[0];
			k ++;

			for (int j = 1; j < ne.length; j ++) {
				terms[k] = "-" + ne[j];
				k ++;
			}
        }

        double [] newCo = new double[k];
		int [] newEx = new int[k];
		int m = 0;
        for (int i = 0; i < k; i++){
            if(terms[i].contains("x")){
                String [] nums = terms[i].split("x");            
				newCo[m] = Double.parseDouble(nums[0]);
				newEx[m] = Integer.parseInt(nums[1]);
                m++;
            }
            else{
                newCo[m] = Double.parseDouble(terms[i]);
                newEx[m] = 0;
                m++;
            }
        }
        this.co = newCo;
        this.ex = newEx;
    }




	public void saveToFile (String fileName) throws FileNotFoundException {

		String x = "";

		PrintStream newFile = new PrintStream(fileName);

		for (int i = 0; i < this.ex.length; i ++)
		{
			if (this.co[i] > 0 && i > 0) {
                x += "+";
            }
            x += String.valueOf(this.co[i]) + "x" + String.valueOf(this.ex[i]);
		}
		newFile.println(x);
	}



}