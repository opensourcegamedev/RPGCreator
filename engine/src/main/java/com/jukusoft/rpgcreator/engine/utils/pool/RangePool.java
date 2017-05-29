package com.jukusoft.rpgcreator.engine.utils.pool;

import com.badlogic.gdx.utils.Pool;

/**
 * Created by Justin on 11.04.2017.
 */
public class RangePool {

    protected static Pool<Range> rangePool = RangePoolFactory.createRectanglePool();

    public static Range create () {
        return rangePool.obtain();
    }

    public static void free (Range range) {
        rangePool.free(range);
    }

}
