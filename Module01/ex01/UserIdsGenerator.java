class UserIdsGenerator{
    static int id = 0;
    static UserIdsGenerator instance;

    public int generateId(){
        return UserIdsGenerator.id++;
    }

    static UserIdsGenerator getInstance(){
        if (UserIdsGenerator.instance == null)
            UserIdsGenerator.instance = new UserIdsGenerator();
        return UserIdsGenerator.instance;
    }
}