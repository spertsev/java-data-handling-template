package com.epam.izh.rd.online.repository;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        long filesCount = 0;
        File file = new File("src/main/resources/" + path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File currentFile : files) {
                filesCount = filesCount + countFilesInDirectory(path + "/" + currentFile.getName());
            }
        } else {
            filesCount++;
        }
        return filesCount;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        long dirsCount = 0;
        File file = new File("src/main/resources/" + path);
        if (file.isDirectory()) {
            dirsCount++;
            File[] files = file.listFiles();
            for (File currentFile : files) {
                dirsCount = dirsCount + countDirsInDirectory(path + "/" + currentFile.getName());
            }
        }
        return dirsCount;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File sourceFolder = new File(from);
        File destFolder = new File(to);
        FilenameFilter txtFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".txt")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        File[] files = sourceFolder.listFiles(txtFilter);
        for (File currentFile : files) {
            File destFile = new File(destFolder.toPath() + "/" + currentFile.getName());
            try {
                Files.copy(currentFile.toPath(), destFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     *
     */
    @Override
    public boolean createFile(String path, String name) {
        try {
            File file = new File(path + "/" + name);
            file.mkdirs();
            file.createNewFile();
            return file.exists();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        try{
            return new String (Files.readAllBytes(Paths.get("src/main/resources/" + fileName)));
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
