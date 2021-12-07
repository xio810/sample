package dto;

public class Article extends Dto {
    public String title;
    public String body;
    public int views;

    public Article(int id, String regDate, String title, String body) {
        this(id, regDate, title, body, 0);
    }

    public Article(int id, String regDate, String title, String body, int views) {
        this.id = id;
        this.regDate = regDate;
        this.title = title;
        this.body = body;
        this.views = views;
    }

    public void increaseViews() {
        views++;
    }
}
