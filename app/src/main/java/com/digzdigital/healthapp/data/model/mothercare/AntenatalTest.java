package com.digzdigital.healthapp.data.model.mothercare;

public class AntenatalTest {

    public AntenatalTest(){

    }

    private String id;
    private int trimester;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrimester() {
        String trimester = "";
        switch (this.trimester){
            case 0:
                trimester = "1st Trimester (1 - 12 weeks)";
                break;
            case 1:
                trimester = "2nd Trimester (13 - 27 weeks)";
                break;
            case 2:
                trimester = "3rd Trimester (7-9 months)";
                break;
        }
        return trimester;
    }

    public void setTrimester(int trimester) {
        this.trimester = trimester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
