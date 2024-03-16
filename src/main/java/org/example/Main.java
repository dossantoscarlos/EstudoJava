package org.example;

import org.example.ci.TelaCi;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TelaCi telaCi = new TelaCi();
        telaCi.execute();
    }
}