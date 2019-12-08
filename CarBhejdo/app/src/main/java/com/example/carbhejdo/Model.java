package com.example.carbhejdo;

import java.util.ArrayList;

public class Model {
    public ArrayList<String> getFavourites() {
        return Favourites;
    }
    public void setFavourites(ArrayList<String> Favourites) {
        this.Favourites = Favourites;
    }
    public static class Favorite {
        public String favourite;
        public Favorite(String favourite) {
            this.favourite = favourite;

        }
    }

    private static Model model = null;
    public static Model getModel(){
        if (model == null){
            model = new Model();
        }
        return model;
    }

    private ArrayList<String> Favourites;
    private Model(){
        setFavourites(new ArrayList<String>());
        //loadModelObjects();
    }

}
