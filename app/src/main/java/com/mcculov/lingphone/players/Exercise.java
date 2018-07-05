package com.mcculov.lingphone.players;

import java.util.ArrayList;


public class Exercise {

    private ArrayList<String> text = new ArrayList<>();
    private ArrayList<PlayItem> playItems = new ArrayList<>();
    private String title;
    private String mediaFileName;

    public Exercise(String title, String mediaFileName) {
        this.title = title;
        this.mediaFileName = mediaFileName;
    }

    public String getMediaFileName() {
        return mediaFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addPlayItem(String playerName, int phraseIndex, int phraseFrom, int phraseLen, int timeFinish, int indexInPhrase) {
        playItems.add(new PlayItem(playerName, phraseIndex, phraseFrom, phraseLen, timeFinish, indexInPhrase));
    }

    public ArrayList<String> getText() {
        return text;
    }

    public ArrayList<PlayItem> getPlayItems() {
        return playItems;
    }

    public void addPhrase(String phrase) {
        text.add(phrase);
    }

    public boolean textIsEmpty() {
        return text.size() == 0;
    }

    public int getPhraseIndex(int msec) {

        return getPlayItem(msec).getPhraseIndex();
    }

    public PlayItem getPlayItem(int msec) {

        if (msec >= getLastPlayItem().getTimeFinish())
            return getLastPlayItem();

        int last = getPlayItems().size() - 2;
        for (int i = last; i >= 0; i--) {
            int lineEnd = getPlayItems().get(i).getTimeFinish();
            if (msec >= lineEnd)
                return getPlayItems().get(i + 1);
        }
        return getPlayItems().get(0);
    }

    public PlayItem getLastPlayItem() {
        return playItems.get(playItems.size() - 1);
    }

    public PlayItem getPrevPlayItem(PlayItem item) {
        if (item == null || item == playItems.get(0))  {
            return playItems.get(0);
        }

        return playItems.get(playItems.indexOf(item) - 1);
    }


    public PlayItem getNextPlayItem(PlayItem item) {
        if (item == null) {
            return playItems.get(0);
        }

        if (item == getLastPlayItem())
            throw new IllegalArgumentException("Call getNextPlayItem for last item is invalid!");

        return playItems.get(playItems.indexOf(item) + 1);
    }

    public class PlayItem {
        private String playerName;
        private int phraseIndex;
        private int phraseFrom;
        private int phraseLen;
        private int timeFinish;
        private int indexInPhrase;

        public PlayItem(String playerName, int phraseIndex, int phraseFrom, int phraseLen, int timeFinish, int indexInPhrase) {
            this.playerName = playerName;
            this.phraseIndex = phraseIndex;
            this.phraseFrom = phraseFrom;

            this.phraseLen = phraseLen;
            this.timeFinish = timeFinish;
            this.indexInPhrase = indexInPhrase;
        }

        public int getIndexInPhrase() {
            return indexInPhrase;
        }

        public int getPhraseFrom() {
            return phraseFrom;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getPhraseIndex() {
            return phraseIndex;
        }

        public int getPhraseLen() {
            return phraseLen;
        }

        public int getTimeFinish() {
            return timeFinish;
        }

        public int getPosition(int msec) {

            if (!(msec < getTimeFinish() && msec >= getTimeFinish() - getPhraseLen()))
                throw new IllegalArgumentException("Попытка вычислить не свою позицию для PlayItem");

            int toEnd = getTimeFinish() - msec;
            int endPos = getPhraseFrom() + getPhraseLen();
            int res = endPos - toEnd;
            return res;
        }
    }
}
