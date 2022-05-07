package model;

import java.util.Date;

public class Movie extends Entity{

    private String tittle;
    private Date date;

    public Movie(int id, String tittle, Date date) {
        super(id);
        this.tittle = tittle;
        this.date = date;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
