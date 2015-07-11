/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Class;
import java.util.*;

public class InfoListRefDok {
	private int jumlahRefDok;
	public ArrayListRefDok ListRefDok,ListRefDokUji;
	// constructor
	public InfoListRefDok(){
		this.jumlahRefDok = 0; // setJumlahRefDok = 0
		this.ListRefDok = new ArrayListRefDok(); // buat ArrayListRefDok
        this.ListRefDokUji = new ArrayListRefDok();
	}
	// SisipData ke ListRefDok
	public void SisipKeListRefDok(RefDok rdok){
		ListRefDok.setData(rdok);
		this.jumlahRefDok++;
	}
      public void SisipKeListRefDokUji(RefDok rdokUji){
		ListRefDokUji.setData(rdokUji);
	}

	// dapatkan ArrayList orang
	public ArrayList getListRefDok(){
		return ListRefDok.getListRefDok();
	}
    
    public ArrayList getListRefDokUji(){
		return ListRefDokUji.getListRefDok();
	}

	public int getInfoJumlahRefDok(){
		return this.jumlahRefDok;
	}
}
