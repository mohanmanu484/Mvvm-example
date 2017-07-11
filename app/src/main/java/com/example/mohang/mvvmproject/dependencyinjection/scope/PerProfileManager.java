package com.example.mohang.mvvmproject.dependencyinjection.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mohang on 11/7/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerProfileManager {
}
