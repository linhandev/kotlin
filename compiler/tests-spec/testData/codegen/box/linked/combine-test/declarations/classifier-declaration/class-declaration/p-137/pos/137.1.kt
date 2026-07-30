// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 137 -> sentence 137
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 137 -> sentence 137
 *                declarations, classifier-declaration, object-declaration -> paragraph 137 -> sentence 137
 * NUMBER: 1
 * DESCRIPTION: companion object const val is compile-time constant outside instance init mutation in class declaration
 */

// TESTCASE NUMBER: 1
class Limits {
    companion object {
        const val MAX = 10
        const val MIN = 0
    }

    var observed = 0

    init {
        observed = MAX + MIN
    }
}

// TESTCASE NUMBER: 2
class Labels {
    companion object {
        const val TAG = "ready"
        const val CODE = 42
    }
}

// TESTCASE NUMBER: 3
class Flags {
    companion object {
        const val ENABLED = true
        const val MASK = 0xFF
    }

    val fromConst = MASK

    init {
        check(ENABLED)
    }
}

fun viaCompanionAccess(): Int = Limits.MAX + Limits.MIN

fun viaInitUsesConst(): Int = Limits().observed

fun viaStringConst(): String = Labels.TAG

fun viaIntConst(): Int = Labels.CODE

fun viaFlagMask(): Int = Flags().fromConst

fun viaBooleanConst(): Boolean = Flags.ENABLED

fun box(): String {
    if (viaCompanionAccess() != 10) return "NOK: companion-access"
    if (viaInitUsesConst() != 10) return "NOK: init-reads-const"
    if (viaStringConst() != "ready") return "NOK: string-const"
    if (viaIntConst() != 42) return "NOK: int-const"
    if (viaFlagMask() != 255) return "NOK: mask"
    if (!viaBooleanConst()) return "NOK: boolean-const"
    if (Limits.MAX != 10 || Limits.MIN != 0) return "NOK: unchanged-const"
    return "OK"
}
