package rainbow;

import rainbow.tools.CodeReader;

/**
 * 这是FunctionPainter2，一个重新开始的版本
 * 没错，之前的版本最终被我放弃了
 *
 * @author Rainbow Yang
 * @date 2017/4/2
 */
public class Start {
    public static void main(String[] args) {
        System.out.println("这里只是一个暂置的Start类");
    }

    static {
        new CodeReader(".//src//rainbow").print(CodeReader.DETAILED);
    }
}
