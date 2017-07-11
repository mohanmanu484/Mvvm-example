package com.example.mohang.mvvmproject.dependencyinjection.qualifier;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by ankit on 26/12/16.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {



}
