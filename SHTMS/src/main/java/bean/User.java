package main.java.bean;


public class User {
    private String userName;
    private String name;
    private String identityNumber;
    private String sex;
    private int age;
    private String tel;
    private String email;

    public User(String userName, String name, String identityNumber, String sex, int age, String tel, String email) {
        this.userName = userName;
        this.name = name;
        this.identityNumber = identityNumber;
        this.sex = sex;
        this.age = age;
        this.tel = tel;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
