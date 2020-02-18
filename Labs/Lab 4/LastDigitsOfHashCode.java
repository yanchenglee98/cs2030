public class LastDigitsOfHashCode implements Transformer<Object, Integer> {
    private final Integer numOfDigits;

    public LastDigitsOfHashCode(Integer numOfDigits) {
        this.numOfDigits = numOfDigits;
    }
    @Override
    public Integer transform(Object o) {
        int hash = o.hashCode();
        return Math.abs((int) (hash % (Math.pow(10, numOfDigits))));
    }
}
