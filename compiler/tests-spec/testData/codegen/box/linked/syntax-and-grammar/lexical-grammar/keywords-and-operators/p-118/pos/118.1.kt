// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 118 -> sentence 118
 * NUMBER: 1
 * DESCRIPTION: ABSTRACT token in abstract class with abstract function
 */
// TESTCASE NUMBER: 1
abstract class Shape118 {
    abstract fun label118(): String
}

class Circle118 : Shape118() {
    override fun label118(): String = "codegen-118-1"
}

fun box(): String = if (Circle118().label118() == "codegen-118-1") "OK" else "NOK"
