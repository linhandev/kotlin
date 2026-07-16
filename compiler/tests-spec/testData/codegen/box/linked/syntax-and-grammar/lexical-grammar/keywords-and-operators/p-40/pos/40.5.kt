// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 40 -> sentence 40
 * NUMBER: 5
 * DESCRIPTION: QUEST_NO_WS token in safe call chain w?.value?.length without Hidden after ?
 */
// TESTCASE NUMBER: 1

class Wrapper(val value: String?)

fun box(): String {
    val w: Wrapper? = Wrapper("OK")
    return if (w?.value?.length == 2) "OK" else "NOK"
}
