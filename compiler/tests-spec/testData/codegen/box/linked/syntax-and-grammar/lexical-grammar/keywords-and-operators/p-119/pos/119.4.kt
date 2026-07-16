// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 119 -> sentence 119
 * NUMBER: 4
 * DESCRIPTION: FINAL token in final override function declaration
 */
// TESTCASE NUMBER: 1
open class BaseFinal119 {
    open fun compute119(): String = "NOK"
}

class Locked119 : BaseFinal119() {
    final override fun compute119(): String = "codegen-119-4"
}

fun box(): String = if (Locked119().compute119() == "codegen-119-4") "OK" else "NOK"
