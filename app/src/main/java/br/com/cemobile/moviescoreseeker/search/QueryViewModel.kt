package br.com.cemobile.moviescoreseeker.search

import android.arch.lifecycle.MutableLiveData
import android.databinding.BaseObservable
import android.databinding.Bindable
import br.com.cemobile.moviescoreseeker.BR

class QueryViewModel(text: String) : BaseObservable() {

    val mutableQuery = MutableLiveData<String>()

    init {
        mutableQuery.value = text
    }

    @Bindable
    fun getQuery() = mutableQuery.value!!

    fun setQuery(query: String) {
        if (mutableQuery.value != query) {
            mutableQuery.value = query
            notifyPropertyChanged(BR.query)
        }
    }

}