/**
 * Parameters class to launch Engine
 */
class Params {
    private double G; // Телескопическое увеличение Г (0)
    private double D; // Диаметр выходного зрачка – D' (м)
    private double tOut; // Удаление выходного зрачка t' (м)
    private double tIn; // Удаление входного зрачка t (м)
    private double twoW; // Угловое поле зрения - 2омега (град)
    private double n; //

    Params() {
    }

    double getG() {
        return G;
    }

    void setG(double g) {
        G = g;
    }

    double getD() {
        return D;
    }

    void setD(double d) {
        D = d;
    }

    double gettOut() {
        return tOut;
    }

    void settOut(double tOut) {
        this.tOut = tOut;
    }

    double gettIn() {
        return tIn;
    }

    void settIn(double tIn) {
        this.tIn = tIn;
    }

    double getTwoW() {
        return twoW;
    }

    void setTwoW(double twoW) {
        this.twoW = twoW;
    }

    double getN() {
        return n;
    }

    void setN(double n) {
        this.n = n;
    }
}
