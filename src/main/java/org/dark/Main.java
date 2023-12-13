package org.dark;

import org.dark.model.Message;
import org.dark.repository.MessageRepository;
import org.dark.repository.Repository;
import org.dark.util.ConnectionDB;

import java.sql.*;


public class Main {
    public static void main(String[] args) {
        try (Connection connection = ConnectionDB.getInstance()) {

            System.out.println("Connection genial");
            Repository<Message> repository = new MessageRepository();

            System.out.println("----------------------");
            System.out.println("--------LIST----------");

            repository.findAll().forEach(System.out::println);

            System.out.println("----------------------");
            System.out.println("--------LIST-ID-------");

            System.out.println(repository.getById(1));

            System.out.println("----------------------");
            System.out.println("--------WriteMessage--");

            Message message =  new Message();
            message.setMessage("Mensaje prueba");
            message.setAuthor("Java");
            repository.save(message);

            System.out.println("write message success");

            System.out.println("----------------------");
            System.out.println("--------DeleteMessage--");

            //  repository.delete(4);

            System.out.println("delete message success");

            System.out.println("----------------------");
            System.out.println("--------LIST----------");

            repository.findAll().forEach(System.out::println);

        } catch (SQLException e) {
            System.out.println("Connected failed");
        }
    }
}