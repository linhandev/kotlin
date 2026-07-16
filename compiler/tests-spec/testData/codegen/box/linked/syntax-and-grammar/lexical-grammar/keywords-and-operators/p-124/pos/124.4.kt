// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 124 -> sentence 124
 * NUMBER: 4
 * DESCRIPTION: NOINLINE token in member inline function noinline parameter
 */
// TESTCASE NUMBER: 1
class NoinlineHolder124 {
    inline fun compute124(noinline block: () -> String): String = block()
}

fun box(): String {
    val expected = "noinline-124-4"
    val result = NoinlineHolder124().compute124 { expected }
    if (result != expected) return "NOK"
    return "OK"
}
