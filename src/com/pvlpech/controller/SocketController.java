package com.pvlpech.controller;

import com.pvlpech.view.View;
import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by pvlpech on 11.11.2018.
 */
public class SocketController implements Controller, Runnable {

    private View view;
    private PropertyChangeSupport support;
    private String SERVER_ADDRESS = "localhost";
    private int PORT = 3345;

    private Socket socket;
    private DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    public SocketController(View view) throws IOException {
        support = new PropertyChangeSupport(this);
        this.view = view;

        this.view.addPropertyChangeListener(this);
        this.addPropertyChangeListener(this.view);

        this.socket = new Socket(SERVER_ADDRESS, PORT);
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.dataInputStream = new DataInputStream(socket.getInputStream());
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
            try {
                dataOutputStream.close();
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace(); //TODO: catch exceptions properly
            }
            return;
        } else if ("help".equalsIgnoreCase(operation)) {
            view.showHelp();
        } else {
            try {
                dataOutputStream.writeUTF(jsonObject. toString());
                dataOutputStream.flush();

                //TODO depends on response object (string or json we should generate appropriate topic)
//                support.firePropertyChange(Constants.VIEW_TOPIC, null, jsonObject);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                System.out.println("Response from server: " + dataInputStream.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
