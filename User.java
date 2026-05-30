package libraryProject;

class User {
    int userId;
    String name;
    String userName;
    String passWord;

    User(int UserId,String name,String userName,String passWord){
        this.userId=userId;
        this.name=name;
        this.userName=userName;
        this.passWord=passWord;
    }
    public String getUserName(){
        return userName;
    }
    public int getUserId(){
        return userId;
    }
    public String getPassWord(){
        return passWord;
    }
}
