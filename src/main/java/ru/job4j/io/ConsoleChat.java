package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        String peopleAnswer = "";
        log.add("Start of LOG");
        while (!peopleAnswer.equals(OUT)) {
            String botAnswer = takeAnswer(botAnswers);
            System.out.println("Введите слово");
            Scanner scanner = new Scanner(System.in);
            peopleAnswer = scanner.nextLine();
            if (peopleAnswer.equals(STOP)) {
                log.add("People say : " + peopleAnswer);
                while (!peopleAnswer.equals(CONTINUE)) {
                    peopleAnswer = scanner.nextLine();
                    log.add("People say : " + peopleAnswer);
                }
                continue;
            }
            if (peopleAnswer.equals(OUT)) {
                log.add("People say : " + peopleAnswer);
                log.add("The End of LOG");
            } else {
                log.add("People say : " + peopleAnswer);
                log.add("Bot answer : " + botAnswer);
                System.out.println(botAnswer);
            }
        }
        saveLog(log);
        System.out.println("Хорошего дня!");
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String takeAnswer(String botAnswers) {
        List<String> answer = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            in.lines().forEach(answer::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = (int) (Math.random() * answer.size());
        return answer.get(index);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/ConsoleChatLog.txt", "./src/data/botAnswer.txt");
        cc.run();
    }
}
