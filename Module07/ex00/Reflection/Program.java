import classes.User;
import classes.Car;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.lang.reflect.Parameter;


class Program{

    public static void main(String [] args) throws Exception {


        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        Class user = User.class;
        Class car = Car.class;
        String input;
        Class  chosenClass = null;

        
        System.out.println("Classes");
        System.out.println(user.getSimpleName());
        System.out.println(Car.class.getSimpleName());
        System.out.println("---------------------");
        
        input = scanner.next().trim();

        
        if (input.equals(user.getSimpleName()))
            chosenClass = User.class;
        if(input.equals(car.getSimpleName()))
            chosenClass = Car.class;

        
        if (chosenClass == null){
            System.err.println("Invalid chosen Class ");
            System.exit(1);
        }

        System.out.println("fields:");
        for (Field field : chosenClass.getDeclaredFields())
        {
            System.out.println("   " + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods:");

        for (Method method : chosenClass.getDeclaredMethods()){

            System.out.println("   " + method.getReturnType().getSimpleName() + " " +method.getName());
        }
        System.out.println("---------------------");
        System.out.println("Let's create an object.");


        Constructor selectedConstructor = null;


        for (Constructor cons : chosenClass.getConstructors())
        {
            if (cons.getParameters().length != 0)
                selectedConstructor = cons;
        }
        if (selectedConstructor == null)
        {
            System.err.println("No constructor found ! ");
            System.exit(1);            
        }

        Parameter[] parameters = selectedConstructor.getParameters();
        Object arguments [] = new Object [parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            System.out.printf("Enter value for " + parameters[i].getName() + " (" + parameters[i].getType().getSimpleName() + "): ");
            input = scanner.next();
            arguments[i] = parseArgument(input, parameters[i].getType());
        }
        
        Object instance = selectedConstructor.newInstance(arguments);

            // Print the created instance
        System.out.println("Object created: " + instance);
        System.out.println("---------------------");
        System.out.println("Enter name of the field for changing:");


        for (Field field : chosenClass.getDeclaredFields()){
            field.setAccessible(true);
            System.out.println(field.getName() + " :");
            System.out.print("->");
            input = scanner.next();
            field.set(instance,parseArgument(input, field.getType()));
        }
        System.out.println("Object updated: " + instance);
        System.out.println("---------------------");
        System.out.println("Enter name of the method for call:");
        input = scanner.next().trim();

        Method methodtoRun = null;
        for (Method method : chosenClass.getMethods()){
            if (input.startsWith(method.getName()))
                methodtoRun = method;
        }

        Class <?> paramsType [] = methodtoRun.getParameterTypes();
        Object methodArguments [] = new Object [paramsType.length];
    

        for (int i = 0; i < paramsType.length; i++){
            System.out.println("Enter " + paramsType[i].getSimpleName() + " value :");
            input = scanner.next();
            methodArguments[i] =  parseArgument(input, paramsType[i]);
        }

        Object result = methodtoRun.invoke(instance, methodArguments);

        if (result != null)
            System.out.println("Method return: " + result);


    }



    private static Object parseArgument(String input, Class<?> type) {
        if (type == int.class) {
            return Integer.parseInt(input);
        } else if (type == double.class) {
            return Double.parseDouble(input);
        } else if (type == float.class) {
            return Float.parseFloat(input);
        } else if (type == boolean.class) {
            return Boolean.parseBoolean(input);
        } else if (type == long.class) {
            return Long.parseLong(input);
        }
        return input; // Default to String or other object types
    }




}