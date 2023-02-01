package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException {                //метод загружает новый файл, старый переименовывает
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target,backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target,new FileInputStream(file));                                      //чтение бинарных данных файла, и их передача на уд.машину
        ftp.disconnect();
    }

    public void restore(String backup, String target) throws IOException {                      //метод, воссстанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(backup,target);
        ftp.disconnect();
    }

}