package com.example.task;

public class TaskLinkedList {
    private static class Node {
        Task task;
        Node next;
        Node(Task task) { this.task = task; }
    }

    private Node head;
    private Node tail;
    private int size;

    public TaskLinkedList() { head = tail = null; size = 0; }

    public void addLast(Task task) {
        Node n = new Node(task);
        if (tail == null) {
            head = tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
        size++;
    }

    public Task searchById(String taskId) {
        Node cur = head;
        while (cur != null) {
            if (cur.task.getTaskId().equals(taskId)) return cur.task;
            cur = cur.next;
        }
        return null;
    }

    public boolean deleteById(String taskId) {
        Node cur = head, prev = null;
        while (cur != null) {
            if (cur.task.getTaskId().equals(taskId)) {
                if (prev == null) { // deleting head
                    head = cur.next;
                    if (head == null) tail = null;
                } else {
                    prev.next = cur.next;
                    if (cur == tail) tail = prev;
                }
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public void traverse() {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.task);
            cur = cur.next;
        }
    }

    public int size() { return size; }
}
