package uz.mh.trello.inertview;

public class Stack {
    private int[] items = new int[5];

    private int count;

    public void push(int item) {
        if (count == items.length)
            throw new StackOverflowError();

        items[count++] = item;
    }

    public int pop() {
        if (count == 0)
            throw new IllegalArgumentException();

        return items[--count];
    }

    public int peek() {
        if (count == 0)
            throw new IllegalArgumentException();
        return items[count - 1];
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
