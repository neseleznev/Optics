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
        return results;
    }
}
