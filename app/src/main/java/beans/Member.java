package beans;

/**
 * Created by 江婷婷 on 2017/9/21.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 注册用户 作者
 */

public class Member {
    private String name;
    private String contactInfo;
    private List<Member> follow;
    private List<Member> fans;
    private List<Book> borrowBooks;

    public Member(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        follow = new ArrayList<>();
        fans = new ArrayList<>();
        borrowBooks = new ArrayList<>();
    }

    public void addBorrowBook(Book book) {
        borrowBooks.add(book);
    }

    public int getFollowNum() {
        return follow.size();
    }

    public int getFansNum() {
        return fans.size();
    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }


}
