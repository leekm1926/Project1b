package project2.ver04;

public class NormalAccount extends Account {

	private int rate;

	public NormalAccount(String accountNum, String name, int balance, int rate) {
		super(accountNum, name, balance);
		this.rate = rate;
	}
	

	@Override
	public String getAccountNum() {
		// TODO Auto-generated method stub
		return super.getAccountNum();
	}

	@Override
	public void setAccountNum(String accountNum) {
		// TODO Auto-generated method stub
		super.setAccountNum(accountNum);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName(name);
	}

	@Override
	public int getBalance() {
		// TODO Auto-generated method stub
		return super.getBalance();
	}

	@Override
	public void setBalance(int balance) {
		// TODO Auto-generated method stub
		super.setBalance(balance);
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

}
