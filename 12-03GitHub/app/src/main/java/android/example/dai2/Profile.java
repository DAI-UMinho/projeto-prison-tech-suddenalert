package android.example.dai2;

public class Profile {
    private String scan, name, localation, color;
    private int type, points;

    public Profile(String scan, String name, String localation, String color, int type, int points) {
        this.scan = scan;
        this.name = name;
        this.localation = localation;
        this.color = color;
        this.type = type;
        this.points = points;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalation() {
        return localation;
    }

    public void setLocalation(String localation) {
        this.localation = localation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
