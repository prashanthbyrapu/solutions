package app.solutions.core.service.metadata;

/**
 * Created by niveditha on 17/4/14.
 */
public class CollectionMetadata {

    /**
     * Is Embedded collection?
     */
    private boolean isEmbedded;

    /**
     * SUper Class of current collection
     */
    private String superClass;

    /**
     * Is Change Version enabled for this collection;
     */
    private boolean changeVersionEnabled;


    public boolean isEmbedded() {
        return isEmbedded;
    }

    public void setEmbedded(boolean isEmbedded) {
        this.isEmbedded = isEmbedded;
    }

    public String getSuperClass() {
        return superClass;
    }

    public void setSuperClass(String superClass) {
        this.superClass = superClass;
    }

    public boolean isChangeVersionEnabled() {
        return changeVersionEnabled;
    }

    public void setChangeVersionEnabled(boolean changeVersionEnabled) {
        this.changeVersionEnabled = changeVersionEnabled;
    }


}
