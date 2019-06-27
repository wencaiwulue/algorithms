package demo.ORMTest.bean;

/**
 * @author fengcaiwen
 * @since 6/26/2019
 */
public class User {

    public String name;
    public Integer gender;


    public User() {
    }

    public User(String name, Integer gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
