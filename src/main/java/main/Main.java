package main;

import controller.DaftarControl;
import view.DaftarView;

public class Main {
    public static void main(String[] args) {
        DaftarControl Controller = new DaftarControl();
        DaftarView view = new DaftarView(Controller);
        view.tampilkanMenuUtama();
    }
}