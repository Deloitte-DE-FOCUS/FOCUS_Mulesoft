package gov.delaware.edi.x12.util;

public class SegmentParser {

    private int pointer;
    private String[] message;
    public SegmentParser(String s) {
        message = s.split("~");
        pointer = 0;
    }
    public int size() {
        return message.length;
    }

    public String getNext() {
        return message[pointer++]+"~";
    }
    public String peekNext() { return message[pointer]+"~"; }
    public void reset() {
        pointer = 0;
    }

    public boolean hasNext() {
        return pointer >= 0 && pointer < message.length;
    }
}
