// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 106 -> sentence 106
 * NUMBER: 3
 * DESCRIPTION: ENUM token in enum class with method body
 */
// TESTCASE NUMBER: 1
enum class Op106 {
    ADD {
        override fun apply(a: Int, b: Int): Int = a + b
    },
    SUB {
        override fun apply(a: Int, b: Int): Int = a - b
    };

    abstract fun apply(a: Int, b: Int): Int
}

fun box(): String = if (Op106.ADD.apply(20, 22) == 42) "OK" else "NOK"
