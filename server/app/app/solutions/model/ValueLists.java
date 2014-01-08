package app.solutions.model;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class ValueLists extends BaseObject {

    @NotBlank
    @Length(min = 1, max = 15)
    private String listId;

    @NotEmpty
    private List<ListEntry> listEntries;

    public List<ListEntry> getListEntries() {
        return listEntries;
    }

    public void setListEntries(List<ListEntry> listEntries) {
        this.listEntries = listEntries;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }
}
