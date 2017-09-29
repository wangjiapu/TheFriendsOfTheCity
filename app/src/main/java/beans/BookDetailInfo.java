package beans;



public class BookDetailInfo {
    private String id;
    private String bookName;
    private String isbn;
    private String author;
    private String bookBriefId;
    private String place;
    private String coverImg;
    private String categoryId;
    private String tagId;
    private String state;
    private String userInfoId;
    private String gmtCreate;
    private String gmtModified;
    private String briefText;
    private BookImg[] bookImgs;
    private String srcUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookBriefId() {
        return bookBriefId;
    }

    public void setBookBriefId(String bookBriefId) {
        this.bookBriefId = bookBriefId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getBriefText() {
        return briefText;
    }

    public void setBriefText(String briefText) {
        this.briefText = briefText;
    }

    public BookImg[] getBookImgs() {
        return bookImgs;
    }

    public void setBookImgs(BookImg[] bookImgs) {
        this.bookImgs = bookImgs;
    }

    public String getSrcUserName() {
        return srcUserName;
    }

    public void setSrcUserName(String srcUserName) {
        this.srcUserName = srcUserName;
    }
}
