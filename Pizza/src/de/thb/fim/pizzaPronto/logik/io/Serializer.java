package de.thb.fim.pizzaPronto.logik.io;

import de.thb.fim.pizzaPronto.logik.Bestellung;

import java.io.*;


public class Serializer {
    private ObjectOutputStream os;
    private ObjectInputStream is;
    String datei;

    public Serializer(String datei) {
        this.datei = datei;
    }

    public void serializeBestellung(Bestellung bestellung) {
        try {
            os = new ObjectOutputStream(new FileOutputStream(datei));
            os.writeObject(bestellung);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Bestellung deserializeBestellung() {
        try {
            is = new ObjectInputStream(new FileInputStream(datei));
            Bestellung bestellung = ((Bestellung) is.readObject());
            return bestellung;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void closeOutput() {
        try {
            os.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void closeInput() {
        try {
            is.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
