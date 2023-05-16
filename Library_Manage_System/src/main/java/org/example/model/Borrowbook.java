package org.example.model;
import java.time.LocalDate;
import java.util.Date;


public class Borrowbook {
    private int id;
    private int userid;
    private int bookid;
    private LocalDate borrowdate;
    private LocalDate expDate;


    public Borrowbook(int id, int userid, int bookid, LocalDate borrowdate, LocalDate expDate) {
        this.id = id;
        this.userid = userid;
        this.bookid = bookid;
        this.borrowdate = borrowdate;
        this.expDate = expDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public Borrowbook(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public LocalDate getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(LocalDate borrowdate) {
        this.borrowdate = borrowdate;
    }

    @Override
    public String toString() {
        return
                 id +
                "," + userid +
                "," + bookid +
                "," + borrowdate +
                "," + expDate;
    }
}
