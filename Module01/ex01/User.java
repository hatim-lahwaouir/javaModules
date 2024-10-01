class User {

    final int ID;
    User(){
        this.ID = UserIdsGenerator.getInstance().generateId();
    
        System.out.printf("Hello from <id: %d>\n", this.ID);
    }

}