package org.openjavacard.gp.scp;

import junit.framework.TestCase;
import org.junit.Assert;
import org.openjavacard.gp.keys.GPKeySet;
import org.openjavacard.util.APDUUtil;
import org.openjavacard.util.HexUtil;

import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import java.io.PrintStream;

public class SCP02WrapperTest extends TestCase {

    SCP0102Protocol SCP02_15 = (SCP0102Protocol)SCPProtocol.decode(0x02, 0x15);

    CommandAPDU plain5 = APDUUtil.buildCommand((byte)0x10, (byte)0x20, (short)0x30,
            new byte[] {1, 2, 3, 4, 5});
    CommandAPDU plain8 = APDUUtil.buildCommand((byte)0x10, (byte)0x20, (short)0x30,
            new byte[] {1, 2, 3, 4, 5, 6, 7, 8});
    CommandAPDU plain12 = APDUUtil.buildCommand((byte)0x10, (byte)0x20, (short)0x30,
            new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4});
    CommandAPDU plain16 = APDUUtil.buildCommand((byte)0x10, (byte)0x20, (short)0x30,
            new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8});

    public void testReference() {
        Assert.assertArrayEquals(HexUtil.hexToBytes("10200030050102030405"), plain5.getBytes());
        Assert.assertArrayEquals(HexUtil.hexToBytes("10200030080102030405060708"), plain8.getBytes());
        Assert.assertArrayEquals(HexUtil.hexToBytes("102000300c010203040506070801020304"), plain12.getBytes());
        Assert.assertArrayEquals(HexUtil.hexToBytes("102000301001020304050607080102030405060708"), plain16.getBytes());
    }

    public void testSimpleCMAC() throws CardException {
        SCP0102Wrapper wrap = new SCP0102Wrapper(GPKeySet.GLOBALPLATFORM, SCP02_15);
        CommandAPDU cmac0 = wrap.wrap(plain5);
        Assert.assertArrayEquals(HexUtil.hexToBytes("142000300d0102030405fb4034e025786ab1"), cmac0.getBytes());
        CommandAPDU cmac1 = wrap.wrap(plain5);
        Assert.assertArrayEquals(HexUtil.hexToBytes("142000300d01020304050a3bebcf697d0829"), cmac1.getBytes());
        CommandAPDU cmac2 = wrap.wrap(plain8);
        Assert.assertArrayEquals(HexUtil.hexToBytes("142000301001020304050607081bebeae51e869dac"), cmac2.getBytes());
        CommandAPDU cmac3 = wrap.wrap(plain8);
        Assert.assertArrayEquals(HexUtil.hexToBytes("1420003010010203040506070840f75851a11470c5"), cmac3.getBytes());
        CommandAPDU cmac4 = wrap.wrap(plain12);
        CommandAPDU cmac5 = wrap.wrap(plain12);
        CommandAPDU cmac6 = wrap.wrap(plain16);
        CommandAPDU cmac7 = wrap.wrap(plain16);
    }

}