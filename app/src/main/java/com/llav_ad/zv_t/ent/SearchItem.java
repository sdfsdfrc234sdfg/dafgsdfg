package com.llav_ad.zv_t.ent;

public class SearchItem {


    public SearchItem() {
    }

    private int img;
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
    private String education;
    private String knowledgeOfLanguages;
    private String orientation;
    private String attitudeTowardsSmoking;
    private String attitudeToAlcohol;
    private String zodiacSign;
    private String title;

    public String getLiveState() {
        return liveState;
    }

    public String getMoneyState() {
        return moneyState;
    }

    public String getChildren() {
        return children;
    }

    private String moneyState;
    private String liveState;
    private String children;

    public SearchItem(int img, String name, String city, int age, String search, String getToKnow, String purposeOfDating, String height, String weight, String bodyType, String appearance, String education, String knowledgeOfLanguages, String orientation, String attitudeTowardsSmoking, String attitudeToAlcohol, String regDate, String zodiacSign, String title, String moneyState, String liveState, String  children) {
        this.img = img;
        this.name = name;
        this.city = city;
        this.age = age;
        this.search = search;
        this.getToKnow = getToKnow;
        this.purposeOfDating = purposeOfDating;
        this.height = height;
        this.weight = weight;
        this.bodyType = bodyType;
        this.appearance = appearance;
        this.education = education;
        this.knowledgeOfLanguages = knowledgeOfLanguages;
        this.orientation = orientation;
        this.attitudeTowardsSmoking = attitudeTowardsSmoking;
        this.attitudeToAlcohol = attitudeToAlcohol;
        this.regDate = regDate;
        this.zodiacSign = zodiacSign;
        this.title = title;
        this.liveState = liveState;
        this.moneyState = moneyState;
        this.children = children;
    }

    public int getImg() {
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
}
