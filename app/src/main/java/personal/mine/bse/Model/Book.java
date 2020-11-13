package personal.mine.bse.Model;

public class Book {
    private String id;
    private String title;
    private String description;
    private String writer;
    private String url;

    public Book(){

    }

    public Book(String id, String title, String description, String writer, String url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.writer = writer;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
