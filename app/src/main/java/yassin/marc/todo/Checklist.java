package yassin.marc.todo;

public class Checklist {
    int value;
    String text;
    boolean isChecked;

    public Checklist(int value, String text){
        this.value = value;
        this.text = text;
    }

    public String getName(){
        return this.text;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(boolean isChecked){
        if (isChecked)
           value = 1;
        else
            value = 0;
    }



}
