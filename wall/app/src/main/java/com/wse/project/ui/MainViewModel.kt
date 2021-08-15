package com.wse.project.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.wse.project.networkdb.ApiService


class MainViewModel( val repo: ApiService): ViewModel(){

    val passengers = Pager(PagingConfig(pageSize = 10)) {

        PassengersDataSource(repo)
    }.flow.cachedIn(viewModelScope)


}