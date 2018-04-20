package edu.uga.cs1302.mytunes;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
public class MP3File {
private String pathname;
private String author;
private String album;
private String date;
private String title;
private int Year;
private String year= ""+Year;
private MP3Player play;
public MP3File(){
	pathname= "unknown";
	author = "";
	album = "";
	date = "1900";
	title = "";
}
/**
 * This is a MP3File constructor
 * @param pathname
 */
public MP3File(String pathname){
	this.pathname=pathname;
	try {
	    FileInputStream fis        = new FileInputStream( pathname );
	    BufferedInputStream bis    = new BufferedInputStream(fis);
	    AudioFileFormat mpegFormat = AudioSystem.getAudioFileFormat(bis);
	    Map properties             = mpegFormat.properties();
	    this.author= (String) properties.get( "author" );
	    this.album=(String) properties.get( "album" );
	    this.title = (String) properties.get( "title" );
	    this.date=(String) properties.get( "date" ) ;
	    
	    this.year=(String) properties.get("year");
        }
        catch( ArrayIndexOutOfBoundsException oobe ) {
            System.err.println( "Usage: java PrintMP3Properties file.mp3" );
        }
        catch( Exception e ) {
            System.out.println(e);
        }


	
}
/**
 * 
 * @return Year
 */
public int getYear(){
	return Year;
}
/**
 * 
 * @param year
 */
public void setYear(int year){
	this.Year=year;
}
/**
 * This method will get the pathname.
 * @return
 */
public String getPath(){
	return this.pathname;
}
/**
 * Will set the pathname
 * @param pathname
 */
public void setPath(String pathname){
	this.pathname=pathname;
}
/**
 * this will get the author.
 * @return
 */
public String getAuthor(){
	return this.author;
}
/**
 * this will set the author. 
 * @param author
 */
public void setAuthor(String author){
	this.author=author;
}
/**
 * this will get the album.
 * @return
 */
public String getAlbum(){
	return this.album;
}
/**
 * This will set the album.
 * @param album
 */
public void setAlbum(String album){
	this.album=album;
}
/**
 * This will get the data.
 * @return
 */
public String getDate(){
	return this.date;
}
/**
 * This will set the data.
 * @param date
 */
public void setDate(String date){
	this.date=date;
}
/**
 * This will get the title.
 * @return
 */
public String getTitle(){
	return this.title;
}
/**
 * This will set the title.
 * @param title
 */
public void setTitle(String title){
	this.title=title;
}
/**
 * this will the string representation of the element.
 */
public String toString(){
	return "author: "+this.author+", "+"title: "+ this.title+", " + "album: "+this.album+", "+"date: "+this.date;
}
/**
 * will the music sample.
 */
public void play(){
	MP3Player player = new MP3Player();

	 player.play(pathname);

        

}

}

 