package model;

import java.util.Date;

public class Movie extends Entity{

    private String title;
    private Date date;

    public Movie() {

    }

    public Movie(int id, String title, Date date) {
        super(id);
        this.title = title;
        this.date = date;
    }

    public String getTittle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                '}';
    }
}
