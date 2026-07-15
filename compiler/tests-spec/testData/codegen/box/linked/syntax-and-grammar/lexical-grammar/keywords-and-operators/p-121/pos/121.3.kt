// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 121 -> sentence 121
 * NUMBER: 3
 * DESCRIPTION: CONST token in companion object const val declaration
 */
// TESTCASE NUMBER: 1
class ConstCompanion121 {
    companion object {
        const val VALUE121: Int = 42
    }
}

fun box(): String = if (ConstCompanion121.VALUE121 == 42) "OK" else "NOK"
