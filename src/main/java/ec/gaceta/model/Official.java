package ec.gaceta.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "officials")
public class Official implements Serializable {

    public Official() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "published_on")
    private String publishedOn;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(columnDefinition = "TEXT")
    private String denomination;

    @Column(name = "application_number")
    private String applicationNumber;

    @Column(name = "error")
    private String error;

    @Column(columnDefinition = "TEXT")
    private String proper;

    @Column(name = "tabloid_number")
    private String tabloid_number;

    // ===== GETTERS Y SETTERS =====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(String publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getProper() {
        return proper;
    }

    public void setProper(String proper) {
        this.proper = proper;
    }
    
        public String getTabloid_number() {
        return tabloid_number;
    }

    public void setTabloid_number(String tabloid_number) {
        this.tabloid_number = tabloid_number;
    }
}
