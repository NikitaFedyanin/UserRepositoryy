package User;

import java.util.ArrayList;

public interface UserRepository {
    ArrayList<User> getAll();
    User findByLogin(String login);
    void delete(User user);
    void save(User user);
    void deleteAll();

}
