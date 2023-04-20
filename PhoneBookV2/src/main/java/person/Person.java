package person;

import category.Family;
import category.ICategory;
import category.Social;
import category.Work;
import enums.Privilege;

public class Person {
    private String name;
    private int number;
    private int id;
    private Privilege privilege;
    private ICategory category;

    public Person(String name, int id, int number, ICategory category, Privilege privilege)
    {
        this.name = name;
        this.number = number;
        this.id = id;
        this.category = category;
        this.privilege = privilege;

    }
    public Person(int id, int number, ICategory category, Privilege privilege)
    {
        new Person("", id, number, category, privilege);
    }
    public Person(int number, ICategory category, Privilege privilege)
    {
        new Person(0, number, category, privilege);
    }
    public Person(ICategory category, Privilege privilege)
    {
        new Person(0, category, privilege);
    }
    public Person(Privilege privilege)
    {
        new Person(null, privilege);
    }
    public Person()
    {
        new Person(null);
    }

    @Override
    public String toString() {
        return  "Name = " + name +
                " | ID = " + id +
                " | Number = " + number +
                " | privilege = " + privilege.getName() +
                " | category = " + category.printCategory();
    }

    public int getId()
    {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ICategory getCategory() {
        return category;
    }

    public void setCategory(ICategory category) {
        this.category = category;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public void callPerson(Person person)
    {
        if (this.id == person.getId()) {
            System.out.println("Kisi Kendisini Arıyamaz..");
            return;
        }

        if (Integer.parseInt(this.privilege.getId()) <= Integer.parseInt(person.privilege.getId()))
            System.out.println(this.name  + " " + person.getName() + " kisisini arıyor..");
        else
            System.out.println(this.name + " kisisinin " + person.getName() + " kisisini arama yetkisi yok..");
    }
}
