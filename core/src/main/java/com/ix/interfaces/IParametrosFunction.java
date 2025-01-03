package com.ix.interfaces;

import java.util.List;

@FunctionalInterface
public interface IParametrosFunction<T>  {
    void apply(T d, List<Object> parametros);
}
