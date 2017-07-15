package rainbow.coordinate.function.mathfunction.simple._2D.spiral;

/**
 * 阿基米德螺线（Archimedean spiral） ，亦称“等速螺线”。
 * 当一点P沿动射线OP以等速率运动的同时，这射线又以等角速度绕点O旋转，点P的轨迹称为“阿基米德螺线”。
 * 它的极坐标方程为: r=a*theta 。这种螺线的每条臂的间距永远相等于 2*pi*a
 *
 * @author Rainbow Yang
 */
public class ArchimedeanSpiral extends Spiral {
    private double a = 1;

    public ArchimedeanSpiral(double a) {
        setFunction(i -> a * i);
        this.a = a;
    }

    public ArchimedeanSpiral() {
        setFunction(i -> a * i);
    }

    {
        setStart(0);
    }
}
