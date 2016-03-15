import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Никита on 27.02.2016.
 */
public class Worker {
    private List<Message> listMessage = new ArrayList<Message>();

    public Worker(List<Message> list) {
        listMessage = list;
    }

    public Worker() {
    }
    private boolean ifNull(Message message){
        if(message.getID()==null||message.getID().isEmpty()){
           return false;
        }else if(message.getAuthor()==null||message.getAuthor().isEmpty()){
            return false;
        }else if(message.getMessage()==null){
            return false;
        }else if(message.getTimestamp()==0){
           return false;
        }
        return true;
    }
    public void fiilList(String fileName) throws IOException, com.google.gson.JsonSyntaxException {
        Gson gson = new Gson();
        Message[] messages = gson.fromJson(new FileReader(fileName), Message[].class);
        for(int i = 0; i < messages.length; i++){
            if (this.ifNull(messages[i])){
                listMessage.add(messages[i]);
            }
        }
    }

    public void addToListFromFile(String fileName) throws IOException, com.google.gson.JsonSyntaxException {
        Gson gson = new Gson();
        Message message = gson.fromJson(new FileReader(fileName), Message.class);
       if(!this.ifNull(message)){
           throw new com.google.gson.JsonSyntaxException("");
       }
        listMessage.add(message);

    }

    public boolean outToConsole() {
        if (listMessage.size() == 0) {
            return false;
        }
        Iterator<Message> iter = listMessage.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        return true;
    }

    public void addToListFromConsole(Scanner sc) {
        Message message = new Message();
        message.put(sc);
        listMessage.add(message);
    }

    public void outToFile(String fileName) throws IOException {
        String str = new Gson().toJson(listMessage);
        FileWriter writer = new FileWriter(fileName, false);
        writer.write(str);
        writer.close();
    }

    public void removeByID(String id) {
        Message mess;
        Iterator<Message> iter = listMessage.iterator();
        while (iter.hasNext()) {
            mess = iter.next();
            if (id.equals(mess.getID())) {
                iter.remove();
            }
        }
    }

    public List<Message> findByAuthor(String author) {
        Message mess;
        List<Message> byAuthorList = new ArrayList<Message>();
        Iterator<Message> iter = listMessage.iterator();
        while (iter.hasNext()) {
            mess = iter.next();
            if (author.equals(mess.getAuthor())) {
                byAuthorList.add(mess);
            }
        }
        return byAuthorList;
    }

    public List<Message> findByTimeLastMessage(long timestamp) {
        Message mess;
        List<Message> list = new ArrayList<Message>();
        List<Message> byTimeList = new ArrayList<Message>();
        for(int i = listMessage.size()-1; i >= 0 ; i--){
            if(listMessage.get(i).getTimestamp() >= timestamp){
                list.add(listMessage.get(i));
            }else{
                break;
            }
        }
        for(int i = list.size()-1; i >= 0 ; i--){
            byTimeList.add(list.get(i));
        }
        return byTimeList;
    }

    public List<Message> findByWords(String words) {
        Message mess;
        List<Message> byWordsList = new ArrayList<Message>();
        Iterator<Message> iter = listMessage.iterator();
        while (iter.hasNext()) {
            mess = iter.next();
            if (mess.getMessage().indexOf(words) != -1) {
                byWordsList.add(mess);
            }
        }
        return byWordsList;
    }

    public List<Message> findByRegEx(String regEx) {
        Message mess;
        List<Message> byRegExpList = new ArrayList<Message>();
        Iterator<Message> iter = listMessage.iterator();
        while (iter.hasNext()) {
            mess = iter.next();
            if (mess.getMessage().matches(regEx)) {
                byRegExpList.add(mess);
            }
        }
        return byRegExpList;
    }
}
