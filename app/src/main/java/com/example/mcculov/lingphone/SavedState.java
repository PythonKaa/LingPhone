package com.example.mcculov.lingphone;

import android.os.Bundle;

import com.google.gson.Gson;

public class SavedState {

    private String getDescriptor()
    {
        return "SAVE_" + this.getClass().getSimpleName().toUpperCase();
    }

    public void saveToBundle(Bundle out) {
        out.putString(getDescriptor(), getJSON());
    }

    public String getJSON() {
        return new Gson().toJson(this);
    }

    public void restoreFromBundle(Bundle in) {
        restoreFromJSON(in.getString(getDescriptor()));
    }

    public void restoreFromJSON(String json)
    {
        this.equals(new Gson().fromJson(json, this.getClass()));
    }

}
