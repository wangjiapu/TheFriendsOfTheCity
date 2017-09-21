package beans;

import java.util.Calendar;

/**
 * Created by 江婷婷 on 2017/9/21.
 */

public class Book {
    private String bookName;
    private String authorName;
    private String introduction;
    private int ImageId;

    private boolean collectedStatus;
    private boolean readOffStatus;
    private boolean borrowStatus;
    private Calendar returnDate;


    public int getImageId() {
        return ImageId;
    }

    public boolean isCollectedStatus() {
        return collectedStatus;
    }

    public void setCollectedStatus(boolean collectedStatus) {
        this.collectedStatus = collectedStatus;
    }

    public boolean isReadOffStatus() {
        return readOffStatus;
    }

    public void setReadOffStatus(boolean readOffStatus) {
        this.readOffStatus = readOffStatus;
    }

    public boolean isBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(boolean borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }


    public Book(String bookName, String authorName, String introduction) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.introduction = introduction;
    }



    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

}
