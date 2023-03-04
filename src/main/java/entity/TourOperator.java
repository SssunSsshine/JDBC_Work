package entity;

import java.util.Objects;

public class TourOperator implements Entity{
    private Long id;
    private String fullName;
    private String shortName;
    private String inn;
    private String phone;
    private String email;
    private String comments;

    public TourOperator() {
    }

    public TourOperator(String fullName, String shortName, String inn, String phone, String email, String comments) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.inn = inn;
        this.phone = phone;
        this.email = email;
        this.comments = comments;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getInn() {
        return inn;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourOperator that = (TourOperator) o;
        return Objects.equals(id, that.getId()) && Objects.equals(fullName, that.getFullName()) && Objects.equals(shortName, that.getShortName()) && Objects.equals(inn, that.getInn()) && Objects.equals(phone, that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, shortName, inn, phone, email, comments);
    }

    @Override
    public String toString() {
        return "TourOperator{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", inn='" + inn + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
