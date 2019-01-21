package ca.shorturl.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@NamedQueries({
        @NamedQuery(name = "shorturl.setShortURL", query = "update ShortURL ST set ST.shortURL = :short where ST.id = :id")
})
@Table(name = "shorturl")
public class ShortURL implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String longURL;

    private String shortURL;

    @Column(nullable = false)
    private Calendar created;

    private Calendar lastClick;

    private Long clickCount;

    @Version
    private long version;

    public ShortURL() {
        this.created = Calendar.getInstance();
    }

    public ShortURL(String longURL) {
        this();
        this.longURL = longURL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getLastClick() {
        return lastClick;
    }

    public void setLastClick(Calendar lastClick) {
        this.lastClick = lastClick;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortURL shortURL1 = (ShortURL) o;

        if (version != shortURL1.version) return false;
        if (id != null ? !id.equals(shortURL1.id) : shortURL1.id != null) return false;
        if (longURL != null ? !longURL.equals(shortURL1.longURL) : shortURL1.longURL != null) return false;
        if (shortURL != null ? !shortURL.equals(shortURL1.shortURL) : shortURL1.shortURL != null) return false;
        if (created != null ? !created.equals(shortURL1.created) : shortURL1.created != null) return false;
        if (lastClick != null ? !lastClick.equals(shortURL1.lastClick) : shortURL1.lastClick != null) return false;
        return clickCount != null ? clickCount.equals(shortURL1.clickCount) : shortURL1.clickCount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (longURL != null ? longURL.hashCode() : 0);
        result = 31 * result + (shortURL != null ? shortURL.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (lastClick != null ? lastClick.hashCode() : 0);
        result = 31 * result + (clickCount != null ? clickCount.hashCode() : 0);
        result = 31 * result + (int) (version ^ (version >>> 32));
        return result;
    }
}
