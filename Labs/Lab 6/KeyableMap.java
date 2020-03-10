import java.util.HashMap;
import java.util.Map;

public class KeyableMap<T, K, V extends Keyable<K>> {
    protected T name;
    protected Map<K, V> map = new HashMap<K, V>();
    
    public <K, V> KeyableMap(T t) {
        this.name = t;
    }

    public V get(K t) {
        return map.get(t);
    }

    public KeyableMap<T, K, V> put(V keyable) {
        this.map.put(keyable.getKey(), keyable);
        return this;
    }

    @Override
    public String toString() {
        String string = this.name + ": " + map.values();
        string = string.replace('[', '{');
        string = string.replace(']', '}');
        return string;
    }
}
