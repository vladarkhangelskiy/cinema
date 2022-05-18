package model;

public class Ticket extends Entity{

    private int price;
    private int seat;
    private int userId;
    private int movieId;

    public Ticket(int id, int price, int seat, int userId, int movieId) {
        super(id);
        this.price = price;
        this.seat = seat;
        this.userId = userId;
        this.movieId = movieId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", price=" + price +
                ", seat=" + seat +
                ", userId=" + userId +
                ", movieId=" + movieId +
                '}';
    }
}
