package com.mcculov.lingphone.players;

import android.util.ArrayMap;

public class PlayersManager {
    private static final PlayersManager ourInstance = new PlayersManager();
    private ArrayMap<String, IMPlayer> players = new ArrayMap<>();

    private PlayersManager() {
    }

    public static PlayersManager getInstance() {
        return ourInstance;
    }

    public void registerPlayer(String playerName, IMPlayer player) {
        players.put(playerName.toUpperCase(), player);
    }

    public IMPlayer getPlayer(String playerName) {
        return players.get(playerName.toUpperCase());
    }


}
