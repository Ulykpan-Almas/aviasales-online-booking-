package kz.javaee.db;

public class Countries {
    private Long id;
    private String name;
    private String short_Name;


    public Countries(Long id, String name, String short_Name) {
        this.id = id;
        this.name = name;
        this.short_Name = short_Name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_Name() {
        return short_Name;
    }

    public void setShort_Name(String short_Name) {
        this.short_Name = short_Name;
    }
}
