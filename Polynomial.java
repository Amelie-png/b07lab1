import java.util.*;
import java.io.*;

public class Polynomial {
    private double[] coeff;
    private int[] exp;

    public Polynomial(){
        coeff = new double[]{};
        exp = new int[]{};
    }

    public Polynomial(double[] coeff, int[] exp){
        this.coeff = coeff;
        this.exp = exp;
    }

    public Polynomial(File f){
        try(Scanner input = new Scanner(f)){
            String[] poly = input.next().split("(?=-)|\\+");
            coeff = new double[poly.length];
            exp = new int[poly.length];
            for(int i = 0; i < poly.length; i++){
                //case exp is 0
                if(!poly[i].contains("x")){
                    coeff[i] = Double.parseDouble(poly[i]);
                    exp[i] = 0;
                    continue;
                }
                String[] temp = poly[i].split("x", -1);
                //case coeff or exp is 1
                temp[0] = ("-".equals(temp[0]) || "".equals(temp[0])) ? temp[0] + "1" : temp[0];
                temp[1] = ("".equals(temp[1])) ? temp[1] + "1" : temp[1];
                //assign values
                coeff[i] = Double.parseDouble(temp[0]);
                exp[i] = Integer.parseInt(temp[1]);
            }
        }
        catch(FileNotFoundException e){
        }
    }

    private int[] mergeArray(int[] a, int[] b){
        int[] temp = new int[a.length + b.length];
        int k;
        for(k = 0; k < a.length; k++){
            temp[k] = a[k];
        }
        for(int j = 0; j < b.length; j++){
            temp[k+j] = b[j];
        }
        Arrays.sort(temp);
        int[] uniq = new int[temp.length];
        uniq[0] = temp[0];
        int uniqInd = 1;
        for(int i = 1; i < temp.length; i++){
            if(temp[i] != temp[i-1]){
                uniq[uniqInd] = temp[i];
                uniqInd++;
            }
        }

        int[] result = new int[uniqInd];
        System.arraycopy(uniq, 0 , result, 0, uniqInd);
        return result;
    }

    public Polynomial add(Polynomial obj){
        int[] sumExp = mergeArray(exp, obj.exp);
        double[] sumCoeff = new double[sumExp.length];

        for(int i = 0; i < sumExp.length; i++){
            for(int j = 0; j < exp.length; j++){
                if(exp[j] == sumExp[i]){
                    sumCoeff[i] += coeff[j];
                }
            }
            for(int j = 0; j < obj.exp.length; j++){
                if(obj.exp[j] == sumExp[i]){
                    sumCoeff[i] += obj.coeff[j];
                }
            }
        }

        Polynomial p = new Polynomial(sumCoeff, sumExp);
        return p;
    }

    private int findMax(int[] arr){
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        return max;
    }

    public Polynomial multiply(Polynomial obj){
        //int[] proExp = new int[findMax(obj.exp) + findMax(exp)];
        double[] tempCoeff = new double[findMax(obj.exp) + findMax(exp) + 1];

        for(int i = 0; i < exp.length; i++){
            for(int j = 0; j < obj.exp.length; j++){
                tempCoeff[exp[i] + obj.exp[j]] += coeff[i] * obj.coeff[j];
            }
        }

        int count = 0;
        for(int i = 0; i < tempCoeff.length; i++){
            if(tempCoeff[i] != 0){
                count++;
            }
        }

        double[] proCoeff = new double[count];
        int[] proExp = new int[count];
        count = 0;

        for(int i = 0; i < tempCoeff.length; i++){
            if(tempCoeff[i] != 0){
                proExp[count] = i;
                proCoeff[count] = tempCoeff[i];
                count++;
            }
        }

        Polynomial p = new Polynomial(proCoeff, proExp);
        return p;
    }

    public double evaluate(double x){
        double sum = 0;
        for(int i = 0; i < coeff.length; i++){
            sum += coeff[i] * Math.pow(x, exp[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public void saveToFile(String fileName){
        String poly = "";
        for(int i = 0; i < coeff.length; i++){
            String tempCoeff = (coeff[i] > 0) ? "+" + coeff[i] : "" + coeff[i];
            if("1.0".equals(tempCoeff.substring(1)) && exp[i] != 0){
                tempCoeff = tempCoeff.substring(0, 1);
            }
            String tempExp = (exp[i] == 0) ? "" : "x";
            tempExp = (exp[i] <= 1) ? tempExp : tempExp + exp[i];

            if(i == 0 && "+".equals(tempCoeff.substring(0, 1))){
                poly = tempCoeff.substring(1) + tempExp;
            }
            else{
                poly =  poly + tempCoeff + tempExp;
            }
        }

        try {
            FileWriter output = new FileWriter(fileName);
            output.write(poly);
            output.close();
        } catch (IOException e) {
        }
    }
}