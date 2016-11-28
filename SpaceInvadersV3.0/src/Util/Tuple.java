package Util;

//This is a custom object that stores the row and column of the alien
//This way we can track where runner aliens need to go in the formation
//Because the shooters in their column stay in place so to get them back in the form 
//We must match their columns
public class Tuple<Row, Column> {

	public Row row;
	public Column column;
	
	public Tuple(Row r, Column c){
		row = r;
		column = c;
		
	}
	
	public int getRow(){
		return (int) row;
	}
	
	public int getColumn(){
		return (int)column;
	}
	
}
