package app.solutions.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Transient;


/**
 * Base Model for all documents
 *
 * @author I038968
 */
public class BaseObject {

    @Id
    private ObjectId id;

    @Transient
    private String clientId;

    private Date createdAt;

    private Date changedAt;

    // TODO -> this should be a reference
    private String changedBy;

    // TODO -> this should be a reference
    private String createdBy;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setId(String id){
        this.id = new ObjectId(id);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @PrePersist
    void prePersist() {
         changedAt = new Date();
        if (createdAt == null) {
            createdAt = changedAt;
        }

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


    public String getText(){
        throw new IllegalStateException("Method should be overridden by subclass");
    }

}
