package app.solutions.model;

/**
 * Created by niveditha on 19/4/14.
 */
public class TestModelB extends BaseObject {


    private String textField;

    private String field2;



    @Override
    public String getText(){
        return textField;
    }

    public String getTextField() {
        return textField;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
