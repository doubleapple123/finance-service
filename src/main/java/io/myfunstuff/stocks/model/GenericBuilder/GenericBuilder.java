package io.myfunstuff.stocks.model.GenericBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

//https://stackoverflow.com/questions/31754786/how-to-implement-the-builder-pattern-in-java-8

public class GenericBuilder<T>{
	private final Supplier<T> instantiator;
	private List<Consumer<T>> instanceModifiers = new ArrayList<>();

	public GenericBuilder(Supplier<T> instantiator){
		this.instantiator = instantiator;
	}

	public static <T> GenericBuilder<T> of (Supplier<T> instantiator){
		return new GenericBuilder<T>(instantiator);
	}

	public <U> GenericBuilder<T> with (BiConsumer<T, U> consumer,U value){
		Consumer<T> c = instance -> consumer.accept(instance,value);
		instanceModifiers.add(c);
		return this;
	}

	public T builder(){
		T value = instantiator.get();
		instanceModifiers.forEach(modifiers -> modifiers.accept(value));
		instanceModifiers.clear();
		return value;
	}
}
