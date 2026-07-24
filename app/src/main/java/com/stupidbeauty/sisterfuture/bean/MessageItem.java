package com.stupidbeauty.sisterfuture.bean;

public class MessageItem {
    public String text;
    private boolean isFromAI;

    public MessageItem(String text, boolean isFromAI) {
        this.text = text;
        this.isFromAI = isFromAI;
    }

    public String getText() {
        return text;
    }

    public boolean isFromAI() {
        return isFromAI;
    }
}
