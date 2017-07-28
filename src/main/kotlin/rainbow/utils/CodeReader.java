package rainbow.utils;

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
 */
public class CodeReader {

    public static void main(String[] args) {
        new CodeReader(".//src").print(DETAILED);
    }

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
        if (fileNode == null) {
            return;
        }
        switch (mode) {
            case DETAILED:
                print(fileNode, true);
                break;
            case NO_FOLDER:
                print(fileNode, false);
                break;
            case SIMPLE:
                System.out.print("files : " + numTo(fileNode.fileCount));
                System.out.println("\tcode lines : " + numTo(fileNode.count));
                return;
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("Files : " + numTo(fileNode.fileCount));
        System.out.println("Code lines : " + numTo(fileNode.count));
        System.out.println("Blank lines : " + numTo(fileNode.blankCount));
        System.out.println("Annotation lines : " + numTo(fileNode.annotation));
        System.out.println("Lines per file : " + numTo((fileNode.count * 100 / fileNode.fileCount + 5) / 100.0));
    }

    private void print(FileNode node, boolean isDetailed) {
        if (isDetailed) indent(node.level);

        if (node.isFile()) {
            System.out.println(fileTo(node.name) + " : " + numTo(node.count) + "(" + numTo(node.annotation) + ")");
        } else {
            if (isDetailed) {
                System.out.println(packageTo(node.name) + " : " + numTo(node.count) + " line" + (node.count == 1 ? "" : "s") +
                        " in " + numTo(node.fileCount) + " file" + (node.fileCount == 1 ? "" : "s") + " {");
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

    public String numTo(Number n) {
        return setColor(n.toString(), "blue", true);
    }

    public String fileTo(String s) {
        return setColor(s, "yellow", false);
    }

    public String packageTo(String s) {
        return setColor(s, "green", true);
    }

    public String setColor(String s, String color, boolean bright) {
        switch (color) {
            case "black":
                if (bright) return ("\u001B[90m" + s + "\u001B[0m");
                else return ("\u001B[30m" + s + "\u001B[0m");
            case "red":
                if (bright) return ("\u001B[91m" + s + "\u001B[0m");
                else return ("\u001B[31m" + s + "\u001B[0m");
            case "green":
                if (bright) return ("\u001B[92m" + s + "\u001B[0m");
                else return ("\u001B[32m" + s + "\u001B[0m");
            case "yellow":
                if (bright) return ("\u001B[93m" + s + "\u001B[0m");
                else return ("\u001B[33m" + s + "\u001B[0m");
            case "blue":
                if (bright) return ("\u001B[94m" + s + "\u001B[0m");
                else return ("\u001B[34m" + s + "\u001B[0m");
            case "magenta":
                if (bright) return ("\u001B[95m" + s + "\u001B[0m");
                else return ("\u001B[35m" + s + "\u001B[0m");
            case "cyan":
                if (bright) return ("\u001B[96m" + s + "\u001B[0m");
                else return ("\u001B[36m" + s + "\u001B[0m");
            case "white":
                if (bright) return ("\u001B[97m" + s + "\u001B[0m");
                else return ("\u001B[37m" + s + "\u001B[0m");
        }
        return null;
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
