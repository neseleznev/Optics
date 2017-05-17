/**
 * EngineImpl, includes all the computations
 */
class EngineImpl implements Engine {

    private Results results;

    EngineImpl() {
        this.results = new Results();
    }

    @Override
    public Results compute(Params params) {
        int x = params.getX();
        results.x0 = x * x;

        firstStep(params);

        return results;
    }

    private void firstStep(Params params) {
        // Get params
        double G = params.getG();
        double D = params.getD();
        double tOut = params.gettOut();
        double tIn = params.gettIn();
        double twoW = params.getTwoW();
        double n = params.getN();

        // Maths
        double A_0 = 1 / G;
        double D_0 = G;
        double b_0_out = tOut; // b'_0
        double b_0_in = tIn; // b_0
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
}
