package dto;

public class Article extends Dto {
    public String title;
    public String body;
    public int views;
    public int memberId;

    public Article(int id, String regDate, int memberId, String title, String body) {
        this(id, regDate, memberId, title, body, 0);
    }

    public Article(int id, String regDate, int memberId, String title, String body, int views) {
        this.id = id;
        this.regDate = regDate;
        this.memberId = memberId;
        this.title = title;
        this.body = body;
        this.views = views;
    }

    public void increaseViews() {
        views++;
    }
}
