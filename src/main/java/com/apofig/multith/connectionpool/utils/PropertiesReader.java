package com.apofig.multith.connectionpool.utils;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class PropertiesReader {

    public static Properties read(String fileName) {
        Properties result = new Properties();
        try (InputStream input = new FileInputStream(getUrl(fileName).getFile())) {
            result.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static URL getUrl(String fileName) {
        return PropertiesReader.class.getClassLoader().getResource(fileName);
    }

    public static void write(String fileName, Properties properties) {
        try (OutputStream output = new FileOutputStream(getUrl(fileName).getFile())) {
            properties.store(output, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
