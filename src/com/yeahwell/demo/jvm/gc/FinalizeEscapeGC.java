package com.yeahwell.demo.jvm.gc;

/**
 * <p>在跟搜索算法中不可达的对象，也并非是“非死不可”的，这时候它们暂时处于“缓刑”阶段，
 * 要真正宣告一个对象死亡，至少要经历两次标记过程</p>
 * <p>避免使用finalize()，它不是C/C++中的析构函数，而是Java刚诞生时为了使C/C++程序员更容易接受它所做出的一个妥协</p>
 * <p>finalize()能做的所有工作，使用try-finally或其他方式都可以做得更好、更及时</p>
 * 
 * 此代码演示了两点：
 * 1. 对象可以在被GC时自我拯救
 * 2. 这种自救的机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次
 * @author yeahwell
 *
 */
public class FinalizeEscapeGC {

	public static FinalizeEscapeGC SAVE_HOOK = null;
	
	public void isAlive(){
		System.out.println("yes, i am still alive");
	}
	
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("finalize method executed!");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}
	
	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new FinalizeEscapeGC();
		
		//对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		//因为Finalizer方法优先级很低，暂停0.5秒，以等待它
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("No, i am dead:(");
		}
		
		//下面这段代码与上面的完全相同，但是这次自救却失败了
		SAVE_HOOK = null;
		System.gc();
		//因为Finalizer方法优先级很低，暂停0.5秒，以等待它
		Thread.sleep(500);
		if(SAVE_HOOK != null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("No, i am dead:(");
		}
	}
	
}
