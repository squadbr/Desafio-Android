package br.com.cemobile.moviescoreseeker.core

import java.util.concurrent.Executor

interface Executors {
    fun diskIO(): Executor
    fun networkIO(): Executor
    fun mainThread(): Executor
}