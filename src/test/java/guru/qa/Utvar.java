package guru.qa;

public enum Utvar {
    TYPEONE("Кофеварка"),
    TYPETWO("Сковорода");

    public final String desc;

    Utvar(String desc) {
        this.desc = desc;
    }
}
