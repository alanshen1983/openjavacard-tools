package better.smartcard.gp.scp;

/**
 * SCP security policies
 *
 * These describe what part of card exchanges has to be
 * authenticated and/or encrypted.
 *
 * All existing protocols require CMAC to be enabled.
 *
 * All existing protocols have a linear tower of
 * options in order of increasing security as follows:
 *  - CMAC (minimum supported level)
 *  - CENC (implies CMAC)
 *  - RMAC (implies CMAC, CENC)
 *  - RENC (implies CMAC, CENC, RMAC)
 */
public enum SCPSecurityPolicy {
    /** Minimum supported level */
    CMAC(false, false, false),
    /** Implies CMAC */
    CENC(true, false, false),
    /** Implies CMAC, CENC */
    RMAC(true, true, false),
    /** Implies CMAC, CENC, RMAC */
    RENC(true, true, true);

    /** True if CMAC is required */
    public final boolean requireCMAC;
    /** True if CENC is required */
    public final boolean requireCENC;
    /** True if RMAC is required */
    public final boolean requireRMAC;
    /** True if RENC is required */
    public final boolean requireRENC;

    /** Internal constructor */
    SCPSecurityPolicy(boolean reqCENC, boolean reqRMAC, boolean reqRENC) {
        requireCMAC = true;
        requireCENC = reqCENC;
        requireRMAC = reqRMAC;
        requireRENC = reqRENC;
    }
}
