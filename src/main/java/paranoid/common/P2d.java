package paranoid.common;

import java.io.Serializable;

public class P2d implements Serializable {

    private static final long serialVersionUID = 5841705605256674477L;
    private final double x;
    private final double y;

    public P2d(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @return the X field
     */
    public double getX() {
        return this.x;
    }

    /**
     * 
     * @return the Y field
     */
    public double getY() {
        return this.y;
    }

    /**
     * 
     * @param v
     * @return vectorial sum of the two positions
     */
    public P2d sum(final V2d v) {
        return new P2d(x + v.getX(), y + v.getY());
    }

    /**
     * 
     * @param v
     * @return vectorial sum of the two positions
     */
    public V2d sub(final P2d v) {
        return new V2d(x - v.x, y - v.y);
    }

    /**
     * 
     * @return String view of the class
     */
    @Override
    public String toString() {
        return "P2d(" + x + "," + y + ")";
    }

    /**
     * 
     * @return a generated hashcode ( pojo )
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * 
     * @return a generated equals ( pojo )
     */
    @Override
    @SuppressWarnings("PMD.SimplifyBooleanReturns")
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final P2d other = (P2d) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
            return false;
            //return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x) avoid pmd warning
        }
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
            return false;
            //Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y) avoid pmd warning
        }
        return true;
    }

}
