

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Никита on 24.02.2016.
 */
public class YPChat {
    public static String FileName = "log.txt";

    public static void outMenu() {
        System.out.println("Download from file- |fromFile \npass");
        System.out.println("Download to file- |toFile \npass");
        System.out.println("Add one message from file- |addOneFromFile \npass");
        System.out.println("Add several messages from file- |addSeveralFromFile \npass");
        System.out.println("Remove by id- |removeID \nid");
        System.out.println("History by author- |historyAuthor \nauthor");
        System.out.println("History by words- |historyWords \nwords");
        System.out.println("History by time- |historyTime \ntimestamp");
        System.out.println("History by RegEx- |historyRegEx \nregEx");
        System.out.println("Out to Console- |outConsole");
        System.out.println("End- |end");
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        Scanner sc = new Scanner(System.in);
        String comand;
        String adress;
        Worker worker1;
        outMenu();
        comand = sc.next();
        int i = 0;
        while (!comand.equals("|end")) {
            i++;
            switch (comand) {
                case "|outConsole":
                    if(!worker.outToConsole()){
                       System.err.println("History is empty!");
                    }
                    break;
                case "|fromFile":
                    adress = sc.next();
                    try {
                        worker.fiilList(adress);
                    } catch (IOException e) {
                        System.err.println("Wrong pass of file!");
                    }catch (com.google.gson.JsonSyntaxException e){
                        System.err.println("Data file do not match!");
                    }
                    break;
                case "|toFile":
                    adress = sc.next();
                    try {
                        worker.outToFile(adress);
                    } catch (IOException e) {
                        System.err.println("Wrong pass of file!");
                    }
                    break;
                case "|addOneFromFile":
                    adress = sc.next();
                    try {
                        worker.addToListFromFile(adress);
                    } catch (IOException e) {
                        System.err.println("Wrong pass of file!");
                    }catch (com.google.gson.JsonSyntaxException e){
                        System.err.println("Data file do not match!");
                    }
                    break;
                case "|addSeveralFromFile":
                    adress = sc.next();
                    try {
                        worker.fiilList(adress);
                    } catch (IOException e) {
                        System.err.println("Wrong pass of file!");
                    }catch (com.google.gson.JsonSyntaxException e){
                        System.err.println("Data file do not match!");
                    }
                    break;
                case "|removeID":
                    adress = sc.next();
                    worker.removeByID(adress);
                    break;
                case "|historyAuthor":
                    sc.nextLine();
                    adress = sc.nextLine();
                    worker1 = new Worker(worker.findByAuthor(adress));
                    if(!worker1.outToConsole()){
                        System.err.println("History by author is empty!");
                    }
                    break;
                case "|historyWords":
                    sc.nextLine();
                    adress = sc.nextLine();
                    worker1 = new Worker(worker.findByWords(adress));
                    if(!worker1.outToConsole()){
                        System.err.println("History by words is empty!");
                    }
                    break;
                case "|historyTime":
                    adress = sc.next();
                    worker1 = new Worker( worker.findByTimeLastMessage(Long.valueOf(adress)));
                    if(!worker1.outToConsole()){
                        System.err.println("History by time is empty!");
                    }
                    break;
                case "|historyRegEx":
                    sc.nextLine();
                    adress = sc.nextLine();
                    worker1 = new Worker( worker.findByRegEx(adress));

                    if(!worker1.outToConsole()){
                        System.err.println("History by RegEx is empty!");
                    }
                    break;
                case "|end":
                    System.err.println("Thanks for work");
                    return;
                default:
                    System.err.println("Wrong command");
                    break;
            }
            if (i == 4) {
                outMenu();
                i = 0;
            }
            comand = sc.next();
        }

    }
}
