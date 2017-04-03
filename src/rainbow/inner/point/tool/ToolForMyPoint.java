package rainbow.inner.point.tool;

import rainbow.inner.point.MyPoint;

/**
 * @author Rainbow Yang
 * @date 2017/4/2
 */
public interface ToolForMyPoint<P extends MyPoint> {
    public default P add(P... ps) {
        throw new NotSupportException("Add");
    }

    public default P spin(P... ps) {
        throw new NotSupportException("Spin");
    }

    public default P times(double times, P... ps) {
        throw new NotSupportException("Times");
    }

    default String getDescription(String name) {
        return new StringBuilder().append(name).append("function hasn't been supported by").append(this.getClass().getSimpleName())
                .toString();
    }

    class NotSupportException extends RuntimeException {
        public NotSupportException(String message) {
            super(message);
        }
    }
}
