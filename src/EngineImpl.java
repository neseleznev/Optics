/**
 * EngineImpl, includes all the computations
 */
class EngineImpl implements Engine {

    private double G, D, b_0_out, b_0_in, twoW, n;
    private Results results;

    EngineImpl() {
        this.results = new Results();
    }

    @Override
    public Results compute(Params params) {
        // Get params
        G = params.getG();
        D = params.getD();
        b_0_out = params.gettOut(); // b'_0
        b_0_in = params.gettIn(); // b_0
        twoW = params.getTwoW();
        n = params.getN();

        firstStep();
        secondStep();

        return results;
    }

    private void firstStep() {

        // Maths
        double A_0 = 1 / G;
        double D_0 = G;
        double y_0 = 0.25 * Math.abs(G) * D; // TODO check
        double V_0 = n * Math.sin(twoW);

        double A_1 = A_0;
        double B_1 = -(b_0_in*A_0 + D_0*b_0_out);
        double C_1 = 0;
        double D_1 = D_0;

        double y_1 = A_0*y_0 - (b_0_in*A_0 + D_0*b_0_out) * V_0;
        double V_1 = D_0 * V_0;

        // Set results
        results.y_0 = y_0;
        results.V_0 = V_0;
        results.y_1 = y_1;
        results.V_1 = V_1;

        results.A_1 = A_1;
        results.B_1 = B_1;
        results.C_1 = C_1;
        results.D_1 = D_1;
    }

    private void secondStep() {
        double F_2 = Math.signum(G) * (G - 1) / (b_0_in + G*G*b_0_out); // Ф2
        double F_2_ocul = -G * F_2; // Ф2'

        double A_2 = results.A_1 - results.B_1*F_2;
        double B_2 = results.B_1;
        double C_2 = -F_2_ocul * results.A_1 +
                Math.signum(G) * results.B_1 * F_2_ocul * F_2 +
                results.C_1 +
                -results.D_1 * F_2;
        double D_2 = -results.B_1 * F_2_ocul + results.D_1;

        double y_2 = A_2*results.y_1 + B_2*results.V_1;
        double V_2 = C_2*results.y_1 + D_2*results.V_1;

        // Results
        results.F_2 = F_2;
        results.F_2_ocul = F_2_ocul;
        results.y_2 = y_2;
        results.V_2 = V_2;

        results.A_2 = A_2;
        results.B_2 = B_2;
        results.C_2 = C_2;
        results.D_2 = D_2;
    }
}
