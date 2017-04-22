package rainbow.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数行数
 *
 * @author Rainbow Yang
 * @date 2017/4/13
 */
public class CodeReader {
    //模式
    public static final String DETAILED = "detailed";
    public static final String NO_FOLDER = "noFolder";
    public static final String SIMPLE = "simple";

    //根目录
    private String root;
    private FileNode fileNode;

    public CodeReader(String root) {
        File file = new File(this.root = root);

        if (!file.exists()) return;

        if (file.isFile()) {
            fileNode = new FileNode(file.getName(), 0, true);
        } else if (file.isDirectory()) {
            fileNode = new FileNode(file.getName(), 0, false);
        }

        try {
            readFile(file, fileNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取文件并生成FileNode树
    private void readFile(File file, FileNode node) throws IOException {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                FileNode newNode = new FileNode(f.getName(), node.level + 1, f.isFile());
                node.map.put(f.getName(), newNode);

                readFile(f, newNode);

                node.count += newNode.count;
                node.blankCount += newNode.blankCount;
                node.annotation += newNode.annotation;
                node.fileCount += newNode.fileCount;
            }
        } else if (file.isFile()) {
            node.fileCount = 1;
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            boolean inAnnotation = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    node.blankCount++;
                    continue;
                }

                node.count++;

                if (inAnnotation) {
                    node.annotation++;
                    if (line.contains("*/")) {
                        inAnnotation = false;
                    }
                } else {
                    if (line.startsWith("/*") || line.startsWith("/**")) {
                        node.annotation++;
                        inAnnotation = true;
                    } else if (line.contains("//")) {
                        node.annotation++;
                    }
                }
            }
            br.close();
        }
    }

    public void print(String mode) {
        switch (mode) {
            case DETAILED:
                print(fileNode, true);
                break;
            case NO_FOLDER:
                print(fileNode, false);
                break;
            case SIMPLE:
                System.out.print("files : " + fileNode.fileCount);
                System.out.println("\tcode lines : " + fileNode.count);
                return;
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("Files : " + fileNode.fileCount);
        System.out.println("Code lines : " + fileNode.count);
        System.out.println("Blank lines : " + fileNode.blankCount);
        System.out.println("Annotation lines : " + fileNode.annotation);
        System.out.println("Lines per file : " + (fileNode.count * 100 / fileNode.fileCount + 5) / 100.0);
    }

    private void print(FileNode node, boolean isDetailed) {
        if (isDetailed) indent(node.level);

        if (node.isFile()) {
            System.out.println(node.name + " : " + node.count + "(" + node.annotation + ")");
        } else {
            if (isDetailed) {
                System.out.println(node.name + " : " + node.count + " line" + (node.count == 1 ? "" : "s") +
                        " in " + node.fileCount + " file" + (node.fileCount == 1 ? "" : "s") + " {");
            }
            for (FileNode f : node.map.values()) {
                print(f, isDetailed);
            }
            if (isDetailed) {
                indent(node.level);
                System.out.println("}");
            }
        }
    }

    // 缩进
    private void indent(int level) {
        for (int x = 0; x < level; x++) {
            System.out.print("\t");
        }
    }

    public FileNode getFileNode() {
        return fileNode;
    }

    class FileNode {
        String name;
        int level;//用于缩进

        //for 包
        Map<String, FileNode> map;
        int fileCount;

        //for 文件 and 包
        int count;
        int blankCount;
        int annotation;

        FileNode(String name, int level, boolean isFile) {
            this.name = name;
            this.level = level;
            if (!isFile) this.map = new HashMap<>();
        }

        boolean isFile() {
            return map == null;
        }
    }
}
