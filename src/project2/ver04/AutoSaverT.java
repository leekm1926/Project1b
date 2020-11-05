package project2.ver04;

public class AutoSaverT extends Thread{

	AccountManager am;
	public AutoSaverT() {
		
	}
	public AutoSaverT(AccountManager _am) {
		this.am = _am;
	}
	@Override
	public void run() {
		while(true)
		{
			try
			{//저장기능을 호출 or 제작 (만들기)
				am.call();
				sleep(5000);
				System.out.println("자동저장ing");
				
			}
			catch(InterruptedException e)
			{
				System.out.println("쓰레드종료. 자동저장Off");
				break;
			}
		}
	}
}
