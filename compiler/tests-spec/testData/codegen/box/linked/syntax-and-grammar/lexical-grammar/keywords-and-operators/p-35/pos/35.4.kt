// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 4
 * DESCRIPTION: AT_NO_WS token in stacked annotations @Deprecated @Suppress on declaration
 */
// TESTCASE NUMBER: 1

@Deprecated("old")
@Suppress("UNUSED_PARAMETER")
class Holder(val value: Int)

fun box(): String {
    return if (Holder(1).value == 1) "OK" else "NOK"
}
