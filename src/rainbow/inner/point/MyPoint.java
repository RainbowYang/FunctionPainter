package rainbow.inner.point;

import rainbow.inner.point.tool.ToolForMyPoint;

/**
 * @author Rainbow Yang
 * @date 2017/4/2
 */
public interface MyPoint {
    /**
     * 得到该类所对应的工具类
     *
     * @return 工具类
     */
    public ToolForMyPoint<? extends MyPoint> getTool();
}
