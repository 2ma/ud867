package com.udacity.gradle.builditbigger;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class Result<T> {

    @NonNull
    final Status status;
    @Nullable
    final T data;
    @Nullable
    final String errorMessage;

    private Result(@NonNull Status status, @Nullable T data, @Nullable String errorMessage) {
        this.status = status;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public static <T> Result<T> loading() {
        return new Result<>(Status.LOADING, null, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(Status.SUCCESS, data, null);
    }

    public static <T> Result<T> error(String errorMessage) {
        return new Result<>(Status.ERROR, null, errorMessage);
    }


}
