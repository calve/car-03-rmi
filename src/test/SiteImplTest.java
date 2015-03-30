package test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import main.SiteImpl;
import main.SiteItf;
import org.junit.Test;

public class SiteImplTest {

	public static final String ID_PERE = "First";
	public static final String ID_FILS = "Second";
	public static final String MESSAGE = "Message test";
	public static final String REPONSE_RECEPTION = "RECEIVED : " + MESSAGE;
	public static final String REPONSE_PROPAGATION = "DEBUG [" + ID_PERE
			+ "] : Propagation du message au fils [" + ID_FILS + "]";

	@Test
	public void testGetId() throws Exception {
		SiteItf noeud = (SiteItf) new SiteImpl(ID_PERE);
		assertEquals(ID_PERE, noeud.getId());
	}
	
	@Test
	public void testLierFils() throws Exception {
		SiteItf pere = (SiteItf) new SiteImpl(ID_PERE);
		SiteItf fils = (SiteItf) new SiteImpl(ID_FILS);
		pere.addSon(fils);
		
		assertEquals(1, pere.getSons().size());
		assertEquals(0, fils.getSons().size());
	}
	
	@Test
	public void testReception() throws Exception {
		SiteItf noeud = (SiteItf) new SiteImpl(ID_PERE);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));

		byte message[] = MESSAGE.getBytes();
		noeud.sendMessage(message);

		assertEquals(REPONSE_RECEPTION,
				output.toString().replaceAll("[\r\n]+", ""));
	}

	@Test
	public void testPropagation() throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));

		SiteItf pere = (SiteItf) new SiteImpl(ID_PERE);
		SiteItf fils = (SiteItf) new SiteImpl(ID_FILS);

		pere.addSon(fils);

		byte message[] = MESSAGE.getBytes();
		pere.sendMessage(message);

		assertEquals(REPONSE_RECEPTION + REPONSE_PROPAGATION + REPONSE_RECEPTION,
				output.toString().replaceAll("[\r\n]+", ""));
	}
}
