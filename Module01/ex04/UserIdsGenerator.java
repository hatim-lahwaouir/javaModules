class UserIdsGenerator{

    static int id = 1;

    // serIdsGenerator.getInstance().generateId();

    static public UserIdsGenerator getInstance(){

        return new UserIdsGenerator();
    }

    public int generateId(){
        return id++;
    }

}