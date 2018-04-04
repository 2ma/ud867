package hu.am2.joke;

public class JokeModel {
    private String Q;
    private String A;

    public JokeModel(String q, String a) {
        Q = q;
        A = a;
    }

    public JokeModel() {
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }
}
