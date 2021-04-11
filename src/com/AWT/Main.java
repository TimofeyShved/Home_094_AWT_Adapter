package com.AWT;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    // создаем в классе пееменные
    private Frame mainFrame; // фрайм и 2 метки (лэйблы) и панель
    private Label headerLabel;
    private Label statusLabel;
    private Panel controlPanel;

    public Main() { // ----------------------------------------- конструктор для нашего класса
        prepareGUI(); // и вызываемый метод prepareGUI
    }

    public static void main(String[] args) {
        Main myMainClass = new Main(); // создание наешого основного класса
        myMainClass.showAdapterDemo(); // и вызываемый метод showAdapterDemo
    }

    // ----------------------------------------------------- описывает основное визуальное отображение
    private void prepareGUI() {
        mainFrame = new Frame("Java AWT Adapter");  // инициализация фрэйма
        mainFrame.setSize(400, 400); // размеры
        mainFrame.setLayout(new GridLayout(3, 1));// расположение объектов на форме

        mainFrame.addWindowListener(new WindowAdapter() {// обработка событий
            public void windowClosing(WindowEvent windowEvent) { // принажатии на кнопку "Закрыть"
                System.exit(0);
            }
        });

        // метки
        headerLabel = new Label(); // инициализация
        headerLabel.setAlignment(Label.CENTER);// в центре
        statusLabel = new Label();// инициализация
        statusLabel.setAlignment(Label.CENTER);// в центре
        statusLabel.setSize(350, 100);// размеры

        controlPanel = new Panel();// новая панель
        controlPanel.setLayout(new FlowLayout()); // расположение объектов на форме (лайаут)

        // добавление объектов на форму
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true); // видимость формы true
    }

    //----------------------------------------------------- Что будет в showAdapterDemo
    private void showAdapterDemo(){

        headerLabel.setText("В действии: Adapter");

        // --------------------------------------- AWT FocusAdapter Class
        Button okButton0 = new Button("OK"); // создание кнопок
        Button cancelButton = new Button("Cancel");

        okButton0.addFocusListener(new FocusAdapter(){ // действие
            public void focusGained(FocusEvent e) {
                statusLabel.setText(//statusLabel.getText()+
                        e.getComponent().getClass().getSimpleName()
                        + " gained focus. ");
            }
        });

        cancelButton.addFocusListener(new FocusAdapter(){  // действие
            public void focusLost(FocusEvent e) {
                statusLabel.setText(//statusLabel.getText()+
                        e.getComponent().getClass().getSimpleName()
                        + " lost focus. ");
            }
        });

        // добавление объектов на форму
        controlPanel.add(okButton0);
        controlPanel.add(cancelButton);

        // --------------------------------------- AWT KeyAdapter Class
        final TextField textField = new TextField(10);

        textField.addKeyListener(new KeyAdapter() { // действие
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){ // если нажали клавишу ENTER
                    statusLabel.setText("Entered text: " + textField.getText());
                }
            }
        });

        Button okButton1 = new Button("OK"); // создание кнопки

        okButton1.addActionListener(new ActionListener() { // действие кнопки
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Entered text: " + textField.getText());
            }
        });

        // добавление объектов на форму
        controlPanel.add(textField);
        controlPanel.add(okButton1);

        // --------------------------------------- AWT MouseAdapter Class
        Panel panel = new Panel(); // создание пенели
        panel.setBackground(Color.blue);
        panel.setLayout(new FlowLayout());

        panel.addMouseListener(new MouseAdapter(){ // действие Clicked
            public void mouseClicked(MouseEvent e) {
                statusLabel.setText("Mouse Clicked: ("
                        +e.getX()+", "+e.getY() +")");
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter(){ // действие Moved
            public void mouseMoved(MouseEvent e) {
                statusLabel.setText("Mouse Moved: ("+e.getX()+", "+e.getY() +")");
            }
        });

        Label msglabel = new Label(); // метка
        msglabel.setAlignment(Label.CENTER);
        msglabel.setForeground(Color.WHITE); // цвет
        msglabel.setText("Привет, мир!");

        msglabel.addMouseListener(new MouseAdapter(){ // действие Clicked
            public void mouseClicked(MouseEvent e) {
                statusLabel.setText("Mouse Clicked: ("
                        +e.getX()+", "+e.getY() +")");
            }
        });

        msglabel.addMouseMotionListener(new MouseMotionAdapter(){ // действие Moved
            public void mouseMoved(MouseEvent e) {
                statusLabel.setText("Mouse Moved: ("+e.getX()+", "+e.getY() +")");
            }
        });

        panel.add(msglabel);
        controlPanel.add(panel);

        // --------------------------------------- AWT MouseAdapter Class

        mainFrame.setVisible(true); // видимость формы true
    }
}