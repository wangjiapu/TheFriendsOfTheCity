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

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    private Calendar calendar;

    public Book(String bookName, String authorName, String introduction) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.introduction = introduction;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
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
