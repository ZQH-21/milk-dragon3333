package store;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import model.Admin;
import model.Mo;
import model.TA;
import model.User;

public class UserStore {
    private static final String FILE_PATH =
            System.getProperty("catalina.base") + File.separator +
            "webapps" + File.separator + "SE" + File.separator +
            "WEB-INF" + File.separator + "file" + File.separator + "users.txt";

    public static void saveUser(User user) {
        String line = user.getName() + "," + user.getPassword() + "," + user.getRole() + "," + user.getEmail();
        try (FileWriter fw = new FileWriter(FILE_PATH, true)) {
            fw.write(line + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User validateUser(String password, String role, String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String p = parts[1];
                    String r = parts[2];
                    String e = parts[3];
                    if (p.equals(password) && r.equals(role) && e.equals(email)) {
                        return buildUser(r, p, e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User validateUser(String password, String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String p = parts[1];
                    String r = parts[2];
                    String e = parts[3];
                    if (p.equals(password) && e.equals(email)) {
                        return buildUser(r, p, e);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isEmailRegistered(String email) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String e = parts[3];
                    if (e.equals(email)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static User buildUser(String role, String password, String email) {
        if ("Admin".equals(role)) {
            return new Admin(password, email);
        }
        if ("TA".equals(role)) {
            return new TA(password, email);
        }
        if ("Mo".equals(role)) {
            return new Mo(password, email);
        }
        return null;
    }
}
