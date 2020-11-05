package project2.ver04;

public class HighCreditAccount extends Account {

	private int rate;
	private String grade;

	public HighCreditAccount(String accountNum, String name, int balance, int rate, String grade) {
		super(accountNum, name, balance);

		this.rate = rate;
		this.grade = grade;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
