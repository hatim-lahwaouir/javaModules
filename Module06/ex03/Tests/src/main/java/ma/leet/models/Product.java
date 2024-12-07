package ma.leet.models;




public class Product{
    Long id;
    Long price;
    String name;


    public Product(Long id, Long price, String name){
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public Long getId(){
        return new Long(this.id);
    }

    public String getName(){
        return new String(this.name);
    }

    public Long getPrice(){
        return this.price;
    }



    public  void setName(String name){
        this.name = name;
    }

    public void setPrice(Long price){
        this.price  = price;

    }

    public void setId(Long id){
        this.id = id;
    }


    public boolean equals(Product product){

        if (product == null)
            return false;
        if (!product.id.equals(product.id))
            return false;
        if (!product.name.equals(product.name))
            return false;
        if (!product.price.equals(product.price))
            return false;

        return true;
    }


    public String  toString(){

        return "id=" + id + " name=" + name + " price=" + price;

    }
}