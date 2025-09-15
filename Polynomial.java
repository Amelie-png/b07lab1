public class Polynomial {
    private double[] coeff;

    public Polynomial(){
        coeff = new double[]{0};
    }

    public Polynomial(double[] args){
        coeff = args;
    }

    public Polynomial add(Polynomial obj){
        //to fix
        int maxLen = (coeff.length > obj.coeff.length) ? coeff.length : obj.coeff.length;
        double[] sumCoeff = new double[maxLen];
        for(int i = 0; i < maxLen; i++){
            if(i < coeff.length){
                sumCoeff[i] += coeff[i];
            }
            if(i < obj.coeff.length){
                sumCoeff[i] += obj.coeff[i];
            }
        }
        Polynomial p = new Polynomial(sumCoeff);
        return p;
    }

    public double evaluate(double x){
        double sum = 0;
        double power = 1;
        for(int i = 0; i < coeff.length; i++){
            power *= x;
            sum += coeff[i] * power;
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}