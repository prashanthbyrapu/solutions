package app.solutions.model;

/**
 * Created by niveditha on 8/1/14.
 */
public class Course extends MasterObject {


    // Specifies whether language course or subject ...
    private boolean isLanguage;

    public boolean isLanguage() {
        return isLanguage;
    }

    public void setLanguage(boolean isLanguage) {
        this.isLanguage = isLanguage;
    }

}
