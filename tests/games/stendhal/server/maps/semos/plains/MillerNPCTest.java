package games.stendhal.server.maps.semos.plains;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.ZonePlayerAndNPCTestImpl;

/**
 * Test for MillerNPC: mill grain.
 *
 * @author Martin Fuchs
 */
public class MillerNPCTest extends ZonePlayerAndNPCTestImpl {

	private static final String ZONE_NAME = "0_semos_plains_ne";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ZonePlayerAndNPCTestImpl.setUpBeforeClass();

		SpeakerNPC npc = new SpeakerNPC("Jenny");
		SingletonRepository.getNPCList().add(npc);

		MillerNPC npcConf = new MillerNPC();
		npcConf.createDialog(npc);

		setupZone(ZONE_NAME);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	public MillerNPCTest() {
		super(ZONE_NAME, "Jenny");
	}

	@Test
	public void testHiAndBye() {
		SpeakerNPC npc = getNPC("Jenny");
		Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi Jenny"));
		assertEquals("Greetings! I am Jenny, the local miller. If you bring me some #grain, I can #mill it into flour for you.", npc.get("text"));

		assertTrue(en.step(player, "bye"));
		assertEquals("Bye.", npc.get("text"));
	}

	@Test
	public void testQuest() {
		SpeakerNPC npc = getNPC("Jenny");
		Engine en = npc.getEngine();

		assertTrue(en.step(player, "hi"));
		assertEquals("Greetings! I am Jenny, the local miller. If you bring me some #grain, I can #mill it into flour for you.", npc.get("text"));

		assertTrue(en.step(player, "job"));
		assertEquals("I run this windmill, where I can #mill people's #grain into flour for them. I also supply the bakery in Semos.", npc.get("text"));

		assertTrue(en.step(player, "grain"));
		assertEquals("There's a farm nearby; they usually let people harvest there. You'll need a scythe, of course.", npc.get("text"));

		assertTrue(en.step(player, "help"));
		assertEquals("Do you know the bakery in Semos? I'm proud to say they use my flour. But the wolves ate my delivery boy again recently... they're probably running out.", npc.get("text"));

		assertTrue(en.step(player, "mill"));
		assertEquals("I can only mill 1 sack of flour if you bring me 5 sheaves of #grain.", npc.get("text"));

		assertTrue(en.step(player, "mill two sacks of flour"));
		assertEquals("I can only mill 2 sacks of flour if you bring me 10 sheaves of #grain.", npc.get("text"));

		assertTrue(en.step(player, "mill grain"));
		assertEquals("Sorry, I can only produce flour.", npc.get("text"));

		assertTrue(en.step(player, "bye"));
		assertEquals("Bye.", npc.get("text"));
	}

}
