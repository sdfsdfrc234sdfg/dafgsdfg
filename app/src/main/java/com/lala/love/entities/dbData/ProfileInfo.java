package com.lala.love.entities.dbData;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfileInfo {

    public ProfileInfo(int id) {
        this.id = id;
    }

    @PrimaryKey
    private int id;
    private String  img;
    private String regDate;
    private String name;
    private String city;
    private int age;
    private String search;
    private String getToKnow;
    private String purposeOfDating;
    private String height;
    private String weight;
    private String bodyType;
    private String appearance;

    public void setImg(String img) {
        this.img = img;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void setGetToKnow(String getToKnow) {
        this.getToKnow = getToKnow;
    }

    public void setPurposeOfDating(String purposeOfDating) {
        this.purposeOfDating = purposeOfDating;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setKnowledgeOfLanguages(String knowledgeOfLanguages) {
        this.knowledgeOfLanguages = knowledgeOfLanguages;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setAttitudeTowardsSmoking(String attitudeTowardsSmoking) {
        this.attitudeTowardsSmoking = attitudeTowardsSmoking;
    }

    public void setAttitudeToAlcohol(String attitudeToAlcohol) {
        this.attitudeToAlcohol = attitudeToAlcohol;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String education;
    private String knowledgeOfLanguages;
    private String orientation;
    private String attitudeTowardsSmoking;
    private String attitudeToAlcohol;
    private String zodiacSign;
    private String title;


    public String  getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getSearch() {
        return search;
    }

    public String getGetToKnow() {
        return getToKnow;
    }

    public String getPurposeOfDating() {
        return purposeOfDating;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getBodyType() {
        return bodyType;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getEducation() {
        return education;
    }

    public String getKnowledgeOfLanguages() {
        return knowledgeOfLanguages;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getAttitudeTowardsSmoking() {
        return attitudeTowardsSmoking;
    }

    public String getAttitudeToAlcohol() {
        return attitudeToAlcohol;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfileInfo{" +
                "id=" + id +
                ", img=" + img +
                ", regDate='" + regDate + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", search='" + search + '\'' +
                ", getToKnow='" + getToKnow + '\'' +
                ", purposeOfDating='" + purposeOfDating + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", bodyType='" + bodyType + '\'' +
                ", appearance='" + appearance + '\'' +
                ", education='" + education + '\'' +
                ", knowledgeOfLanguages='" + knowledgeOfLanguages + '\'' +
                ", orientation='" + orientation + '\'' +
                ", attitudeTowardsSmoking='" + attitudeTowardsSmoking + '\'' +
                ", attitudeToAlcohol='" + attitudeToAlcohol + '\'' +
                ", zodiacSign='" + zodiacSign + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
