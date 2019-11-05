package thangND15.threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadImplement {
	public static AtomicInteger counter = new AtomicInteger(0);

	public static boolean check = false;

	public static int sumFunction;

	public static void main(String[] args) {

	sumFunction = 250;

	List<TestScriptRunner> scriptRunners = new ArrayList<TestScriptRunner>();

	ExecutorService executorService = Executors.newFixedThreadPool(4);

	scriptRunners.add(new TestScriptRunner());

	scriptRunners.add(new TestScriptRunner());

	scriptRunners.add(new TestScriptRunner());

	scriptRunners.add(new TestScriptRunner());

	scriptRunners.forEach(executorService::submit);

	executorService.shutdown();

	}

}
