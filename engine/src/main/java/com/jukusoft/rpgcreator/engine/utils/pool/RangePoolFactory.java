package com.jukusoft.rpgcreator.engine.utils.pool;

import com.badlogic.gdx.utils.Pool;

/**
 * Created by Justin on 11.04.2017.
 */
public class RangePoolFactory {

    protected static Pool<Range> rangePool = null;

    public static Pool<Range> createRectanglePool () {
        if (rangePool == null) {
            rangePool = new Pool<Range>() {

                @Override
                protected Range newObject () {
                    return new Range();
                }

            };
        }

        return rangePool;
    }

    public static Pool<Range> createNewRectanglePool () {
        return new Pool<Range>() {

            @Override
            protected Range newObject () {
                return new Range();
            }

        };
    }

}
