package com.example.carbhejdo;

import java.util.ArrayList;

public class Model {
    public ArrayList<Favorite> getFavourites() {
        return Favourites;
    }
    public void setFavourites(ArrayList<Favorite> Favourites) {
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

    private ArrayList<Favorite> Favourites;
    private Model(){
        setFavourites(new ArrayList<Favorite>());
        //loadModelObjects();
    }

}
