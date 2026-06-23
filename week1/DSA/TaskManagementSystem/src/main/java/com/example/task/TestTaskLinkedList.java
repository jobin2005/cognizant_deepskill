package com.example.task;

public class TestTaskLinkedList {
    public static void main(String[] args) {
        TaskLinkedList list = new TaskLinkedList();

        list.addLast(new Task("T001","Design DB","TODO"));
        list.addLast(new Task("T002","Implement API","IN_PROGRESS"));
        list.addLast(new Task("T003","Write Tests","TODO"));

        System.out.println("Traverse list:");
        list.traverse();

        System.out.println("Search T002: " + list.searchById("T002"));

        System.out.println("Delete T001: " + list.deleteById("T001"));
        System.out.println("After delete traverse:");
        list.traverse();

        System.out.println("Size: " + list.size());
    }
}
