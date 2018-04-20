package edu.uga.cs1302.mytunes;
import java.util.*;
import java.io.File;
import java.lang.*; 
import java.util.Arrays;

public class MP3Collection {
List<MP3File> collection;
//private	 static String directory;
private File[] listoffiles = null;
private MP3Player Player = new MP3Player();
public MP3Collection(){
	collection = new LinkedList<MP3File>();
	
	
}
/**
 * 
 * @param directoryPathname
 */
public MP3Collection(String directoryPathname ){
	collection = new LinkedList<MP3File>();
	File file = new File(directoryPathname);
	if(file.isDirectory()){
		listoffiles=file.listFiles();
	}
	
	for(int i=0; i<listoffiles.length;i++){
	collection.add(new MP3File(listoffiles[i].getAbsolutePath().toString()));
		
	}
}
/**
 * 
 * @return
 */
public Object[][] getTableData(){
	 
	Object [][] table = new Object [collection.size()][4];

	
	for(int j =0; j<collection.size();j++){
		table[j ][0]= collection.get(j).getAuthor();
		table[j ][1]= collection.get(j).getTitle();
		table[j ][2]= collection.get(j).getAlbum();
		table[j ][3]= collection.get(j).getYear();
	}
		
	
	return table;
	
}
/**
 * 
 * @param index
 * @return
 * @throws IndexOutOfBoundsException
 */
public MP3File getFile(int index) throws IndexOutOfBoundsException{
	if(index>=collection.size() || index<0){
		throw new IndexOutOfBoundsException();
	}
	return collection.get(index);
}
/**
 * 
 * @return
 */
public int size(){
	return collection.size();

	
}
/**
 * 
 * @param index
 * @throws IndexOutOfBoundsException
 */
public void startPlay(int index) throws IndexOutOfBoundsException{
	
	if(index>=collection.size() || index<0){
		throw new IndexOutOfBoundsException();
	}
	Player.play(collection.get(index).getPath());
}
/**
 * @author abdul_000
 */
public void stopPlay(){
	
	Player.stop();
}

}

