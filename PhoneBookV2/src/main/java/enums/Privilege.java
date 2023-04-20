package enums;

public enum Privilege {
    FAMILY("0", "aile"),
    WORK("1", "is"),
    SOCIAL("2", "sosyal");

    private String id;
    private String name;

     Privilege(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameById(String id)
    {
        return Privilege.valueOf(id).name;
    }
}
