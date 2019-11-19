package lk.ijse.dep.hms.principle;

public class UserLogin {

    private static String userID;
    private static String userPassword;

    public UserLogin() {
    }




    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserLogin.userID = userID;
    }

    public static String getUserPassword() {
        return userPassword;
    }

    public static void setUserPassword(String userPassword) {
        UserLogin.userPassword = userPassword;
    }
}
