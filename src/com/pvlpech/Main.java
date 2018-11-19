package com.pvlpech;

import com.pvlpech.controller.Controller;
import com.pvlpech.view.gui.SwingView;
import com.pvlpech.view.View;

public class Main {

    public static void main(String[] args) {
//        View view = new ConsoleView();
        View view = new SwingView();
        Controller controller = new Controller(view);

        view.init();
    }
}