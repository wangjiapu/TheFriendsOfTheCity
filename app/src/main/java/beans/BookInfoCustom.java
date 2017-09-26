package beans;

import java.util.List;

/**
 * Created by whz on 2017/9/26.
 */
public class BookInfoCustom extends BookInfo{

    private BookBrief bookBrief;
    private List<BookImg> bookImgs;

    public List<BookImg> getBookImgs() {
        return bookImgs;
    }

    public void setBookImgs(List<BookImg> bookImgs) {
        this.bookImgs = bookImgs;
    }

    public BookBrief getBookBrief() {
        return bookBrief;
    }

    public void setBookBrief(BookBrief bookBrief) {
        this.bookBrief = bookBrief;
    }
}
