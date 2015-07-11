package Class;

public class RefDok {
	private Dokumen d;
	private int idDok;
        private int idPar;
        private int idKal;
	private int  fText;

	public RefDok(Dokumen d, int ftext){
		this.d = d;
		this.idDok = d.getIdDok();
	
		this.fText = ftext;
	}

	public Dokumen getDok(){return this.d;}
	public int getIdDok(){return this.idDok;}
	public int getFText(){return this.fText;}
	public void cetakRefDok(){
		System.out.print("|"+idDok+"|"+fText+"| kategori :"+d.getKat());
          
	}
}
