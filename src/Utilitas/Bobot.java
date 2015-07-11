/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitas;

public class Bobot{
	public int frek;
	public int id;
        public Bobot(){
		this.frek = 1;
	}
	public void  Naikkan(){
		this.frek++;
	}
	public void  Naikkan(int dt){
		this.frek += dt;
	}
	public int getFrek(){
		return this.frek;
	}
}