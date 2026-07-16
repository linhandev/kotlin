// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 117 -> sentence 117
 * NUMBER: 4
 * DESCRIPTION: OVERRIDE token in final override function declaration
 */
// TESTCASE NUMBER: 1
open class BaseOpen117 {
    open fun compute117(): String = "NOK"
}

class FinalOverride117 : BaseOpen117() {
    final override fun compute117(): String = "codegen-117-4"
}

fun box(): String = if (FinalOverride117().compute117() == "codegen-117-4") "OK" else "NOK"
