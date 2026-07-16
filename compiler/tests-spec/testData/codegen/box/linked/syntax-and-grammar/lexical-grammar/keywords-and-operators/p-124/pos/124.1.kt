// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 124 -> sentence 124
 * NUMBER: 1
 * DESCRIPTION: NOINLINE token in inline function noinline parameter
 */
inline fun runBoth124(cross: () -> String, noinline block: () -> String): String {
    cross()
    return block()
}

// TESTCASE NUMBER: 1
fun box(): String = if (runBoth124({ "codegen-124-1" }, { "codegen-124-1" }) == "codegen-124-1") "OK" else "NOK"
