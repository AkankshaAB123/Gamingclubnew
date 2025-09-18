package com.gaming.gamer;

import java.util.List;

public class MemberSearchResponseDTO<PlayedHistoryDTO> {

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
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPhone() {
		// TODO Auto-generated method stub
		return null;
	}
	public Double getBalance() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
	public void setPhone(String phone) {
		// TODO Auto-generated method stub
		
	}
	public void setBalance(Double balance) {
		// TODO Auto-generated method stub
		
	}
}
