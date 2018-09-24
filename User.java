package User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    public static ArrayList<User> users;
    String login;
    String password;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static void main(String[] args) throws IOException {
        users = new ArrayList<>();
        String filename="d:/user.txt";
        try(BufferedReader in = new BufferedReader(new FileReader(filename))){
            while (in.ready()){
                String pair = in.readLine();
                String [] loginAndPass = pair.split("\t");
                users.add(new User(loginAndPass[0],loginAndPass[1]));
            }
        }
        for (User user : users)
            System.out.println(user);

        trueUser();

    }



    public static void trueUser() throws IOException{
        String filename="d:/user.txt";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new FileWriter(filename,true))) {
            System.out.println("Enter login and password");
            String logAndPass = in.readLine();
            String [] result = logAndPass.split(" ");

            for (User user : users) {
                if ((user.login.equals(result[0])) && (user.password.equals(result[1]))) {
                    System.out.println("Confirmed");
                    return;
                }
            }
            out.write("");
            out.write(result[0]+"\t"+result[1]);
            System.out.println("New User was added");


        }
    }
}
