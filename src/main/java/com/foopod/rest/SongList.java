package com.foopod.rest;

import java.util.ArrayList;
import com.google.gson.Gson;

public class SongList{

    private ArrayList<SongChoice> songs;

    public SongList(){
      songs = new ArrayList<SongChoice>();
    }

    public void addSong(SongChoice song){
      songs.add(song);
    }

    public SongChoice getSong(int index){
      return songs.get(index);
    }

    public ArrayList<SongChoice> toArrayList(){
      return songs;
    }
}
