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
		EasyMock.expect(environnementMock.nextLine()).andReturn("dédé");
		applicationMock.newInput("dédé");
		EasyMock.expect(applicationMock.isFinish()).andReturn(true);
		EasyMock.expect(applicationMock.hasLastSreen()).andReturn(false);
		applicationMock.stop();
		EasyMock.replay(environnementMock,applicationMock);
		
		runner.run();
		EasyMock.verify(environnementMock,applicationMock);
		
	}

	@Test
	public void testRunCallsStart() {
		applicationMock.start();
		EasyMock.expect(applicationMock.isFinish()).andReturn(true);
		EasyMock.expect(applicationMock.hasLastSreen()).andReturn(false);
		applicationMock.stop();
		EasyMock.replay(applicationMock);
		runner.run();
		EasyMock.verify(applicationMock);
	}

}
