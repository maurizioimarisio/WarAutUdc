package Utility;

public class InsertSpaces {
	public InsertSpaces(){
		
	}
	public String space(int numSpaces){
		String strSpaces = "";
		for(int i=0; i< numSpaces; i++){
			strSpaces = strSpaces + " ";
		}
		return strSpaces;
	}
}
