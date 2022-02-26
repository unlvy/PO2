import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) {

        Lab10A lab10 = new Lab10A();
        try {

            System.out.println("\nFields:");
            Field[] fields = Class.forName("Lab10A").getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isPublic(field.getModifiers())) {
                    System.out.println("Field: " + field.getName() + ", value: " + field.get(lab10));
                }
            }

            System.out.println("\nMethods:");

            Method[] methods = Class.forName("Lab10A").getDeclaredMethods();
            for (Method method : methods) {
                if (Modifier.isPublic(method.getModifiers())) {
                    Parameter[] params = method.getParameters();
                    StringBuilder cArgs = new StringBuilder();
                    for (Parameter param : params) {
                       cArgs.append(param.getType()).append(" ");
                    }
                    System.out.println("Method: " + method.getName() + ", params: " +  cArgs.toString());
                }
            }

            System.out.println("\nMethods results:");
            System.out.println(lab10.hfkenf());
            System.out.println(lab10.oqfgnr(new Object()));
            System.out.println(lab10.asdfrd(1, 2, 3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
