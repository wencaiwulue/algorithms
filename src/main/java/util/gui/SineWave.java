package util.gui;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author fengcaiwen
 * @since 8/27/2019
 */
public class SineWave {
    public static void main(String[] args) throws IOException {
        User user = new User();
        user.data = "hello".getBytes();
        user.id = 1;
//        ObjectOutputStream outputStream = new ObjectOutputStream();

    }

    public static class User implements Serializable {
        public Integer id;
        public byte[] data;

        public User() {
        }
    }
}
