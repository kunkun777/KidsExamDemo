package mapper;


import entity.User;

public interface UserDao {

    public User login(User user);

    public boolean register(User user);

}
