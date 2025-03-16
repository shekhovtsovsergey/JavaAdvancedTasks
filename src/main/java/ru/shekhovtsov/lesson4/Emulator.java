package ru.shekhovtsov.lesson4;


import java.io.IOException;
import java.util.Scanner;

public class Emulator {
    private final AbstractFileCache cache;
    private final Scanner scanner = new Scanner(System.in);

    public Emulator(AbstractFileCache cache) {
        this.cache = cache;
    }

    public static void main(String[] args) {
        // Выбор реализации кэша
        AbstractFileCache cache = new SoftFileCache(); // или new WeakFileCache()
        Emulator emulator = new Emulator(cache);
        emulator.start();
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1 -> setCacheDirectory();
                case 2 -> loadFileToCache();
                case 3 -> getFileContent();
                case 4 -> System.exit(0);
                default -> System.out.println("Неверный выбор");
            }
        }
    }

    private void setCacheDirectory() {
        System.out.print("Введите путь к директории: ");
        String path = scanner.nextLine();
        cache.setDirectory(path);
        System.out.println("Директория установлена");
    }

    private void loadFileToCache() {
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine();
        try {
            cache.getFileContent(fileName);
            System.out.println("Файл загружен в кэш");
        } catch (IOException e) {
            System.out.println("Ошибка загрузки файла: " + e.getMessage());
        }
    }

    private void getFileContent() {
        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine();
        try {
            String content = cache.getFileContent(fileName);
            System.out.println("Содержимое файла:\n" + content);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Указать директорию");
        System.out.println("2. Загрузить файл в кэш");
        System.out.println("3. Получить содержимое файла");
        System.out.println("4. Выход");
        System.out.print("Выберите действие: ");
    }
}
