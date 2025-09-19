package com.gaming.gamer;

import java.util.List;

import com.gaming.gamer.members;
import com.gaming.gamer.RechargeDTO;
import com.gaming.gamer.games;
import com.gaming.gamer.PlayedHistoryDTO;

public class MemberSearchDTO {

    private members member;
    private List<RechargeDTO> rechargeHistory;
    private List<games> games;
    private List<PlayedHistoryDTO> playedHistory;

    // Constructors
   

    public MemberSearchDTO(members member, List<RechargeDTO> rechargeHistory,
                           List<games> games, List<PlayedHistoryDTO> playedHistory) {
        this.member = member;
        this.rechargeHistory = rechargeHistory;
        this.games = games;
        this.playedHistory = playedHistory;
    }

    // Getters and Setters
    public members getMember() {
        return member;
    }

    public void setMember(members member) {
        this.member = member;
    }

    public List<RechargeDTO> getRechargeHistory() {
        return rechargeHistory;
    }

    public void setRechargeHistory(List<RechargeDTO> rechargeHistory) {
        this.rechargeHistory = rechargeHistory;
    }

    public List<games> getGames() {
        return games;
    }

    public void setGames(List<games> games) {
        this.games = games;
    }

    public List<PlayedHistoryDTO> getPlayedHistory() {
        return playedHistory;
    }

    public void setPlayedHistory(List<PlayedHistoryDTO> playedHistory) {
        this.playedHistory = playedHistory;
    }
}