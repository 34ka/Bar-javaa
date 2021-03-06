package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")

public class GroupDate {
    @XStreamOmitField
    @Id
    @Column (name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column (name = "group_name")
    private String name;

    @Expose
    @Type(type = "text")
    @Column (name = "group_header")
    private String header;

    @Expose
    @Type(type = "text")
    @Column (name = "group_footer")
    private String footer;

    public int getId() {
        return id;
    }

    public GroupDate withId(int id) {
        this.id = id;
        return this;
    }

    public GroupDate withName(String name) {
        this.name = name;
        return this;
    }

    public GroupDate withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupDate withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "GroupDate{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDate date = (GroupDate) o;
        return id == date.id &&
                Objects.equals(name, date.name) &&
                Objects.equals(header, date.header) &&
                Objects.equals(footer, date.footer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, header, footer);
    }
}
