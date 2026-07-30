// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 289 -> sentence 289
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 289 -> sentence 289
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 289 -> sentence 289
 * NUMBER: 1
 * DESCRIPTION: enum class may declare an explicitly private constructor used only by enum constants; companion can list constants; contrasts with p-276 private class constructor outside failure
 */

// TESTCASE NUMBER: 1
enum class Color private constructor(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00);

    companion object {
        fun listAll(): List<Color> = entries
    }
}

// TESTCASE NUMBER: 2
enum class Phase private constructor(val code: Int) {
    INIT(0),
    RUN(1),
    DONE(2);

    companion object {
        fun sumCodes(): Int = entries.sumOf { it.code }
    }
}

// TESTCASE NUMBER: 3
enum class Flag private constructor(val bit: Int) {
    A(1),
    B(2);

    fun mask(): Int = bit
}

fun box(): String {
    if (Color.listAll().size != 2) return "NOK: color-size"
    if (Color.RED.rgb != 0xFF0000) return "NOK: color-red"
    if (Color.GREEN.rgb != 0x00FF00) return "NOK: color-green"

    if (Phase.sumCodes() != 3) return "NOK: phase-sum"
    if (Phase.RUN.code != 1) return "NOK: phase-run"
    if (Phase.entries.size != 3) return "NOK: phase-size"

    if (Flag.A.mask() != 1) return "NOK: flag-a"
    if (Flag.B.mask() != 2) return "NOK: flag-b"
    if (Flag.entries.map { it.bit }.sum() != 3) return "NOK: flag-sum"
    return "OK"
}
