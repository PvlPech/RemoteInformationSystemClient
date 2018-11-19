package com.pvlpech.controller;

import java.beans.PropertyChangeListener;

public interface Controller extends PropertyChangeListener {

    void addPropertyChangeListener(PropertyChangeListener pcl);

    void removePropertyChangeListener(PropertyChangeListener pcl);

}
