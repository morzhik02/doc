package com.iitu.doc.util.converter;

public interface Filler<S, T> {

    /**
     * Маппит значения из source в target
     *
     * @param source источник данных
     * @param target цель маппинга
     */
    void fill(S source, T target);

    T createTarget();

}
