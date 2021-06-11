package utils.query;

public class UsersQueryUtils {
    public static final String USER_BY_USERNAME_QUERY ="Select n From User n WHERE n.username = :username";
    public static final String USER_LOGIN_QUERY ="Select n From User n WHERE n.username = :username and  n.password = :password";
    public static final String USER_BY_EMAIL_QUERY ="Select n from User n where n.email = :email";
    public static final String UPDATE_USER_QUERY="Update User n Set n.email=:email, n.name=:name, n.password=:password, n.money=:money where n.username=:username";
    public static final String DELETE_USER_QUERY="Delete From User n Where n.idUser=:idUser";
    public static final String USER_SELECT_QUERY="SELECT n FROM User n";
}
