package com.gaming.gamer;

import java.util.List;

public class MemberProfileDTO {
    private MemberDTO member;
    private List<RechargeDTO> recharge_history;
    private List<GameDTO> games;
    private List<PlayedHistoryDTO> played_history;

    // Getters and Setters
    public MemberDTO getMember() { return member; }
    public void setMember(MemberDTO member) { this.member = member; }
    public List<RechargeDTO> getRecharge_history() { return recharge_history; }
    public void setRecharge_history(List<RechargeDTO> recharge_history) { this.recharge_history = recharge_history; }
    public List<GameDTO> getGames() { return games; }
    public void setGames(List<GameDTO> games) { this.games = games; }
    public List<PlayedHistoryDTO> getPlayed_history() { return played_history; }
    public void setPlayed_history(List<PlayedHistoryDTO> played_history) { this.played_history = played_history; }
}