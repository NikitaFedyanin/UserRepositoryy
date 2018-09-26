package User;

import java.io.IOException;
import java.util.ArrayList;

public interface UserRepository {
    ArrayList<User> getAll();
    User findByLogin(String login);
    void delete(User user) throws IOException;
    void save(User user) throws IOException;
    void deleteAll()throws IOException;

}
