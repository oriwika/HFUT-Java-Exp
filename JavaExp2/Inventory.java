import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Inventory {
    public static void main(String[] args) throws IOException {
        new NewInventory();
    }
}

class Goods{
    private String Item_Number;
    private  int Quantity;
    private String Supplier;
    private String Description;
    public Goods(String item_Number, int quantity, String supplier, String description) {
        Item_Number = item_Number;
        Quantity = quantity;
        Supplier = supplier;
        Description = description;
    }
    public Goods() {
    }
    public String getItem_Number() {
        return Item_Number;
    }
    public void setItem_Number(String item_Number) {
        Item_Number = item_Number;
    }
    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
    public String getSupplier() {
        return Supplier;
    }
    public void setSupplier(String supplier) {
        Supplier = supplier;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
class NewInventory{
    ArrayList<Goods> goodsArray = new ArrayList<>();
    BufferedWriter errorWriter = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\java\\java实验\\JavaExp2\\text\\Errors.txt"));
    ArrayList<String[]> strArr_O = new ArrayList<>();
    ArrayList<String[]> strArr_A = new ArrayList<>();
    ArrayList<String[]> strArr_R = new ArrayList<>();
    ArrayList<String[]> strArr_D = new ArrayList<>();
    String readIn = null;
    public NewInventory() throws IOException {
        getInventory();
        getTransactions();
        process_A();
        process_R();
        process_O();
        process_D();
        getNewInventory();
    }
    public void getInventory() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(("C:\\Users\\Administrator\\Desktop\\java\\java实验\\JavaExp2\\text\\Inventory.txt")));
        String temp;
        String[] t = null;
        while((temp = br.readLine())!=null){
            t = temp.split("\\s+");
            goodsArray.add(new Goods(t[0], Integer.parseInt(t[1]), t[2], t[3]));
        }
        br.close();
    }

    public void getTransactions() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Administrator\\Desktop\\java\\java实验\\JavaExp2\\text\\Transactions.txt"));
        while((readIn = br.readLine())!=null){
            if(readIn.charAt(0)=='O'){
                strArr_O.add(readIn.split("\\s+"));
            }
            if(readIn.charAt(0)=='A'){
                strArr_A.add(readIn.split("\\s+"));
            }
            if(readIn.charAt(0)=='R'){
                strArr_R.add(readIn.split("\\s+"));
            }
            if(readIn.charAt(0)=='D'){
                strArr_D.add(readIn.split("\\s+"));
            }
        }
        Collections.sort(strArr_O, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(Integer.parseInt(o1[2])<Integer.parseInt(o2[2])){
                    return -1;
                } else if (Integer.parseInt(o1[2])<Integer.parseInt(o2[2])) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
    }

    public void process_A(){
        for(String[] temp:strArr_A){
            goodsArray.add(new Goods(temp[1], 0, temp[2], temp[3]));
        }
    }

    public void process_R(){
        for(String[] temp:strArr_R) {
            for (Goods good : goodsArray) {
                if (good.getItem_Number().equals(temp[1])){
                    good.setQuantity(good.getQuantity()+Integer.parseInt(temp[2]));
                }
            }
        }
    }

    public void process_O() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\java\\java实验\\JavaExp2\\text\\Shipping.txt"));
        BufferedWriter ebw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\java\\java实验\\JavaExp2\\text\\Errors.txt"));
        Collections.sort(strArr_O, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.parseInt(o1[2])-Integer.parseInt(o2[2]);
            }
        });
        for(String[] temp:strArr_O){
            for(Goods good:goodsArray){
                if(good.getItem_Number().equals(temp[1])){
                    if(Integer.parseInt(temp[2])>good.getQuantity()){
                        ebw.write(temp[3]+"\t"+good.getItem_Number()+"\t"+temp[2]);
                        ebw.flush();
                        ebw.newLine();
                        ebw.close();
                    } else {
                        good.setQuantity(good.getQuantity() - Integer.parseInt(temp[2]));
                        bw.write(temp[3] + "\t" + temp[1] + "\t" + temp[2]);
                        bw.flush();
                        bw.newLine();
                    }
                }
            }
        }
        bw.close();
    }

    public void process_D() throws IOException {
        for(String[] temp:strArr_D){
            for(int i=0;i<goodsArray.size();i++){
                if(goodsArray.get(i).getItem_Number().equals(temp[1])){
                    if(goodsArray.get(i).getQuantity()==0){
                        goodsArray.remove(i);
                    } else {
                        errorWriter.write(0+"\t"+temp[1]+"\t"+goodsArray.get(i).getQuantity());
                        errorWriter.flush();
                        errorWriter.newLine();
                    }
                }
            }
        }
    }

    public void getNewInventory() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Administrator\\Desktop\\java\\java实验\\JavaExp2\\text\\NewInventory.txt"));
        Collections.sort(goodsArray, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if(Integer.parseInt(o1.getItem_Number())<Integer.parseInt(o2.getItem_Number())){
                    return -1;
                } else if (Integer.parseInt(o1.getItem_Number())>Integer.parseInt(o2.getItem_Number())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for(Goods good:goodsArray){
            bw.write(good.getItem_Number()+'\t'+good.getQuantity()+'\t'+good.getSupplier()+'\t'+good.getDescription());
            bw.flush();
            bw.newLine();
        }
        bw.close();
    }
}