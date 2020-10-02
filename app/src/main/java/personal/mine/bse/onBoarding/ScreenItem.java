package personal.mine.bse.onBoarding;

public class ScreenItem {

    String Title, Description;
    int Screenimg;

    public ScreenItem(String title, String description, int screenimg) {
        Title = title;
        Description = description;
        Screenimg = screenimg;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setScreenimg(int screenimg) {
        Screenimg = screenimg;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getScreenimg() {
        return Screenimg;
    }
}
