package User;

import java.io.*;
import java.util.ArrayList;

public class FileUserRepository implements UserRepository {

    private String filename;
    public static ArrayList<User> users;

    public FileUserRepository(String filename) throws IOException{
        this.filename = filename;
        initializeUsers();

    }

    public void initializeUsers() throws IOException{
        users = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(filename))){
            while (in.ready()){
                String pair = in.readLine();
                String [] loginAndPass = pair.split("\t");
                users.add(new User(loginAndPass[0],loginAndPass[1]));
            }
        }

    }



    @Override
    public ArrayList<User> getAll() {
        return users;
    }

    @Override
    public User findByLogin(String login) {
        User result = null;

        for (User example : users){
            if (example.getLogin().equals(login)) {
                result = example;
                break;
            }
        }
        return result;
    }

    @Override
    public void delete(User user) throws IOException {
        User forRemoveUser = null;
        for (User example : users){
            if (example.equals(user))
                forRemoveUser=example;
        }
        if (forRemoveUser!=null) {
            users.remove(forRemoveUser);
            try(BufferedWriter out = new BufferedWriter(new FileWriter(filename))){
                for (User example : users){
                    out.write(example.getLogin()+"\t"+example.getPassword()+"\r\n");
                }
            }
        }
    }

    @Override
    public void save(User user) throws IOException {
        users.add(user);
        try(BufferedWriter out = new BufferedWriter(new FileWriter(filename,true))){
                out.write(user.getLogin()+"\t"+user.getPassword()+"\r\n");
        }
    }

    @Override
    public void deleteAll() throws IOException {
        users.clear();
        try(BufferedWriter out = new BufferedWriter(new FileWriter(filename))){
            for (User example : users){
                out.write(example.getLogin()+"\t"+example.getPassword()+"\r\n");
            }
        }
    }

    public void showUsers(){
        for (User user : users)
            System.out.println(user);
    }

    public    void enterUserAndShowTrueOrAdd() throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter out = new BufferedWriter(new FileWriter(filename,true))) {
            System.out.println("Enter login and password");
            String logAndPass = in.readLine();
            String [] result = logAndPass.split(" ");

            for (User user : users) {
                if ((user.getLogin().equals(result[0])) && (user.getPassword().equals(result[1]))) {
                    System.out.println("Confirmed");
                    return;
                }
            }
            out.write("");
            out.write("\r\n"+result[0]+"\t"+result[1]);
            System.out.println("New User was added");
        }
    }
}
