package ru.stqa.pft.mantis.model;

public class MailMessage {
    public String text;
    public String to;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
