package hu.ait.android.multiactivitydemo.perferences;

import java.util.List;

import hu.ait.android.multiactivitydemo.data.PersonalData;

public class DataManager {

    private static DataManager instance = null;
    private PersonalData personalData;
    private List<PersonalData> personalDataList;


    public static DataManager getInstance() {
        if (instance == null ) {
            instance = new DataManager();
        }

        return instance;
    }

    private DataManager() {
    }

    public static void setInstance(DataManager instance) {
        DataManager.instance = instance;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }
}
