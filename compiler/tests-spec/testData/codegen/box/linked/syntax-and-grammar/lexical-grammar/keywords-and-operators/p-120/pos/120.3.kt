// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 120 -> sentence 120
 * NUMBER: 3
 * DESCRIPTION: OPEN token in open function overridden in subclass
 */
// TESTCASE NUMBER: 1
open class OpenOverride120 {
    open fun compute120(value: Int): Int = value
}

class OpenOverrideChild120 : OpenOverride120() {
    override fun compute120(value: Int): Int = value + 1
}

fun box(): String = if (OpenOverrideChild120().compute120(41) == 42) "OK" else "NOK"
