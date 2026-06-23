package com.example.decorator;

public class TestDecorator {
    public static void main(String[] args) {
        Notifier email = new EmailNotifier();
        Notifier withSms = new SMSNotifierDecorator(email);
        Notifier withSmsAndSlack = new SlackNotifierDecorator(withSms);

        withSmsAndSlack.send("Hello user!");
    }
}
