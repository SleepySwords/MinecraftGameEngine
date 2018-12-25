package me.spirafy;

@FunctionalInterface
public interface ReturnableValues<T, Value> {
    public T returns(Value value);
}
