package HUMANOID_FAMILY;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;


public class Humanoid {


    // GETS CLASS NAMES FROM CURRENT WORKING DIRECTORY (main.java)
    public static ArrayList<String> ls_dir() {
        // INIT FILES
        String[] pathnames;
        String java_file_name = "";
        ArrayList<String> CLASS_MODELS_OUTPUT = new ArrayList<String>();

        //ADD TO MODELS ARRAY
        File f = new File(System.getProperty("user.dir") + "/src/HUMANOID_FAMILY");
        pathnames = f.list();
        //FOR EVERY FILE PATH
        for (String pathname : pathnames) {
            //FILE EXTENSION LOOKUP
            if (pathname.endsWith(".java")) {
                java_file_name = pathname.split("[.]")[0];
                CLASS_MODELS_OUTPUT.add(java_file_name);
            }
        }
        return CLASS_MODELS_OUTPUT;
    }


    // CHECKS INTERACTION OF TWO CLASSES TO BE POLYMORPHIC
    public static boolean check_instances(Object other) {
        String clean_data_class = "";
        String other_class = other.getClass().getName();
        ArrayList<String> recognizable_obs = Humanoid.ls_dir();
        boolean intractable_other = false;


        // SANITIZE DATA
        int counter = other_class.length() - 1;
        boolean down = true;
        while (true) {

            if (other_class.charAt(counter) == '.') {
                down = false;
                counter++;
            }

            if (down) {
                counter--;
            } else {
                clean_data_class += other_class.charAt(counter);
                counter++;
            }

            if (counter == other_class.length()) break;

        }

        //CHECK IF INTRACTABLE
        //FOR EVERY CLASS INSTANCE WITH HUMANOID
        if (recognizable_obs.contains(clean_data_class)) {
            intractable_other = true;
        }

        return intractable_other;
    }


    public static HashMap<String, HashMap<String, Method>> setters_and_getters(Object other) {
        HashMap<String, HashMap<String, Method>> output = new HashMap<String, HashMap<String, Method>>();

        //CHECK IF OTHER IS INTRACTABLE
        boolean intractable_other = Humanoid.check_instances(other);


        //GUARD CAUSE
        if (!intractable_other) return null;


        //POPULATE USABLE FUNCTIONS FROM INTERACTIVE OBJECTS LISTS
        try {
            Class<?> obj = Class.forName(other.getClass().getName());
            Method method = null;
            Method[] methods = obj.getMethods();
            HashMap<String, Method> setter_functions = new HashMap<String, Method>();
            HashMap<String, Method> getter_functions = new HashMap<String, Method>();
            HashMap<String, Method> functional_functions = new HashMap<String, Method>();
            String function_temp_name;
            boolean down;
            int counter;

            for (int i = 0; i < methods.length - 1; i++) {
                //System.out.println(methods[i]);

                //LIST ALL FUNCTIONS INTO ARRAYList
                counter = String.valueOf(methods[i]).length() - 1;
                down = true;
                function_temp_name = "";
                while (true) {
                    if (String.valueOf(methods[i]).charAt(counter) == '.') {
                        down = false;
                        counter++;
                    }
                    if (down) {
                        counter--;
                    } else {
                        function_temp_name += String.valueOf(methods[i]).charAt(counter);
                        counter++;
                    }
                    if (counter == String.valueOf(methods[i]).length()) break;
                }

                if (function_temp_name.startsWith("set")) {
                    setter_functions.put(String.valueOf(function_temp_name), methods[i]);
                    // setter_functions.put( String.valueOf(   methods[i]   )  ,  methods[i]);
                } else if (function_temp_name.startsWith("get")) {
                    // getter_functions.put(  String.valueOf(  methods[i]   ) ,   methods[i]);
                    getter_functions.put(function_temp_name, methods[i]);
                }
                else
                {
                    functional_functions.put(function_temp_name, methods[i]);
                }
            }


            //DATA SANITIZATION COMPLETE
            output.put("setters", setter_functions);
            output.put("getters", getter_functions);
            output.put("functional", functional_functions);


        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return output;
    }


    public static Method fetchMethod(HashMap<String, Method> methods, String command) {

        Object[] methods_list = methods.keySet().toArray();
        for (int x = 0; x < methods_list.length; x++) {
            // System.out.println(methods_list[x]);
            if (methods_list[x].equals(command)) {
                return methods.get(methods_list[x]);
            }
        }

        return null;
        //return methods.get(methods_list[0]);
    }


    public String attack(Object other) {

        return "nothing interesting happened...";
    }


}
