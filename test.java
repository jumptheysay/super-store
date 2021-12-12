import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

class MinorCitizenException extends Exception{
        String s;
        public MinorCitizenException(String s) {
        this.s = s;
        }
}

class Employee implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private double hour;
    private double hourlyWage;
    int id;
    String name;
    String aadhaar;
    int age;
    String type;
    public Employee(double hour, double hourlyWage, int id, String aNo, String name, int age) {
        this.hour = hour;
        this.hourlyWage = hourlyWage;
        this.id = id;
        this.age = age;
        this.name = name;
        this.aadhaar = aNo;
    }
    public void display(){
        System.out.println("\t\t EMPLOYEE DETAILS \n");
        System.out.println("Name : \t"+this.name);
        System.out.println("ID : \t"+this.id);
        System.out.println("Type : \t"+this.type);
        System.out.println("Age : \t"+ this.age);
        System.out.println("Aadhaar : \t"+this.aadhaar);
        System.out.println("Hours worked : \t"+this.hour);
        System.out.println("\n");
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public double getHour() {
        return hour;
    }
}

class Cashier extends Employee{
    public Cashier(double hour, double hourlyWage, int id, String aNo, String name, int age) {
        super(hour, hourlyWage, id, aNo, name, age);
        super.type = "Cashier";
    }
}
class Janitor extends Employee{
    public Janitor(double hour, double hourlyWage, int id, String aNo, String name, int age) {
        super(hour, hourlyWage, id, aNo, name, age);
        super.type = "Janitor";
    }
}
class Manager extends Employee{
    public Manager(double hour, double hourlyWage, int id, String aNo, String name, int age) {
        super(hour, hourlyWage, id, aNo, name, age);
        super.type = "Manager";
    }
}
class AisleWorkers extends Employee{
    public AisleWorkers(double hour, double hourlyWage, int id, String aNo, String name, int age) {
        super(hour, hourlyWage, id, aNo, name, age);
        super.type = "Aisle Worker";
    }
}


public class test {
    public static boolean hasAadhar(String aadhar) throws NullPointerException{
        if(aadhar.length() != 12 ||
                !aadhar.matches("[0-9]*")) {
            throw new NullPointerException();
        }
        return true;
    }
    public static Employee createNew(String type){
        Scanner s = new Scanner(System.in);
        System.out.println("\t\t New Employee Inputs \n");
        System.out.println("Name : ");
        String name = s.nextLine();
        System.out.println("ID : ");
        int id = s.nextInt();
        System.out.println("Aadhaar : ");
        String aadhar = s.next();
        try {
            hasAadhar(aadhar);
        }catch (NullPointerException e){
            System.out.println("Aadhar format mismatch ");
            System.out.println("Enter aadhar again : ");
            aadhar = s.next();
        }
        System.out.println("Age : ");
        int age = s.nextInt();
        System.out.println("Hourly wage : ");
        double wage = s.nextDouble();
        System.out.println("Enter hours worked");
        double hours = s.nextDouble();
       // T a = (T)(new Employee());
        if(type.equalsIgnoreCase("janitor"))
            return  new Janitor(hours, wage, id, aadhar, name, age);
        else if(type.equalsIgnoreCase("cashier"))
            return new Cashier(hours,wage,id,aadhar,name,age);
        else if(type.equalsIgnoreCase("aisleworker"))
            return new AisleWorkers(hours,wage,id,aadhar,name,age);
        else if(type.equalsIgnoreCase("manager"))
            return new Manager(hours,wage,id,aadhar,name,age);
        return  new Employee(hours,wage,id,aadhar,name,age);
    }
    public static void main(String[] args){
        test objectIO = new test();
        File empList = new File("empList.txt");
        FileOutputStream file_out_stm = null;
        try {
            file_out_stm = new FileOutputStream("empList.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream obj_out_stm = null;
        try {
            obj_out_stm = new ObjectOutputStream(file_out_stm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream file_in_stm = null;
        try {
            file_in_stm = new FileInputStream("empList.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream obj_in_stm= null;
        try {
             obj_in_stm = new ObjectInputStream(file_in_stm);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner s = new Scanner(System.in);
        Employee[] employees = new Employee[20];
        int size = 0;

        try {
            Scanner reader = new Scanner(empList);
            while(reader.hasNext()){
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String opt;
        do{
            opt=s.nextLine();
            if(opt.equalsIgnoreCase("add cashier")){
                String type = "cashier";
                employees[size]= createNew(type);
                try {
                    obj_out_stm.writeObject("Java");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                size++;
            }
            if(opt.equalsIgnoreCase("add janitor")){
                String type = "janitor";

                employees[size]= createNew(type);
                try {
                    obj_out_stm.writeObject("Java");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                size++;
            }
            if(opt.equalsIgnoreCase("add manager")){
                String type = "manager";

                employees[size]= createNew(type);
                try {
                    obj_out_stm.writeObject("Java");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                size++;
            }
            if(opt.equalsIgnoreCase("add aisleworker")){
                String type = "aisleworker";

                employees[size]= createNew(type);
                try {
                    obj_out_stm.writeObject("Java");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                size++;
            }
            if(opt.equalsIgnoreCase("display records")) {
                try {
                    for (Employee e : employees)
                        e.display();

                } catch (NullPointerException e) {
                    System.out.println();
                }

            }
            else if(opt.equalsIgnoreCase("display file")) {
                for (int i = 0; i < size; ++i) {
                    Employee employee = null;
                    try {
                        employee =  (Employee) obj_in_stm.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    employee.display();
                }
            }
        } while (!opt.equalsIgnoreCase("exit"));
    }
}
