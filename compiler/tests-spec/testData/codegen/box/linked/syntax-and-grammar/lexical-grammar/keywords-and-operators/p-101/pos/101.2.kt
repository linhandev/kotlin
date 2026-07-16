// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 101 -> sentence 101
 * NUMBER: 2
 * DESCRIPTION: DYNAMIC token as soft keyword used as class name
 */
// TESTCASE NUMBER: 1
@Suppress("ClassName")
class dynamic {
    fun value(): String = "codegen-101-2"
}

fun box(): String = if (dynamic().value() == "codegen-101-2") "OK" else "NOK"
