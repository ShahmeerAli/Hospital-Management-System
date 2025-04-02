package org.example;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Project {
    static LocalDate date = LocalDate.now();
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    static String[] DoctorList = new String[6];
    static int DoctorCount = 0;

    static String[] NeurologyList = new String[7];
    static int NeurologyCount = 0;

    static String[] OrthopedicList = new String[4];
    static int OrthoCount = 0;
    static String[] CardiologyList = new String[9];

    static int CardioCount = 0;
    static String[] TestRequests = new String[7];
    static int TestCount = 0;
    static String[] EmergencyBed = new String[10];
    static int EmergencyCount = 0;

    //---------------------------------------------------------LAB REPORTS-------------------------------------------//
    private static void LabReports() {
        //CHECKS THE LAB RECORD FOR THE REPORTS
        System.out.println("Welcome to Lab Record");
        System.out.println("Enter the Name of the Patient");
        int rd = random.nextInt(2);
        String PatientName = input.next();
        input.nextLine();
        boolean recordFound = false;

        if (rd == 1) {
            for (int i = 0; i < TestCount; i++) {
                if (TestRequests[i] != null && TestRequests[i].contains(PatientName + " ")) {
                    System.out.println("-------------------------------------------LAB RECORDS---------------------------------------------------------------");
                    System.out.println("Name     age     Test Name     WARD      Approval Days        Result");
                    System.out.println(TestRequests[i] + "      " + "      Positive");
                    recordFound = true;
                }
            }

            if (!recordFound) {

                System.out.println("-----------------------------------No Record Found------------------------------");
                System.out.println("The Patient should be Admitted in the Hospital");
            }
            System.out.println();

        } else {
            for (int i = 0; i < TestCount; i++) {
                if (TestRequests[i] != null && TestRequests[i].contains(PatientName + " ")) {
                    System.out.println("-------------------------------------------LAB RECORDS---------------------------------------------------------------");
                    System.out.println("Name     age     Test Name     WARD      Approval Days        Result");
                    System.out.println(TestRequests[i] + "       " + "       Negative");
                    recordFound = true;
                }


            }
            if (!recordFound) {
                System.out.println("----------------------------------------No Record Found-------------------------------------------------------------");
            }
            System.out.println();
        }

    }


    //------------------------------------------------LAB TESTS REQUESTS-------------------------------------------------
    protected static void TestRequests() {
        //TAKES INPUT AND CHECKS FOR SLOTS. IF SLOTS ARE AVAILABLE THE PATIENT IS ADVISED TO VISIT LAB AFTER SOME DAYS
        if (TestCount == 7) {
            System.out.println("No Slots Available.Try Again After some Time");
        } else {
            System.out.println("Enter the name of the Patient:");
            String name = input.next();
            input.nextLine();
            System.out.println("Enter the Age of the Patient: ");
            int age = input.nextInt();
            System.out.println("Enter the Test Name:");
            String test = input.next();
            input.nextLine();
            System.out.println("Enter Your Ward");
            String ward = input.next();
            System.out.println();
            System.out.println("Your Request is being processed. Please Wait");
            System.out.println("Checking Available Slots");
            //GENERATES RANDOM DAYS(BETWEEN 0 AND 10)
            int date = (int) (Math.random() * 10);
            //TAKES 7 SECONDS TO  NOTIFY THE PATIENT
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println();
                    System.out.println("Request Approved");
                    System.out.println("Visit the Laboratory after " + date + " Days");
                }
            };
            timer.schedule(task, 5000);
            String info = name + "     " + age + "      " + test + "         " + ward + "       " + date;
            TestRequests[TestCount] = info;
            TestCount++;

        }
    }


    // ---------------------------FOR ADMITTING THE PATIENT------------------------------------------

    public static void AdmitPatient() {
        System.out.println("Press 1 to OPD \nPress 2 to Access Emergency");
        int option1 = input.nextInt();
        switch (option1) {
            case 1:
                System.out.println("-----------------------WELCOME TO OPD------------------------");
                int NeurologyBeds = 0;
                int OrthopedicBeds = 0;
                int CardiologyBeds = 0;
                int MaxPatient = 7;
                int MaxCap = 4;
                int MaximumBeds = 9;
                System.out.println("Select Your Ward:");
                System.out.println("Press 1 for Neurology");
                System.out.println("Press 2 for Orthopedic Ward");
                System.out.println("Press 3 for Cardiology");
                System.out.println("Press 4 to Enter Main Menu");
                int choice = input.nextInt();
                if (choice == 1) {
                    System.out.println("Press 0 to leave Neurology Ward");
                    System.out.println("Press 1 to continue");
                    int option = input.nextInt();
                    do {

                        System.out.println("------------------------Welcome to Neurology----------------------------");

                        System.out.println("Enter your Details");
                        System.out.println("Enter The Name of the Patient:");
                        String name = input.next();
                        input.nextLine();
                        System.out.println("Enter the age of the Patient");
                        int age = input.nextInt();
                        System.out.println("Enter the Gender of the Patient(M for Male and F for Female)");
                        String gender = input.next();
                        input.nextLine();
                        System.out.println("Enter the Bed No of the Patient");
                        int BedNo = input.nextInt();
                        System.out.println("Enter the ID of the Patient");
                        String id = input.next();
                        if (NeurologyBeds == MaxPatient) {
                            System.out.println("No Beds Available");
                            System.out.println();
                        } else {
                            System.out.println("Bed Allocated");
                            NeurologyBeds += 1;
                            System.out.println("The Patient has been admitted on " + date);
                            System.out.println("The Beds Remaining are  " + (MaxPatient - NeurologyBeds));
                            String Neuroinfo = name + "     " + age + "       " + gender + "        " + date;

                            NeurologyList[NeurologyCount] = Neuroinfo;
                            NeurologyCount++;
                            System.out.println();
                            System.out.println("Press 0 to leave Neurology Ward");
                            System.out.println("Press any Number to continue");
                            option = input.nextInt();
                        }
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("NeurologyPatient.txt", true
                            ));
                            writer.write(String.format("ID : %s . Name: %s. AGE: %d. Gender:%s. BedNo : %d .ADMIT DATE: %s \n", id, name, age, gender, BedNo, date));
                            writer.close();
                        } catch (IOException io) {
                            System.out.println("Error occurred while Writing the file");
                            io.printStackTrace();
                        }

                    } while (option != 0);


                } else if (choice == 2) {
                    System.out.println("Press 0 to leave Orthopedic Ward");
                    System.out.println("Press 1 to continue");
                    int option = input.nextInt();
                    do {
                        System.out.println("------------------Welcome to Orthopedic Ward-----------------");
                        System.out.println("Enter your Details");
                        System.out.println("Enter The Name of the Patient:");
                        String name = input.next();
                        input.nextLine();
                        System.out.println("Enter the age of the Patient");
                        int age = input.nextInt();
                        System.out.println("Enter the Gender of the Patient(M for Male and F for Female)");
                        String gender = input.next();
                        input.nextLine();
                        System.out.println("Enter the ID of the Patient");
                        String id = input.next();
                        System.out.println("Enter the Bed No of the Patient");
                        int BedNo = input.nextInt();

                        if (MaxCap == OrthopedicBeds) {
                            System.out.println("No Beds Available");
                            System.out.println();
                        } else {
                            OrthopedicBeds += 1;
                            System.out.println("Bed Allocated");
                            System.out.println("The Patient has been admitted on  " + date);
                            System.out.println("The Remaining Beds Available are " + ((MaxCap - OrthopedicBeds)));
                            String information = name + "      " + age + "      " + gender + "       " + date;
                            OrthopedicList[OrthoCount] = information;
                            OrthoCount++;
                            System.out.println();
                            System.out.println("Press 0 to leave Orthopedic Ward");
                            System.out.println("Press 1 to continue");
                            option = input.nextInt();
                        }
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("OrthoPatients.txt", true));
                            writer.write(String.format("ID: %s. Name : %s . Age : %d . Gender : %s . BedNo: %d  . ADMIT DATE : %s\n", id, name, age, gender, BedNo, date));
                            writer.close();
                        } catch (IOException io) {
                            System.out.println("Some Error Has Occurred While Writing the File");
                            io.printStackTrace();
                        }


                    }

                    while (option != 0);

                } else if (choice == 3) {
                    System.out.println("--------------------------Welcome to Cardiology Ward------------------------");
                    System.out.println("Press 0 to Exit");
                    System.out.println("Press 1 to Continue");
                    int option = input.nextInt();
                    do {
                        System.out.println("Enter your Details");
                        System.out.println("Enter The Name of the Patient:");
                        String name = input.next();
                        input.nextLine();
                        System.out.println("Enter the age of the Patient");
                        int age = input.nextInt();
                        System.out.println("Enter the Gender of the Patient(M for Male and F for Female)");
                        String gender = input.next();
                        input.nextLine();
                        System.out.println("Enter the ID of the Patient");
                        String id = input.next();
                        System.out.println("Enter the Bed No of  the Patient");
                        int BedNo = input.nextInt();
                        String details = name + "        " + age + "        " + gender + "        " + date;
                        if (MaximumBeds == CardiologyBeds) {
                            System.out.println("No Beds Available");
                            System.out.println();
                        } else {
                            System.out.println("Bed Allocated");
                            CardiologyBeds += 1;
                            System.out.println("The Patient has been admitted on " + date);
                            CardiologyList[CardioCount] = details;
                            CardioCount++;
                            System.out.println();
                            System.out.println("Press 0 to Exit");
                            System.out.println("Press 1 to Continue");
                            option = input.nextInt();
                        }
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter("CardiologyPatients.txt", true));
                            writer.write(String.format("ID : %s .Name : %s.  Age: %d . Gender: %s. BedNo: %d  . ADMIT DATE: %s\n", id, name, age, gender, BedNo, date));
                            writer.close();
                        } catch (IOException io) {
                            System.out.println("Some Error Occurred While Writing the File");
                            io.printStackTrace();
                        }


                    } while (option != 0);


                } else {
                    System.out.println("Wrong input!");

                }

                break;
            case 2:
                System.out.println("--------------------------Welcome to Emergency-----------------------");
                int beds = 0;
                int ask;
                do {

                    int Maximunbeds = 10;
                    System.out.println("Doctors Available");
                    System.out.println("MAXIMUM BEDS AVAILABLE = 10");
                    System.out.println("1 HouseOfficer");
                    System.out.println("2 PostGraduates");
                    System.out.println("Enter the Name of the Patient:");
                    String name = input.next();
                    input.nextLine();
                    System.out.println("Enter the Age of the Patient:");
                    int age = input.nextInt();
                    System.out.println("Enter the Gender of the Patient (M of Male F for Female):");
                    String gender = input.next();
                    System.out.println("Enter the initial Diagnostics of the Patient:");
                    String diagnosis = input.next();
                    System.out.println("Enter the ID of the Patient");
                    String id = input.next();
                    input.nextLine();
                    beds += 1;
                    System.out.println("Patient " + name + " Admitted to Emergency");
                    System.out.println("The Remaining beds are  " + (Maximunbeds - beds));
                    String details = name + "       " + age + "        " + gender + "          " + diagnosis + "        " + date;
                    EmergencyBed[EmergencyCount] = details;
                    EmergencyCount++;
                    System.out.println();
                    System.out.println("Press any digit to continue:");
                    System.out.println("Press 0 to exit");
                    ask = input.nextInt();
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("EmergencyPatient.txt", true));
                        writer.write(String.format("ID : %s. Name : %s . Age : %d . Gender : %s . Initial Diagnostics : %s . ADMIT DATE :%s\n", id, name, age, gender, diagnosis, date));
                        writer.close();
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                    if (Maximunbeds == beds) {
                        System.out.println("No Beds Available");
                        System.out.println("Contact Admission Officer");
                        break;
                    }
                    System.out.println("-----------------------------------------------------------------------");

                } while (ask != 0);
            default:
                break;

        }
        System.out.println();


    }

    //------------------------------------- FOR DOCTOR REGISTRATION--------------------------------------------
    protected static void DoctorReg() {
        System.out.println("Press 1 to Register Yourself");
        System.out.println("Press 2 to login");
        System.out.println("Press 3 to Enter Main Menu");

        int answer = input.nextInt();
        if (answer == 1) {

            System.out.println("Enter your Name:");
            String name = input.next();
            input.nextLine();
            System.out.println("Enter your CNIC:");
            String CnicNo = input.next();
            while (CnicNo.length() != 13) {
                System.out.println("Your Cnic should of 13 Numbers");
                CnicNo = input.next();
            }
            System.out.println("Enter the Field of your Specialization");
            String field = input.next();
            System.out.println("Enter the Years of Experience:");
            int years = input.nextInt();
            System.out.println("Profile Created");
            System.out.println("Doctor Registered Successfully");
            DoctorCount++;
            System.out.println();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("DoctorDetails.txt", true));
                writer.write(String.format("Name : %s. CNICNo : %s. Field : %s\n", name, CnicNo, field));
                writer.close();
            } catch (IOException io) {
                io.printStackTrace();
                System.out.println("Some Error has occurred while Writing the File");
            }

        } else if (answer == 2) {
            ArrayList<String> check = new ArrayList<>();
            boolean acccountFound = false;
            try {
                BufferedReader reader = new BufferedReader(new FileReader("DoctorDetails.txt"));
                String info;
                while ((info = reader.readLine()) != null) {
                    check.add(info);
                }
            } catch (IOException io) {
                io.printStackTrace();
            }
            System.out.println("Enter the CNIC NO:");
            String cnic = input.next();

            String[] array = check.toArray(new String[0]);
            for (int i = 0; i < array.length; i++) {
                if (array[i].contains(cnic)) {
                    acccountFound = true;
                }
            }
            if (acccountFound) {
                System.out.println("Login SuccessFul");
                System.out.println("Press 1 to see Patient History");
                System.out.println("Press 2 to view Patient Reports");
                System.out.println("Press 3 to logout");
                int ans = input.nextInt();
                if (ans == 1) {
                    System.out.println("Enter your Field");
                    System.out.println("Press 1 for Neurology");
                    System.out.println("Press 2 for Orthopedic");
                    System.out.println("Press 3 for Cardiology");
                    System.out.println("Press 4 for Emergency");
                    int field = input.nextInt();
                    if (field == 1) {
                        try {
                            System.out.println("---------------------------------------------------------------------");
                            BufferedReader reader = new BufferedReader(new FileReader("NeurologyPatient.txt"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            reader.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }

                    } else if (field == 2) {
                        try {
                            System.out.println("------------------------------------------------------");
                            BufferedReader reader = new BufferedReader(new FileReader("OrthoPatients.txt"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            reader.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    } else if (field == 3) {
                        try {
                            System.out.println("------------------------------------------------------");
                            BufferedReader reader = new BufferedReader(new FileReader("CardiologyPatients.txt"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            reader.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    } else if (field == 4) {
                        try {
                            System.out.println("------------------------------------------------------");
                            BufferedReader reader = new BufferedReader(new FileReader("EmergencyPatient.txt"));
                            String line;
                            while ((line = reader.readLine()) != null) {
                                System.out.println(line);
                            }
                            reader.close();
                        } catch (IOException io) {
                            io.printStackTrace();
                        }
                    } else {
                        System.out.println("Wrong Input!");
                    }

                } else if (ans == 2) {
                    LabReports();
                } else if (ans == 3) {
                    System.out.println();
                }

            } else {
                System.out.println("No Account Found");
            }

        } else if (answer == 3) {

        }
    }

    //-------------------------------DOCTOR DETAILS-------------------------------------------------

    public static void DocDetails() {
        try {
            System.out.println("Press 1 to see the Details");
            System.out.println("Press 2 to go back");
            int option = input.nextInt();
            if (option == 1) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("DoctorDetails.txt"));
                    String details;
                    while ((details = reader.readLine()) != null) {
                        System.out.println(details);
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                }
            } else if (option == 2) {

            } else {
                System.out.println("Invalid Option");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");

        }


    }


    //-------------------------------FOR PATIENT DETAILS------------------------------------------

    protected static void PatientDetails() {

        System.out.println("Press 1 to see Patient Details \n Press 2 to Discharge the Patient");
        int choice = input.nextInt();
        if (choice == 1) {
            String Passcode = "we34";
            System.out.println("Pass code is we34");
            System.out.println("Enter the Passcode to Access");
            String code = input.next();

            if (Passcode.matches(code)) {
                System.out.println("------------------------Neurology ward------------------");

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("NeurologyPatient.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("-------------------------Orthopedic Ward------------------- ");
                try {
                    BufferedReader reader1 = new BufferedReader(new FileReader("OrthoPatients.txt"));
                    String details;
                    while ((details = reader1.readLine()) != null) {
                        System.out.println(details);
                    }
                } catch (IOException ei) {
                    ei.printStackTrace();
                }
                System.out.println("----------------------Cardiology Ward------------------------");
                try {
                    BufferedReader reader2 = new BufferedReader(new FileReader("CardiologyPatients.txt"));
                    String info;
                    while ((info = reader2.readLine()) != null) {
                        System.out.println(info);
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }

                System.out.println("--------------------EMERGENCY WARD-----------------------------");
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("EmergencyPatient.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                }
            } else {
                System.out.println("Access Denied");
                System.out.println("Enter the Passcode to Access");
                code = input.next();
            }
//-----------------------------------------------------------------------------------------------------------------------------------------------
            //DISCHARGING THE PATIENT
        } else if (choice == 2) {
            System.out.println("Select the Ward.");
            System.out.println("Press 1 for Neurology \n Press 2 for Orthopedic \n Press 3 for Cardiology \n Press 4 for Emergency");
            int answer = input.nextInt();
            ArrayList<String> NeurologyRecords = new ArrayList<>();
            if (answer == 1) {

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("NeurologyPatient.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        NeurologyRecords.add(line);

                    }

                } catch (IOException io) {
                    io.printStackTrace();
                }

                System.out.println("Enter the ID of the Patient you want to discharge");
                String id = input.next();
                input.nextLine();

                //ARRAYLIST.I WANT TO REMOVE THE INDEX IF THE VALUE MATCHES
                ArrayList<String> temp = new ArrayList<>();

                for (String Record : NeurologyRecords) {
                    if (!Record.contains(id)) {
                        temp.add(Record);
                    }

                }
                NeurologyRecords = temp;

                try {
                    BufferedWriter newwriter = new BufferedWriter(new FileWriter("NeurologyPatient.txt", false));

                    for (String record : NeurologyRecords) {

                        newwriter.write(record);
                        newwriter.newLine();


                    }
                    newwriter.close();
                } catch (IOException io) {
                    System.out.println("Some Error has Occurred while writing the file");
                    io.printStackTrace();
                }


            } else if (answer == 2) {

                ArrayList<String> OrthoPedicRecords = new ArrayList<>();

                try {
                    BufferedReader reader = new BufferedReader(new FileReader("OrthoPatients.txt"));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        OrthoPedicRecords.add(line);

                    }
                } catch (IOException io) {
                    io.printStackTrace();
                    System.out.println("Some Error has occurred while reading the file");
                }


                System.out.println("Enter the ID of the Patient you want to Discharge");
                String id = input.next();
                ArrayList<String> temp = new ArrayList<>();
                for (String value : OrthoPedicRecords) {
                    if (!value.contains(id)) {
                        temp.add(value);
                    }
                }

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("OrthoPatients.txt", false));
                    for (String records : temp) {
                        writer.write(records);
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException io) {
                    io.printStackTrace();
                    System.out.println("Some Error has Occurred while Writing the file");

                }


            } else if (answer == 3) {
                ArrayList<String> CardioRecords = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("CardiologyPatients.txt"));
                    String records;
                    while ((records = reader.readLine()) != null) {
                        CardioRecords.add(records);
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                    System.out.println("Error occurred while reading the file");
                }
                System.out.println("Enter the ID of the Patient you want to Discharge");
                String id = input.next();
                ArrayList<String> temp = new ArrayList<>();
                for (String value : CardioRecords) {
                    if (!value.contains(id)) {
                        temp.add(value);
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("CardiologyPatients.txt", false));
                    for (String Records : temp) {
                        writer.write(Records);
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException io) {
                    io.printStackTrace();
                    System.out.println("Some Error has Occurred while Writing the file");
                }
            } else if (answer == 4) {
                ArrayList<String> EmergencyRecords = new ArrayList<>();
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("EmergencyPatient.txt"));
                    String info;
                    while ((info = reader.readLine()) != null) {
                        EmergencyRecords.add(info);
                    }
                } catch (IOException io) {
                    io.printStackTrace();
                    System.out.println("Some Error has occurred while reading the file");
                }
                ArrayList<String> temp = new ArrayList<>();
                System.out.println("Enter the ID of the Patient you want to Discharge");
                String id = input.next();
                for (String value : EmergencyRecords) {
                    if (!value.contains(id)) {
                        temp.add(value);
                    }
                }
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("EmergencyPatient.txt", true));
                    for (String Records : temp) {
                        writer.write(Records);
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException io) {
                    io.printStackTrace();
                    System.out.println("Some Error has occurred while Writing the file");
                }
            }

        }
    }


    //----------------------------------------------Random DOCTOR-------------------------------------
    public static String randomdoctor() {
        Random random2 = new Random();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("DoctorDetails.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                DoctorList[DoctorCount] = line;

            }
            reader.close();
        } catch (IOException ie) {
            ie.printStackTrace();
            System.out.println("Error");
        }
        int Doctor = random2.nextInt(DoctorList.length);

        return DoctorList[Doctor];
    }

    //-----------------------------------------------APPOINTMENT DETAILS-----------------------------------
    public static void Appointment() {
        System.out.println("Enter your Name");
        String name = input.next();
        input.nextLine();
        System.out.println("Enter your age");
        int age = input.nextInt();
        System.out.println("Enter Your Phone Number");
        String number = input.next();
        System.out.println("Please Enter your Email:");
        String PatientMail = input.next();
        String RandomDoctor = randomdoctor();
        String message = "Thanks for Booking an Appointment in our Hospital.\n Your Appoinment has been booked with \nDoctor " + RandomDoctor + "\n Please Visit the Hospital at the Time Given \n This is a System Generated Email. Incase of any Query Please Mail us at qshahmeer647@gamil.com";
        String subject = "Appointment Booking";
        String to = PatientMail;
        EmailSender.EmailNotification(to, subject, message);

        System.out.println("Your Appointment Has been Booked with the Doctor  " + RandomDoctor);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
        int days = (int) (Math.random() * 10);
        System.out.println("Visit the Hospital After " + days + "  Days");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("AppoinmentDetails.txt", true));
            writer.write(String.format("Patient Name: %s . Age : %d  Phone Number : %s  .  Doctor %s \n", name, age, number, RandomDoctor));
            writer.close();
        } catch (IOException io) {
            System.out.println("Some Error Has occurred while writing the file");
        }


    }

    //----------------------------------------MAIN FUNCTION----------------------------------------------------------
    public static void main(String[] args) {
        boolean progarm = true;
        int option;
        while (progarm) {
            // MAIN FUNCTION
            System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.println("---------------------------------------------Welcome to Hospital XYZ---------------------------------------------------");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");

            System.out.println("SELECT AN OPTION TO PROCEED");
            System.out.println("Press 1 for ADMISSION BRANCH");
            System.out.println("Press 2 for Doctor Registration");
            System.out.println("Press 3 for Patient Details");
            System.out.println("Press 4 for Lab Tests Requests");
            System.out.println("Press 5 for Lab reports");
            System.out.println("Press 6 for Doctor Details");
            System.out.println("Press 7 to Book an Appointment");
            System.out.println("Press 8 to Exit");

            try {
                System.out.println("Enter any Number form Above");
                option = input.nextInt();


                switch (option) {
                    case 1:
                        System.out.println("------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("----------------------------------Welcome to Admin Branch-------------------------------");
                        System.out.println("------------------------------------------------------------------------------------------------------------------------");
                        AdmitPatient();
                        break;
                    case 2:
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("--------------------------------------------------------Welcome to Doctor's Portal------------------------------------------------------");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        DoctorReg();
                        break;
                    case 3:
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("----------------------------------------Welcome to Patient Details--------------------------- ");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        PatientDetails();
                        break;
                    case 4:
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("-----------------------------------------------Welcome to Lab------------------------------");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        TestRequests();
                        break;
                    case 5:
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("--------------------------------------------Welcome to Lab Records--------------------------");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        LabReports();
                        break;
                    case 6:
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("------------------------------------------Doctor Details------------------------------------");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        DocDetails();
                        break;
                    case 7:
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("----------------------------------------------Welcome to Patient Booking-----------------------------------");
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
                        Appointment();
                        break;
                    case 8:
                        System.out.println("-----------------------------------------------------------------------");
                        System.out.println("                 Thank you for Visiting OUR HOSPITAL");
                        System.out.println("------------------------------------------------------------------------");
                        progarm = false;
                        break;

                }


            } catch (InputMismatchException ime) {
                System.out.println("Invalid Input");
                System.out.println("Select  again");
                input.nextLine();


            }
        }
    }
}

