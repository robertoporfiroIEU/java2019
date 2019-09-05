package be.technifutur.applicationrunner;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InteractiveRunnerTest {

	private InteractiveRunner runner;
	private InteractiveApplication applicationMock;
	private Environnement environnementMock;

	@BeforeEach
	public void init() {
		runner = new InteractiveRunner();
		applicationMock = EasyMock.createMock(InteractiveApplication.class);
		runner.setApplication(applicationMock);
		environnementMock = EasyMock.createMock(Environnement.class);
		runner.setEnv(environnementMock);
	}

	@Test
	public void testSetApplicationNotNUll() {
		this.called = true;
		assertAll(() -> assertNotNull(runner.getApplication()), () -> assertSame(applicationMock, runner.getApplication()));
	}

	public boolean called = false;

	@Test
	public void testGetScreenReturnTest1() {
		applicationMock.start();
		EasyMock.expect(applicationMock.isFinish()).andReturn(false);
		EasyMock.expect(applicationMock.getScreen()).andReturn(new StringBuilder("toto"));
		environnementMock.print("toto");
		EasyMock.expect(applicationMock.isFinish()).andReturn(true);
		EasyMock.expect(applicationMock.isFinish()).andReturn(true);
		applicationMock.stop();
		EasyMock.replay(environnementMock,applicationMock);
		
		runner.run();
		EasyMock.verify(environnementMock,applicationMock);
		
	}

	String result = "";
	private void setEnv() {
		runner.setEnv(new Environnement() {
			
			@Override
			public void print(CharSequence out) {
				result =  out.toString();				
			}
			
			@Override
			public String nextLine() {
				return null;
			}
		});
	}
	@Test
	public void testRunCallsStart() {
		applicationMock.start();
		EasyMock.expect(applicationMock.isFinish()).andReturn(true);
		applicationMock.stop();
		EasyMock.replay(applicationMock);
		runner.run();
		EasyMock.verify(applicationMock);
	}

	private InteractiveApplication createApplication() {

		// TODO Auto-generated method stub
		return new InteractiveApplication() {

			private int cpt;

			@Override
			public void stop() {
				// TODO Auto-generated method stub

			}

			@Override
			public void start() {
				InteractiveRunnerTest.this.called = true;

			}

			@Override
			public void newInput(String input) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean isFinish() {
				this.cpt++;
				return cpt>1;
			}

			@Override
			public StringBuilder getScreen() {
				return new StringBuilder("test1");
			}
		};
	}

}
