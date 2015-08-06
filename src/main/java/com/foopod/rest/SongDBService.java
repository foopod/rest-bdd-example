package com.foopod.rest;

import java.util.ArrayList;
import com.google.gson.Gson;
import java.sql.*;

public class SongDBService{

  // private ArrayList<Customer> customers;

  public static final String table = "SONGLIST";
  public static final String titleField = "TITLE";
  public static final String artistField = "ARTIST";
  public static final String URLField = "URL";
  public static final String submittedByField = "SUBMITTEDBY";
  public static final String IDField = "RID";

  private Gson gson;

  public SongDBService(){
    gson = new Gson();
    // TODO Test Connection maybe
  }

  public Connection getDBConnection(){
    Connection conn = null;
    try {
      Class.forName("org.sqlite.JDBC");
      conn = DriverManager.getConnection("jdbc:sqlite:songs.db");
      conn.setAutoCommit(false);
    } catch(Exception e) {
      System.out.println(e.toString());
    }
    return conn;
  }

  public void closeDBConnection(Connection conn){
    try {
      conn.close();
    } catch(Exception e) {
      System.out.println(e.toString());
    }
  }

  public void setUpDBFromList(SongList listOfSongs){
    clearSongs();
    for(SongChoice song : listOfSongs.toArrayList()){
      createSong(song);
    }
  }

  public void clearSongs(){
    Connection conn = getDBConnection();
    try{
      String deleteAllSongsStatement = "DELETE FROM " + table;
      Statement stmt = conn.createStatement();
      stmt.executeUpdate(deleteAllSongsStatement);
      // conn.commit();
      closeDBConnection(conn);
    } catch(Exception e) {
      System.out.println(e.toString());
    }
  }

  public String getSongs(){
    Connection conn = getDBConnection();
    SongList songs = new SongList();

    try{
      String getSongsStatement = "SELECT * FROM " + table;
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(getSongsStatement);

      while (rs.next()) {
        int RID = rs.getInt(IDField);
        String title = rs.getString(titleField);
        String artist = rs.getString(artistField);
        String URL = rs.getString(URLField);
        String submittedBy = rs.getString(submittedByField);

        SongChoice song = new SongChoice(title, artist, URL, submittedBy);
        song.setID(RID);
        songs.addSong(song);
      }

    closeDBConnection(conn);

    } catch(Exception e) {
      System.out.println(e.toString());
    }

    return gson.toJson(songs);
  }

  public String getSong(int ID){
    Connection conn = getDBConnection();
    SongChoice song = null;

    try{
      String getSongsStatement = "SELECT * FROM " + table  + " WHERE " + IDField + " = ?";
      PreparedStatement stmt = conn.prepareStatement(getSongsStatement);
      stmt.setInt(1, ID);
      ResultSet rs = stmt.executeQuery();
      conn.commit();

      while (rs.next()) {
        int RID = rs.getInt(IDField);
        String title = rs.getString(titleField);
        String artist = rs.getString(artistField);
        String URL = rs.getString(URLField);
        String submittedBy = rs.getString(submittedByField);

        song = new SongChoice(title, artist, URL, submittedBy);
        song.setID(RID);
      }

    closeDBConnection(conn);

    } catch(Exception e) {
      System.out.println(e.toString());
    }

    if(song != null){
      return gson.toJson(song);
    } else {
      return gson.toJson("Failed");
    }
  }

  public String deleteSong(int ID){
    Connection conn = getDBConnection();
    SongChoice song = null;

    try{
      String getSongsStatement = "DELETE FROM " + table  + " WHERE " + IDField + " = ?";
      PreparedStatement stmt = conn.prepareStatement(getSongsStatement);
      stmt.setInt(1, ID);
      stmt.executeUpdate();
      conn.commit();
      closeDBConnection(conn);

    } catch(Exception e) {
      System.out.println(e.toString());
      return gson.toJson("Failed");
    }
    return gson.toJson("Success");
  }

  public String updateSong(String jsonSong){
    SongChoice song = gson.fromJson(jsonSong, SongChoice.class);

    Connection conn = getDBConnection();
    try{
      String getSongsStatement = "SELECT * FROM " + table  + " WHERE " + IDField + " = ?";
      PreparedStatement stmt = conn.prepareStatement(getSongsStatement);
      stmt.setInt(1, song.getID());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String updateSongStatement = "UPDATE "+ table +" SET " +titleField+" = '" +song.getTitle()+ "', " +artistField+" = '" +song.getArtist()+ "', " +URLField+" = '" +song.getYoutubeURL()+ "', " +submittedByField+" = '" +song.getSubmittedBy()+ "' WHERE " + IDField + " = " + song.getID();
        System.out.println(updateSongStatement);
        stmt = conn.prepareStatement(updateSongStatement);
        stmt.executeUpdate();
        conn.commit();
      }

    closeDBConnection(conn);

    } catch(Exception e) {
      System.out.println(e.toString());
      return gson.toJson("Failed");
    }

    return gson.toJson("Success");
  }

  public String createSong(SongChoice song){
    // SongChoice song = gson.fromJson(jsonSong, SongChoice.class);

    Connection conn = getDBConnection();
    try{
      String getSongsStatement = "SELECT * FROM " + table  + " WHERE " + titleField + " = ?";
      PreparedStatement stmt = conn.prepareStatement(getSongsStatement);
      stmt.setString(1, song.getTitle());
      ResultSet rs = stmt.executeQuery();

      if (!rs.next()) {

        //Find next available ID
        String getHighestIDStatement = "SELECT * FROM " + table + " ORDER BY " + IDField + " DESC LIMIT 0, 1";
        stmt = conn.prepareStatement(getHighestIDStatement);
        rs = stmt.executeQuery();

        int nextID;
        if(rs.next()){
          nextID = rs.getInt(IDField) + 1;
        } else {
          nextID = 0;
        }
        // System.out.println(nextID);

        String createSongStatement = "INSERT INTO "+ table +" ("+titleField+","+artistField+","+URLField+","+submittedByField+","+IDField+") VALUES ('"+song.getTitle()+"','"+song.getArtist()+"','"+song.getYoutubeURL()+"','"+song.getSubmittedBy()+"',"+nextID+");";
        // System.out.println(createSongStatement);
        stmt = conn.prepareStatement(createSongStatement);
        stmt.executeUpdate();
        conn.commit();
      }

    closeDBConnection(conn);

    } catch(Exception e) {
      System.out.println(e.toString());
      return gson.toJson("Failed");
    }

    return gson.toJson("Success");
  }

}
