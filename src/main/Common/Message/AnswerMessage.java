package main.Common.Message;

public class AnswerMessage implements Message{
    public static final long serialVersionUID = 62345678L;
    private boolean value;

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
    public void Handle() {

    }
}
