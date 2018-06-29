package com.example.mcculov.lingphone;

import android.os.Bundle;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public final class PackerToBundle {


    public <T> T restoreFromJSON(String json, Class<T> stateClass)
    {
        return new Gson().fromJson(json, stateClass);
    }

    private <T> String getDescriptor(Class<T> stateClass)
    {
        return "SAVE_" + stateClass.getSimpleName().toUpperCase();
    }

    public <T> void saveToBundle(Bundle out, Object src) {
        out.putString(getDescriptor(src.getClass()), getJSON(src));
    }

    public String getJSON(Object src) {
        return new Gson().toJson(src);
    }

    public <T> T restoreFromBundle(Bundle in, Class<T> stateClass) {
        return restoreFromJSON(in.getString(getDescriptor(stateClass)), stateClass);
    }



}
