package com.fionicholas.footballapp.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fionicholas.footballapp.base.BaseViewModel
import com.fionicholas.footballapp.data.league.remote.response.DetailLeague
import com.fionicholas.footballapp.data.match.MatchDataSource
import com.fionicholas.footballapp.data.match.remote.response.Match
import com.fionicholas.footballapp.data.team.remote.response.Team
import io.reactivex.schedulers.Schedulers

class MatchViewModel(private val repository: MatchDataSource) : BaseViewModel() {

    private val _matchList = MutableLiveData<List<Match>>().apply { value = emptyList() }
    val matchList: LiveData<List<Match>> = _matchList

    private val _detailMatch = MutableLiveData<List<Match>>().apply { value = emptyList() }
    val detailMatch: LiveData<List<Match>> = _detailMatch

    private val _detailLeague = MutableLiveData<List<DetailLeague>>().apply { value = emptyList() }
    val detailLeague: LiveData<List<DetailLeague>> = _detailLeague

    private val _badgeHome = MutableLiveData<List<Team>>().apply { value = emptyList() }
    val badgeHome: LiveData<List<Team>> = _badgeHome

    private val _badgeAway = MutableLiveData<List<Team>>().apply { value = emptyList() }
    val badgeAway: LiveData<List<Team>> = _badgeAway

    private val _searchMatch = MutableLiveData<List<Match>>().apply { value = emptyList() }
    val searchMatch: LiveData<List<Match>> = _searchMatch

    private val _isViewLoading = MutableLiveData<Boolean>()
    val isViewLoading: LiveData<Boolean> = _isViewLoading

    private val _onMessageError = MutableLiveData<String>()
    val onMessageError: LiveData<String> = _onMessageError

    fun loadMatchList(id: String) {
        subscribe(repository.getMatchList(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _matchList.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadDetailMatch(id: String) {
        subscribe(repository.getDetailMatch(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _detailMatch.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadDetailLeague(id: String) {
        subscribe(repository.getDetailLeague(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _detailLeague.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadBadgeHomeTeam(id: String) {
        subscribe(repository.getTeamDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _badgeHome.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadBadgeAwayTeam(id: String) {
        subscribe(repository.getTeamDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _badgeAway.postValue(it)
            }, {
                onErrorNetwork(it)
            })
        )
    }

    fun loadSearchMatch(query: String) {
        subscribe(repository.getSearchMatch(query)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnSubscribe { _isViewLoading.postValue(true) }
            .subscribe({
                _isViewLoading.postValue(false)
                _searchMatch.postValue(it)
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