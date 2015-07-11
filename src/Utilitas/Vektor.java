package Utilitas;

import java.util.HashMap;
import java.util.Map;

public class Vektor {
    private int nData;
    private double []item;
    // contructor
    public Vektor(int n){
    this.nData = n;
    this.item =  new double[this.nData];
    }    
    public Vektor(double data[]){
        this.nData = data.length;
        this.item = data;
    }
    public Vektor(){
        this.nData = 0;
    }
    public void setItem(int i, double data){
    //try {
        this.item[i] = data;
    //}catch(ArrayIndexOutOfBoundsException e){
        //  throw new ArrayIndexOutOfBoundsException("Sub Matrik indeks");
    // }

    }
    public int getSize(){
        return this.nData;
    }
    public double getItem(int i){
        return  this.item[i];
    }
    public static Vektor copy(Vektor vAsal){
        Vektor vHasil = new Vektor(vAsal.getSize());
        for (int i=0; i<vAsal.getSize(); i++){
                vHasil.setItem(i,vAsal.getItem(i));
        }
        return vHasil;
    }
    public int getNpadat(){
        int t = 0;
        for (int i=0; i< nData; i++){
                if (getItem(i) != 0.0) t++;
        }
        return t;
    }
    public Vektor getKITem(int k){
        Vektor v = null;
        try {
            if (k <= this.nData) {
                v = new Vektor(k);
                for (int i=0; i<k; i++){
                    v.setItem(i, this.item[i]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){

        }

        return v;
    }
    public void cetak(String kom){
        System.out.println(kom);
        for (int i=0; i<nData; i++){
            if (this.item[i] != 0.0)
                System.out.printf("%d : %.4f\n",i,this.item[i]);
        }
    }
    public double getSum(){
        double hasil = 0;
        for (int i=0;i<nData; i++){
            hasil += this.item[i];
        }
        return hasil;
    }
    public String cetakToString(String kom){
    String ot = kom+"\n";
    for (int i=0; i<nData; i++){
            if (this.item[i] != 0.0)
                ot += this.item[i]+"\n";
    }
    return ot;
    }        
    public static Vektor sort(Vektor v, int flag){        
        Vektor vHasil = new Vektor();
        vHasil = Vektor.copy(v);
        //vHasil.cetak("Vhasil Sementara");
        double temp=0.0;
        int n = vHasil.getSize();
        for (int i=0; i<n; i++){
            if (flag == 0) {// dari kecil ke besar
                int idKecil = vHasil.findIdTerkecil(i,n-1);
                temp = vHasil.getItem(i);                    
                vHasil.setItem(i, vHasil.getItem(idKecil));
                vHasil.setItem(idKecil,temp);                    
            } else { // dari besar ke kecil
                int idBesar = vHasil.findIdTerbesar(i,n-1);
                temp = vHasil.getItem(i);                    
                vHasil.setItem(i, vHasil.getItem(idBesar));
                vHasil.setItem(idBesar,temp);
            } 
        }
        return vHasil;
    }           
    /**
     * 
     * @param dt
     * @return 
     */
    public int getIdKatTerbanyak(int []dt){
        int idTerbesar = -9999;
        int terbesar = -9999;
        HashMap<Integer,Integer> h = new HashMap<Integer, Integer>();
        for (int i=0; i<dt.length; i++){
            Integer kunci = new Integer(dt[i]);
            Integer nilai = new Integer(0);
            if (!h.containsKey(kunci)){                                
                nilai = new Integer(1);
                h.put(kunci, nilai);
            }else {
                nilai = h.get(kunci);
                int a = nilai.intValue();
                a += 1;
                nilai = new Integer(a);
                h.put(kunci, nilai);
            }
        }        
        
        for (Map.Entry<Integer,Integer> entry : h.entrySet()){
            Integer kunci = entry.getKey();
            Integer nilai = entry.getValue();
            if (terbesar < nilai.intValue()){
                terbesar = nilai.intValue();
                idTerbesar = kunci.intValue();
            }
            //System.out.printf("%d:%d\n",kunci.intValue(),nilai.intValue());
        }
        return idTerbesar;
    }    
    public static int getIdKatMax(int []dt){
        int idTerbesar = -9999;
        int terbesar = -9999;
        HashMap<Integer,Integer> h = Vektor.jEachItem(dt);
        
        for (Map.Entry<Integer,Integer> entry : h.entrySet()){
            Integer kunci = entry.getKey();
            Integer nilai = entry.getValue();
            if (terbesar < nilai.intValue()){
                terbesar = nilai.intValue();
                idTerbesar = kunci.intValue();
            }
            //System.out.printf("%d:%d\n",kunci.intValue(),nilai.intValue());
        }
        return idTerbesar;
    }    
    public static HashMap<Integer,Integer> jEachItem(int []dt){
        HashMap<Integer,Integer> h = new HashMap<Integer, Integer>();
        for (int i=0; i<dt.length; i++){
            Integer kunci = new Integer(dt[i]);
            Integer nilai = new Integer(0);
            if (!h.containsKey(kunci)){                                
                nilai = new Integer(1);
                h.put(kunci, nilai);
            }else {
                nilai = h.get(kunci);
                int a = nilai.intValue();
                a += 1;
                nilai = new Integer(a);
                h.put(kunci, nilai);
            }
        }
        return h;
    }    
    public int findIdTerkecil(int i, int n){
        double terkecil = this.getItem(i);
        int idKecil = i;
        for (int id=i-1;id<=n;id++){
            if(terkecil > this.getItem(id)) {
                terkecil = this.getItem(id);
                idKecil = id;
            }
        }
        return idKecil;
    }    
    public int findIdTerbesar(int i, int n){
        double terbesar = this.getItem(i);
        int idBesar = i;
        for (int id=i+1;id<=n;id++){            
            if(terbesar < this.getItem(id)) {
                terbesar = this.getItem(id);
                idBesar = id;                
            }            
        }
        return idBesar;
    }        
    public double [] getItems(){
        return item;
    }
    public static void elTambah(Vektor v1, Vektor v2){        
        for (int i=0; i<v1.getSize();i++){
            double dtC = v1.getItem(i)+v2.getItem(i);
            v1.setItem(i, dtC);            
        }        
    }
    public static void elTambah(double []v1, double []v2){        
        for (int i=0; i<v1.length;i++)v1[i] += v2[i];                            
    }
    public int finPos(double dt){
        int pos = -1;
        int i=0;
        boolean ketemu = false;
        while (!ketemu && (i < this.nData)) {
            if (dt == this.item[i]) {
                ketemu = true;
                pos = i;
            }
            i++;
        }
        return pos;
    }    
    public static void main(String s[]){
        double []a = {0.4771,0.1000,2.11000};
        double []b = {0.1000,0.0010,0.11000};
        Vektor v1 = new Vektor(a);
        Vektor v2 = new Vektor(b);
                
        int idBesar = v1.findIdTerbesar(0, 2);
        System.out.println("id Besar : "+idBesar);
        Vektor.elTambah(v1, v2);
        v1.cetak("V1");
        Vektor v3 = null;
        
        v3 = Vektor.sort(v1,1);        
        v1.cetak("v1 sebelum diurut");
        v3.cetak("v3");         
        v1.cetak("V1");
        Vektor dt = v3.getKITem(2);
        dt.cetak("data terbesar ke-");
        
        int []data = {1,2,4,4,3,1,2,3,3,1,1};
        for (int id=0; id<data.length;id++){
            System.out.print(data[id]+" ");
        }
        System.out.println();
        Vektor v = new Vektor();
        //HashMap<Integer, Integer> h = v.getUnik();
        //int idTerbesar = v.getIdKatTerbanyak(data);
        int idTerbesar = Vektor.getIdKatMax(data);
       
        System.out.println(idTerbesar);
    }
}