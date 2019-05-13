package com.littlepure.models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

/**
 * Data I/O
 */
abstract class DataIO {

    /**
     * Save to local text file
     * @param data data
     * @param location local file location
     * @param <E> data type
     */
    < E > void save(E data, String location) {
        try {
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(location));
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(data);
            encoder.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read from local text file
     * @param location local file location
     * @return {@code Arraylist}
     */
    ArrayList<?> read(String location) {
        try {
            XMLDecoder d=new XMLDecoder(new BufferedInputStream(new FileInputStream(location)));
            ArrayList<?> p = (ArrayList<?>) d.readObject();
            d.close();
            return p;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
