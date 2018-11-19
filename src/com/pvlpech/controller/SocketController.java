package com.pvlpech.controller;

import com.pvlpech.view.View;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Created by pvlpech on 11.11.2018.
 */
public class SocketController implements Controller {

    private View view;
    private PropertyChangeSupport support;

    public SocketController(View view) {
        support = new PropertyChangeSupport(this);
        this.view = view;

        this.view.addPropertyChangeListener(this);
        this.addPropertyChangeListener(this.view);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        JSONObject jsonObject = (JSONObject) evt.getNewValue();
        String operation = jsonObject.getString("operation");
        if ("exit".equalsIgnoreCase(operation)) {
            return;
        } else if ("help".equalsIgnoreCase(operation)) {
            view.showHelp();
        } else {
//            loader.performAction(jsonObject);
//            ...
//            support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
        }
    }
}
