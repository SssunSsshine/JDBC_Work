package entity;

import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.Objects;

public class Tourist implements Entity {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private Date birthday;
    private String email;
    private String phone;

    public Tourist() {
    }

    public Tourist(String surname, String name, @Nullable String patronymic, Date birthday, @Nullable String email, String phone) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourist tourist = (Tourist) o;
        return Objects.equals(id, tourist.getId()) && Objects.equals(surname, tourist.getSurname()) && Objects.equals(name, tourist.getName()) && Objects.equals(patronymic, tourist.getPatronymic()) && Objects.equals(birthday, tourist.getBirthday()) && Objects.equals(email, tourist.getEmail()) && Objects.equals(phone, tourist.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, patronymic, birthday, email, phone);
    }

    @Override
    public String toString() {
        return "Tourist{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
