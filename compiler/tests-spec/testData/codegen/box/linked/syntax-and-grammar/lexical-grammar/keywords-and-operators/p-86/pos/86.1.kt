// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 86 -> sentence 86
 * NUMBER: 1
 * DESCRIPTION: CATCH token in catch (e: Exception) clause
 */
// TESTCASE NUMBER: 1
fun catch85(): String {
    try {
        return "OK"
    } catch (e: Exception) {
        return if (e.message == null) "OK" else "NOK"
    }
}

fun box(): String = catch85()
