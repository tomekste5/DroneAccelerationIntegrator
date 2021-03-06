public class DyncFilter implements Filter{

    private double x_up = -0;
    private double x_down = 0;
    private double y_down = 0;
    private double y_up = -0;
    private double z_down = 1;
    private double z_up = 1;

    private double delta_rate ;
    private double target;

    DyncFilter(double delta_rate, double target) {
        this.delta_rate = delta_rate;
        this.target = target;
    }

    public void update(double[] signal) {
        if (signal[1] > target) {
            if (signal[8] > target) {
                x_down += delta_rate;
                x_up -= delta_rate;
            }
            if (signal[9] > target) {
                y_down += delta_rate;
                y_up -= delta_rate;
            }
            if (signal[10] > target) {
                z_down += delta_rate;
                z_up -= delta_rate;
            }
            if (signal[8] < -target) {
                x_down += delta_rate;
                x_up -= delta_rate;
            }
            if (signal[9] < -target) {
                y_down += delta_rate;
                y_up -= delta_rate;
            }
            if (signal[10] < -target) {
                z_down += delta_rate;
                z_up -= delta_rate;
            }
        }
        System.out.println("z_down: "+ z_down);
        System.out.println("z_up: "+ z_up);
        System.out.println("x_down: "+ x_down);
        System.out.println("x_up: "+ x_up);
        System.out.println("y_down: "+ y_down);
        System.out.println("y_up: "+ y_up);
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public double[] filter(double[] accVec) {
        if (accVec[0] < x_down * 9.81 && accVec[0] > 0) {
            accVec[0] = 0;
        }
        if (accVec[1] < y_down * 9.81 && accVec[1] > 0) {
            accVec[1] = 0;
        }
        if (accVec[2] < 1 * 9.81 && accVec[2] > z_up * 9.81) {
            accVec[2] = 9.81;
        }
        if (accVec[2] < z_down * 9.81 && accVec[2] > 1 * 9.81) {
            accVec[2] = 9.81;
        }
        if (accVec[0] > x_up * 9.81 && accVec[0] < 0) {
            accVec[0] = 0;
        }
        if (accVec[1] > y_up * 9.81 && accVec[1] < 0) {
            accVec[1] = 0;
        }
        if (accVec[2] > -1 * 9.81 && accVec[2] < -0.5 * 9.81) {
            accVec[2] = -9.81;
        }
        return accVec;
    }
}

