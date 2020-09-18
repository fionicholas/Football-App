package com.fionicholas.footballapp.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.team.TeamDataSource
import com.fionicholas.footballapp.data.team.remote.response.Team
import io.reactivex.schedulers.Schedulers

class TeamViewModel(private val repository: TeamDataSource) : BaseViewModel() {

    private val _teamList = MutableLiveData<List<Team>>().apply { value = emptyList() }
    val teamList: LiveData<List<Team>> = _teamList

    private val _teamDetail = MutableLiveData<List<Team>>().apply { value = emptyList() }
    val teamDetail: LiveData<List<Team>> = _teamDetail

    private val _searchTeam = MutableLiveData<List<Team>>().apply { value = emptyList() }
    val searchTeam: LiveData<List<Team>> = _searchTeam

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadTeamList(id: String) {
        subscribe(repository.getTeamList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _teamList.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadTeamDetail(id: String) {
        subscribe(repository.getTeamDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _teamDetail.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadSearchTeam(query: String) {
        subscribe(repository.getSearchTeam(query)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _searchTeam.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    private fun onErrorNetwork(throwable: Throwable) {
        _isViewLoading.postValue(false)
        _onMessageError.postValue(throwable.message)
    }

}