package com.foopod.rest;

public class SongChoice{

  private String title, artist, youtubeURL, submittedBy;
  private int ID;

  public SongChoice(String title, String artist, String youtubeURL, String submittedBy){
    this.ID = 0;
    this.title = title;
    this.artist = artist;
    this.youtubeURL = youtubeURL;
    this.submittedBy = submittedBy;
  }

  public int getID(){
    return ID;
  }

  public String getTitle(){
    return title;
  }

  public String getArtist(){
    return artist;
  }

  public String getYoutubeURL(){
    return youtubeURL;
  }

  public String getSubmittedBy(){
    return submittedBy;
  }

  public void setID(int ID){
    this.ID = ID;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public void setArtist(String artist){
    this.artist = artist;
  }

  public void setYoutubeURL(String youtubeURL){
    this.youtubeURL = youtubeURL;
  }

  public void setSubmittedBy(String submittedBy){
    this.submittedBy = submittedBy;
  }

}
