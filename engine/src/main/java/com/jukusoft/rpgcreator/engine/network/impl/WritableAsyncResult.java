package com.jukusoft.rpgcreator.engine.network.impl;

import com.jukusoft.rpgcreator.engine.network.AsyncResult;

/**
 * Created by Justin on 25.06.2017.
 */
public class WritableAsyncResult<T> implements AsyncResult<T> {

    protected T result = null;
    protected Throwable cause = null;
    protected boolean succeeded = false;

    public WritableAsyncResult(T result, boolean succeeded) {
        this.result = result;
        this.succeeded = succeeded;
    }

    public WritableAsyncResult(Throwable throwable) {
        this.succeeded = false;
        this.cause = throwable;
    }

    @Override
    public T result() {
        return this.result;
    }

    @Override
    public Throwable cause() {
        return this.cause;
    }

    @Override
    public boolean succeeded() {
        return this.succeeded;
    }

    @Override
    public boolean failed() {
        return !this.succeeded;
    }

}
