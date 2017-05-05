package com.karumi.maxibonkata;

public class SlackChat implements Chat {
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
