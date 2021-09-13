package kz.javaee.db;

public class Footballers {
    Long id;
    String name;
    String surname;
    int salary;
    String club;
    int transferPrice;
    private Countries year;


    public Footballers() {
    }

    public Footballers(Long id, String name, String surname, int salary, String club, int transferPrice, Countries year) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.club = club;
        this.transferPrice = transferPrice;
        this.year = year;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getTransferPrice() {
        return transferPrice;
    }

    public void setTransferPrice(int transferPrice) {
        this.transferPrice = transferPrice;
    }

    public Countries getYear() {
        return year;
    }

    public void setYear(Countries year) {
        this.year = year;
    }
}

