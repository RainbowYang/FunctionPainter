package rainbow.inner.function.mathfunction;

import rainbow.inner.coordinate.point.MyPoint;
import rainbow.inner.coordinate.point.PointForAxes;
import rainbow.inner.coordinate.system.comp.Range;
import rainbow.inner.function.PointFunction;
import rainbow.inner.system.MySystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

/**
 * 本类由各个维度上的Function来控制。
 * 并自动根据取值范围生成点。
 *
 * @author Rainbow Yang
 */
public class MathFunction extends PointFunction {
    //每组Function的集合
    private List<
            //每组Function都会生成一个点集
            List<DoubleUnaryOperator>
            > functionsList = new ArrayList<>();
    private double start, end, step;

    //返回本身的DoubleUnaryOperator
    public static final DoubleUnaryOperator SELF = i -> i;

    //每个点的偏移，可用于平移
    private List<Double> translations;

    public MathFunction() {
    }

    public MathFunction(DoubleUnaryOperator... functions) {
        this(Arrays.asList(functions));
    }

    public MathFunction(List<DoubleUnaryOperator> functions) {
        this.functionsList.add(functions);
    }

    {
        Range range = MySystem.getSystem().getCoordinateSystem().getRange();
        start = range.getStart();
        end = range.getEnd();
        step = range.getStep();
    }

    //生成所有的点
    @Override
    public void calcPoint() {
        for (List<DoubleUnaryOperator> functions : this.functionsList) {
            if (functions.size() == 0) {
                continue;
            }
            newPoints();
            for (double i = start; i < end; i += step) {
                double[] ds = new double[functions.size()];
                for (int j = 0; j < ds.length; j++) {
                    ds[j] = functions.get(j).applyAsDouble(i) + getTranslations(j);
                }
                addPoint(createPoint(ds));
            }
        }
    }

    private double getTranslations(int index) {
        if (translations == null || index >= translations.size()) {
            return 0;
        }
        return translations.get(index);
    }

    protected void setTranslations(Double... translations) {
        this.translations = Arrays.asList(translations);
    }

    /**
     * 设置functions，会先清空functionsList
     *
     * @param functions 要设置的functions
     */
    protected void setFunctions(List<DoubleUnaryOperator> functions) {
        this.functionsList.clear();
        addFunctions(functions);
    }

    /**
     * 设置functions，会先清空functionsList
     *
     * @param functions 要设置的functions
     */
    protected void setFunctions(DoubleUnaryOperator... functions) {
        setFunctions(Arrays.asList(functions));
    }

    /**
     * 添加functions
     *
     * @param functions 要添加的functions
     */
    protected void addFunctions(List<DoubleUnaryOperator> functions) {
        this.functionsList.add(functions);
    }

    /**
     * 添加functions
     *
     * @param functions 要添加的functions
     */
    protected void addFunctions(DoubleUnaryOperator... functions) {
        addFunctions(Arrays.asList(functions));
    }

    /**
     * 需要创建的点
     * 子类如需创造不是坐标系的点，只要重写此方法即可。
     *
     * @param ds 所有的参数
     * @return 所创建的点
     */
    protected MyPoint createPoint(double... ds) {
        return new PointForAxes(ds);
    }

    protected void setStart(double start) {
        this.start = start;
    }

    protected void setEnd(double end) {
        this.end = end;
    }

    protected void setStep(double step) {
        this.step = step;
    }

    protected void setRange(double start, double end, double step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public String toString() {
        return "MathFunction{" +
                "start=" + start +
                ", end=" + end +
                ", step=" + step +
                ", functionsList=" + functionsList +
                ", pointsList=" + pointsList +
                '}';
    }
}
