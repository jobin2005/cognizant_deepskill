package com.example.command;

public class TestCommand {
    public static void main(String[] args) {
        Light livingRoomLight = new Light("Living Room");
        Command on = new LightOnCommand(livingRoomLight);
        Command off = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(on);
        remote.pressButton();

        remote.setCommand(off);
        remote.pressButton();
    }
}
