// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 289 -> sentence 289
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 289 -> sentence 289
 *                declarations, classifier-declaration, enum-class-declaration -> paragraph 289 -> sentence 289
 * NUMBER: 1
 * DESCRIPTION: precise types when enum constants are created via an explicitly private enum constructor and exposed through companion/constants
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
enum class Color private constructor(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00);

    companion object {
        fun redRgb(): Int = RED.rgb
        fun greenRgb(): Int = GREEN.rgb
    }
}

fun case1() {
    Color.redRgb() checkType { check<Int>() }
    Color.GREEN.rgb checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
enum class Phase private constructor(val code: Int) {
    INIT(0),
    RUN(1),
    DONE(2);

    companion object {
        fun runCode(): Int = RUN.code
    }
}

fun case2() {
    Phase.runCode() checkType { check<Int>() }
    Phase.RUN checkType { check<Phase>() }
}

// TESTCASE NUMBER: 3
enum class Flag private constructor(val bit: Int) {
    A(1),
    B(2);

    fun mask(): Int = bit
}

fun case3() {
    Flag.A.mask() checkType { check<Int>() }
    Flag.B.bit checkType { check<Int>() }
}
