package com.thinging.project.data;

import com.thinging.project.entity.ConnectionOption;

public interface ThingIngDataManager {

    void openSession();
    void search();
    void save();
    void remove();
    void closeSession();

}
