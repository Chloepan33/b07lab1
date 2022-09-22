public class Polynomial {
    private double [] co;

    public Polynomial(){
        co = new double[100];
        co[0] = 0;
    }

    public Polynomial(double[] co){
        this.co = co;
    }


    public Polynomial add (Polynomial p){
        int length = Math.max(this.co.length, p.co.length);
        double[] result = new double[length];
        for (int i = 0; i < length; i++) {
            if (i < this.co.length) {
                result[i] += this.co[i];
            }
            if (i < p.co.length) {
                result[i] += p.co[i];
            }
        }
        return new Polynomial(result);
    }

    public double evaluate (double m){
        double n = 0;
        for (int i = 0; i < co.length; i++){
            n = n + co[i] * Math.pow(m, i);
        }
        return n;

    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }


}