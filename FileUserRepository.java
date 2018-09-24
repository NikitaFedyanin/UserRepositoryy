package User;

import java.util.ArrayList;

public class FileUserRepository implements UserRepository {

    @Override
    public ArrayList<User> getAll() {
        return User.users;
    }

    @Override
    public User findByLogin(String login) {
        User result = null;

        for (User example : User.users){
            if (example.login.equals(login)) {
                result = example;
                break;
            }
        }
        return result;
    }

    @Override
    public void delete(User user) {
        for (User example : User.users){
            if (example.equals(user))
                User.users.remove(example);
        }
    }

    @Override
    public void save(User user) {
        User.users.add(user);
    }

    @Override
    public void deleteAll() {
        User.users.clear();
    }
}
