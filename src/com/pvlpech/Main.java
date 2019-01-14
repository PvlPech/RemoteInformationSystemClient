package com.pvlpech;

import com.pvlpech.controller.Controller;
import com.pvlpech.controller.SocketController;
import com.pvlpech.view.gui.SwingView;
import com.pvlpech.view.View;
import com.pvlpech.view.tui.ConsoleView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws IOException {
//        View view = new ConsoleView();
        View view = new SwingView();
        executorService.execute(new SocketController(view));

        view.init();


    }
}
