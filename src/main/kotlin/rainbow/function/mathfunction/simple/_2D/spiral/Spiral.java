package rainbow.function.mathfunction.simple._2D.spiral;

import rainbow.point.CoordinatePoint;
import rainbow.point.Point2DPolar;
import rainbow.function.mathfunction.simple.SimpleMathFunction;

/**
 * 所有的二维螺线都应该继承这个类
 * 这个类将createPoint(double... ds)改为产生PointPolar2D
 * <p>
 * (本类及其子类部分资料来自中文Wiki)
 * 目前的螺线有
 * 费马螺线(Fermat spiral)  r^2 =theta
 * 等角螺线(Isometric spiral)   r = a*e^(b*theta)
 * 双曲螺线(Hyperbolic spiral)  r = c\theta
 * 阿基米德螺线(Archimedean spiral) r=a*theta
 * 连锁螺线(Lituus spiral) r^2*theta =k
 * <p>
 * 不知道是个啥
 * 弯曲螺线(Wiki上都没，不知道是个啥)
 * 欧拉螺线(Euler spiral)https://zh.wikipedia.org/wiki/%E7%BE%8A%E8%A7%92%E8%9E%BA%E7%BA%BF
 *
 * @author Rainbow Yang
 */
public abstract class Spiral extends SimpleMathFunction {


    @Override
    protected CoordinatePoint createPoint(double... ds) {
        //此处使用ds[1], ds[0]的原因是在计算的时候，角度会被当做第一个维度，而长度则为第二个
        //而PointPolar2D的顺序是先长度后角度
        return new Point2DPolar(ds[1], ds[0]);
    }
}
