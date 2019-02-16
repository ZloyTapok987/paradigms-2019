package md2html;

import java.io.IOException;

public class Md2Html {
    public static void main(String[] args) throws IOException {
        FileManager fileManager=new FileManager(args[0],args[1]);
        //FileManager fileManager=new FileManager("read.txt","out.txt");
       // FileManager fileManager1=new FileManager("read.txt","read.txt");
       // fileManager1.write("    # Может показаться, что это заголовок.\nНо нет, это абзац начинающийся с `#`.\n\n");
       // fileManager1.close();
        MdParser mdParser=new MdParser(fileManager);
        fileManager.write(mdParser.parse());
        fileManager.close();
    }
}
